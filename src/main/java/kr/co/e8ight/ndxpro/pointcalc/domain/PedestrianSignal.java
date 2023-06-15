package kr.co.e8ight.ndxpro.pointcalc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedestrianSignal {
    private Integer id;
    private Integer E;
    private Integer W;
    private Integer S;
    private Integer N;
    private PedestrianSignalRemain remainSeconds;
}
