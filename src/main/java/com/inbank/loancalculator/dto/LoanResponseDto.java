package com.inbank.loancalculator.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoanResponseDto {
    private Integer approvedLoanPeriod;
    private Integer approvedLoanAmount;
}
