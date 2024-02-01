package br.com.microservices.orchestrated.paymentservice.core.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${spring.kafka.topic.orchestrator}")
    private String orchestratorTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendEvent(String payload) {
        try {
            kafkaTemplate.send(orchestratorTopic, payload);
            log.info("sending event to topic {} with data {}", orchestratorTopic, payload);
        } catch (Exception e) {
            log.error("error trying to send data to topic {} with data {}", orchestratorTopic, payload, e);
        }
    }

}
