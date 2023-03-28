# Frontend

This repo contains the backend program. The frontend application can be found here: https://github.com/rainer-viirlaid/inbank-assignment-frontend

# Endpoint

The endpoint for requesting a loan offer is:  POST `/loan`

The request body should have the following format:
```json
{
  "personalCode": "Personal code",
  "loanAmount": "Desired amount (integer)",
  "loanPeriod": "Desired period (integer)"
}
```

For example:
```json
{
  "personalCode": "49002010987",
  "loanAmount": 9000,
  "loanPeriod": 10
}
```

The response will have the following format:
```json
{
  "approvedLoanPeriod": "Approved loan period (integer)",
  "approvedLoanAmount": "Approved amount (integer)"
}
```

For example:
```json
{
  "approvedLoanPeriod": 30,
  "approvedLoanAmount": 9000
}
```

If the loan was not approved, then both fields will be zero:
```json
{
  "approvedLoanPeriod": 0,
  "approvedLoanAmount": 0
}
```


# Loan calculation logic

Evaluation of the loan request is done in the `calculateLoan` method of the `LoanCalculationService` class.

The process is as follows:
1. The loan parameters are clamped to acceptable ranges and the person's credit modifier is found.
2. A credit score is calculated using the best circumstances. If it is less than 1, then any loan is denied.
3. The maximum allowed loan amount is calculated for different periods, starting from the requested period.
The maximum amount is found by setting the credit score to 1 and deriving the loan amount from the original formula.
$$1 = \frac{creditModifier * loanPeriod}{loanAmount} => maxLoanAmount = creditModifier * loanPeriod$$
If the maximum amount is larger or equal to the requested amount, then it is approved. Otherwise, the loan period
is increased. In other words, the system tries to find the minimum period required for the requested amount and then the
maximum amount that can be borrowed in that period. This ensures that the client gets an offer that matches their needs as closely as possible.
4. If the requested amount cannot be approved (even over the longest allowed period), then the largest allowed loan is offered. 

The application uses a database to store people and credit segments. Each person has a unique personal code and a reference
to a credit segment. Each credit segment has a credit modifier number (0 for 'debt'). Additional CRUD endpoints could be
created to add and manage people and credit segments.

