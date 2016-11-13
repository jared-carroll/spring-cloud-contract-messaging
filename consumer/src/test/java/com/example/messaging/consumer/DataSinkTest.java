package com.example.messaging.consumer;

import com.example.messaging.consumer.data.DataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureStubRunner(
        ids = "com.example.messaging.producer:producer:+",
        workOffline = true
)
public class DataSinkTest {
    @Autowired
    StubTrigger stubTrigger;

    @Autowired
    DataRepository dataRepository;

    @Test
    public void getData() {
        long count = dataRepository.count();

        stubTrigger.trigger("postData");

        assertThat(dataRepository.count()).isEqualTo(count + 1);
    }
}
