package org.jb.project2.controllers;

import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.login.ClientType;
import org.jb.project2.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @PostMapping("login")
    public UUID login(@RequestParam ClientType clientType,@RequestParam String email,@RequestParam String password) throws CouponSystemException {
        return authService.login(email, password, clientType);
    }
}
