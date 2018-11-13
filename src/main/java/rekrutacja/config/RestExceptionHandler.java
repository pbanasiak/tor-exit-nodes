package rekrutacja.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import rekrutacja.exception.ExitNodesNotFound;

@ControllerAdvice

public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ExitNodesNotFound.class)
  protected ResponseEntity<Object> handleExitNodesNotFounds() {
    return ResponseEntity.notFound().build();
  }

}
