package org.jg.pbtdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.StatisticsReport;
import net.jqwik.time.api.DateTimes;

public class MyDatePbTest {

  ObjectMapper objectMapper = MyObjectMapperFactory.get();

  @Example
  @Label("example MyDate converted to and from json are the same")
  boolean exampleMyDateConvertedToJsonAndBackIsSameAsStarter(
      @ForAll("validMyDate") MyDate myDate
  ) throws JsonProcessingException {
    String jsonString = objectMapper.writeValueAsString(myDate);
    final MyDate myReadDate = objectMapper.readValue(jsonString, MyDate.class);
    return myDate.equals(myReadDate);
  }

  @Property
  @Report(Reporting.GENERATED)
  @StatisticsReport(format = Histogram.class)
  @Label("generated MyDate converted to and from json are the same")
  boolean anyMyDateConvertedToJsonAndBackIsSameAsStarter(
      @ForAll("validMyDate") MyDate myDate
  ) throws JsonProcessingException {
    String jsonString = objectMapper.writeValueAsString(myDate);
    final MyDate myReadDate = objectMapper.readValue(jsonString, MyDate.class);
    return myDate.equals(myReadDate);
  }

  @Provide
  static Arbitrary<MyDate> validMyDate() {
    Arbitrary<OffsetDateTime> offsetDateTimeArbitrary = DateTimes.offsetDateTimes()
        .between(LocalDateTime.now(), LocalDateTime.parse("2022-11-11T11:11:11"));

    return offsetDateTimeArbitrary.map(MyDate::new);
  }

}
