package org.jg.pbtdemo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import lombok.Value;

@Value
public class MyDate {

  @JsonProperty("offset-time")
  private final OffsetDateTime offset;

  @JsonCreator
  public MyDate(@JsonProperty("offset-time") OffsetDateTime dateTime) {
    this.offset = dateTime;
  }

}
