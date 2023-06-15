package kr.co.e8ight.ndxpro.pointcalc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TrafficInfo {
    List<Vehicle> vehicles;
    List<Vehicle> pedestrians;
    List<VehicleSignal> vehicleSignals;
    List<PedestrianSignal> pedestrianSignals;
}
