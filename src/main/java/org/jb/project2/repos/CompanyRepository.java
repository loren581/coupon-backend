package org.jb.project2.repos;

import org.jb.project2.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByName(String name);

    boolean existsByEmail(String email);

@Query(nativeQuery = true,value = "select id from company where email = ?")
     int companyEmailToId(String email);


}
