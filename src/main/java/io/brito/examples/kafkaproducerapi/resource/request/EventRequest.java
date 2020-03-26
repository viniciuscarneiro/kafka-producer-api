package io.brito.examples.kafkaproducerapi.resource.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventRequest {

    @NotBlank(message = "0000000000001")
    private String eventName;

    @NotNull(message = "0000000000001")
    private Object eventBody;
}
