package io.stockgeeks.spring.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import io.stockgeeks.spring.kafka.producer.randommessage.Message;
import io.stockgeeks.spring.kafka.consumer.config.KafkaConsumerConfig;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class MessageConsumer {

  @KafkaListener(topics = KafkaConsumerConfig.USER_MESSAGES_TOPIC_NAME)
  public void consume(Message message) {
    log.info("Consume message from Kafka topic: {}. Key: {} Value: {}", KafkaConsumerConfig.USER_MESSAGES_TOPIC_NAME, message.getId(), message);
  }

}