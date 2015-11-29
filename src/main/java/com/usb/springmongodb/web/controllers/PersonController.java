package com.usb.springmongodb.web.controllers;

import com.usb.springmongodb.dao.PersonDao;
import com.usb.springmongodb.models.Person;
import com.usb.springmongodb.web.common.ValidationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@Component
@Controller
@RequestMapping(value = "/model/people")
public class PersonController {

    Logger log = LoggerFactory.getLogger(PersonController.class);

    private PersonDao personDao;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Person> list(){
        log.info("Person listed");
        return personDao.list();
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.PUT,  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ValidationResponse update(@Valid @RequestBody Person person, BindingResult bindingResult){
        ValidationResponse response = new ValidationResponse(bindingResult);
        if(!bindingResult.hasErrors()){
            personDao.update(person);
            log.info("Person Updated"+person.toString());
        }
        response.setEntity(person);
        return response;
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ValidationResponse delete(@PathVariable String id){
        ValidationResponse response = new ValidationResponse();
        Person person = personDao.findById(id);
        personDao.delete(person);
        log.info("Person deleted"+person.toString());
        return response;
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ValidationResponse save(@Valid @RequestBody Person person, BindingResult bindingResult){
        ValidationResponse response = new ValidationResponse(bindingResult);
        if(!bindingResult.hasErrors()){
            personDao.insert(person);
            log.info("Person insrted"+person.toString());
        }
        response.setEntity(person);
        return response;
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
}
