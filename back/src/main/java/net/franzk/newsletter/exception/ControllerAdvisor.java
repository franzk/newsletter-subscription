package net.franzk.newsletter.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller advisor to handle exceptions
 */
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    /**
     * Handle EmailAlreadySubscribedException
     *
     * @param ex the exception
     * @return response entity with conflict status
     */
    @ExceptionHandler(EmailAlreadySubscribedException.class)
    public ResponseEntity<String> handleEmailAlreadySubscribedException(EmailAlreadySubscribedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already subscribed");
    }

    /**
     * Handle NoSuchEmailSubscribedException
     *
     * @param ex the exception
     * @return response entity with not found status
     */
    @ExceptionHandler(NoSuchEmailSubscribedException.class)
    public ResponseEntity<String> handleNoSuchEmailSubscribedException(NoSuchEmailSubscribedException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not subscribed");
    }

    /**
     * Handle MethodArgumentNotValidException
     *
     * @param ex the exception
     * @param headers the headers
     * @param status the status
     * @param request the request
     * @return response entity with bad request status
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errors.add(fieldError.getField() + " : " + fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
