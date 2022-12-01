package kr.co.e8ight.ndxpro.pointcalc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ID {

    private String id;
    private String type;
    private String servicePath;

    public ID(String id) {
        this.id = id;
        this.type = "https://ndxpro/pintel/data-models#Link";
        this.servicePath = "/";
    }

    @Override
    public String toString() {
        return "ID{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", servicePath='" + servicePath + '\'' +
                '}';
    }
}
