package org.jb.project2.services;

import org.jb.project2.beans.Category;
import org.jb.project2.beans.Company;
import org.jb.project2.beans.Coupon;
import org.jb.project2.exceptions.CouponSystemException;

import java.util.List;

public interface CompanyService {
    void addCoupon(Coupon coupon,int id) throws CouponSystemException;
    void deleteCoupon(int couponId,int id) throws CouponSystemException;
    void updateCoupon(int couponId,Coupon coupon,int id) throws CouponSystemException;
    List<Coupon> getCompanyCoupons(int id);
    List<Coupon> getCompanyCoupons(Category category,int id);
    List<Coupon> getCompanyCoupons(Double maxPrice,int id);
    Company getCompanyDetails(int id);
}
