package org.jb.project2.controllers;

import org.jb.project2.beans.Coupon;
import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.exceptions.ErrMessage;
import org.jb.project2.login.ClientType;
import org.jb.project2.security.TokenServiceImpl;
import org.jb.project2.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/company")

public class CompanyController {
    @Autowired
    private CompanyService companyService;
@Autowired
private TokenServiceImpl tokenService;
    @PostMapping
    void addCoupon(@RequestBody Coupon coupon,@RequestHeader(value = "Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)){
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }

        int companyId= (int) tokenService.getTokens().get(token).getId();
        companyService.addCoupon(coupon,companyId);
    }
}
