package org.jb.project2.repos;

import org.jb.project2.beans.Category;
import org.jb.project2.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {
//    @Query(nativeQuery = true,value = "SELECT * FROM `spring-159`.coupon where company_id=?")
//    List<Coupon> getAllCouponsByCompanyId(int companyId);
//    @Query(nativeQuery = true,value = "SELECT * FROM `spring-159`.coupon WHERE (`company_id` = ?) AND (`category` = ?)")
//    List<Coupon> getAllCouponsByCompanyIdAndCategory(int companyId, Category category);
    List<Coupon> findByCompanyIdAndCategory(int companyId,Category category);
    List<Coupon> findByCompanyId(int companyId);
    @Query(nativeQuery = true,value = "SELECT * FROM `spring-159`.coupon WHERE (`company_id` = ?) AND (`price` < ?)")
    List<Coupon> getAllCouponsByCompanyIdAndMaxPrice(int companyId, double maxPrice);

}
