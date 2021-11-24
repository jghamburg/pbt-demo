package org.jg.pbtdemo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class BusinessData {

  public static final String FIRST_VALUE_NAME = "first-value";
  public static final String SECOND_VALUE_NAME = "second-value";
  @JsonProperty(FIRST_VALUE_NAME)
  private final String firstValue;
  @JsonProperty(SECOND_VALUE_NAME)
  private final String secondValue;

  @JsonCreator
  public BusinessData(@JsonProperty(FIRST_VALUE_NAME) String firstValue,
      @JsonProperty(SECOND_VALUE_NAME) String secondValue) {
    this.firstValue = firstValue;
    this.secondValue = secondValue;
  }
}
