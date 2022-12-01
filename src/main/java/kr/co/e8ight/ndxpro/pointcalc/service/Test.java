package kr.co.e8ight.ndxpro.pointcalc.service;

import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        Point2D point1 = new Point2D(-5747497.0770822857, -967711.71921884734);
        Point2D point2 = new Point2D(-5747408.9223127, -967660.83500597731);
        Point2D point3 = new Point2D(-5747404.2640865184, -967658.05182598718);
        Link link = new Link(List.of(point1,point2,point3), 5, 3.2);

        Car car = new Car();
        car.set_linkNo(119);
        car.set_laneNo(5);
        car.set_distance(Double.valueOf("91.74497436635662"));

        System.out.println(link.getAllPositions(car));

        car.set_distance(Double.valueOf("103.50645870073927"));

        System.out.println(link.getAllPositions(car));
    }
}
