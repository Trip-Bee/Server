package com.ssafy.trip.domain.plan.controller;

import com.ssafy.trip.domain.plan.entity.Vehicle;
import com.ssafy.trip.domain.plan.service.VehicleService;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping()
    public ResponseEntity getVehicles() {

        List<Vehicle> vehicles = vehicleService.getVehicles();

        return ResponseEntity.ok(Response.success(vehicles));
    }
}
