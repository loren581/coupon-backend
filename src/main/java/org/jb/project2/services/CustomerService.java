package org.jb.project2.services;

import org.jb.project2.beans.Category;
import org.jb.project2.beans.Coupon;
import org.jb.project2.beans.Customer;
import org.jb.project2.exceptions.CouponSystemException;

import java.util.List;

public interface CustomerService {
    void purchaseCoupon(int customerId, int couponId) throws CouponSystemException;
    List<Coupon> getCostumersCoupons(int customerId);
    List<Coupon> getCostumersCoupons( int customerId,Category category);
    List<Coupon> getCostumersCoupons( int customerId,Double maxPrice);
    Customer getCostumerDetails(int customerId);
}
