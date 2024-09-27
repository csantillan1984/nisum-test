package com.nisum.user.app.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends RuntimeException {

  private final HttpStatus httpStatus;
  private final String message;

  public UserException(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
    this.message = message;
  }
}
