package kr.co.e8ight.ndxpro.pointcalc.controller;

import kr.co.e8ight.ndxpro.pointcalc.domain.VehicleResponseDto;
import kr.co.e8ight.ndxpro.pointcalc.service.CSVInjectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
public class VehicleController {
    @Autowired
    private CSVInjectionService service;

    @GetMapping("/{timeGroup}")
    public VehicleResponseDto responseVehicle(@PathVariable String timeGroup) throws IOException {
        return service.getVehicles(timeGroup);
    }

    @PostMapping("/api/getTraffic.json")
    public VehicleResponseDto getTraffic() throws IOException {
        return service.getVehicles();
    }

    @PostMapping("/")
    public VehicleResponseDto responseVehiclePost() throws IOException {
        return service.getVehicles("오전 첨두");
    }
}
