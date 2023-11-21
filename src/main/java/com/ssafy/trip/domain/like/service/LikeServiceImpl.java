package com.ssafy.trip.domain.like.service;

import com.ssafy.trip.domain.like.entity.Like;
import com.ssafy.trip.domain.like.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeMapper likeMapper;

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
    public int count(Long spotId) throws Exception {
        return likeMapper.count(spotId);
    }

    @Override
    public void addOrDeleteLike(Long userId, Long spotId) throws Exception {
        // userId, spotId에 해당하는 like가 존재하면 제거
        // 존재하지 않으면 저장
        Map<String, Long> map = new HashMap<>();
        map.put("userId", userId);
        map.put("spotId", spotId);

        // TODO xml에서 findByUserIdAndSpotId, delete, save 구현 / likeMapper 에서 다듬기 / LikeDto, Like 다듬기
        Optional<Like> findLike = likeMapper.findByUserIdAndSpotId(map);

        if (findLike.isPresent()) { // 존재할 경우 삭제
            Like like = findLike.get();
            likeMapper.delete(like.getId());
        } else {    // 없을 경우 저장
            Like like = Like.builder().userId(userId).spotId(spotId).build();
            likeMapper.save(like);
        }
    }
}
