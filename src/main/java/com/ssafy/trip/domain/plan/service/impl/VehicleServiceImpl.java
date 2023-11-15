package com.ssafy.trip.domain.plan.service.impl;

import com.ssafy.trip.domain.plan.entity.Vehicle;
import com.ssafy.trip.domain.plan.mapper.VehicleMapper;
import com.ssafy.trip.domain.plan.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleMapper vehicleMapper;

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleMapper.findAll();
    }
}
