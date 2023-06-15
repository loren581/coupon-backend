package org.jb.project2.clr;

import org.jb.project2.beans.Category;
import org.jb.project2.beans.Coupon;
import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.login.ClientType;
import org.jb.project2.login.LoginManager;
import org.jb.project2.repos.CompanyRepository;
import org.jb.project2.repos.CouponRepository;
import org.jb.project2.repos.CustomerRepository;
import org.jb.project2.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Order(2)
public class CompanyServiceTest implements CommandLineRunner {
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("checking addCoupon method and succeding");
        CompanyService companyService = (CompanyService) loginManager.login("mcdonalds@gmail.com", "1234", ClientType.COMPANY);
        Coupon coupon = new Coupon(1, companyRepository.findById(5).get(), Category.VACATION, "if you lucky you will get a vacation", "you have 20% to win a vacstion", Date.valueOf(LocalDate.now().minusMonths(3)), Date.valueOf(LocalDate.now().plusMonths(6)), 7, 100, "VACATIONNN");
        companyService.addCoupon(coupon,5);
        System.out.println("checking addCoupon method and not suceeding same title");
        Coupon coupon1 = new Coupon(2, companyRepository.findById(5).get(), Category.VACATION, "if you lucky you will get a vacation", "you have 20% to win a vacstion", Date.valueOf(LocalDate.now().minusMonths(3)), Date.valueOf(LocalDate.now().plusMonths(6)), 7, 100, "VACATIONNN");
        try
        {companyService.addCoupon(coupon1,5);}
        catch (CouponSystemException e){
            System.out.println(e.getMessage());
        }

    }
}
