package kr.co.e8ight.ndxpro.pointcalc.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document
public class Attr {
    String name;
    String uri;
    String type;
    String description;
    List<ObjectMember> objectMembers;
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document
class ObjectMember {
    String name;
    String valueType;
}
