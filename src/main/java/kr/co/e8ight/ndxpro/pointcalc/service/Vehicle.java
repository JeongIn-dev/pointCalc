package kr.co.e8ight.ndxpro.pointcalc.service;

import java.io.Serializable;

public class Vehicle implements Serializable {
    int id;
    int linkId;
    int lane;
    double location;
    double speed;

    String type;

    public Vehicle() {
    }

    public Vehicle(int id, int linkId, int lane, double location, double speed, String type) {
        this.id = id;
        this.linkId = linkId;
        this.lane = lane;
        this.location = location;
        this.speed = speed;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int getLinkId() {
        return linkId;
    }

    public int getLane() {
        return lane;
    }

    public double getLocation() {
        return location;
    }

    public double getSpeed() {
        return speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public void setLane(int lane) {
        this.lane = lane;
    }

    public void setLocation(double location) {
        this.location = location;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
