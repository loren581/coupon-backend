package org.jb.project2.services;

import org.jb.project2.repos.CompanyRepository;
import org.jb.project2.repos.CouponRepository;
import org.jb.project2.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class  ClientService {
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CouponRepository couponRepository;

    public abstract boolean login (String email,String password);
}
