package org.jb.project2.config;

import org.jb.project2.repos.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Date;
import java.time.LocalDate;

@Configuration
@EnableScheduling
@Order(4)
public class DailyRemover {
    @Autowired
    private CouponRepository couponRepository;
    @Scheduled(fixedRate = 1000*60*60*24)
    public void deleteExpiredCoupons(){
        couponRepository.getExpiredCoupons().forEach(coupon -> couponRepository.deleteCouponPurchaseWithCouponId((int) coupon.getId()));

        couponRepository.getExpiredCoupons().forEach(coupon -> couponRepository.deleteById((int) coupon.getId()));
    }
}
