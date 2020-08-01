package com.spring.jpa.entitygraph.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "calls")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "duration_seconds")
    private int duration;
    @Column(name = "call_date")
    private LocalDateTime date;
    @Version
    @Column(name = "version")
    private Integer version;
    @JoinColumn(name = "phone_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Phone phone;
}
