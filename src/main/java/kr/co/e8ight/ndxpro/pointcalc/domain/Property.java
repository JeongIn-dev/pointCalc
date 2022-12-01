package kr.co.e8ight.ndxpro.pointcalc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    String type;
    Double creDate;
    Double modDate;
    Object value;

    public Property(Object value) {
        this.type = "Property";
        this.value = value;
        this.creDate = 1663909331.153627;
        this.modDate = 1663909331.153627;
    }

    @Override
    public String toString() {
        return "Property{" +
                "type='" + type + '\'' +
                ", creDate=" + creDate +
                ", modDate=" + modDate +
                ", value=" + value +
                '}';
    }
}
