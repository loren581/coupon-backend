package org.jb.project2.controllers;

import org.jb.project2.beans.Company;
import org.jb.project2.beans.Customer;
import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping
    @RequestMapping("companies")
    public List<Company> getAllCompanies() {
        return adminService.getAllCompanies();
    }

    @GetMapping
    @RequestMapping("customers")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }
    @GetMapping
    @RequestMapping("companies/{companyId}")
    public Company getSingleCompany(@PathVariable int companyId) throws CouponSystemException {
        return adminService.getSingleCompany( companyId);
    }
    @GetMapping
    @RequestMapping("customers/{customersId}")
    public Customer getSingleCustomer(@PathVariable int customersId) throws CouponSystemException {
        return adminService.getSingleCustomer( customersId);
    }
    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company) throws CouponSystemException {
        adminService.addCompany(company);
    }
    @PostMapping("customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) throws CouponSystemException {
        adminService.addCustomer(customer);
    }
    @DeleteMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int companyId) throws CouponSystemException {
        adminService.deleteCompany(companyId);
    }
    @DeleteMapping("customers/{customersId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int customerId) throws CouponSystemException {
        adminService.deleteCustomer(customerId);
    }
    @PutMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@PathVariable int companyId,@RequestBody Company company) throws CouponSystemException {
        adminService.updateCompany(companyId,company);
    }
    @PutMapping("customers/{customersId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable int customersId,@RequestBody Customer customer) throws CouponSystemException {
        adminService.updateCustomer(customersId,customer);
    }
}
