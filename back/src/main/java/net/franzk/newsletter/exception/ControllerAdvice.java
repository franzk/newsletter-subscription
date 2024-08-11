package net.franzk.newsletter.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailAlreadySubscribedException.class)
    public ResponseEntity<String> handleEmailAlreadySubscribedException(EmailAlreadySubscribedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already subscribed");
    }

    @ExceptionHandler(NoSuchEmailSubscribedException.class)
    public ResponseEntity<String> handleNoSuchEmailSubscribedException(NoSuchEmailSubscribedException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not subscribed");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }
}
