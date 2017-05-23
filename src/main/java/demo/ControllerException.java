package demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Created by tom on 23.05.2017.
 */
@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class ControllerException extends RuntimeException {
    public ControllerException(String message) {
        super(message);
    }
}
