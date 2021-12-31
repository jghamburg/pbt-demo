package org.jg.pbtdemo;

import java.time.OffsetDateTime;

public class EventWrapperTestData {

  public static final String EVENT_ID = "12345-67890";
  public static final String EVENT_TYPE = "context.TYPE";

  public EventWrapper<BusinessEvent> eventWrapperWithoutData() {
    final OffsetDateTime eventTime = OffsetDateTime.now();
    return new EventWrapper<BusinessEvent>(EVENT_ID, EVENT_TYPE,
        eventTime,
        null);
  }
}
