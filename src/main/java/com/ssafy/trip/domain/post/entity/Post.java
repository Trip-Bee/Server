package com.ssafy.trip.domain.post.entity;

import com.ssafy.trip.domain.post.dto.PostDto;
import com.ssafy.trip.global.entity.BaseEntity;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

    private Long id;
    private Long writerId;
    private String title;
    private Category category;
    private String content;
    private boolean isDeleted;
    private Long hit;

    public enum Category {
        NOTICE, QnA
    }

    public PostDto toDto() {
        return PostDto.builder()
                .id(id)
                .writerId(writerId)
                .title(title)
                .category(category)
                .content(content)
                .hit(hit)
                .build();
    }

}
