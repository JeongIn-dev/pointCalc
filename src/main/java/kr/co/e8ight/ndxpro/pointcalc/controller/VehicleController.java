package kr.co.e8ight.ndxpro.pointcalc.controller;

import kr.co.e8ight.ndxpro.pointcalc.service.CSVInjectionService;
import kr.co.e8ight.ndxpro.pointcalc.service.VehicleResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class VehicleController {
    @Autowired
    private CSVInjectionService service;

    @GetMapping("/")
    public VehicleResponseDto responseVehicle() throws IOException {
        return service.getVehicles();
    }
}
