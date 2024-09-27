package com.nisum.user.app.infrastructure.input.adapter.rest.handler;

import com.nisum.user.app.infrastructure.exception.UserException;
import com.nisum.user.app.infrastructure.input.adapter.rest.handler.to.ApiExceptionTo;
import com.nisum.user.app.infrastructure.input.adapter.rest.handler.to.ErrorCodeTo;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.MissingRequestValueException;

@ControllerAdvice
@Log4j2
public class UserAppExceptionHandler {

  private static final String ERROR_INFORMATION = "Error sending information";

  @ExceptionHandler(UserException.class)
  protected ResponseEntity<ApiExceptionTo> handleUserException(UserException exception) {
    ApiExceptionTo responseApi = getExcepcion(exception);
    return new ResponseEntity<>(responseApi, HttpStatusCode.valueOf(responseApi.getHttpStatus()));
  }

  @ExceptionHandler(RuntimeException.class)
  protected ResponseEntity<ApiExceptionTo> handleException(RuntimeException exception) {
    ApiExceptionTo responseApi = getExcepcion(exception);
    return new ResponseEntity<>(responseApi, HttpStatusCode.valueOf(responseApi.getHttpStatus()));
  }

  @ExceptionHandler(WebExchangeBindException.class)
  protected ResponseEntity<ApiExceptionTo> handleWebExchangeBindException(
      WebExchangeBindException exception) {
    ApiExceptionTo responseApi = getExcepcion(exception);
    return new ResponseEntity<>(responseApi, HttpStatusCode.valueOf(responseApi.getHttpStatus()));
  }

  @ExceptionHandler(MissingRequestValueException.class)
  protected ResponseEntity<ApiExceptionTo> handleMissingRequestValueException(
      MissingRequestValueException exception) {
    ApiExceptionTo responseApi = getExcepcion(exception);
    return new ResponseEntity<>(responseApi, HttpStatusCode.valueOf(responseApi.getHttpStatus()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ApiExceptionTo> handleMethodArgumentException(
      MethodArgumentNotValidException exception) {
    ApiExceptionTo responseApi = getExcepcion(exception);
    return new ResponseEntity<>(responseApi, HttpStatusCode.valueOf(responseApi.getHttpStatus()));
  }

  private ApiExceptionTo getExcepcion(Exception ex) {
    ApiExceptionTo exception = ApiExceptionTo.builder().build();

    if (ex instanceof MethodArgumentNotValidException) {
      MethodArgumentNotValidException wex = (MethodArgumentNotValidException) ex;
      exception.setTitle(ERROR_INFORMATION);
      exception.setHttpStatus(HttpStatus.BAD_REQUEST.value());
      exception.setMessage("error fields exist");
      exception.setErrorCodeList(new ArrayList<>());
      loadWebArgumentException(wex.getFieldErrors(), exception, null);
    } else {
      if (ex instanceof MissingRequestValueException) {
        MissingRequestValueException wex = (MissingRequestValueException) ex;
        exception.setTitle(ERROR_INFORMATION);
        exception.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        exception.setErrorCodeList(new ArrayList<>());
        loadWebArgumentException(null, exception, wex);
      } else {
        if (ex instanceof UserException) {
          UserException aex = (UserException) ex;
          exception.setTitle("Validation Error");
          exception.setMessage(aex.getMessage());
          exception.setHttpStatus(aex.getHttpStatus().value());
        } else {
          exception.setTitle("Internal error server");
          exception.setMessage("Please contact the administrator");
          exception.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
      }
    }

    log.error("Error (general excepcion, custom excepcion) [{},{}]", () -> ex, () -> exception);
    return exception;
  }

  private void loadWebArgumentException(
      List<FieldError> fieldErrors, ApiExceptionTo exception, MissingRequestValueException mex) {
    if (fieldErrors != null) {
      fieldErrors.stream()
          .forEach(
              err -> {
                exception
                    .getErrorCodeList()
                    .add(
                        ErrorCodeTo.builder()
                            .errorCode(HttpStatus.BAD_REQUEST.value())
                            .errorReason(err.getDefaultMessage())
                            .build());
              });
    } else {
      exception
          .getErrorCodeList()
          .add(
              ErrorCodeTo.builder()
                  .errorReason(mex.getReason())
                  .errorCode(mex.getStatusCode().value())
                  .build());
    }
  }
}
