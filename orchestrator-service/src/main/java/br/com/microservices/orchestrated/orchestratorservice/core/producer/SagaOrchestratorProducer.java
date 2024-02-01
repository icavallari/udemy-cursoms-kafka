package br.com.microservices.orchestrated.orchestratorservice.core.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SagaOrchestratorProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendEvent(String payload, String topic) {
        try {
            kafkaTemplate.send(topic, payload);
            log.info("sending event to topic {} with data {}", topic, payload);
        } catch (Exception e) {
            log.error("error trying to send data to topic {} with data {}", topic, payload, e);
        }
    }

}
