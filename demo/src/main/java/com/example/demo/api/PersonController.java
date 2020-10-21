package com.example.demo.api;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/people")
@RestController
public class PersonController {
    private static PersonService personService;


    @Autowired
    public PersonController(PersonService personService) {
        this.personService=personService;
    }


    @GetMapping("/")
    public ModelAndView getAll(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("people", personService.getAll());
        return modelAndView;

    }

    @PostMapping
    public String insertPerson(@RequestBody Person person){
        return personService.insertPerson(person);
    }

    @PatchMapping(path = "{id}")
    public String updatePerson(@PathVariable("id") UUID id,@RequestBody Person person){
        return personService.updatePerson(id,person);
    }

    @GetMapping(path = "{id}")
    public Optional<Person> selectByID(@PathVariable("id") UUID id){
        return personService.selectByID(id);
    }

    @DeleteMapping(path = "{id}")
    public String deletePersonByID(@PathVariable("id") UUID id){
        return personService.deletePersonByID(id);
    }



}
