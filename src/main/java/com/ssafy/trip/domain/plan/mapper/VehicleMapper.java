package com.ssafy.trip.domain.plan.mapper;

import com.ssafy.trip.domain.plan.entity.Vehicle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VehicleMapper {
    List<Vehicle> findAll();
}
