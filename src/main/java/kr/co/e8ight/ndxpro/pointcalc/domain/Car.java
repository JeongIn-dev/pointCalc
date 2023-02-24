package kr.co.e8ight.ndxpro.pointcalc.domain;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class Car {
    private int _linkNo = 0;
    private int _laneNo = 0;
    private double _distance = 0;

    public int get_linkNo() {
        return _linkNo;
    }

    public void set_linkNo(int _linkNo) {
        this._linkNo = _linkNo;
    }

    public int get_laneNo() {
        return _laneNo;
    }

    public void set_laneNo(int _laneNo) {
        this._laneNo = _laneNo;
    }

    public double get_distance() {
        return _distance;
    }

    public void set_distance(double _distance) {
        this._distance = _distance;
    }
}
