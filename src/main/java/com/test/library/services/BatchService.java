package com.test.library.services;

import com.github.javafaker.Faker;
import com.test.library.model.Book;
import com.test.library.model.Person;
import com.test.library.repositories.BooksRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@Transactional
public class BatchService {
    final private BooksRepositories booksRepositories;
    final private PeopleService peopleService;

    public BatchService(BooksRepositories booksRepositories, PeopleService peopleService) {
        this.booksRepositories = booksRepositories;
        this.peopleService = peopleService;
    }

    public void createPerson(int atm) {
        Faker faker = new Faker(new Locale("ru", "RU"));
        for (int i = 0; i < atm; i++) {
            Person person = new Person(faker.name().fullName(), faker.number().numberBetween(1900, 2023));
            peopleService.save(person);
        }
    }

    public void createBook(int atm){
        Faker faker = new Faker(new Locale("ru", "RU"));
        for(int i = 0; i < atm; i++){
            Book book = new Book(faker.book().title(), faker.book().author(), faker.number().numberBetween(1900, 2023));
            booksRepositories.save(book);
        }
    }



}
