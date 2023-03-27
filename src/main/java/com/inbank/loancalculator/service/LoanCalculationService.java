package com.inbank.loancalculator.service;

import com.inbank.loancalculator.dto.LoanRequestDto;
import com.inbank.loancalculator.dto.LoanResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoanCalculationService {
    private static final int MINIMUM_LOAN_AMOUNT = 2000;
    private static final int MAXIMUM_LOAN_AMOUNT = 10000;
    private static final int MINIMUM_LOAN_PERIOD = 12;
    private static final int MAXIMUM_LOAN_PERIOD = 60;

    private final PersonService personService;

    public LoanResponseDto calculateLoan(LoanRequestDto requestDto) {
        // Clamp loan request parameters
        int requestedPeriod = Math.max(MINIMUM_LOAN_PERIOD, Math.min(MAXIMUM_LOAN_PERIOD, requestDto.getLoanPeriod()));
        int requestedAmount = Math.max(MINIMUM_LOAN_AMOUNT, Math.min(MAXIMUM_LOAN_AMOUNT, requestDto.getLoanAmount()));
        // Get person's credit modifier
        int creditModifier = personService.getPersonEntity(requestDto.getPersonalCode()).getCreditSegment().getCreditModifier();

        // Check if the person can have a loan at all
        if (1 > (double) creditModifier / (double) MINIMUM_LOAN_AMOUNT * (double) MAXIMUM_LOAN_PERIOD) return LoanResponseDto.builder()
                .approvedLoanAmount(0).approvedLoanPeriod(0).build();

        // Find nearest suitable period
        for (int period = requestedPeriod; period <= MAXIMUM_LOAN_PERIOD; period++) {
            // Find max loan amount for the period and return it if it's suitable
            int maxAmount = Math.min(creditModifier * period, MAXIMUM_LOAN_AMOUNT);
            if (maxAmount >= requestedAmount) return LoanResponseDto.builder()
                    .approvedLoanAmount(maxAmount).approvedLoanPeriod(period).build();
        }

        // Return maximum loan amount, even though it is not as much as was requested
        int maxAmount = Math.min(creditModifier * MAXIMUM_LOAN_PERIOD, MAXIMUM_LOAN_AMOUNT);
        return LoanResponseDto.builder().approvedLoanAmount(maxAmount).approvedLoanPeriod(MAXIMUM_LOAN_PERIOD).build();
    }

}
