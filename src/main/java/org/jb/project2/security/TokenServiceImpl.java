package org.jb.project2.security;

import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.exceptions.ErrMessage;
import org.jb.project2.login.ClientType;
import org.jb.project2.repos.CompanyRepository;
import org.jb.project2.repos.CustomerRepository;
import org.jb.project2.services.AdminService;
import org.jb.project2.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AdminService adminService;
    private Map<UUID, Information> tokens=new HashMap<>();

    @Override
    public UUID addToken(ClientType clientType, String email) throws CouponSystemException {
        UUID token = UUID.randomUUID();
        if (clientType.equals(ClientType.COMPANY)) {

            Information information = Information.builder().time(LocalDateTime.now()).id(companyRepository.companyEmailToId(email)).clientType(ClientType.COMPANY).build();
            tokens.put(token, information);
            return token;
        }
        if (clientType.equals(ClientType.CUSTOMER)) {

            Information information = Information.builder().time(LocalDateTime.now()).id(customerRepository.customerEmailToId(email)).clientType(ClientType.CUSTOMER).build();
            tokens.put(token, information);
            return token;
        }
        if (clientType.equals(ClientType.ADMINISTRATOR)){
            Information information = Information.builder().time(LocalDateTime.now()).id(0).clientType(ClientType.ADMINISTRATOR).build();
            tokens.put(token, information);
            return token;
        }
        throw new CouponSystemException(ErrMessage.NOT_SUCH_AN_EMAIL);
    }

    @Override
    public boolean isUserAllowed(UUID token, ClientType clientType) throws CouponSystemException {
        if (!tokens.containsKey(token)){
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        Information information = tokens.get(token);
        ClientType clientType1 = information.getClientType();
        return clientType1.equals(clientType);
    }

    public Map<UUID, Information> getTokens() {
        return tokens;
    }
}
