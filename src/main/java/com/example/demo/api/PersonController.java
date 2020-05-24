package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id).orElse(null);
    }

    @PutMapping(path = "{id}")
    public String updatePersonById(@PathVariable("id") UUID id, @RequestBody Person person) {
        if(personService.updatePersonInfoById(id,person)>0)
            return "SUCCESSFUL";
        return "FAILED";
    }


    @DeleteMapping(path = "{id}")
    public String deletePersonById(@PathVariable("id") UUID id) {
        if(personService.deletePersonById(id)>0)
            return "SUCCESSFUL";
        return "FAILED";
    }

}
