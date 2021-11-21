package org.jg.pbtdemo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Utc8601ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

  private static final DateTimeFormatter utc8601Format = new DateTimeFormatterBuilder()
// date/time
      .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
// offset +00:00 or Z
      .optionalStart().appendOffset("+HH:MM", "Z").optionalEnd()
// offset +0000, +00 or Z
      .optionalStart().appendOffset("+HHmm", "Z").optionalEnd()
// create formatter
      .toFormatter();

  @Override
  public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    return ZonedDateTime.parse(p.getText(), utc8601Format);
  }
}
