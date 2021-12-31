package org.jg.pbtdemo;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class BusinessEventTest {

  @Test
  void verifyJsonMapper() throws JsonProcessingException {
    ObjectMapper om = ObjectMapperFactory.get();
    BusinessEvent businessEvent = new BusinessEvent("first", "second");
    final String value = om.writeValueAsString(businessEvent);
    assertThat(value).isEqualTo("{\"first-value\":\"first\",\"second-value\":\"second\"}");
    assertThat(om.readValue(value, BusinessEvent.class)).isEqualTo(businessEvent);
  }
}