package com.test.library.dao;

import com.test.library.model.Book;
import com.test.library.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory  = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery("from Person", Person.class).getResultList();
    }
    @Transactional(readOnly = true)
    public Person index(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional
    public void edit(int id, Person people){
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        person.setFullName(people.getFullName());
        person.setYear(people.getYear());
        session.merge(person);
    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.remove(person);
    }

    @Transactional
    public void save(Person people) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(people);
    }

    @Transactional
    public List<Book> ownerBook(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Book where person.id = :id", Book.class).setParameter("id", id).getResultList();
    }

    @Transactional
    public List<Book> bookNotOwner(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Book where person.id = null", Book.class).getResultList();
    }
}
