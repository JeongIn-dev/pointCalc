package kr.co.e8ight.ndxpro.pointcalc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class VehicleResponseDto implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "Asia/Seoul")
    LocalDateTime timePoint;
    List<Vehicle> vehicles;
    List<Vehicle> pedestrians;
    List<Signal> vehicleSignals;

    public VehicleResponseDto(LocalDateTime timePoint, List<Vehicle> vehicles, List<Vehicle> pedestrians, List<Signal> vehicleSignals) {
        this.timePoint = timePoint;
        this.vehicles = vehicles;
        this.pedestrians = pedestrians;
        this.vehicleSignals = vehicleSignals;
    }

    public LocalDateTime getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(LocalDateTime timePoint) {
        this.timePoint = timePoint;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Vehicle> getPedestrians() {
        return pedestrians;
    }

    public void setPedestrians(List<Vehicle> pedestrians) {
        this.pedestrians = pedestrians;
    }

    public List<Signal> getVehicleSignals() {
        return vehicleSignals;
    }

    public void setVehicleSignals(List<Signal> vehicleSignals) {
        this.vehicleSignals = vehicleSignals;
    }
}
