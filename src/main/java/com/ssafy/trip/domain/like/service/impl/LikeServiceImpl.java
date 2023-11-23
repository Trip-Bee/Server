package com.ssafy.trip.domain.like.service.impl;

import com.ssafy.trip.domain.like.dto.LikeResponse;
import com.ssafy.trip.domain.like.entity.Like;
import com.ssafy.trip.domain.like.mapper.LikeMapper;
import com.ssafy.trip.domain.like.service.LikeService;
import com.ssafy.trip.domain.spot.entity.Spot;
import com.ssafy.trip.domain.spot.mapper.SpotMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeMapper likeMapper;
    private final SpotMapper spotMapper;

//    @Override
//    public void addLike(LikeDto likeDto) throws Exception {
//        likeMapper.save(likeDto.toEntity());
//    }
//
//    @Override
//    public void deleteLike(Long likeId) throws Exception {
//        likeMapper.delete(likeId);
//    }

    @Override
    public int count(int spotId) throws Exception {
        return likeMapper.countBySpotId(spotId);
    }

    @Override
    public LikeResponse addOrDeleteLike(Long userId, int spotId) throws Exception {
        // userId, spotId에 해당하는 like가 존재하면 제거
        // 존재하지 않으면 저장
        Map<String, Long> map = new HashMap<>();    // like 조회할때 사용할 값
        Map<String, Integer> spotMap = new HashMap<>(); // 여행지에서 좋아요 개수 update 할때 사용할 값
        map.put("userId", userId);
        map.put("spotId", (long) spotId);
        spotMap.put("spotId", spotId);

        Optional<Like> findLike = likeMapper.findByUserIdAndSpotId(map);
//        int likeCount = likeMapper.countBySpotId(spotId);
        Spot spot = spotMapper.findBySpotId(spotId);

        log.debug("spot {}", spot);

        int likeCount = spot.getLikeCount();
        log.debug("before likeCount {}", likeCount);

        // spot 의 likeCount -1 or + 1

        if (findLike.isPresent()) { // 존재할 경우 삭제 >> 좋아요 취소
            Like like = findLike.get();
            likeMapper.delete(like.getId());
            likeCount--;
            log.debug("after likeCount {}", likeCount);
            spotMap.put("likeCount", likeCount);
            spotMapper.updateLikeCount(spotMap);
            return LikeResponse.builder().isLike(false).likeCount(likeCount).build();
        } else {    // 없을 경우 저장 >> 좋아요 처리
            Like like = Like.builder().userId(userId).spotId(spotId).build();
            likeMapper.save(like);
            likeCount++;
            log.debug("after likeCount {}", likeCount);
            spotMap.put("likeCount", likeCount);
            spotMapper.updateLikeCount(spotMap);
            return LikeResponse.builder().isLike(true).likeCount(likeCount).build();
        }
    }
}
