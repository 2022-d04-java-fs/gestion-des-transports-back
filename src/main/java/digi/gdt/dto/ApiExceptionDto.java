package digi.gdt.dto;

import org.springframework.http.HttpStatus;

public class ApiExceptionDto {
  private final String message;
  private final HttpStatus httpStatus;

  public ApiExceptionDto(String message, HttpStatus httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
  }

  public String getMessage() {
    return this.message;
  }

  public HttpStatus getHttpStatus() {
    return this.httpStatus;
  }

}
