package com.ssafy.trip.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private DataHeader dataHeader;
    private T dataBody;


    public static Response success() {
        return Response.builder()
                .dataHeader(DataHeader.success())
                .build();
    }

    public static <T> Response<T> success(T dataBody) {
        return Response.<T>builder()
                .dataHeader(DataHeader.success())
                .dataBody(dataBody)
                .build();
    }

    public static <T> Response<T> success(T dataBody, String resultCode, String resultMessage) {
        return Response.<T>builder()
                .dataHeader(DataHeader.success(resultCode, resultMessage))
                .dataBody(dataBody)
                .build();
    }

    public static <T> Response<T> fail(String resultCode, String resultMessage) {
        return Response.<T>builder()
                .dataHeader(DataHeader.fail(resultCode, resultMessage))
                .dataBody(null)
                .build();
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class DataHeader {
        private int successCode;    // 0: 성공, 1: 실패
        private String resultCode;  // 프론트에서 해당 코드로 예외구분?
        private String resultMessage;

        private static DataHeader success(){
            return DataHeader.builder()
                    .successCode(0)
                    .build();
        }

        private static DataHeader success(String resultCode, String resultMessage) {
            return DataHeader.builder()
                    .successCode(0)
                    .resultCode(resultCode)
                    .resultMessage(resultMessage)
                    .build();
        }

        private static DataHeader fail(String resultCode, String resultMessage) {
            return DataHeader.builder()
                    .successCode(1)
                    .resultCode(resultCode)
                    .resultMessage(resultMessage)
                    .build();
        }
    }
}
