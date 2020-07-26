package com.neo4jkafka.kafkaconsumer.service;

import com.neo4jkafka.kafkaconsumer.dto.PersonDTO;
import com.neo4jkafka.kafkaconsumer.model.Person;
import com.neo4jkafka.kafkaconsumer.repository.GraphDBRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsumerService {

   @Autowired
  GraphDBRepository graphDBRepository;

  @Transactional(readOnly = true)
  public Person getValueByName(String name) {
    Person person = graphDBRepository.findByName(name);
    return  person;
  }

public Iterable<Person> getAllValue() {
  List<Person> reports = new ArrayList<>();
  Iterable<Person> allReports =  graphDBRepository.findAll();
  allReports.forEach(reports::add);
  return allReports;
}


  public Person savetoDB(PersonDTO personDTO) {

    Person person = new Person();
    person.setId(personDTO.getId());
    person.setAge(personDTO.getAge());
    person.setName(personDTO.getName());
    person.setCountry(personDTO.getCountry());

    Person newPerson = graphDBRepository.save(person);
    return newPerson;
  }
}
