package com.ssafy.trip.domain.post.dto;

import com.ssafy.trip.domain.post.entity.Post;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PostDto {

    private Long id;
    private Long writerId;
    private String title;
    private Post.Category category;
    private String content;
    private Long hit;

    public PostDto(Long id, Long writerId, String title, String category, String content, Long hit) {
        this.id = id;
        this.writerId = writerId;
        this.title = title;
        this.category = Post.Category.valueOf(category);
        this.content = content;
        this.hit = hit;
    }

    public Post toEntity() {
        return Post.builder()
                .id(id)
                .writerId(writerId)
                .title(title)
                .category(category)
                .content(content)
                .hit(hit)
                .build();
    }

}
