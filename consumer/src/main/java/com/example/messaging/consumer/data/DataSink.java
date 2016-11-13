package com.example.messaging.consumer.data;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@EnableBinding(Sink.class)
public class DataSink {
    private DataRepository dataRepository;

    public DataSink(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @StreamListener(Sink.INPUT)
    public void doSink(Map<String, Object> map) {
        Data data = new Data();
        data.setData((String) map.get("data"));
        dataRepository.save(data);
    }
}
