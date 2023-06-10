package org.jb.project2.services;

import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.login.ClientType;

import java.util.UUID;

public interface AuthService {
    UUID login(String email, String password, ClientType clientType) throws CouponSystemException;
}
