package com.ssafy.trip.domain.spot.entity;

import com.ssafy.trip.domain.spot.dto.SpotDto;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Spot {

    private int id;
    private int typeId;
    private String title;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String tel;
    private String image;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Long sidoCode;
    private Long gugunCode;
    private String overview;

    public SpotDto toDto() {
        String addr = "";
        if (!zipcode.isEmpty()) {
            addr += ("(" + zipcode + ") ");
        }
        addr += addr1;
        addr += addr2;
        return SpotDto.builder()
                .id(id)
                .typeId(typeId)
                .title(title)
                .addr(addr)
                .tel(tel)
                .image(image)
                .latitude(latitude)
                .longitude(longitude)
                .sidoCode(sidoCode)
                .gugunCode(gugunCode)
                .overview(overview)
                .build();
    }

}
