package com.inbank.loancalculator.repository;

import com.inbank.loancalculator.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByPersonalCode(String personalCode);
}
