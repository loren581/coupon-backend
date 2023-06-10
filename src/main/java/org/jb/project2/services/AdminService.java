package org.jb.project2.services;

import org.jb.project2.beans.Company;
import org.jb.project2.beans.Customer;
import org.jb.project2.exceptions.CouponSystemException;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    void addCompany(Company company) throws CouponSystemException;
    void updateCompany(int companyId,Company company) throws CouponSystemException;
    void deleteCompany(int companyId) throws CouponSystemException;
    List<Company> getAllCompanies();
    Company getSingleCompany(Integer companyId) throws CouponSystemException;
    void addCustomer(Customer customer) throws CouponSystemException;
    void updateCustomer(int customerId,Customer customer) throws CouponSystemException;
    void deleteCustomer(int customerId) throws CouponSystemException;
    List<Customer> getAllCustomers();
    Customer getSingleCustomer(Integer customerId) throws CouponSystemException;


}
