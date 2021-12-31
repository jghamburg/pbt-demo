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
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.StatisticsReport;
import net.jqwik.time.api.DateTimes;

class EventWrapperPbTest {

  private ObjectMapper objectMapper = ObjectMapperFactory.get();

  @Property
  @Report(Reporting.GENERATED)
  @StatisticsReport(format = Histogram.class)
  @Label("events transformed to json format and back will be equal.")
  void anyEventDataToJsonAndBackIsEqual(
      @ForAll("validEventWrapper") EventWrapper wrapper
  ) throws JsonProcessingException {
    // when: transforming to JSON and back to object
    final EventWrapper<BusinessEvent> eventWrapperAgain =
        objectMapper.readValue(objectMapper.writeValueAsString(wrapper),
        EventWrapper.class);
    // then: start and end values are equal
    assertThat(eventWrapperAgain).isEqualTo(wrapper);
  }

  @Provide
  static Arbitrary<BusinessEvent> validBusinessData() {
    Arbitrary<String> firstValue = Arbitraries.strings();
    Arbitrary<String> secondValue = Arbitraries.strings();
    return Combinators.combine(firstValue, secondValue).as(BusinessEvent::new);
  }

  @Provide
  Arbitrary<EventWrapper<BusinessEvent>> validEventWrapper() {
    Arbitrary<String> eventId = Arbitraries.strings();
    Arbitrary<String> eventType = Arbitraries.strings();
    final Arbitrary<OffsetDateTime> eventTime = DateTimes
        .offsetDateTimes()
        .between(LocalDateTime.of(2021, 1, 01, 01, 01, 01, 001),
            LocalDateTime.of(2022, 01, 01, 01, 01, 01, 01));
    return Combinators.combine(eventId, eventType, eventTime, validBusinessData())
        .as(EventWrapper::new);
  }

  @Property
  @Report(Reporting.GENERATED)
  @StatisticsReport(format = Histogram.class)
  @Label("to json transform of BusinessData works just fine.")
  void anyValidBusinessDataJsonIsSmallerThan255(
      @ForAll("validBusinessData") BusinessEvent businessEvent)
      throws JsonProcessingException {
    String value = objectMapper.writeValueAsString(businessEvent);
    assertThat(value)
        .isNotEmpty()
        .isNotBlank();
  }
}
