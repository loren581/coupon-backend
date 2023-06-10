package org.jb.project2.security;

import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.login.ClientType;
import org.jb.project2.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface TokenService {
    UUID addToken(ClientType clientType,String email) throws CouponSystemException;
    boolean isUserAllowed(UUID token, ClientType clientType) throws CouponSystemException;
}
