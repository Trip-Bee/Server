package com.ssafy.trip.domain.like.dto;

import com.ssafy.trip.domain.like.entity.Like;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class LikeDto {

    private Long id;
    private Long userId;
    private Long spotId;

    public Like toEntity() {
        return Like.builder()
                .id(id)
                .userId(userId)
                .spotId(spotId).build();
    }

}
