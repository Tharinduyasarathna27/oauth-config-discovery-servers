package lk.group1.auth.server.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<ExceptionHolder> handleUserNotFoundException(DataNotFoundException ex,
                                                                             WebRequest request) {
        ExceptionHolder exceptionHolder = new ExceptionHolder(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionHolder, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionHolder> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionHolder exceptionHolder = new ExceptionHolder(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionHolder, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(CustomDataIntergrityVoilationException.class)
    public final ResponseEntity<ExceptionHolder> handleDuplicateEntryException(CustomDataIntergrityVoilationException ex,
                                                                             WebRequest request) {
        ExceptionHolder exceptionHolder = new ExceptionHolder(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionHolder, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

}
