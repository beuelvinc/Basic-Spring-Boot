package com.example.demo.dao;
import com.example.demo.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PersonData implements  PersonDao {
    private  static List<Person> DB=new  ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();
    public PersonData() {
        UUID uuid=UUID.randomUUID();
        Person person=new Person(uuid,"Umid");
        DB.add(person);
    }

    @Override
    public Optional<Person> selectByID(UUID id) {
        return DB.stream().filter(person1 -> person1.getId().equals(id)).findFirst();
    }

    @Override
    public String updatePerson(UUID id,Person person) {
        Optional<Person> personOld=selectByID(id);
         return personOld.map(p->{

             int indexOfPerson=DB.indexOf(personOld.get());

            if  (indexOfPerson>=0){
                DB.set(indexOfPerson,person);
                ObjectNode response = mapper.createObjectNode();
                response.put("status",200);
                response.put("result","updated");
                String json = response.toString();
                return  json;
            }
            else
            {
                ObjectNode response = mapper.createObjectNode();
                response.put("status",200);
                response.put("result","Could not updated");
                String json = response.toString();
                return  json;
            }
        }).orElse("0");
    }

    @Override
    public String insertPerson(UUID id, Person person) {
        DB.add(new Person(id,person.getName()));
        ObjectNode response = mapper.createObjectNode();
        response.put("status",200);
        response.put("result","added");
        String json = response.toString();
        return  json;
    }

    @Override
    public  List<Person> getAll() {
        return DB;
    }

    @Override
    public String deletePersonByID(UUID id) {
        Optional<Person> person= DB.stream().filter(person1 -> person1.getId().equals(id)).findFirst();
        if (person.isPresent()){
            DB.remove(person.get());
            ObjectNode response = mapper.createObjectNode();
            response.put("status",200);
            response.put("result","deleted");
            String json = response.toString();
            return  json;        }
        else{
            ObjectNode response = mapper.createObjectNode();
            response.put("status",200);
            response.put("result","Could not deleted");
            String json = response.toString();
            return json;
        }
    }
}
