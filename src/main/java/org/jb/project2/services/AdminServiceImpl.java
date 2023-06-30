package org.jb.project2.services;

import org.jb.project2.beans.Company;
import org.jb.project2.beans.Coupon;
import org.jb.project2.beans.Customer;
import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.exceptions.ErrMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {
    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@gmail.com") && password.equals("1234");

    }

    @Override
    public void addCompany(Company company) throws CouponSystemException {
        if (companyRepository.existsById(company.getId())) {
            throw new CouponSystemException(ErrMessage.COMPANY_ID_EXIST);

        }
        if (companyRepository.existsByName(company.getName())) {
            throw new CouponSystemException(ErrMessage.COMPANY_NAME_EXIST);


        }
        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponSystemException(ErrMessage.COMPANY_EMAIL_EXIST);


        }
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws CouponSystemException {
        company.setId(companyId);
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMessage.COMPANY_ID_NOT_EXISTS);
        }
        company.setId(companyId);


        Company companyFromDb = companyRepository.findById(companyId).get();
        if (!companyFromDb.getName().equals(company.getName())) {
            throw new CouponSystemException(ErrMessage.CANT_UPDATE_COMPANY_NAME);
        }
        companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int companyId) throws CouponSystemException {
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMessage.COMPANY_ID_NOT_EXISTS);
        }
        couponRepository.findByCompanyId(companyId).forEach(coupon ->couponRepository.deleteCouponPurchaseWithCouponId((int) coupon.getId()));
//   List<Coupon> coupons=couponRepository.findByCompanyId(companyId);
//        for (Coupon c:coupons
//             ) {couponRepository.deleteCouponPurchaseWithCouponId((int) c.getId());
//
//        }
        companyRepository.deleteById(companyId);

    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getSingleCompany(Integer companyId) throws CouponSystemException {
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMessage.COMPANY_ID_NOT_EXISTS);
        }
        return companyRepository.findById(companyId).get();
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        if (customerRepository.existsById(customer.getId())) {
            throw new CouponSystemException(ErrMessage.COSTUMER_ID_EXIST);
        }
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponSystemException(ErrMessage.COSTUMER_EMAIL_EXIST);

        }
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws CouponSystemException {
        if (!customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrMessage.COSTUMER_ID_NOT_EXISTS);

        }
        customer.setId(customerId);
        customerRepository.saveAndFlush(customer);

    }

    @Override
    public void deleteCustomer(int customerId) throws CouponSystemException {
        if (!customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrMessage.COSTUMER_ID_NOT_EXISTS);
        }
        couponRepository.deleteCouponPurchaseWithCustomerId(customerId);
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getSingleCustomer(Integer customerId) throws CouponSystemException {
        if (!customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrMessage.COSTUMER_ID_NOT_EXISTS);
        }
        return customerRepository.findById(customerId).get();
    }
}
