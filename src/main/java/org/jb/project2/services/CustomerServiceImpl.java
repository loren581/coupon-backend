package org.jb.project2.services;

import lombok.Getter;
import lombok.Setter;
import org.jb.project2.beans.Category;
import org.jb.project2.beans.Coupon;
import org.jb.project2.beans.Customer;
import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.exceptions.ErrMessage;
import org.jb.project2.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service


public class CustomerServiceImpl extends ClientService implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public boolean login(String email, String password) {
        return customerRepository.existsByEmailAndPassword(email, password);
    }

    @Override
    public void purchaseCoupon(int customerId, int couponId) throws CouponSystemException {
        if (!couponRepository.existsById(couponId)){
            throw new CouponSystemException(ErrMessage.COUPON_DOESNT_EXIST);
        }
        if (customerRepository.isPurchaseExists(customerId,couponId)==1) {
            throw new CouponSystemException(ErrMessage.COSTUMER_HAS_THIS_COUPON);
        }
        Coupon coupon=couponRepository.findById(couponId).get();
        System.out.println(coupon);
            if (coupon.getAmount() == 0) {
            throw new CouponSystemException(ErrMessage.COUPON_OUT_OF_STOCK);
        }
            customerRepository.addCouponPurchase(customerId,couponId);
            coupon.setAmount(coupon.getAmount()-1);
            couponRepository.saveAndFlush(coupon);

    }

    @Override
    public List<Coupon> getCostumersCoupons(int customerId) {
        return couponRepository.getAllCouponsByCustomerId(customerId);
    }

    @Override
    public List<Coupon> getCostumersCoupons( int customerId,Category category) {
        return couponRepository.getAllCouponsByCustomerIdAndCategory(customerId,category.ordinal());
    }

    @Override
    public List<Coupon> getCostumersCoupons( int customerId,Double maxPrice) {
        return couponRepository.getAllCouponsByCustomerIdAndMaxPrice(customerId,maxPrice);
    }

    @Override
    public Customer getCostumerDetails(int customerId) {
        return customerRepository.findById(customerId).get();
    }
}
