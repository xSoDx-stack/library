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
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO){
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    public void disableFavicon() {
    }

    @GetMapping()
    public String index(Model model,
                        @ModelAttribute("book") Book books){
        model.addAttribute("books", bookDAO.index());

        return "book/book";
    }

    @GetMapping("/{id}/show")
    public String show(Model model, @PathVariable int id,
                       @ModelAttribute("people") Person people) {
        model.addAttribute("person", personDAO.index());
        model.addAttribute("book", bookDAO.index(id));
        return "book/bookShow";
    }

    @PostMapping("/{id}/edit")
    public String edit(@ModelAttribute("book") Book book,
                       @PathVariable int id){
        bookDAO.edit(id, book);
        System.out.println("Книга успешно изменена");
        return "book/bookShow";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/delete/owner")
    public String deleteOwner(@PathVariable ("id") int id){
        bookDAO.deleteOwner(id);
        return "redirect:/books/{id}/show";
    }

    @PostMapping("/new")
    public String save(@ModelAttribute("people") Book book){
        bookDAO.save(book);
        System.out.println("Новая книга создана");
        return "redirect:/books";
    }

    @PatchMapping("/{id}/setOwner")
    public String setOwner(@PathVariable("id") int id,
                           @ModelAttribute("people") Person people ){
        bookDAO.setOwner(id, people.getId());
        return "redirect:/books/{id}/show";
    }

}
