package com.inbank.loancalculator.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String personalCode;
    @ManyToOne
    @JoinColumn(nullable = false)
    private CreditSegment creditSegment;
}
