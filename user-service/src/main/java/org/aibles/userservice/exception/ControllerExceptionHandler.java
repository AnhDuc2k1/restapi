package org.aibles.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler (UserNotFoundException.class)
    public ResponseEntity <String> userNotFoundException(UserNotFoundException e){
        return new ResponseEntity<String>("User Not Found", HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler (UserAlreadyExistsException.class)
    public ResponseEntity<String> userAlreadyExistsException(UserAlreadyExistsException e){
        return new ResponseEntity<String> ("User Already Exists", HttpStatus.CONFLICT);
    }
}
