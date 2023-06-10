package org.jb.project2.repos;

import org.jb.project2.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByEmail(String email);
    @Query(nativeQuery = true,value = "select id from customer where email = ?")
    int customerEmailToId(String email);
}
