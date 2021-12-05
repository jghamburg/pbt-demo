package org.jg.pbtdemo;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Provide;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.StatisticsReport;
import net.jqwik.time.api.DateTimes;

public class MyOffsetDateTimePbTest {

  ObjectMapper objectMapper = MyObjectMapperFactory.get();

  // @Example
  @Report(Reporting.GENERATED)
  @StatisticsReport(format = Histogram.class)
  @Label("example MyDate converted to and from json are the same")
  void exampleMyDateConvertedToJsonAndBackIsSameAsStarter(
      @ForAll("validMyDate") MyOffsetDateTime myOffsetDateTime
  ) throws JsonProcessingException {
    String jsonString = objectMapper.writeValueAsString(myOffsetDateTime);
    final MyOffsetDateTime myReadDate = objectMapper.readValue(jsonString, MyOffsetDateTime.class);
    assertThat(myReadDate)
        .isEqualTo(myOffsetDateTime);
  }

  //@Property
  @Report(Reporting.GENERATED)
  @StatisticsReport(format = Histogram.class)
  @Label("generated MyDate converted to and from json are the same")
  boolean anyMyDateConvertedToJsonAndBackIsSameAsStarter(
      @ForAll("validMyDate") MyOffsetDateTime myOffsetDateTime
  ) throws JsonProcessingException {
    String jsonString = objectMapper.writeValueAsString(myOffsetDateTime);
    final MyOffsetDateTime myReadDate = objectMapper.readValue(jsonString, MyOffsetDateTime.class);
    assertThat(myReadDate)
        .isEqualTo(myOffsetDateTime);
    return myOffsetDateTime.equals(myReadDate);
  }

  @Provide
  static Arbitrary<MyOffsetDateTime> validMyDate() {
    Arbitrary<OffsetDateTime> offsetDateTimeArbitrary = DateTimes.offsetDateTimes()
        .between(LocalDateTime.now(), LocalDateTime.parse("2022-11-11T11:11:11.123456"));
    Arbitrary<String> idArbitrary = Arbitraries.strings().ofLength(30);
    return Combinators.combine(idArbitrary, offsetDateTimeArbitrary).as(MyOffsetDateTime::new);
  }

}
