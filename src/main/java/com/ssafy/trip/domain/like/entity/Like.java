package com.ssafy.trip.domain.like.entity;

import com.ssafy.trip.domain.like.dto.LikeDto;
import com.ssafy.trip.global.entity.BaseEntity;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Like extends BaseEntity {

    private Long id;
    private Long userId;
    private int spotId;

    public LikeDto toDto() {
        return LikeDto.builder()
//                .id(id)
                .userId(userId)
                .spotId(spotId)
                .build();
    }

}
