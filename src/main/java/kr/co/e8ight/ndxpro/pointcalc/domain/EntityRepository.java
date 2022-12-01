package kr.co.e8ight.ndxpro.pointcalc.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntityRepository extends MongoRepository<Entity, ID> {
}