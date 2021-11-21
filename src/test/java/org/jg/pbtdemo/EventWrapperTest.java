package org.jg.pbtdemo;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventWrapperTest {

  public static final String EVENT_ID = "12345-67890";
  public static final String EVENT_TYPE = "context.TYPE";
  private ObjectMapper objectMapper;
  private EventWrapper<BusinessData> eventWrapper;
  private BusinessData businessData;
  private ZonedDateTime eventTime;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    ;
    // "yyyy-MM-dd'T'HH:mm:ss[.SSS[SSS]]XXX"
    eventTime = ZonedDateTime.parse("2021-11-21T10:15:01.123Z");
    eventWrapper = new EventWrapper(EVENT_ID, EVENT_TYPE, eventTime, null);
    businessData = new BusinessData("firstValue", "secondValue");
  }

  @Test
  void verifyObjectToJsonWithoutData() throws JsonProcessingException {
    // given: an event
    // when transforming to json
    String value = objectMapper.writeValueAsString(eventWrapper);
    // then a valid json string is provided
    assertThat(value).isEqualTo(
        "{\"event-id\":\"12345-67890\",\"event-type\":\"context.TYPE\",\"event-time\":\"2021-11-21T10:15:01.123123Z\",\"data\":null}");
  }

  @Test
  void verifyObjectToJsonWithDataClass() throws JsonProcessingException {
    // given: an event wrapper containing a data class
    EventWrapper<BusinessData> secondWrapper = this.eventWrapper.withData(businessData);
    // when: transforming to json
    String value = objectMapper.writeValueAsString(secondWrapper);
    // then: the extra data object is also transformed to json
    assertThat(value).isEqualTo(
        """
            {"event-id":"12345-67890","event-type":"context.TYPE","event-time":"2021-11-21T10:15:01.123123Z","data":{"first-value":"firstValue","second-value":"secondValue"}}""");
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