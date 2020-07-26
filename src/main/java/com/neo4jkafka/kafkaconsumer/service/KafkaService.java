package com.neo4jkafka.kafkaconsumer.service;

import com.neo4jkafka.kafkaconsumer.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

@Autowired
  ConsumerService consumerService;

  @KafkaListener(topics = "EXAMPLE_TOPIC",groupId = "group_id")
  public void consume(String message)
  {
    System.out.println(String.format("Message recieved -> %s", message));
  }

  @KafkaListener(topics = "Example_Topic_24", groupId = "group_json", containerFactory = "userKafkaListener")
  public void consume(PersonDTO personDTO) {
    System.out.println("Message recieved -> %s" + personDTO);
    consumerService.savetoDB(personDTO);
  }
}
