package com.example.messaging.producer.data;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.http.Http;
import org.springframework.integration.dsl.support.Transformers;

@Configuration
@EnableBinding(Source.class)
public class DataSource {
    @Bean
    public IntegrationFlow integrationFlow() {
        return IntegrationFlows
                .from(Http.inboundChannelAdapter("/data"))
                .transform(Transformers.fromJson(Data.class))
                .channel(Source.OUTPUT)
                .get();
    }
}
