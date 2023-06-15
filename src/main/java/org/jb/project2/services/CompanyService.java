package org.jb.project2.services;

import org.jb.project2.beans.Category;
import org.jb.project2.beans.Company;
import org.jb.project2.beans.Coupon;
import org.jb.project2.exceptions.CouponSystemException;

import java.util.List;

public interface CompanyService {
    void addCoupon(Coupon coupon,int id) throws CouponSystemException;
    void deleteCoupon(int couponId) throws CouponSystemException;
    void updateCoupon(int couponId,Coupon coupon) throws CouponSystemException;
    List<Coupon> getCompanyCoupons();
    List<Coupon> getCompanyCoupons(Category category);
    List<Coupon> getCompanyCoupons(Double maxPrice);
    Company getCompanyDetails();
}
