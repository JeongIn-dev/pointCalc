package kr.co.e8ight.ndxpro.pointcalc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Frame {
    Integer sceneId;
    String sceneType;
    Integer frame;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "Asia/Seoul")
    LocalDateTime dtm;

    TrafficInfo tod;
    TrafficInfo rtsc;
}
