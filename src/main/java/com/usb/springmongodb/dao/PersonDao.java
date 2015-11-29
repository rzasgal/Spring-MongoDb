package com.usb.springmongodb.dao;

import com.usb.springmongodb.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static String COLLECTION = "Person";
    private MongoTemplate mongoTemplate;

    @Autowired
    public PersonDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Person findById(String id){
        Query query = new Query();
        List<String> idList = new ArrayList<String>();
        idList.add(id);
        query.addCriteria(Criteria.where("id").in(idList));
        Person person = mongoTemplate.findOne(query, Person.class, COLLECTION);
        return person;
    }

    public Person delete(Person person){
        mongoTemplate.remove(person, COLLECTION);
        return person;
    }

    public Person update(Person person){
        mongoTemplate.save(person, COLLECTION);
        return person;
    }

    public Person insert(Person person){
        mongoTemplate.insert(person, COLLECTION);
        return person;
    }

    public List<Person> list(){
        return mongoTemplate.findAll(Person.class, COLLECTION);
    }
}
