package kr.co.e8ight.ndxpro.pointcalc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle implements Serializable {
    int id;
    int linkId;
    int lane;
    double location;
    double speed;
    String type;
}
