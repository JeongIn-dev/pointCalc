package kr.co.e8ight.ndxpro.pointcalc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VissimResponseDto {

    private Integer resultCode;
    private String resultMsg;
    private String isFinish;
    private Integer itemCount;
    private List<Object> items;
}
