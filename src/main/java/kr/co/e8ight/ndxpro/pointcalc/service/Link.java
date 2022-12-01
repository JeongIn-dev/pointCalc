package kr.co.e8ight.ndxpro.pointcalc.service;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class Link {
    private List<Point2D> _points = new ArrayList<>();
    private List<Double> _theta = new ArrayList<>();
    private List<Double> _distance = new ArrayList<>();
    private List<Point2D> _leftPoints = new ArrayList<>();
    private int _laneQty = 0;
    private double _laneWidth = 0;
    private double _totalWidth = 0;
    private double _totalDistance = 0;


    public Link(List<Point2D> points, int laneQty, double laneWidth) {
        _laneQty = laneQty;
        _laneWidth = laneWidth;
        _totalWidth = _laneQty * laneWidth;
        set_points(points);
    }

    public List<Point2D> get_points() {
        return _points;
    }

    private void set_points(List<Point2D> value) {
        _points = value;
        _theta.clear();
        _distance.clear();
        for (int i = 0; i < _points.size() - 1; i++) {
            _theta.add(Math.atan2(_points.get(i + 1).getY() - _points.get(i).getY(), _points.get(i + 1).getX() - _points.get(i).getX()));
            _distance.add(_points.get(i).distance(_points.get(i + 1)));
            _totalDistance += _distance.get(i);
            _leftPoints.add(new Point2D(_points.get(i).getX() - (Math.sin(_theta.get(i)) * _totalWidth / 2),
                    _points.get(i).getY() + (Math.cos(_theta.get(i)) * _totalWidth / 2)));
        }
    }

    public Point2D getAllPositions(Car car) {
        return getPosition(car.get_laneNo(), car.get_distance());
    }

    private Point2D getPosition(int laneNo, double distance) {
        if ( distance < 0 ) {
            distance = 0;
        }
        double partialDistance = distance;
        int pointIndex = 0;

        while (partialDistance > _distance.get(pointIndex)) {
            partialDistance -= _distance.get(pointIndex);
            pointIndex++;
        }

//        if (pointIndex == 0) throw new RuntimeException("lane : " + laneNo + ", distance : " + distance + "Check. Car distance value");
//        pointIndex--;

        var dn = (_laneQty - laneNo) * _laneWidth + _laneWidth / 2;
        Point2D position = new Point2D(_leftPoints.get(pointIndex).getX() + dn * Math.sin(_theta.get(pointIndex)),
                _leftPoints.get(pointIndex).getY() - dn * Math.cos(_theta.get(pointIndex)));

        position.move(_theta.get(pointIndex), partialDistance);
        return position;

//        List<Point2D> list = new ArrayList<>();
//        list.add(position);
//        list.add(_points.get(pointIndex));
//        list.add(_points.get(pointIndex+1));
//        return list;
    }
}

class Point2D {
    private double X;
    private double Y;

    public Point2D(double x, double y) {
        this.X = x;
        this.Y = y;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public double distance(Point2D target) {
        return Math.sqrt(Math.pow(X - target.X, 2) + Math.pow(Y - target.Y, 2));
    }

    public void move(double rad, double distance) {
        X += distance * Math.cos(rad);
        Y += distance * Math.sin(rad);
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }
}

