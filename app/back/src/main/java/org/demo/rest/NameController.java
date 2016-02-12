package org.demo.rest;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.demo.domain.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class NameController {

    @RequestMapping("/name")
    @HystrixCommand
    public Person index() {

        Person person = new Person();
        person.setFirstname("Marc");
        person.setLastname("Dupont");
        person.setBirthdate(new Date());

        return person;
    }

}