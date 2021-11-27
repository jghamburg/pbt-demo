package org.jg.pbtdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.StatisticsReport;

class EventWrapperPbTest {

  private ObjectMapper objectMapper = MyObjectMapperFactory.get();

  @Property
  @Report(Reporting.GENERATED)
  @StatisticsReport(format = Histogram.class)
  @Label("json export of BusinessData will never exeed 255 chars.")
  boolean anyValidBusinessDataJsonIsSmallerThan20(
      @ForAll("validBusinessData") BusinessData businessData)
      throws JsonProcessingException {
    String value = objectMapper.writeValueAsString(businessData);
    return value.length() <= 255;
  }

  @Property
  @Report(Reporting.GENERATED)
  @StatisticsReport(format = Histogram.class)
  @Label("generated events in json format will never exceed 255 chars")
  boolean anyEventDataJsonIsSmaller255(
      @ForAll("validEventWrapper") EventWrapper wrapper
  ) throws JsonProcessingException {
    String value = objectMapper.writeValueAsString(wrapper);
    return value.length() <= 350;
  }

  @Provide
  static Arbitrary<BusinessData> validBusinessData() {
    Arbitrary<String> firstValue = Arbitraries.strings()
        .withCharRange('a', 'z')
        .ofMinLength(2).ofMaxLength(10)
        .map(String::toUpperCase);
    Arbitrary<String> secondValue = Arbitraries.strings()
        .withCharRange('0', '9')
        .ofMinLength(2).ofMaxLength(20);

    return Combinators.combine(firstValue, secondValue).as(BusinessData::new);
  }

  @Provide
  Arbitrary<EventWrapper<BusinessData>> validEventWrapper() {
    Arbitrary<String> eventId = Arbitraries.strings().ofMaxLength(40);
    Arbitrary<String> eventType = Arbitraries.strings()
        .alpha().ofMaxLength(20);
    final Arbitrary<OffsetDateTime> eventTime = Arbitraries
        .of(List.copyOf(ZoneId.getAvailableZoneIds()))
        .flatMap(zone -> Arbitraries
            .longs()
            .between(1266258398000L, 1897410427000L) // ~ +/- 10 years
            .map(epochMilli -> Instant.ofEpochMilli(epochMilli))
            .map(instant -> OffsetDateTime.from(instant.atZone(ZoneId.of(zone)))));

    return Combinators.combine(eventId, eventType, eventTime, validBusinessData())
        .as(EventWrapper::new);
  }
}
