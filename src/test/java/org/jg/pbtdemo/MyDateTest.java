package org.jg.pbtdemo;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

class MyDateTest {

  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSXXX");

  @Test
  void verifyDateTimeToJson() throws JsonProcessingException {
    final ObjectMapper mapper = Jackson2ObjectMapperBuilder
        .json()
        .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .modules(new JavaTimeModule())
        .dateFormat(dateFormat)
        .build();
    final MyDate date = new MyDate(OffsetDateTime.parse("2021-11-23T23:08:59.492234+01:00"));
    final String value = mapper.writeValueAsString(date);
    assertThat(value)
        .isEqualTo("{\"offset-time\":\"2021-11-23T23:08:59.492234+01:00\"}");
    // and back
    final MyDate readValue = mapper.readerFor(MyDate.class).readValue(value);
    assertThat(readValue).isEqualTo(date);
  }
}