package org.jb.project2.controllers;

import org.jb.project2.beans.Category;
import org.jb.project2.beans.Coupon;
import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.exceptions.ErrMessage;
import org.jb.project2.login.ClientType;
import org.jb.project2.security.TokenServiceImpl;
import org.jb.project2.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/company")

public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TokenServiceImpl tokenService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void addCoupon(@RequestBody Coupon coupon, @RequestHeader(value = "Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }

        int companyId = (int) tokenService.getTokens().get(token).getId();
        companyService.addCoupon(coupon, companyId);
    }

    @PutMapping("/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCoupon(@RequestBody Coupon coupon, @RequestHeader(value = "Authorization") UUID token, @PathVariable int couponId) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        int companyId = (int) tokenService.getTokens().get(token).getId();
        companyService.updateCoupon(couponId, coupon, companyId);

    }

    @DeleteMapping("/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCoupon( @RequestHeader(value = "Authorization") UUID token, @PathVariable int couponId) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        int companyId = (int) tokenService.getTokens().get(token).getId();
        companyService.deleteCoupon(couponId,companyId);
    }
    @GetMapping("coupons/maxPrice")
    List<Coupon> getCompanyCouponsByMaxPrice(@RequestHeader(value = "Authorization") UUID token,@RequestParam double val) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        int companyId = (int) tokenService.getTokens().get(token).getId();
        return companyService.getCompanyCoupons(val,companyId);
    }
    @GetMapping("coupons/category")
    List<Coupon>  getCompanyCouponsByCategory(@RequestHeader(value = "Authorization") UUID token, @RequestParam Category category) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        int companyId = (int) tokenService.getTokens().get(token).getId();
        return companyService.getCompanyCoupons(category,companyId);
    }
    @GetMapping("coupons")
    List<Coupon> getCompanyCoupons(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        int companyId = (int) tokenService.getTokens().get(token).getId();
     return  companyService.getCompanyCoupons(companyId);
    }

}