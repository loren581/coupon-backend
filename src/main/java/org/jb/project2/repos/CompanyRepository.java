package org.jb.project2.repos;

import org.jb.project2.beans.Company;
import org.jb.project2.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByName(String name);

    boolean existsByEmail(String email);

    @Query(nativeQuery = true, value = "select id from company where email = ?")
    int companyEmailToId(String email);

    boolean existsByEmailAndPassword(String email, String password);


}
