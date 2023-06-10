package org.jb.project2.utils;

import org.jb.project2.beans.Company;
import org.jb.project2.repos.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class FactoryMethods  {
    @Autowired
    private static CompanyRepository companyRepository;

    public static void initDataBaseWithCompanies() {

        Company company7 = Company.builder().name("pepsi").email("pepsi@gmail.com").password("1234").build();
        Company company8 = Company.builder().name("super-pharm").email("superpharm@gmail.com").password("1234").build();
        Company company9 = Company.builder().name("aroma").email("aroma@gmail.com").password("1234").build();
        Company company10 = Company.builder().name("mcdonalds").email("mcdonalds@gmail.com").password("1234").build();
        Company company11 = Company.builder().name("burgerking").email("burgerking@gmail.com").password("1234").build();
        List<Company> companies = new ArrayList<>();
        companies.addAll(List.of(company7, company8, company9, company10, company11));
        companyRepository.saveAll(companies);
    }
}
