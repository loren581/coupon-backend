package org.jb.project2.login;

import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.exceptions.ErrMessage;
import org.jb.project2.repos.CompanyRepository;
import org.jb.project2.repos.CustomerRepository;
import org.jb.project2.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginManager {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;

    public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {
        switch (clientType) {
            case ADMINISTRATOR:
                if (((ClientService) adminService).login(email, password)) {
                return (ClientService) adminService;
            }
            break;

            case COMPANY:
                if (((ClientService) companyService).login(email, password)) {

                    ((CompanyServiceImpl) companyService).setCompanyId(companyRepository.companyEmailToId(email));
                    return (ClientService) companyService;
                }
                break;
            case CUSTOMER:

                if (((ClientService) customerService).login(email, password)) {
                    ((CustomerServiceImpl) customerService).setCustomerId(customerRepository.customerEmailToId(email));
                    return (ClientService) customerService;

                }
                break;

        }
        throw new CouponSystemException(ErrMessage.EMAIL_OR_PASSWORD_INCORRECT);


    }
}
