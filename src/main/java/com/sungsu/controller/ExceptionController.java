package com.sungsu.controller;

import com.sungsu.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody // ViewResolver 에러 방지
    public ErrorResponse inValidRequestHandler(MethodArgumentNotValidException e){

        ErrorResponse response =  ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();

        for(FieldError fieldError : e.getFieldErrors()){
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return response;
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody // ViewResolver 에러 방지
//    public Map<String, String> inValidRequestHandler(MethodArgumentNotValidException e){
//
//        FieldError fieldError = e.getFieldError();
//
//        String fieldName = fieldError.getField();
//        String defaultMessage = fieldError.getDefaultMessage();
//
//        Map<String, String> error = new HashMap<>();
//
//        error.put(fieldName, defaultMessage);
//
//
//        return error;
//    }

}
