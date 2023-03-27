package com.inbank.loancalculator.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CreditSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer creditModifier;
}
