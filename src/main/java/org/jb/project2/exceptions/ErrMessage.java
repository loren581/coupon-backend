package org.jb.project2.exceptions;

import lombok.Getter;

@Getter
public enum ErrMessage {
    COMPANY_ID_EXIST(" cannot add company because a company with the same id exists  "),

    COMPANY_NAME_EXIST(" cannot add company because a company with the same name exists  "),

    COMPANY_EMAIL_EXIST(" cannot add company because a company with the same email exist "),
    COMPANY_ID_NOT_EXISTS("can not update/delete/find because the company doesnt exist"),
    EMAIL_OR_PASSWORD_INCORRECT("incorrect email or password"),
    CANT_UPDATE_COMPANY_ID("can not update because you cant update an id of a company"),
    CANT_UPDATE_COMPANY_NAME("can not update/delete/find because you cant update a name of a company"),
    COSTUMER_ID_EXIST(" cannot add costumer because a costumer with the same id exists  "),
    COSTUMER_EMAIL_EXIST(" cannot add costumer because a costumer with the same email exists  "),
    COSTUMER_ID_NOT_EXISTS("can not update because the costumer doesnt exist"),
    CANT_UPDATE_COSTUMER_ID("can not update because you cant update an id of a costumer"),
    CANT_ADD_COUPON("cant add a coupon because a coupon with the same company id and tiltle already exists"),
    CANT_UPDATE_COUPON_ID("can not update because you cant update an id of a coupon"),
    CANT_UPDATE_COUPON_COMPANY_ID("can not update because you cant update company id of a coupon"),
    COSTUMER_HAS_THIS_COUPON("YOU CANT PURCHASE THE SAME COUPON TWICE"),
    COUPON_OUT_OF_STOCK("sorry the coupon is out of stock"),
    COUPON_EXPIRED("cant purchase an expired coupon"),
    NOT_SUCH_AN_EMAIL("this email isnt exists");


    private String message;

    ErrMessage(String message) {
        this.message = message;
    }
}
