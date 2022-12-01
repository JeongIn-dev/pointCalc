package kr.co.e8ight.ndxpro.pointcalc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document
@AllArgsConstructor
public class Attribute {

    @Field(name = "https://uri-etsi-org/ngsi-ld/location")
    GeoProperty location;

    @Field(name = "https://smartdatamodels-org/name")
    Property name;

    @Field(name = "https://smartdatamodels-org/dataModel-Transportation/totalLaneNumber")
    Property totalLaneNumber;

    @Field(name = "https://smartdatamodels-org/dataModel-Transportation/carriagewayLength")
    Property carriagewayLength;

    @Field(name = "https://smartdatamodels-org/dataModel-Transportation/carriagewayWidth")
    Property carriagewayWidth;

    @Field(name = "https://smartdatamodels-org/dataModel-Transportation/angle")
    Property angle;

    @PersistenceCreator
    public Attribute() {
    }

    public Attribute(int i) {
    }

    public Attribute(List<Point> points, double totalLaneNumber, double width, double[] length, double[] angle) {
        this.location = new GeoProperty(points);
        this.name = new Property("Valladolid-Dueñas");
        this.totalLaneNumber = new Property(totalLaneNumber);
        this.carriagewayLength = new Property(length);;
        this.carriagewayWidth = new Property(width);
        this.angle = new Property(angle);
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "location=" + location +
                ", name=" + name +
                '}';
    }
}

@Data
class Relationship {
    String type;
    Double creDate;
    Double modDate;
    String value;

    public Relationship() {
        this.type = "Relationship";
        this.value = "urn:ngsi-ld:Building:farm002";
        this.creDate = 1663909331.153627;
        this.modDate = 1663909331.153627;
    }
}

class Address {
    String streetAddress;
    String addressRegion;
    String addressLocality;
    String postalCode;

    public Address() {
        this.streetAddress = "Bornholmer Straße 65";
        this.addressRegion = "Berlin";
        this.addressLocality = "Prenzlauer Berg";
        this.postalCode = "10439";
    }
}