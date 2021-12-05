package org.jg.pbtdemo.mongo;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoConfig {

  @Bean
  public MongoCustomConversions customConversions() {
    List<Converter<?, ?>> converterList = new ArrayList<>();
    converterList.add(new OffsetDateTimeSerializer());
    converterList.add(new OffsetDateTimeDeserializer());
    return new MongoCustomConversions(converterList);
  }

  @ReadingConverter
  public class OffsetDateTimeDeserializer implements Converter<String, OffsetDateTime> {

    @Override
    public OffsetDateTime convert(String source) {
      return OffsetDateTime.parse(source, DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
  }

  @WritingConverter
  public class OffsetDateTimeSerializer implements Converter<OffsetDateTime, String> {

    @Override
    public String convert(OffsetDateTime source) {
      return source.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
  }

}
