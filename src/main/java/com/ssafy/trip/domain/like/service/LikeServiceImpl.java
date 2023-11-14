package com.ssafy.trip.domain.like.service;

import com.ssafy.trip.domain.like.dto.LikeDto;
import com.ssafy.trip.domain.like.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeMapper likeMapper;

    @Override
    public void addLike(LikeDto likeDto) throws Exception {
        likeMapper.insert(likeDto.toEntity());
    }

    @Override
    public void deleteLike(Long likeId) throws Exception {
        likeMapper.delete(likeId);
    }

    @Override
    public int count(Long spotId) throws Exception {
        return likeMapper.count(spotId);
    }
}
