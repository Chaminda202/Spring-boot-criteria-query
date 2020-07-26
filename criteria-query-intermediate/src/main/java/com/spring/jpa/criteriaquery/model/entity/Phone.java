package com.spring.jpa.criteriaquery.model.entity;

import com.spring.jpa.criteriaquery.enumeration.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "phone")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "phone_number")
    private String number;
    @Enumerated(EnumType.STRING)
    @Column(name = "phone_type")
    private PhoneType phoneType;
    @Version
    @Column(name = "version")
    private Integer version;
    @JoinColumn(name = "emp_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;
    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL)
    private List<Call> calls = new ArrayList<>();
}
