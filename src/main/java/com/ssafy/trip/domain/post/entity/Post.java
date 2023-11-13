package com.ssafy.trip.domain.post.entity;

import com.ssafy.trip.domain.post.dto.PostResponseDto;
import com.ssafy.trip.global.entity.BaseEntity;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

    private Long id;
    private Long writerId;
    private String nickname;
    private String title;
    private Category category;
    private String content;
    private boolean isDeleted;
    private Long hit;

    public static enum Category {
        NOTICE, QnA, BOARD
    }

    public PostResponseDto toDto() {
        return PostResponseDto.builder()
                .id(id)
                .writerId(writerId)
                .nickname(nickname)
                .title(title)
                .category(category)
                .content(content)
                .hit(hit)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }


}
