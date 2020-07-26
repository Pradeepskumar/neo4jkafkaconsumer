package com.neo4jkafka.kafkaconsumer.repository;

import com.neo4jkafka.kafkaconsumer.dto.PersonDTO;
import com.neo4jkafka.kafkaconsumer.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface GraphDBRepository extends Neo4jRepository<Person,String> {

  Person findByName(@Param("name") String name);
}
