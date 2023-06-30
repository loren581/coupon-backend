package org.jb.project2.repos;

import org.jb.project2.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByEmail(String email);
    @Query(nativeQuery = true,value = "select id from customer where email = ?")
    int customerEmailToId(String email);
    boolean existsByEmailAndPassword(String email, String password);

    @Query(nativeQuery = true,value = "INSERT INTO `spring-159`.`customer_coupons` (`customer_id`, `coupons_id`) VALUES (?, ?)")
    @Transactional
    @Modifying
    void addCouponPurchase(int customerId,int couponId);
@Query(nativeQuery = true,value = "SELECT EXISTS (SELECT * FROM `spring-159`.customer_coupons WHERE (`customer_id` = ?) and (`coupons_id` = ?) )")

   int isPurchaseExists(int customerId,int couponId);
}
