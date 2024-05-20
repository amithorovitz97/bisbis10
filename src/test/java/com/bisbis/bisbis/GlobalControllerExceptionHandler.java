package com.bisbis.bisbis;

import java.io.IOException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bisbis.bisbis.dish.DishNotFound;
import com.bisbis.bisbis.rating.RatingsNotFound;
import com.bisbis.bisbis.restaurant.RestaurantNotFound;

import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler({RestaurantNotFound.class, RatingsNotFound.class, DishNotFound.class})
    public void springHandleNotFound(HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    //@ExceptionHandler(DishNotFound.class)
    //public void springHandleNotFound(HttpServletResponse response) throws IOException{
    //    response.sendError(HttpStatus.NOT_FOUND.value());
    //}
    

   // @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request){
        return new ResponseEntity<>(status);                                                            
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationExcpetion(HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
