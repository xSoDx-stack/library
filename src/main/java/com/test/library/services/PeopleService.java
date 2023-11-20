package com.test.library.services;

import com.test.library.model.Person;
import com.test.library.repositories.BooksRepositories;
import com.test.library.repositories.PeopleRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepositories peopleRepositories;
    private final BooksRepositories booksRepositories;

    @Autowired
    public PeopleService(PeopleRepositories peopleRepositories, BooksRepositories booksRepositories){
        this.peopleRepositories = peopleRepositories;
        this.booksRepositories = booksRepositories;
    }

    public List<Person> findAll(){
        return peopleRepositories.findAll();
    }
    public Person findOne(int id){
        Optional<Person> foundPerson = peopleRepositories.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person){
        peopleRepositories.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson){
        updatePerson.setId(id);
        peopleRepositories.save(updatePerson);
    }

    @Transactional
    public void delete(int id){
       peopleRepositories.deleteById(id);
    }

    @Transactional
    public void  setOwner(int book_id, int people_id){
        Person person = peopleRepositories.findById(people_id).orElse(null);
        booksRepositories.findById(book_id).ifPresent(book -> {
            book.setPerson(person);
            booksRepositories.save(book);
        });
    }





}
