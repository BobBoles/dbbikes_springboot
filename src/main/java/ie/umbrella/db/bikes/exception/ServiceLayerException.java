package ie.umbrella.db.bikes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceLayerException extends RuntimeException {
    public ServiceLayerException(String message, Throwable cause) {
        super(message, cause);
    }
}