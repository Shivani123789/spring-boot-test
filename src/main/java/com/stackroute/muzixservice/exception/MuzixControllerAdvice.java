package com.stackroute.muzixservice.exception;

import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class MuzixControllerAdvice {
    @ExceptionHandler(value = TrackAlreadyExistsException.class)
    public ResponseEntity<String> exceptionHandler(TrackAlreadyExistsException e)
    {
        return new ResponseEntity<String>("Error occurred" +e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = TrackNotFoundException.class)
    public ResponseEntity<String> exceptionHandler(TrackNotFoundException e)
    {
        return new ResponseEntity<String>("Error occurred" +e.getMessage(), HttpStatus.CONFLICT);
    }



}
