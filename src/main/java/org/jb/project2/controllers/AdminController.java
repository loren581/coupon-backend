package org.jb.project2.controllers;

import org.jb.project2.beans.Company;
import org.jb.project2.beans.Customer;
import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.exceptions.ErrMessage;
import org.jb.project2.login.ClientType;
import org.jb.project2.security.TokenService;
import org.jb.project2.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    @Autowired
    TokenService tokenService;
    @Autowired
    AdminService adminService;

    @GetMapping
    @RequestMapping("companies")
    public List<Company> getAllCompanies(@RequestHeader(value = "Authorization")UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)){
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        return adminService.getAllCompanies();
    }

    @GetMapping
    @RequestMapping("customers")
    public List<Customer> getAllCustomers(@RequestHeader(value = "Authorization")UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)){
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        return adminService.getAllCustomers();
    }
    @GetMapping
    @RequestMapping("companies/{companyId}")
    public Company getSingleCompany(@RequestHeader(value = "Authorization")UUID token,@PathVariable int companyId) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)){
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        return adminService.getSingleCompany( companyId);
    }
    @GetMapping
    @RequestMapping("customers/{customersId}")
    public Customer getSingleCustomer(@RequestHeader(value = "Authorization")UUID token,@PathVariable int customersId) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)){
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        return adminService.getSingleCustomer( customersId);
    }
    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestHeader(value = "Authorization")UUID token,@RequestBody Company company) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)){
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        adminService.addCompany(company);
    }
    @PostMapping("customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestHeader(value = "Authorization")UUID token,@RequestBody Customer customer) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)){
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        adminService.addCustomer(customer);
    }
    @DeleteMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@RequestHeader(value = "Authorization")UUID token,@PathVariable int companyId) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)){
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        adminService.deleteCompany(companyId);
    }
    @DeleteMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@RequestHeader(value = "Authorization")UUID token,@PathVariable int customerId) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)){
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        adminService.deleteCustomer(customerId);
    }
    @PutMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@RequestHeader(value = "Authorization")UUID token,@PathVariable int companyId,@RequestBody Company company) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)){
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        adminService.updateCompany(companyId,company);
    }
    @PutMapping("customers/{customersId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestHeader(value = "Authorization")UUID token,@PathVariable int customersId,@RequestBody Customer customer) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)){
            throw new CouponSystemException(ErrMessage.NOT_ALLOWED);
        }
        adminService.updateCustomer(customersId,customer);
    }
}
