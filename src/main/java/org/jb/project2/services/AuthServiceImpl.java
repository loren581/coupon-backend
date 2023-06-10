package org.jb.project2.services;

import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.exceptions.ErrMessage;
import org.jb.project2.login.ClientType;
import org.jb.project2.repos.CompanyRepository;
import org.jb.project2.repos.CustomerRepository;
import org.jb.project2.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AdminService adminService;
    @Autowired
    private TokenService tokenService;
    @Override
    public UUID login(String email, String password, ClientType clientType) throws CouponSystemException {
        if (clientType.equals(ClientType.COMPANY)) {
            if (!companyRepository.existsByEmailAndPassword(email, password)) {
                throw new CouponSystemException(ErrMessage.EMAIL_OR_PASSWORD_INCORRECT);
            }
        }
        if (clientType.equals(ClientType.CUSTOMER)) {
            if (!customerRepository.existsByEmailAndPassword(email, password)) {
                throw new CouponSystemException(ErrMessage.EMAIL_OR_PASSWORD_INCORRECT);
            }
        }
        if (clientType.equals(ClientType.ADMINISTRATOR)){
            if (!((ClientService) adminService).login(email, password)) {
                throw new CouponSystemException(ErrMessage.EMAIL_OR_PASSWORD_INCORRECT);
            }
    }
        return tokenService.addToken(clientType,email);
    }
}
