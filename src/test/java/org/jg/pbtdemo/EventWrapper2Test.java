package org.jg.pbtdemo;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventWrapper2Test {

  public static final String EVENT_ID = "12345-67890";
  public static final String EVENT_TYPE = "context.TYPE";
  private ObjectMapper mapper;
  EventWrapper<BusinessData> eventWrapper;
  private BusinessData businessData;
  private OffsetDateTime eventTime;

  @BeforeEach
  void setUp() {

    mapper = ObjectMapperFactory.get();
    // "yyyy-MM-dd'T'HH:mm:ss[.SSS[SSS]]XXX"
    eventTime = OffsetDateTime.parse("2021-11-21T10:15:01.123Z");
    eventWrapper = new EventWrapper<BusinessData>(EVENT_ID, EVENT_TYPE, eventTime, null);
    businessData = new BusinessData("firstValue", "secondValue");
  }

  @Test
  void verifyObjectToJsonAndBackWithoutData() throws JsonProcessingException {
    // given: an event
    // when transforming to json
    String value = mapper.writeValueAsString(eventWrapper);
    // then a valid json string is provided
    assertThat(value)
        .isEqualTo("""
            {"event-id":"12345-67890","event-type":"context.TYPE","event-time":"2021-11-21T10:15:01.123Z","data":null}"""
        );
    // and back again
    EventWrapper<?> readValue = mapper.readValue(value, EventWrapper.class);
    // then: start and end should be equal
    assertThat(eventWrapper).isEqualTo(readValue);
  }

  @Test
  void verifyObjectToJsonWithDataClass() throws JsonProcessingException {
    // given: an event wrapper containing a data class
    EventWrapper<BusinessData> secondWrapper = this.eventWrapper.withData(businessData);
    // when: transforming to json
    String value = mapper.writeValueAsString(secondWrapper);
    // then: the extra data object is also transformed to json
    assertThat(value)
        .isEqualTo("""
            {"event-id":"12345-67890","event-type":"context.TYPE","event-time":"2021-11-21T10:15:01.123Z","data":{"first-value":"firstValue","second-value":"secondValue"}}""");
  }

  @Test
  void validateBackAndForthConversion() throws JsonProcessingException {
    // given: an event wrapper with valid business data
    EventWrapper<BusinessData> startWrapper = this.eventWrapper.withData(businessData);
    // when: transformation to json is processed
    String value = mapper.writeValueAsString(startWrapper);
    // and: transformation from string (json) back to object
    TypeReference ref = new TypeReference<EventWrapper<BusinessData>>() {
    };
    EventWrapper<BusinessData> wrapper = (EventWrapper<BusinessData>) mapper.readValue(value,
        ref);
    // then: entry object and returned object are equal
    assertThat(wrapper).isEqualTo(startWrapper);
  }
}