package io.brito.examples.kafkaproducerapi.service;

import io.brito.examples.kafkaproducerapi.resource.request.EventRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final KafkaTemplate<String, EventRequest> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    @Override
    public void produceEvent(EventRequest eventRequest) {
        kafkaTemplate.send(topic, UUID.randomUUID().toString(), eventRequest);
    }
}
