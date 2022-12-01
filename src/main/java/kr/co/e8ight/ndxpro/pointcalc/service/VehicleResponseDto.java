package kr.co.e8ight.ndxpro.pointcalc.service;

import java.io.Serializable;
import java.util.List;

public class VehicleResponseDto implements Serializable {
    int time;
    List<Vehicle> vehicles;

    public VehicleResponseDto(int time, List<Vehicle> vehicles) {
        this.time = time;
        this.vehicles = vehicles;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
