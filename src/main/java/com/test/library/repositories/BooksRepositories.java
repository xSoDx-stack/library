package com.test.library.repositories;

import com.test.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepositories extends JpaRepository<Book, Integer> {
    List<Book> findAllByPersonId(Integer id);

    @Query("select b from Book b left join fetch b.person")
    Page<List<Book>> findByAllWithPerson(Pageable pageable);
}