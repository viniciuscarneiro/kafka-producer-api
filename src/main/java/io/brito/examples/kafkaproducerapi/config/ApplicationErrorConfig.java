package io.brito.examples.kafkaproducerapi.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ApplicationErrorConfig {

    @Bean
    public MessageSource applicationErrorMessageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/api_errors");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
