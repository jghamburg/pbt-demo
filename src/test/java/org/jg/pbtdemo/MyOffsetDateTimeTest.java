package org.jg.pbtdemo;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

class MyOffsetDateTimeTest {

  @Test
  void verifyDateTimeToJson() throws JsonProcessingException {
    final ObjectMapper mapper = MyObjectMapperFactory.get();
    final MyOffsetDateTime date = new MyOffsetDateTime(
        OffsetDateTime.parse("2021-11-23T23:08:59.492234Z"));
    final String value = mapper.writeValueAsString(date);
    assertThat(value)
        .isEqualTo("{\"offset-time\":\"2021-11-23T23:08:59.492234Z\"}");
    // and back
    final MyOffsetDateTime readValue = mapper.readerFor(MyOffsetDateTime.class).readValue(value);
    assertThat(readValue).isEqualTo(date);
  }
}