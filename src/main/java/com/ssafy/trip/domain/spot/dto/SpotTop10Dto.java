package com.ssafy.trip.domain.spot.dto;

import com.ssafy.trip.domain.spot.entity.Spot;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpotTop10Dto {
    private int spotId;
    private String title;
    private String addr;
    private String image;
    private Boolean isLike;
    private int likeCount;

    public static SpotTop10Dto from(Spot spot) {
        String addr = "";
        String addr1 = spot.getAddr1();
        String addr2 = spot.getAddr2();
        String zipcode = spot.getZipcode();

        if (!zipcode.isEmpty()) {
            addr += ("(" + zipcode + ") ");
        }
        addr += addr1;
        addr += addr2;

        return SpotTop10Dto.builder()
                .spotId(spot.getId())
                .title(spot.getTitle())
                .addr(addr)
                .image(spot.getImage())
                .likeCount(spot.getLikeCount())
                .build();
    }
}
