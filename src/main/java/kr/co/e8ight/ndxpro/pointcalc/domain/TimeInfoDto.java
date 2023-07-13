package kr.co.e8ight.ndxpro.pointcalc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeInfoDto {
    private Integer resultCode;
    private String resultMsg;
    private Integer itemCount;
    private List<Item> items;
}
