package org.jg.pbtdemo;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class BusinessDataTest {

  @Test
  void verifyJsonMapper() throws JsonProcessingException {
    ObjectMapper om = MyObjectMapperFactory.get();
    BusinessData businessData = new BusinessData("first", "second");
    final String value = om.writeValueAsString(businessData);
    assertThat(value).isEqualTo("{\"first-value\":\"first\",\"second-value\":\"second\"}");
    assertThat(om.readValue(value, BusinessData.class)).isEqualTo(businessData);
  }
}