package org.jg.pbtdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class MyObjectMapperFactory {

  static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSXXX");

  public final static ObjectMapper get() {
    return Jackson2ObjectMapperBuilder
        .json()
        .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .modules(new JavaTimeModule())
        .dateFormat(dateFormat)
        .build();
  }
}
