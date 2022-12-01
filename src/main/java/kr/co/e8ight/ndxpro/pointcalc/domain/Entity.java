package kr.co.e8ight.ndxpro.pointcalc.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;

@Getter
@Setter
@Document(collection = "entities")
@AllArgsConstructor
public class Entity {
    @Id
    @Field(name = "_id", targetType = FieldType.OBJECT_ID)
    private ID id;
    @Field
    private List<String> attrNames;
    @Field
    private Attribute attribute;
    @Field
    private Double creDate;
    @Field
    private Double modDate;
    @Field
    private String lastCorrelator;


    @PersistenceCreator
    public Entity() {
    }

    public Entity(String id, List<String> attrNames, Attribute attribute, Double creDate, Double modDate, String lastCorrelator) {
        this.id = new ID(id);
        this.attrNames = attrNames;
        this.attribute = attribute;
        this.creDate = creDate;
        this.modDate = modDate;
        this.lastCorrelator = lastCorrelator;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", attrNames=" + attrNames +
                ", attribute=" + attribute +
                ", creDate=" + creDate +
                ", modDate=" + modDate +
                ", lastCorrelator='" + lastCorrelator + '\'' +
                '}';
    }
}

