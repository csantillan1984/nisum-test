package com.nisum.user.app.infrastructure.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

public class RegexValidator implements ConstraintValidator<PatternPassword, String> {
  @Value("${validation.passwd.pattern}")
  private String regex;

  @Override
  public void initialize(PatternPassword constraintAnnotation) {}

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return false;
    }
    return value.matches(this.regex);
  }
}
