package kr.co.e8ight.ndxpro.pointcalc.domain;

import lombok.Data;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;

import java.util.List;

@Data
public class GeoProperty {
    String type;
    Double creDate;
    Double modDate;
    GeoJson value;

    @PersistenceCreator
    public GeoProperty(String type, Double creDate, Double modDate, GeoJsonLineString value) {
        this.type = type;
        this.creDate = creDate;
        this.modDate = modDate;
        this.value = value;
    }

    public GeoProperty() {
    }

    public GeoProperty(List<Point> points) {
        this.type = "GeoProperty";
        this.value = new GeoJsonLineString(points);
        this.creDate = 1663909331.153627;
        this.modDate = 1663909331.153627;
    }

    public GeoProperty(int i) {
        Double x = 13.3986 + i / 10000d;
        Double y = 52.5547 + i / 10000d;
        List<Double> coord = List.of(x, y);
        this.type = "GeoProperty";
        this.value = new GeoJsonLineString(List.of());
        this.creDate = 1663909331.153627;
        this.modDate = 1663909331.153627;
    }

    @Override
    public String toString() {
        return "GeoProperty{" +
                "type='" + type + '\'' +
                ", creDate=" + creDate +
                ", modDate=" + modDate +
                ", value=" + value +
                '}';
    }
}
