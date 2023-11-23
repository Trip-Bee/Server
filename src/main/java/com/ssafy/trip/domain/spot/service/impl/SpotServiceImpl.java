package com.ssafy.trip.domain.spot.service.impl;

import com.ssafy.trip.domain.like.mapper.LikeMapper;
import com.ssafy.trip.domain.spot.dto.SpotDto;
import com.ssafy.trip.domain.spot.dto.SpotTypeDto;
import com.ssafy.trip.domain.spot.entity.Spot;
import com.ssafy.trip.domain.spot.entity.SpotType;
import com.ssafy.trip.domain.spot.mapper.SpotMapper;
import com.ssafy.trip.domain.spot.service.SpotService;
import com.ssafy.trip.domain.user.dto.LoginUserDto;
import com.ssafy.trip.global.dto.PageRequest;
import com.ssafy.trip.global.dto.PageResponse;
import com.ssafy.trip.global.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpotServiceImpl implements SpotService {

    private final SpotMapper spotMapper;
    private final LikeMapper likeMapper;
    @Override
    public List<SpotTypeDto> getSpotTypeList() throws Exception {
        return spotMapper.findAllSpotType()
                .stream().map(SpotType::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PageResponse search(Map<String, String> param, LoginUserDto loginUserDto) throws Exception {
        PageRequest pageRequest = new PageRequest(param.get("page"), param.get("size"));
        param.putAll(PageUtil.getStartAndSize(pageRequest));

        int size = Integer.parseInt(param.get("size"));
        int totalCount = spotMapper.countBySearch(param);
        int currentPage = Integer.parseInt(param.get("page"));
        int totalPage = (totalCount - 1) / Integer.parseInt(param.get("size")) + 1;

        List<SpotDto> list = spotMapper.search(param)
                .stream().map(Spot::toDto)
                .collect(Collectors.toList());

        // 로그인 한 사용자의 경우 isLike 세팅
        if (loginUserDto != null) {
            Map<String, Long> map = new HashMap<>();
            Long userId = loginUserDto.getId();
            map.put("userId", userId);

            list.stream().forEach(spot -> {
                map.put("spotId", Long.valueOf(spot.getId()));
                spot.setIsLike(likeMapper.findByUserIdAndSpotId(map).isPresent());
            });
        } else {
            list.stream().forEach(spot -> spot.setIsLike(false));
        }

        for (SpotDto spotDto : list) {
            int spotId = spotDto.getId();
            int likeCount = likeMapper.countBySpotId(spotId);
            spotDto.setLikeCount(likeCount);
        }

        // spotDto의 id를 이용해서 on() 으로 묶어서 like DB에서 where spot_id = id 로 개수목록 조회
        // 반복문 돌리면서 개수 넣기

        return PageResponse.<List<SpotDto>>builder()
                .data(list)
                .size(size)
                .currentPage(currentPage)
                .totalPage(totalPage)
                .build();
    }

    @Override
    public SpotDto getSpot(int spotId, LoginUserDto loginUserDto) throws Exception {

        SpotDto spot = spotMapper.findBySpotId(spotId).toDto();
        int likeCount = likeMapper.countBySpotId(spotId);
        spot.setLikeCount(likeCount);

        if (loginUserDto != null) {
            Map<String, Long> map = new HashMap<>();
            map.put("userId", loginUserDto.getId());
            map.put("spotId", (long) spotId);
            spot.setIsLike(likeMapper.findByUserIdAndSpotId(map).isPresent());
        } else {
            spot.setIsLike(false);
        }

        return spot;
    }
}
