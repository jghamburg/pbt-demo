package org.jg.pbtdemo;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import javax.validation.Valid;
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

class EventWrapper2PbTest {

  @Property
  @Report(Reporting.GENERATED)
  @StatisticsReport(format = Histogram.class)
  @Label("events transformed to json format and back will be equal.")
  void anyEventDataToJsonAndBackIsEqual(
      @ForAll("validEventWrapper") @Valid EventWrapper2<BusinessEvent> wrapper
  ) throws JsonProcessingException {
    // Assume.that(!wrapper.getEventId().isEmpty());
    // when: transforming to JSON
    String value = wrapper.toJson();
    // and: back to object
    EventWrapper2<BusinessEvent> actualEventWrapper = wrapper.fromJson(value, "BusinessData");
    // then: start and end values are equal
    assertThat(actualEventWrapper).isEqualTo(wrapper);
  }

  @Provide
  static Arbitrary<BusinessEvent> validBusinessData() {
    Arbitrary<String> firstValue = Arbitraries.strings()
        .withCharRange('a', 'z')
        .ofMinLength(2).ofMaxLength(10)
        .map(String::toUpperCase);
    Arbitrary<String> secondValue = Arbitraries.strings()
        .withCharRange('0', '9')
        .ofMinLength(2).ofMaxLength(20);
    return Combinators.combine(firstValue, secondValue).as(BusinessEvent::new);
  }

  @Provide
  Arbitrary<EventWrapper2<BusinessEvent>> validEventWrapper() {
    Arbitrary<String> eventId = Arbitraries.strings().ofMinLength(1)
        .ofLength(40)
        .excludeChars(' ');
    Arbitrary<String> eventType = Arbitraries.strings()
        .alpha().ofLength(30);
    final Arbitrary<OffsetDateTime> eventTime = DateTimes
        .offsetDateTimes()
        .between(LocalDateTime.of(2021, 1, 01, 01, 01, 01, 001),
            LocalDateTime.of(2022, 01, 01, 01, 01, 01, 01));

    return Combinators.combine(eventId, eventType, eventTime, validBusinessData())
        .as(EventWrapper2<BusinessEvent>::new);
  }
}
