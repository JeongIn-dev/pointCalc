package kr.co.e8ight.ndxpro.pointcalc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleSignal {
    private Integer id;
    private Integer EBL;
    private Integer WBT;
    private Integer SBL;
    private Integer NBT;
    private Integer WBL;
    private Integer EBT;
    private Integer NBL;
    private Integer SBT;
}
