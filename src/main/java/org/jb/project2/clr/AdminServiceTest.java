package org.jb.project2.clr;

import org.jb.project2.beans.Category;
import org.jb.project2.beans.Company;
import org.jb.project2.beans.Coupon;
import org.jb.project2.beans.Customer;
import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.login.ClientType;
import org.jb.project2.repos.CompanyRepository;
import org.jb.project2.repos.CouponRepository;
import org.jb.project2.repos.CustomerRepository;
import org.jb.project2.services.AdminService;
import org.jb.project2.utils.FactoryMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(1)
public class AdminServiceTest implements CommandLineRunner {
@Autowired
private AdminService adminService;

    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    CustomerRepository customerRepository;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("@@@@@@@@@@@@@ checking add company method @@@@@@@@@@@@@@@@");
        Company company1 = Company.builder().name("coca-cola").email("cocacola@gmail.com").password("1234").build();
        Company company2 = Company.builder().name("cocicoli").email("cocacola@gmail.com").password("1234").build();
        Company company3 = Company.builder().name("coca-cola").email("cocicoli@gmail.com").password("1234").build();
        Company company4 = Company.builder().name("coci-coli").email("cocicoli@gmail.com").password("1234").id(1).build();
        Company company5 = Company.builder().name("coca-cola").email("cocicoli@gmail.com").password("1234").id(3).build();
        Company company6 = Company.builder().name("coci-coli").email("cocicoli@gmail.com").password("1234").id(1).build();

        adminService.addCompany(company1);
        System.out.println("trying to add a company with the same email");
        try {
            adminService.addCompany(company2);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        System.out.println("trying to add a company with the same name");
        try {
            adminService.addCompany(company3);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        System.out.println("trying to add a company with the same id");
        try {
            adminService.addCompany(company4);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("@@@@@@@@@@@@@ checking update company method @@@@@@@@@@@@@@@@");
        System.out.println("trying to change the id of a company");
        try {
            adminService.updateCompany(5, company5);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("trying to change the name of a company");
        try {
            adminService.updateCompany(1, company6);
        } catch (
                CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("@@@@@@@@@@@@@ checking delete company method @@@@@@@@@@@@@@@@");
        Coupon coupon1 = Coupon.builder().company(company1).category(Category.FOOD).price(5.5).amount(6).startDate(Date.valueOf(LocalDate.now())).endDate(Date.valueOf(LocalDate.now().plusMonths(5))).title("1+1").image("COLA").description("buy one coca-cola and get another one for free").build();
        couponRepository.save(coupon1);
        try {
            adminService.deleteCompany(1);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("@@@@@@@@@@@@@ checking get all companies method @@@@@@@@@@@@@@@@");
        Company company7 = Company.builder().name("pepsi").email("pepsi@gmail.com").password("1234").build();
        Company company8 = Company.builder().name("super-pharm").email("superpharm@gmail.com").password("1234").build();
        Company company9 = Company.builder().name("aroma").email("aroma@gmail.com").password("1234").build();
        Company company10 = Company.builder().name("mcdonalds").email("mcdonalds@gmail.com").password("1234").build();
        Company company11 = Company.builder().name("burgerking").email("burgerking@gmail.com").password("1234").build();
        List<Company> companies = new ArrayList<>();
        companies.addAll(List.of(company7, company8, company9, company10, company11));
        companyRepository.saveAll(companies);
        System.out.println(adminService.getAllCompanies());
        System.out.println("@@@@@@@@@@@@@ checking get single company method @@@@@@@@@@@@@@@@");
        System.out.println(adminService.getSingleCompany(2));
        System.out.println("@@@@@@@@@@@@@ checking add customer method @@@@@@@@@@@@@@@@");
        Customer costumer1 = Customer.builder().firstName("romi").lastName("mip").email("romimip@gmail.com").password("1234").build();
        Customer costumer2 = Customer.builder().firstName("roy").lastName("mop").email("roymop@gmail.com").password("1234").id(1).build();
        Customer costumer3 = Customer.builder().firstName("tali").lastName("mer").email("romimip@gmail.com").password("1234").build();
        Customer costumer4 = Customer.builder().firstName("ron").lastName("eliaho").email("roneliho@gmail.com").password("1234").id(5).build();

        adminService.addCustomer(costumer1);

        System.out.println("trying to add a customer with the same id");
        try {
            adminService.addCustomer(costumer2);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("trying to add a customer with the same email");
        try {
            adminService.addCustomer(costumer3);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        System.out.println("@@@@@@@@@@@@@ checking update customer method @@@@@@@@@@@@@@@@");
        System.out.println("trying to update unexisting customer ");

        try {
            adminService.updateCustomer(8, costumer4);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("@@@@@@@@@@@@@ checking delete company method @@@@@@@@@@@@@@@@");
        adminService.deleteCustomer(1);


        System.out.println("@@@@@@@@@@@@@ checking get single customer method @@@@@@@@@@@@@@@@");
        Customer costumer5 = Customer.builder().firstName("tal").lastName("cohen").email("talcohen@gmail.com").password("1234").build();
        Customer costumer6 = Customer.builder().firstName("tom").lastName("bri").email("tombri@gmail.com").password("1234").build();
        Customer costumer7 = Customer.builder().firstName("yarden").lastName("jim").email("yardenjim@gmail.com").password("1234").build();
        Customer costumer8 = Customer.builder().firstName("kyle").lastName("mor").email("kylemor@gmail.com").password("1234").build();
        List<Customer> customers = new ArrayList<>();
        customers.addAll(List.of(costumer5, costumer6, costumer7, costumer8));
        customerRepository.saveAll(customers);
        System.out.println(adminService.getSingleCustomer(3));
        System.out.println("@@@@@@@@@@@@@ checking get all customers method @@@@@@@@@@@@@@@@");
        System.out.println(adminService.getAllCustomers());
    }

}