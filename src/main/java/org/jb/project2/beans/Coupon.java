package org.jb.project2.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Company company;
    private Category category;
    @Column(nullable = false, length = 40)

    private String title;
    @Column(nullable = false, length = 80)

    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    @Column(nullable = false, length = 40)

    private String image;



}
