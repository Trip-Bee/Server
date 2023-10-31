package com.ssafy.trip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.ssafy.**.mapper"})
public class TripBeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripBeeApplication.class, args);
    }

}
