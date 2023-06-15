package org.jb.project2.controllers;

import org.jb.project2.beans.User;
import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.login.ClientType;
import org.jb.project2.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
   private AuthService authService;
    @PostMapping("login")
    public UUID login(@RequestBody User user) throws CouponSystemException {
        return authService.login(user.getEmail(), user.getPassword() ,user.getClientType());
    }
}
