package io.brito.examples.kafkaproducerapi.resource;

import io.brito.examples.kafkaproducerapi.resource.request.EventRequest;
import io.brito.examples.kafkaproducerapi.service.EventService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/produce-event")
public class ProduceEventResource {

    private final EventService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void produceEvent(@Valid @RequestBody EventRequest eventRequest) {
        log.info("Sending event: {}", eventRequest);
        service.produceEvent(eventRequest);
        log.info("Event sent!");
    }
}
