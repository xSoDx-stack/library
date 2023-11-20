package com.test.library.services;

import com.test.library.model.Book;
import com.test.library.repositories.BooksRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BooksRepositories booksRepositories;

    @Autowired
    public BookService(BooksRepositories booksRepositories){
        this.booksRepositories = booksRepositories;
    }

    public List<Book> findAll(){
        return booksRepositories.findAll();
    }

    public Book findOne(int id){
        Optional<Book> foundPerson = booksRepositories.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Book book){
        booksRepositories.save(book);
    }

    @Transactional
    public void update(int id, Book updateBook){
        updateBook.setId(id);
        booksRepositories.save(updateBook);
    }

    @Transactional
    public void delete(int id){
        booksRepositories.deleteById(id);
    }

    public List<Book> findByOwner(Integer id){
        return booksRepositories.findAllByPersonId(id);
    }

    @Transactional
    public void deleteOwner(Integer id){
        booksRepositories.findById(id).ifPresent(book ->{
            book.setPerson(null);
            booksRepositories.save(book);
        });
    }
}
