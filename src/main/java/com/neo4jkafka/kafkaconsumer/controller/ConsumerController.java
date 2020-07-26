package com.neo4jkafka.kafkaconsumer.controller;

import com.neo4jkafka.kafkaconsumer.model.Person;
import com.neo4jkafka.kafkaconsumer.service.ConsumerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getValue")
public class ConsumerController {

private ConsumerService consumerService;

@Autowired
public ConsumerController(ConsumerService consumerService){
  this.consumerService = consumerService;

}

    @GetMapping("/findByName")
  public ResponseEntity<Person> getValuesByName(@RequestParam String name){
    Person person =  consumerService.getValueByName(name);
      if( person != null){
        return new ResponseEntity<>(person, HttpStatus.OK);
      }
    return new ResponseEntity("No Matching Found", HttpStatus.NOT_FOUND);
  }

  @GetMapping("/findAll")
  public ResponseEntity<List<Person>> getConsumerService() {
    Iterable<Person> person =  consumerService.getAllValue();
    if( person != null){
      return new ResponseEntity(person, HttpStatus.OK);
    }
    return new ResponseEntity("No Data Found", HttpStatus.NOT_FOUND);
  }

}
