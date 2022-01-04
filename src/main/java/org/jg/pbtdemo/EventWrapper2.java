package org.jg.pbtdemo;

import static org.apache.commons.lang3.Validate.*;
import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import java.time.OffsetDateTime;
import lombok.Value;
import lombok.With;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Value
@With
public class EventWrapper2<T> {

  public static final String EVENT_ID_NAME = "event-id";
  public static final String EVENT_TYPE_NAME = "event-type";
  public static final String DATA_NAME = "data";
  public static final String EVENT_TIME_NAME = "event-time";

  @JsonProperty(EVENT_ID_NAME)
  String eventId;
  @JsonProperty(EVENT_TYPE_NAME)
  String eventType;
  // 2020-09-22T11:47:22.634+0000
  @JsonProperty(EVENT_TIME_NAME)
  OffsetDateTime eventTime;
  @JsonProperty(DATA_NAME)
  T data;

  @JsonCreator
  public EventWrapper2(@JsonProperty(EVENT_ID_NAME) String eventId,
      @JsonProperty(EVENT_TYPE_NAME) String eventType,
      @JsonProperty(EVENT_TIME_NAME) OffsetDateTime eventTime,
      @JsonProperty(DATA_NAME) T data) {
    notBlank(eventId, EVENT_ID_NAME);
    this.eventId = eventId;
    notBlank(eventType, EVENT_TYPE_NAME);
    this.eventType = eventType;
    notNull(eventTime, EVENT_TIME_NAME);
    this.eventTime = eventTime;
    notNull(data, DATA_NAME);
    this.data = data;
  }
  public String toJson() throws JsonProcessingException {
    return ObjectMapperFactory.get().writeValueAsString(this);
  }

  public static EventWrapper2 fromJson(String jsonString, String dataType) {
    final String checkedJsonString = notEmpty(notNull(jsonString, "jsonString"), "jsonString");
    inclusiveBetween(2,2048, checkedJsonString.length());
    final String checkedDataType = notEmpty(notNull(dataType, "dataType"), "dataType");
    TypeReference ref;
    if ("businessdata".equalsIgnoreCase(checkedDataType.toLowerCase())) {
      ref = new TypeReference<EventWrapper2<BusinessEvent>>() {
      };
    } else {
      ref = new TypeReference<EventWrapper2>() {
      };
    }
    EventWrapper2 eventObject;
    try {
      eventObject = (EventWrapper2) ObjectMapperFactory.get().readValue(jsonString, ref);
    } catch (JsonProcessingException ex) {
      throw new RuntimeException("Error parsing json object");
    }
    return eventObject;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    EventWrapper2<?> that = (EventWrapper2<?>) obj;
    return this.eventId.equals(that.getEventId())
        && this.eventType.equals(that.getEventType())
        && this.eventTime.isEqual(that.getEventTime());
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(eventId).append(eventType).append(eventTime)
        .toHashCode();
  }

}
