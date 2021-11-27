package org.jg.pbtdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class MyObjectMapperFactory {

  public final static ObjectMapper get() {
    return Jackson2ObjectMapperBuilder
        .json()
        .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .modules(new JavaTimeModule())
        .build();
  }
}
