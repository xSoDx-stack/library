package com.test.library.controller;

import com.test.library.model.Book;
import com.test.library.model.Person;
import com.test.library.services.BookService;
import com.test.library.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class PersonController {
    private final PeopleService peopleService;
    private final BookService bookService;

    @Autowired
    public PersonController(PeopleService peopleService, BookService bookService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    public void disableFavicon() {
    }

    @GetMapping()
    public String index(Model model, @ModelAttribute("people") Person person){
        model.addAttribute("person", peopleService.findAll());
        return "person/people";
    }

    @GetMapping("/{id}/show")
    public String show(Model model, @PathVariable int id,
                       @ModelAttribute("books") Book book) {
        model.addAttribute("people", peopleService.findOne(id));
        model.addAttribute("bookOwner", bookService.findByOwner(id));
        model.addAttribute("bookNotOwner", bookService.findByOwner(null));
        return "person/peopleShow";
    }

    @PostMapping("/{id}/edit")
    public String edit(@ModelAttribute("people") Person people,
                       @PathVariable int id){
        peopleService.update(id, people);
        return "redirect:/{id}/show";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/people/new")
    public String save(@ModelAttribute("people") Person people){
        peopleService.save(people);
        return "redirect:/";
    }

    @PatchMapping("/{id}/setBook")
    public String setBook(@ModelAttribute("books")Book book,
                          @PathVariable("id") int id){
        peopleService.setOwner(book.getId(), id);
        return "redirect:/{id}/show";
    }
    @DeleteMapping("/{id}/delete/book/{person_id}")
    public String deleteOwner(@PathVariable("id") int id,
                              @PathVariable("person_id") int person_id){
        bookService.deleteOwner(id);
        return "redirect:/{person_id}/show";
    }

}



