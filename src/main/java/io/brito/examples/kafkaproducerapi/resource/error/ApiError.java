package io.brito.examples.kafkaproducerapi.resource.error;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonInclude(NON_NULL)
public class ApiError {

    private final String code;
    private final String message;
    private final String[] args;
}
