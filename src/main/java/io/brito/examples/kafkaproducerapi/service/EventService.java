package io.brito.examples.kafkaproducerapi.service;

import io.brito.examples.kafkaproducerapi.resource.request.EventRequest;

public interface EventService {
    void produceEvent(EventRequest eventRequest);
}
