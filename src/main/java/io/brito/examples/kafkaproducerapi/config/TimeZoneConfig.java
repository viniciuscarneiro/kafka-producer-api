package io.brito.examples.kafkaproducerapi.config;

import java.util.Date;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class TimeZoneConfig {
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        log.info("Date in UTC: " + new Date().toString());
    }
}
