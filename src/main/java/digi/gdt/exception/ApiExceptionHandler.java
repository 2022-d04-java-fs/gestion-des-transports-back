package digi.gdt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import digi.gdt.dto.ApiExceptionDto;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(value = { ApiRequestException.class })
  public ResponseEntity<?> handleApiRequestException(ApiRequestException exception) {
    HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    ApiExceptionDto apiException = new ApiExceptionDto(
        exception.getMessage(),
        badRequest);
    return new ResponseEntity<>(apiException, badRequest);
  }
}
