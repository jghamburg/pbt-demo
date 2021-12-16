package org.jg.pbtdemo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.Objects;
import lombok.Value;

@Value
public class MyOffsetDateTime {

  public static final String ID_NAME = "id";
  public static final String OFFSET_TIME_NAME = "offset-time";
  @JsonProperty(ID_NAME)
  private final String id;
  @JsonProperty(OFFSET_TIME_NAME)
  private final OffsetDateTime offset;

  @JsonCreator
  public MyOffsetDateTime(@JsonProperty(ID_NAME) String id,
      @JsonProperty(OFFSET_TIME_NAME) OffsetDateTime dateTime) {
    this.id = id;
    this.offset = dateTime;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    MyOffsetDateTime that = (MyOffsetDateTime) obj;
    return Objects.equals(id, that.id) && offset.isEqual(that.offset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, offset);
  }
}
