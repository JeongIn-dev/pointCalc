package kr.co.e8ight.ndxpro.pointcalc.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.geo.GeoJson;

@AllArgsConstructor
@ToString
@Getter
public class TestDto {
    GeoJson geoJson;
}
