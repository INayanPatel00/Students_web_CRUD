package com.brevitaz.advice;

import com.brevitaz.exception.StudentNotFoundException;
import com.brevitaz.payloads.APIResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<APIResponse> resourceNotFoundExceptionHandler(StudentNotFoundException exception){
        String message =exception.getMessage();
        APIResponse apiResponse =new APIResponse(message,false);
        return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<APIResponse> requestMethodNotSupported(HttpRequestMethodNotSupportedException exception){
        String message = "This method " + exception.getMethod() + " is not supported at this endpoint, try different endpoint";
        APIResponse apiResponse =new APIResponse(message,false);
        return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.METHOD_NOT_ALLOWED);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<APIResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception){
        String message = exception.getMessage();
        APIResponse apiResponse =new APIResponse(message,false);
        return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse> handleValidationException(MethodArgumentNotValidException exception){
        String message = exception.getMessage();
        APIResponse apiResponse =new APIResponse(message,false);
        return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<APIResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
        String errorMessage = "Invalid value for ";
        APIResponse apiResponse =new APIResponse(errorMessage,false);
        return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<APIResponse> handleIllegalArgumentException(IllegalArgumentException exception){
        String errorMessage = exception.getMessage();
        APIResponse apiResponse =new APIResponse(errorMessage,false);
        return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.BAD_REQUEST);
    }

}



//
//
//
//    In Java, there is no built-in exception class that corresponds exactly to InvalidInputException. However, depending on the specific scenario in which you need to throw an exception, you might be able to use one of the built-in exception classes that are provided by Java. Here are some examples:
//
//        IllegalArgumentException: This is a built-in exception class that you can use to indicate that a method has been called with an illegal or inappropriate argument value. You could use this exception class to represent an input validation error similar to InvalidInputException.
//        NumberFormatException: This is a built-in exception class that you can use to indicate that a string could not be parsed as a number. You could use this exception class to represent an error when trying to convert a string to a numeric type.
//        InputMismatchException: This is a built-in exception class that you can use to indicate that the input provided by the user does not match the expected format or type. You could use this exception class to represent an error when trying to parse user input.
//        In general, when choosing a built-in exception class to use in your application, it's important to choose one that best represents the type of error you're encountering. If there is no built-in exception class that exactly matches your scenario, it's generally a good idea to define a custom exception class like InvalidInputException as we discussed earlier.