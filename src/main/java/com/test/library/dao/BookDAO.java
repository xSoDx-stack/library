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
public class BookDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Book> index() {
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery("select p from Book p", Book.class).getResultList();
    }
    @Transactional(readOnly = true)
    public Book index(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    @Transactional
    public void edit(int id, Book book){
        Session session = sessionFactory.getCurrentSession();
        Book getBook = session.get(Book.class, id);
        getBook.setName(book.getName());
        getBook.setAuthor(book.getAuthor());
        getBook.setYear(book.getYear());
        session.merge(getBook);
    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book person = session.get(Book.class, id);
        session.remove(person);
    }

    @Transactional
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }

    @Transactional
    public void setOwner(int book_id, int person_id){
        Session session = sessionFactory.getCurrentSession();
       Book book = session.get(Book.class, book_id);
       Person person = session.get(Person.class, person_id);
       book.setPerson(person);
        System.out.println("SetOwner выполнился");
       session.merge(book);
    }

    @Transactional
    public void deleteOwner(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        book.setPerson(null);
        session.merge(book);
    }
}
