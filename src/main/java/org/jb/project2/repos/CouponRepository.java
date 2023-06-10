package org.jb.project2.repos;

import org.jb.project2.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {
}
