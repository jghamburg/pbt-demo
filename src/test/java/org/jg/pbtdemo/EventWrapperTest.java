package org.jg.pbtdemo;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventWrapperTest {

  public static final String EVENT_ID = "12345-67890";
  public static final String EVENT_TYPE = "context.TYPE";
  private ObjectMapper objectMapper;
  private EventWrapper<BusinessData> eventWrapper;
  private BusinessData businessData;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
    eventWrapper = new EventWrapper(EVENT_ID, EVENT_TYPE, null);
    businessData = new BusinessData("firstValue", "secondValue");
  }

  @Test
  void verifyObjectToJsonWithoutData() throws JsonProcessingException {
    // given: an event
    // when transforming to json
    String value = objectMapper.writeValueAsString(eventWrapper);
    // then a valid json string is provided
    assertThat(value).isEqualTo(
        "{\"event-id\":\"12345-67890\",\"event-type\":\"context.TYPE\",\"data\":null}");
  }

  @Test
  void verifyObjectToJsonWithDataClass() throws JsonProcessingException {
    // given: an event wrapper containing a data class
    EventWrapper<BusinessData> secondWrapper = this.eventWrapper.withData(businessData);
    // when: transforming to json
    String value = objectMapper.writeValueAsString(secondWrapper);
    // then: the extra data object is also transformed to json
    assertThat(value).isEqualTo(
        "{\"event-id\":\"12345-67890\",\"event-type\":\"context.TYPE\",\"data\":{\"first-value\":\"firstValue\",\"second-value\":\"secondValue\"}}");
  }

  @Test
  void validateBackAndForthConversion() throws JsonProcessingException {
    // given: an event wrapper with valid business data
    EventWrapper<BusinessData> secondWrapper = this.eventWrapper.withData(businessData);
    // when: transformation to json is proccessed
    String value = objectMapper.writeValueAsString(secondWrapper);
    // and: transformation from string (json) back to object
    TypeReference ref = new TypeReference<EventWrapper<BusinessData>>() {
    };
    EventWrapper<BusinessData> wrapper = (EventWrapper<BusinessData>) objectMapper.readValue(value,
        ref);
    // then: entry object and returned object are equal
    assertThat(secondWrapper).isEqualTo(wrapper);
  }
}