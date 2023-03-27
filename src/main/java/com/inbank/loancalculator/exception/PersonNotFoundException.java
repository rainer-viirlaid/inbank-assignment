package com.inbank.loancalculator.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String personalCode) {
        super("Person with code '" + personalCode + "' not found.");
    }
}
