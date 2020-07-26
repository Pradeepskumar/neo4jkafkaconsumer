package com.neo4jkafka.kafkaconsumer.config;


import com.neo4jkafka.kafkaconsumer.dto.PersonDTO;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class kakfaConfig {


  @Bean
  public ConsumerFactory<String, PersonDTO> consumerFactory(){
    Map<String,Object> config = new HashMap<>();

    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
    config.put(ConsumerConfig.GROUP_ID_CONFIG,"group_id");
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);

    return new DefaultKafkaConsumerFactory<>(config);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> userKafkaListenerFactory(){
    ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

  @Bean
  public ConsumerFactory<String, PersonDTO> userConsumerFactory() {
    Map<String, Object> config = new HashMap<>();

    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
    config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
    config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

    return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
        new JsonDeserializer<>(PersonDTO.class));
  }
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, PersonDTO> userKafkaListener(){
    ConcurrentKafkaListenerContainerFactory<String, PersonDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(userConsumerFactory());
    return factory;
  }
}

