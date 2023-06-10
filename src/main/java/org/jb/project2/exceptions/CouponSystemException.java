package org.jb.project2.exceptions;

public class CouponSystemException extends Exception {
    public CouponSystemException(ErrMessage errMessage) {
        super(errMessage.getMessage());
    }
}
