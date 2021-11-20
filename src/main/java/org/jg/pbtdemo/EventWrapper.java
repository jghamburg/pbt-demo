package org.jg.pbtdemo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import lombok.With;

@Value
@With
public class EventWrapper<T> {

  public static final String EVENT_ID_NAME = "event-id";
  public static final String EVENT_TYPE_NAME = "event-type";
  public static final String DATA_NAME = "data";
  @JsonProperty(EVENT_ID_NAME)
  private final String eventId;
  @JsonProperty(EVENT_TYPE_NAME)
  private final String eventType;
  @JsonProperty(DATA_NAME)
  private final T data;

  @JsonCreator
  public EventWrapper(@JsonProperty(EVENT_ID_NAME) String eventId,
      @JsonProperty(EVENT_TYPE_NAME) String eventType,
      @JsonProperty(DATA_NAME) T data) {
    this.eventId = eventId;
    this.eventType = eventType;
    this.data = data;
  }

}
