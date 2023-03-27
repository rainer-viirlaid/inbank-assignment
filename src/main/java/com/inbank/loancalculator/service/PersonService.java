package com.inbank.loancalculator.service;

import com.inbank.loancalculator.entity.Person;
import com.inbank.loancalculator.exception.PersonNotFoundException;
import com.inbank.loancalculator.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public Person getPersonEntity(String personalCode) {
        Optional<Person> personOptional = personRepository.findByPersonalCode(personalCode);
        personOptional.orElseThrow(() -> {throw new PersonNotFoundException(personalCode);});
        return personOptional.get();
    }

}
