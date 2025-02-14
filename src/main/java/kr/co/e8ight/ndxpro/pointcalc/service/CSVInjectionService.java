package kr.co.e8ight.ndxpro.pointcalc.service;

import kr.co.e8ight.ndxpro.pointcalc.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

@Service
public class CSVInjectionService {

    private int curr;

    private Integer min;
    private Integer max;
    private String date;
    private String timeGroup;
    private boolean stop = false;

    @PostConstruct
    public void setFirstNum() {
        String[] list = new File("/visim/tod/01.Vehicle_Info").list();

        if ( list == null ) {
            throw new RuntimeException("no csv files.");
        }

//        min = Arrays.stream(list)
//                .map(name -> Integer.parseInt(name.replace("TOD_Vehicle_Info__VISSIM_Time_", "").replace(".csv", "")))
//                .min(Comparator.naturalOrder()).get();
//        System.out.println("min : " + min);

        min = 300;
        curr = min;
        max = Arrays.stream(list)
                .map(name -> Integer.parseInt(name.replace("TOD_Vehicle_Info__VISSIM_Time_", "").replace(".csv", ""))).max(Comparator.naturalOrder()).get();
        System.out.println("max : " + max);
//        max = 2099;
    }

    public VehicleResponseDto getVehicles(String timeGroup) throws IOException {
        if ( stop ) {
            return new VehicleResponseDto(0, "success", "Y", this.timeGroup, 0, new ArrayList<>());
        }

        if ( curr > max ) {
            curr = min;
//            throw new RuntimeException("max number reached.");
        }

        System.out.println("read Frame : " + curr);

        TrafficInfo tod = getTrafficInfo("tod");
        TrafficInfo rtsc = getTrafficInfo("rtsc");
        List<Frame> items = List.of(new Frame(curr, "HISTORY", curr, LocalDateTime.now(), tod, rtsc));
        curr++;

        String isFinish;
        if ( curr > max ) {
//        if ( curr % 60 == 0 ) {
            isFinish = "Y";
            return new VehicleResponseDto(0, "success", isFinish, timeGroup, 0, new ArrayList<>());
        } else {
            isFinish = "N";
            return new VehicleResponseDto(0, "success", isFinish, timeGroup, items.size(), items);
        }
    }

    public VehicleResponseDto getVehicles() throws IOException {
        if ( stop ) {
            return new VehicleResponseDto(0, "success", "Y", this.timeGroup, 0, new ArrayList<>());
        }

        if ( curr > max ) {
            curr = min;
//            throw new RuntimeException("max number reached.");
        }

        System.out.println("read Frame : " + curr);

        TrafficInfo tod = getTrafficInfo("tod");
        TrafficInfo rtsc = getTrafficInfo("rtsc");

        long totalSize = tod.getVehicles().size() + rtsc.getVehicles().size() + tod.getVehicleSignals().size() + rtsc.getVehicleSignals().size();
      System.out.println("entity size : " + totalSize);
        List<Frame> items = List.of(new Frame(curr, "HISTORY", curr, LocalDateTime.now(), tod, rtsc));
        curr++;

        String isFinish;
        if ( curr > max ) {
//        if ( curr % 60 == 0 ) {
            isFinish = "Y";
            return new VehicleResponseDto(0, "success", isFinish, this.timeGroup, 0, new ArrayList<>());
        } else {
            isFinish = "N";
            return new VehicleResponseDto(0, "success", isFinish, this.timeGroup, items.size(), items);
        }
    }

    private TrafficInfo getTrafficInfo(String signalType) throws IOException {
        int fileNum = curr;
        String tag = null;

        if (Objects.equals(signalType, "tod")) {
            tag = "TOD";
        } else if (Objects.equals(signalType, "rtsc")) {
            tag = "SC";
        }
        List<Vehicle> vehicles = new ArrayList<>();
        List<Vehicle> pedestrians = new ArrayList<>();
        FileReader vehicleFileReader = new FileReader("/visim/"+signalType+"/01.Vehicle_Info/" + tag + "_Vehicle_Info__VISSIM_Time_"+fileNum+".csv");
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
                      vehicles.add(new Vehicle(id+5000, linkId, lane, distance, speed, type));
                      vehicles.add(new Vehicle(id+6000, linkId, lane, distance, speed, type));
                      vehicles.add(new Vehicle(id+7000, linkId, lane, distance, speed, type));
                      vehicles.add(new Vehicle(id+8000, linkId, lane, distance, speed, type));
                      vehicles.add(new Vehicle(id+9000, linkId, lane, distance, speed, type));
                      vehicles.add(new Vehicle(id+10000, linkId, lane, distance, speed, type));
                    }
                });

        vehicleBufferedReader.close();
        vehicleFileReader.close();

        List<VehicleSignal> vehicleSignals = new ArrayList<>();
        FileReader signalFileReader = new FileReader("/visim/"+signalType+"/02.Signal_Info/" + tag + "_Signal_Info__VISSIM_Time_"+fileNum+".csv");
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
                    vehicleSignals.add(new VehicleSignal(id, ebl, wbt, sbl, nbt, wbl, ebt, nbl, sbt));
                });
        signalBufferedReader.close();
        signalFileReader.close();

        List<PedestrianSignal> pedestrianSignals = new ArrayList<>();
        FileReader pedestrianSignalFileReader = new FileReader("/visim/"+signalType+"/03.PED_Info/" + tag + "_PED_Info__VISSIM_Time_"+fileNum+".csv");
        BufferedReader pedestrianSignalBufferedReader = new BufferedReader(pedestrianSignalFileReader);
        Stream<String> pedestrianSignalLines = pedestrianSignalBufferedReader.lines();

        Map<Integer, PedestrianSignalRemain> pedestrianSignalRemainMap = new HashMap<>();
        FileReader pedestrianSignalsRemainSecondsFileReader = new FileReader("/visim/"+signalType+"/04.PED_Remain_Info/" + tag + "_PED_Remain_Info__VISSIM_Time_"+fileNum+".csv");
        BufferedReader pedestrianSignalsRemainSecondsBufferedReader = new BufferedReader(pedestrianSignalsRemainSecondsFileReader);
        Stream<String> pedestrianSignalsRemainSecondsLines = pedestrianSignalsRemainSecondsBufferedReader.lines();

        pedestrianSignalsRemainSecondsLines.skip(1).forEach(
                row -> {
                    String[] split = row.split(",");
                    int id = Integer.parseInt(split[0]);
                    int e = Integer.parseInt(split[1]);
                    int s = Integer.parseInt(split[2]);
                    int w = Integer.parseInt(split[3]);
                    int n = Integer.parseInt(split[4]);
                    pedestrianSignalRemainMap.put(id, new PedestrianSignalRemain(e, w, s, n));
                });

        pedestrianSignalLines.skip(1).forEach(
                row -> {
                    String[] split = row.split(",");
                    int id = Integer.parseInt(split[0]);
                    int e = Integer.parseInt(split[1]);
                    int s = Integer.parseInt(split[2]);
                    int w = Integer.parseInt(split[3]);
                    int n = Integer.parseInt(split[4]);
                    pedestrianSignals.add(new PedestrianSignal(id, e, w, s, n, pedestrianSignalRemainMap.get(id)));
                });
        signalBufferedReader.close();
        signalFileReader.close();
        signalBufferedReader.close();
        signalFileReader.close();

        return new TrafficInfo(vehicles, pedestrians, vehicleSignals, pedestrianSignals);
    }

    public void setTimeGroup(String timeGroup) {
        this.timeGroup = timeGroup;
    }

    public void stop() {
        this.stop = true;
    }

    public int start(String fromDtm, String toDtm) {
        LocalDateTime from = LocalDateTime.parse(fromDtm, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        LocalDateTime to = LocalDateTime.parse(toDtm, DateTimeFormatter.ofPattern("yyyyMMddHHmmss")).minusHours(2);
        Duration between = Duration.between(to.toLocalTime(), from.toLocalTime());
        long seconds = between.getSeconds();
        curr = (int) (300 + seconds);
        this.stop = false;
        return curr;
    }
}

