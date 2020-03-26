package io.brito.examples.kafkaproducerapi.resource.error;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

import java.util.Locale;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ApplicationErrorHandler {

    public static final String UNEXPECTED_ERROR_MESSAGE_KEY = "0000000000002";
    public static final String LOG_EXCEPTION = "Exception= {}";

    private final ApplicationErrorMessage applicationErrorMessage;

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<ErrorResponse> handleServerWebInputException(
            ServerWebInputException exception, Locale locale) {
        var message = Optional.ofNullable(exception.getMessage()).orElse(EMPTY);
        log.error(message, exception);
        return getInternalServerErrorResponse(locale, message, exception);
    }

    private ResponseEntity<ErrorResponse> getInternalServerErrorResponse(
            Locale locale, String message, ServerWebInputException exception) {
        ResponseEntity<ErrorResponse> error =
                status(INTERNAL_SERVER_ERROR)
                        .body(
                                applicationErrorMessage.getErrorMessage(
                                        locale,
                                        INTERNAL_SERVER_ERROR.value(),
                                        UNEXPECTED_ERROR_MESSAGE_KEY,
                                        message));
        log.error(LOG_EXCEPTION, error, exception);
        return error;
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            WebExchangeBindException exception, Locale locale) {
        ResponseEntity<ErrorResponse> error =
                badRequest()
                        .body(
                                applicationErrorMessage.getErrorMessage(
                                        locale,
                                        BAD_REQUEST.value(),
                                        exception.getBindingResult().getFieldErrors()));
        log.error(LOG_EXCEPTION, error, exception);
        return error;
    }
}
