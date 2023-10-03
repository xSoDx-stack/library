package com.test.library.dao;

import com.github.javafaker.Faker;
import com.test.library.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Controller
public class BatchDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public BatchDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void createPerson(int atm){
        Session session = sessionFactory.getCurrentSession();
        Faker faker = new Faker(new Locale("ru","RU"));
        for(int i = 0; i < atm; i++){
            Person person = new Person(faker.name().fullName(),faker.number().numberBetween(1900,2023));
            session.persist(person);
        }

    }




}
