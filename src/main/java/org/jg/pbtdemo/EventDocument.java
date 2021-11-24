package org.jg.pbtdemo;

import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")
@Data
public class EventDocument<T> implements Serializable {

  @Id
  private String eventId;
  @Version
  private Long version;
  //...
  private String eventTpye;
  private OffsetDateTime eventTime;
  private T data;

  public static EventDocument of(EventWrapper event) {
    return new EventDocument()
        .setEventId(event.);
  }
}
