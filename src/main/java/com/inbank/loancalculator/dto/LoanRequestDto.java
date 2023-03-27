package com.inbank.loancalculator.dto;

import lombok.Data;

@Data
public class LoanRequestDto {
    private String personalCode;
    private Integer loanAmount;
    private Integer loanPeriod;
}
