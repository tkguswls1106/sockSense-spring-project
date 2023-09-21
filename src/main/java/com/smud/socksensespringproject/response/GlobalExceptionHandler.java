package com.smud.socksensespringproject.response;

import com.smud.socksensespringproject.response.exeption.GenderBadRequestException;
import com.smud.socksensespringproject.response.exeption.ImagesBadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity handleException(Exception ex) {
//        return ResponseData.toResponseEntity(ResponseCode.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(GenderBadRequestException.class)
    public ResponseEntity handleGenderBadRequestException(GenderBadRequestException ex) {
        return ResponseData.toResponseEntity(ResponseCode.BAD_REQUEST_GENDER);
    }

    @ExceptionHandler(ImagesBadRequestException.class)
    public ResponseEntity handleImagesBadRequestException(ImagesBadRequestException ex) {
        return ResponseData.toResponseEntity(ResponseCode.BAD_REQUEST_IMAGES);
    }

}
