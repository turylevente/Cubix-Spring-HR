package hu.cubix.hr.tlevi.exception;

import hu.cubix.hr.tlevi.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IncorrectIdException.class)
    public ResponseEntity<Object> handleInCorrectIdException(IncorrectIdException exception) {
        return new ResponseEntity<>(new ErrorDto("error", exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = WrongRegistrationNumber.class)
    public ResponseEntity<Object> handleInCorrectRegNumException(WrongRegistrationNumber exception) {
        return new ResponseEntity<>(new ErrorDto("error", exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}


