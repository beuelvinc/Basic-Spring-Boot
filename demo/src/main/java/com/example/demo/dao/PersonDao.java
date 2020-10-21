package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    String insertPerson(UUID id, Person person);
     List<Person> getAll();
    String updatePerson(UUID id,Person person);
    String deletePersonByID(UUID id);

    Optional<Person> selectByID(UUID id);

    default String insertPerson(Person person){
        UUID id=UUID.randomUUID();
        return insertPerson(id,person);
    }

}
