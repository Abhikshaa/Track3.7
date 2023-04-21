package com.Track.exception;

import com.Track.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException exception){

Map<String,String> resp = new HashMap<>();
exception.getBindingResult().getAllErrors().forEach((error) ->{//print error msg

    String field = ((FieldError) error).getField();
    String message = error.getDefaultMessage();


    resp.put(field,message);

} );

return  new ResponseEntity<Map<String,String>>(resp, HttpStatus.BAD_REQUEST);


    }
}
