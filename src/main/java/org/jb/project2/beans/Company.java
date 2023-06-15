package org.jb.project2.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false,length = 40)
    private String name;
    @Column(nullable = false,length = 40)

    private String email;
    @Column(nullable = false,length = 40)

    private String password;
    @OneToMany(mappedBy = "company",cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private List<Coupon> coupons;

}
