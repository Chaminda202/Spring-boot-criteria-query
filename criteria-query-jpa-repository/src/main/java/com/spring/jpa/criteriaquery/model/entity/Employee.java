package com.spring.jpa.criteriaquery.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "dob")
    private LocalDate dob;
    @Column(name = "salary")
    private BigDecimal salary;
    @Version
    @Column(name = "version")
    private Integer version;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Phone> phones; // = new ArrayList<>();
    public Employee(String name, String email, BigDecimal salary) {
        this.name = name;
        this.email = email;
        this.salary = salary;
    }
}
