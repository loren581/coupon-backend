package org.jb.project2.clr;

import org.jb.project2.beans.Category;
import org.jb.project2.beans.Coupon;
import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.repos.CompanyRepository;
import org.jb.project2.repos.CouponRepository;
import org.jb.project2.services.AdminService;
import org.jb.project2.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Order(3)
public class CustomerServiceTest implements CommandLineRunner {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    CouponRepository couponRepository;
    @Autowired
   private AdminService adminService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@checking addCouponPurchase and suceeding@@@@@@@@@@@@@@@@@@@@@@");
        customerService.purchaseCoupon(2, 3);
        System.out.println();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@checking addCouponPurchase and not suceeding-already purchased@@@@@@@@@@@@@@@@@@@@@@");
        try {
            customerService.purchaseCoupon(2, 3);

        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@checking addCouponPurchase and not suceeding-amount is 0 purchased@@@@@@@@@@@@@@@@@@@@@@");
        Coupon coupon = new Coupon(6, companyRepository.findById(5).get(), Category.VACATION, "if you lucky you will get a spa", "you have 80% to win a spa", Date.valueOf(LocalDate.now().minusMonths(3)), Date.valueOf(LocalDate.now().plusMonths(6)), 0, 100, "SPAAA");
      couponRepository.save(coupon);
        try {
          customerService.purchaseCoupon(5, 6);
      }
      catch (CouponSystemException e){
          System.out.println(e.getMessage());
      }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@checking getCustomerCoupons@@@@@@@@@@@@@@@@@@@@");
        System.out.println(customerService.getCostumersCoupons(2));
        System.out.println();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@checking getCustomerCouponsByMaxPrice@@@@@@@@@@@@@@@@@@@@");
        System.out.println(customerService.getCostumersCoupons(2,5.0));
        System.out.println();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@checking getCustomerCouponsByCategory@@@@@@@@@@@@@@@@@@@@");
        System.out.println(customerService.getCostumersCoupons(2,Category.FOOD));
        System.out.println();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@checking get customerDetails");
        System.out.println(customerService.getCostumerDetails(2));
        Coupon coupon2 = new Coupon(7, companyRepository.findById(5).get(), Category.VACATION, "if you  you will get a spa", "you have % to win a spa", Date.valueOf(LocalDate.now().minusMonths(3)), Date.valueOf(LocalDate.now().minusMonths(6)), 0, 100, "SPAAA");
couponRepository.save(coupon2);
    }
}
