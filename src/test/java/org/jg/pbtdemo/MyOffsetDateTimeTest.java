package org.jg.pbtdemo;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

class MyOffsetDateTimeTest {

  @Test
  void verifyDateTimeToJson() throws JsonProcessingException {
    final ObjectMapper mapper = ObjectMapperFactory.get();
    final String id = "b9c85f96-2a07-4f7b-9d21-ca519966d597";
    final MyOffsetDateTime date = new MyOffsetDateTime(
        id, OffsetDateTime.parse("2021-11-23T23:08:59.492234Z"));
    final String value = mapper.writeValueAsString(date);
    assertThat(value)
        .isEqualTo(
            "{\"id\":\"b9c85f96-2a07-4f7b-9d21-ca519966d597\",\"offset-time\":\"2021-11-23T23:08:59.492234Z\"}");
    // and back
    final MyOffsetDateTime readValue = mapper.readerFor(MyOffsetDateTime.class).readValue(value);
    assertThat(readValue).isEqualTo(date);
  }
}