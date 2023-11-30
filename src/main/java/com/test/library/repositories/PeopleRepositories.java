package com.test.library.repositories;

import com.test.library.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepositories extends JpaRepository<Person,Integer> {

   @Query("select p from Person p left join fetch p.books")
   List<Person> findAllWithBooks();
}
