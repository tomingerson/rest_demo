package demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Created by tom on 23.05.2017.
 */
@RestControllerAdvice("demo")
public class ControllerExceptionhandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(final Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(ex
                .getLocalizedMessage());
    }
}
