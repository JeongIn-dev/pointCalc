package kr.co.e8ight.ndxpro.pointcalc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.e8ight.ndxpro.pointcalc.domain.Entity;
import kr.co.e8ight.ndxpro.pointcalc.domain.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class CSVInjectionService {

    @Autowired
    private EntityRepository entityRepository;

    private Map<String, Link> links = new HashMap<>();

    public CSVInjectionService(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

//    @PostConstruct
    private void importLinkData() throws IOException {
        List<Entity> entities = entityRepository.findAll();
        for (Entity entity : entities) {
            GeoJsonLineString value = (GeoJsonLineString) entity.getAttribute().getLocation().getValue();
            List<Point> coordinates = value.getCoordinates();

            List<Point2D> points = new ArrayList<>();
            for (Point coordinate : coordinates) {
                points.add(new Point2D(coordinate.getX(), coordinate.getY()));
            }
            int numberOfLane = ((Double) entity.getAttribute().getTotalLaneNumber().getValue()).intValue();
            double laneWidth = (Double) entity.getAttribute().getCarriagewayWidth().getValue();
            Link link = new Link(points, numberOfLane, laneWidth);
            links.put(entity.getId().getId().substring(21), link);
        }

        System.out.println("Run Car Point Calculator");
//        calcXYfromCSV();
//        jsonfromCSV();
    }

    public void calcXYfromCSV() throws IOException {
        for (int i = 900; i <= 1200; i++) {
//        for (int i = 1190; i <= 1190; i++) {
            FileReader fileReader = new FileReader("/visim/01.Vehicle_Info/Vehicle_Info__VISSIM_Time_" + i + ".csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Stream<String> lines = bufferedReader.lines();
            FileWriter fileWriter = new FileWriter("/visim/01.Vehicle_Info/json/Vehicle_Info__VISSIM_Time_" + i + ".json");
            lines.skip(1).forEach(
                    row -> {
                        try {
                            Car car = new Car();
                            String[] split = row.split(",");
                            String id = split[0];
                            System.out.println("cat id : " + id);
                            String link_id = split[1];
                            String lane = split[2];
                            String distance = split[3];
                            String speed = split[4];
                            car.set_linkNo(Integer.parseInt(link_id));
                            car.set_laneNo(Integer.parseInt(lane));
                            car.set_distance(Double.parseDouble(distance));
                            System.out.println(car);
                            Link link = links.get(link_id);
                            System.out.println(link);
                            Point2D position = link.getAllPositions(car);
                            fileWriter.write(row + "," + position.getX() + "," + position.getY());

//                            fileWriter.write(row+","+positions.get(0).getX()+","+positions.get(0).getY()
//                                    +","+positions.get(1).getX()+","+positions.get(1).getY()
//                                    +","+positions.get(2).getX()+","+positions.get(2).getY()+"\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
            bufferedReader.close();
            fileReader.close();
            fileWriter.close();
        }
    }

    public void jsonfromCSV() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        for (int i = 900; i <= 1200; i++) {
//        for (int i = 1190; i <= 1190; i++) {
            List<Vehicle> vehicles = new ArrayList<>();
            FileReader fileReader = new FileReader("/visim/01.Vehicle_Info/Vehicle_Info__VISSIM_Time_"+i+".csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Stream<String> lines = bufferedReader.lines();
            FileWriter fileWriter = new FileWriter("/visim/01.Vehicle_Info/json/Vehicle_Info__VISSIM_Time_"+i+".json");

            int time = i;

            lines.skip(1).forEach(
                    row -> {
                        String[] split = row.split(",");
                        int id = Integer.parseInt(split[0]);
                        int linkId = Integer.parseInt(split[1]);
                        int lane = Integer.parseInt(split[2]);
                        double distance = Double.parseDouble(split[3]);
                        double speed = Double.parseDouble(split[4]);
                        String type = split[5];
                        vehicles.add(new Vehicle(id, linkId, lane, distance, speed, type));
                    }
            );
            VehicleResponseDto responseDto = new VehicleResponseDto(time, vehicles);
            String json = objectMapper.writeValueAsString(responseDto);
            fileWriter.write(json);
            bufferedReader.close();
            fileReader.close();
            fileWriter.close();
        }

    }

    public VehicleResponseDto getVehicles() throws IOException {
        long currentTimeMillis = System.currentTimeMillis();
        long l = Math.round(currentTimeMillis / 1000.0);
        int i = (int) (l % 1805);

        int fileNum = i + 900;

        List<Vehicle> vehicles = new ArrayList<>();
        FileReader fileReader = new FileReader("/visim/01.Vehicle_Info/Vehicle_Info__VISSIM_Time_"+fileNum+".csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Stream<String> lines = bufferedReader.lines();

        lines.skip(1).forEach(
                row -> {
                    String[] split = row.split(",");
                    int id = Integer.parseInt(split[0]);
                    int linkId = Integer.parseInt(split[1]);
                    int lane = Integer.parseInt(split[2]);
                    double distance = Double.parseDouble(split[3]);
                    double speed = Double.parseDouble(split[4]);
                    String type = split[5];
                    vehicles.add(new Vehicle(id, linkId, lane, distance, speed, type));
                });

        bufferedReader.close();
        fileReader.close();
        return new VehicleResponseDto(fileNum, vehicles);
    }

    public Point2D calcXY(Integer linkId, Integer lane, Double distance) {
        Car car = new Car();
        car.set_linkNo(linkId);
        car.set_laneNo(lane);
        car.set_distance(distance);

        Link link = links.get(String.valueOf(linkId));

        return link.getAllPositions(car);
    }
}

