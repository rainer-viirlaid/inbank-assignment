package com.inbank.loancalculator.controller;

import com.inbank.loancalculator.dto.LoanRequestDto;
import com.inbank.loancalculator.dto.LoanResponseDto;
import com.inbank.loancalculator.service.LoanCalculationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class LoanCalculationController {

    private final LoanCalculationService loanCalculationService;

    @PostMapping("/loan")
    public LoanResponseDto requestLoan(@RequestBody LoanRequestDto requestDto) {
        return loanCalculationService.calculateLoan(requestDto);
    }
}
