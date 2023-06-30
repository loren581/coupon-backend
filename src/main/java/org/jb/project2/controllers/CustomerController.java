package org.jb.project2.controllers;

import org.jb.project2.beans.Category;
import org.jb.project2.beans.Coupon;
import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.exceptions.ErrMessage;
import org.jb.project2.login.ClientType;
import org.jb.project2.security.TokenServiceImpl;
import org.jb.project2.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private CustomerService customerService;
    @PostMapping("/{couponId}")
    @ResponseStatus(HttpStatus.CREATED)
    void addPurchase(@PathVariable int couponId, @RequestHeader(value = "Authorization")UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        int customerId = (int) tokenService.getTokens().get(token).getId();
        customerService.purchaseCoupon(customerId,couponId);
    }
    @GetMapping("coupons")
    List<Coupon> getCustomersCoupons(@RequestHeader(value = "Authorization")UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        int customerId = (int) tokenService.getTokens().get(token).getId();
        return customerService.getCostumersCoupons(customerId);
    }
    @GetMapping("coupons/maxPrice")
    List<Coupon> getCustomersCoupons(@RequestHeader(value = "Authorization")UUID token,@RequestParam double val) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        int customerId = (int) tokenService.getTokens().get(token).getId();
        return customerService.getCostumersCoupons(customerId,val);
    }
    @GetMapping("coupons/category")
    List<Coupon> getCustomersCoupons(@RequestHeader(value = "Authorization")UUID token,@RequestParam Category val) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        int customerId = (int) tokenService.getTokens().get(token).getId();
        return customerService.getCostumersCoupons(customerId,val);
    }
}
