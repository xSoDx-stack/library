package com.test.library.controller;

import com.test.library.dao.BookDAO;
import com.test.library.dao.PersonDAO;
import com.test.library.model.Book;
import com.test.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class PersonController {
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PersonController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    public void disableFavicon() {
    }

    @GetMapping()
    public String index(Model model, @ModelAttribute("people") Person person){
        model.addAttribute("person", personDAO.index());
        return "person/people";
    }

    @GetMapping("/{id}/show")
    public String show(Model model, @PathVariable int id,
                       @ModelAttribute("books") Book book) {
        model.addAttribute("people", personDAO.index(id));
        model.addAttribute("bookOwner", personDAO.ownerBook(id));
        model.addAttribute("bookNotOwner", personDAO.bookNotOwner());
        return "person/peopleShow";
    }

    @PostMapping("/{id}/edit")
    public String edit(@ModelAttribute("people") Person people,
                       @PathVariable int id){
        personDAO.edit(id, people);
        System.out.println("Пользователь успешно изменён");
        return "person/peopleShow";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/";
    }

    @PostMapping("/people/new")
    public String save(@ModelAttribute("people") Person people){
        personDAO.save(people);
        System.out.println("Новый пользователь создан");
        return "redirect:/";
    }

    @PatchMapping("/{id}/setBook")
    public String setBook(@ModelAttribute("books")Book book,
                          @PathVariable("id") int id){
        bookDAO.setOwner(book.getId(), id);
        return "redirect:/{id}/show";
    }
    @DeleteMapping("/{id}/delete/book/{person_id}")
    public String deleteOwner(@PathVariable("id") int id,
                              @PathVariable("person_id") int person_id){
        bookDAO.deleteOwner(id);
        return "redirect:/{person_id}/show";
    }

}



