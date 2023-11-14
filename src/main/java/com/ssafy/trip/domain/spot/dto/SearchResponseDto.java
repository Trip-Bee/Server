package com.ssafy.trip.domain.spot.dto;

import com.ssafy.trip.domain.spot.entity.Spot;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SearchResponseDto {

    private Long id;
    private Long typeId;
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

    public Spot toEntity() {
        return Spot.builder()
                .id(id)
                .typeId(typeId)
                .title(title)
                .addr1(addr1)
                .addr2(addr2)
                .zipcode(zipcode)
                .tel(tel)
                .image(image)
                .latitude(latitude)
                .longitude(longitude)
                .sidoCode(sidoCode)
                .gugunCode(gugunCode)
                .build();
    }

}
