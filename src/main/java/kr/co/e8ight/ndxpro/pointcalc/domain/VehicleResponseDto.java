package kr.co.e8ight.ndxpro.pointcalc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VehicleResponseDto implements Serializable {
    Integer resultCode;
    String resultMsg;
    String isFinish;
    String timeGroup;
    Integer itemCount;
    List<Frame> items;
}
