package com.neo4jkafka.kafkaconsumer.model;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
public class Person {

  @Id
  private String id;
  private String name;
  private String age;
  private String country;


}
