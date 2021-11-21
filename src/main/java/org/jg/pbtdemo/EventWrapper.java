package org.jg.pbtdemo;

import static org.jg.pbtdemo.EventWrapper.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import lombok.Value;
import lombok.With;

@Value
@With
public class EventWrapper<T> {

  public static final String EVENT_ID_NAME = "event-id";
  public static final String EVENT_TYPE_NAME = "event-type";
  public static final String DATA_NAME = "data";
  public static final String EVENT_TIME_NAME = "event-time";
  @JsonProperty(EVENT_ID_NAME)
  private final String eventId;
  @JsonProperty(EVENT_TYPE_NAME)
  private final String eventType;
  // 2020-09-22T11:47:22.634+0000
  @JsonProperty(EVENT_TIME_NAME)
  @JsonDeserializer(using = Utc8601ZonedDateTimeDeserializer.class)
  private ZonedDateTime eventTime;
  @JsonProperty(DATA_NAME)
  private final T data;

  @JsonCreator
  public EventWrapper(@JsonProperty(EVENT_ID_NAME) String eventId,
      @JsonProperty(EVENT_TYPE_NAME) String eventType,
      @JsonProperty(EVENT_TIME_NAME) ZonedDateTime eventTime,
      @JsonProperty(DATA_NAME) T data) {
    this.eventId = eventId;
    this.eventType = eventType;
    this.eventTime = eventTime;
    this.data = data;
  }

}
