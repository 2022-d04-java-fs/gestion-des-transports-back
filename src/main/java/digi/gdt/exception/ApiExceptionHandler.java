package digi.gdt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import digi.gdt.dto.ApiExceptionDto;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(value = { NotFoundException.class })
  public ResponseEntity<?> handleApiRequestException(NotFoundException exception) {
    HttpStatus badRequest = HttpStatus.NOT_FOUND;
    ApiExceptionDto apiException = new ApiExceptionDto(
        exception.getMessage(),
        badRequest);
    return new ResponseEntity<>(apiException, badRequest);
  }

  @ExceptionHandler(value = { BadRequestException.class })
  public ResponseEntity<?> handleApiRequestException(BadRequestException exception) {
    HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    ApiExceptionDto apiException = new ApiExceptionDto(
        exception.getMessage(),
        badRequest);
    return new ResponseEntity<>(apiException, badRequest);
  }
}
