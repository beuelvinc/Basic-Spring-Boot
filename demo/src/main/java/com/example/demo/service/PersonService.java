package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class PersonService {
    private static PersonDao personDao;

    @Autowired
    public PersonService(PersonDao personDao) {
        this.personDao=personDao;
    }

    public List<Person> getAll(){
        return personDao.getAll();
    }

    public  String updatePerson (UUID id,Person newPerson){
        return personDao.updatePerson(id,newPerson);
    }

    public Optional<Person> selectByID (UUID id){
        return personDao.selectByID(id);
    }


    public String  deletePersonByID (UUID id){

        return personDao.deletePersonByID(id);
    }

    public String  insertPerson (Person person){

        return personDao.insertPerson(person);
    }

}