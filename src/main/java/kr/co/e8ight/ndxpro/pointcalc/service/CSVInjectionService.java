package kr.co.e8ight.ndxpro.pointcalc.service;

import kr.co.e8ight.ndxpro.pointcalc.domain.Signal;
import kr.co.e8ight.ndxpro.pointcalc.domain.Vehicle;
import kr.co.e8ight.ndxpro.pointcalc.domain.VehicleResponseDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CSVInjectionService {

    private Integer curr;

    @PostConstruct
    public void setFirstNum() {
        String[] list = new File("/visim/01.Vehicle_Info").list();

        if ( list == null ) {
            throw new RuntimeException("no csv files.");
        }

        curr = Arrays.stream(list)
                .map(name -> Integer.parseInt(name.replace("Vehicle_Info__VISSIM_Time_", "").replace(".csv", "")))
                .sorted().findFirst().get();
    }

    public VehicleResponseDto getVehicles() throws IOException {
//        long currentTimeMillis = System.currentTimeMillis();
//        long l = Math.round(currentTimeMillis / 1000.0);
//        int i = (int) (l % length);

        System.out.println(curr);
        int fileNum = curr;

        List<Vehicle> vehicles = new ArrayList<>();
        List<Vehicle> pedestrians = new ArrayList<>();
        FileReader vehicleFileReader = new FileReader("/visim/01.Vehicle_Info/Vehicle_Info__VISSIM_Time_"+fileNum+".csv");
        BufferedReader vehicleBufferedReader = new BufferedReader(vehicleFileReader);
        Stream<String> vehicleLines = vehicleBufferedReader.lines();

        vehicleLines.skip(1).forEach(
                row -> {
                    String[] split = row.split(",");
                    int id = Integer.parseInt(split[0]);
                    int linkId = Integer.parseInt(split[1]);
                    int lane = Integer.parseInt(split[2]);
                    double distance = Double.parseDouble(split[3]);
                    double speed = Double.parseDouble(split[4]);
                    String type = split[5];

                    if ( Integer.parseInt(type) >= 510 ) {
                        pedestrians.add(new Vehicle(id, linkId, lane, distance, speed, type));
                    } else {
                        vehicles.add(new Vehicle(id, linkId, lane, distance, speed, type));
                    }
                });

        vehicleBufferedReader.close();
        vehicleFileReader.close();

        List<Signal> vehicleSignals = new ArrayList<>();
        FileReader signalFileReader = new FileReader("/visim/02.Signal_Info/Signal_Info__VISSIM_Time_"+fileNum+".csv");
        BufferedReader signalBufferedReader = new BufferedReader(signalFileReader);
        Stream<String> signalLines = signalBufferedReader.lines();

        signalLines.skip(1).forEach(
                row -> {
                    String[] split = row.split(",");
                    int id = Integer.parseInt(split[0]);
                    int ebl = Integer.parseInt(split[1]);
                    int wbt = Integer.parseInt(split[2]);
                    int sbl = Integer.parseInt(split[3]);
                    int nbt = Integer.parseInt(split[4]);
                    int wbl = Integer.parseInt(split[5]);
                    int ebt = Integer.parseInt(split[6]);
                    int nbl = Integer.parseInt(split[7]);
                    int sbt = Integer.parseInt(split[8]);
                    vehicleSignals.add(new Signal(id, ebl, wbt, sbl, nbt, wbl, ebt, nbl, sbt));
                });

        curr++;
        return new VehicleResponseDto(LocalDateTime.now(), vehicles, pedestrians, vehicleSignals);
    }
}

