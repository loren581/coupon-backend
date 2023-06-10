package org.jb.project2.advice;

import org.jb.project2.exceptions.CouponSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponSystemController {
    @ExceptionHandler(value = {CouponSystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrDetails handleException(CouponSystemException e){
        return new ErrDetails(e.getMessage());
    }
}
