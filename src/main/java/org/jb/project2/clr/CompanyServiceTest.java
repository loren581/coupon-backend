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
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@checking addCoupon method and succeding@@@@@@@@@@@@@@@@@@@@@@@@@@");
        CompanyService companyService = (CompanyService) loginManager.login("mcdonalds@gmail.com", "1234", ClientType.COMPANY);
        Coupon coupon = new Coupon(1, companyRepository.findById(5).get(), Category.VACATION, "if you lucky you will get a vacation", "you have 20% to win a vacstion", Date.valueOf(LocalDate.now().minusMonths(3)), Date.valueOf(LocalDate.now().plusMonths(6)), 7, 100, "VACATIONNN");
        companyService.addCoupon(coupon, 5);
        System.out.println("checking addCoupon method and not suceeding same title");
        Coupon coupon1 = new Coupon(2, companyRepository.findById(5).get(), Category.VACATION, "if you lucky you will get a vacation", "you have 20% to win a vacstion", Date.valueOf(LocalDate.now().minusMonths(3)), Date.valueOf(LocalDate.now().plusMonths(6)), 7, 100, "VACATIONNN");
        try {
            companyService.addCoupon(coupon1, 5);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@checking update method and succeding@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Coupon coupon3 = new Coupon(3, companyRepository.findById(5).get(), Category.VACATION, "if you lucky you will get a vacation", "you have 50% to win a vacstion", Date.valueOf(LocalDate.now().minusMonths(3)), Date.valueOf(LocalDate.now().plusMonths(6)), 7, 100, "VACATIONNN");
        companyService.updateCoupon(2, coupon3, 5);
        System.out.println();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@checking update method and not succeding:not same company id@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Coupon coupon4 = new Coupon(3, companyRepository.findById(5).get(), Category.VACATION, "if you lucky you will get a vacation", "you have 80% to win a vacstion", Date.valueOf(LocalDate.now().minusMonths(3)), Date.valueOf(LocalDate.now().plusMonths(6)), 7, 100, "VACATIONNN");
        try {
            companyService.updateCoupon(2, coupon4, 4);

        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@checking deleteCoupon method and not  suceeding:not samecompany id@@@@@@@@@@@@@@@@@@@@@@@@@@ ");
        try {
            companyService.deleteCoupon(2, 4);

        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@checking deleteCoupon method and suceeding@@@@@@@@@@@@@@@@@@@@@@@@@@ ");
        companyService.deleteCoupon(2, 5);
        System.out.println();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@checking getAllCompanyCoupons method and suceeding@@@@@@@@@@@@@@@@@@@@@@@@@@ ");
        Coupon coupon5 = new Coupon(3, companyRepository.findById(5).get(),Category.FOOD, "ice cream", "buy one ice cream and get another one for free", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusMonths(2)), 3, 2.3, "ice creammm");
        Coupon coupon6 = new Coupon(4,companyRepository.findById(5).get(), Category.FOOD, "humburger for free", "buy one burger and get another one for free", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusMonths(3)), 3, 5.5, "burgerrr");
        Coupon coupon7 = new Coupon(5,companyRepository.findById(5).get(), Category.FOOD, "1+1", "buy one coffee and get another one for free", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusMonths(4)), 3, 7, "coffeee");
    companyService.addCoupon(coupon5,5);
    companyService.addCoupon(coupon6,5);
    companyService.addCoupon(coupon7,5);
        System.out.println(companyService.getCompanyCoupons(5));
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@checking getAllCompanyCouponsByMaxPrice method and suceeding@@@@@@@@@@@@@@@@@@@@@@@@@@ ");
        System.out.println(companyService.getCompanyCoupons(2.9, 5));
        System.out.println();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@checking getAllCompanyCouponsByCategory method and suceeding@@@@@@@@@@@@@@@@@@@@@@@@@@ ");
        System.out.println(companyService.getCompanyCoupons(Category.FOOD, 5));
    }
}