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
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService){
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    public void disableFavicon() {
    }

    @GetMapping()
    public String index(Model model,
                        @ModelAttribute("book") Book books){
        model.addAttribute("books", bookService.findAll());

        return "book/book";
    }

    @GetMapping("/{id}/show")
    public String show(Model model, @PathVariable int id,
                       @ModelAttribute("people") Person people) {
        model.addAttribute("person", peopleService.findAll());
        model.addAttribute("book", bookService.findOne(id));
        return "book/bookShow";
    }

    @PostMapping("/{id}/edit")
    public String edit(@ModelAttribute("book") Book book,
                       @PathVariable int id){
        bookService.update(id, book);
        return "book/bookShow";
    }

    @PostMapping("/new")
    public String save(@ModelAttribute("people") Book book){
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id){
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/delete/owner/{patch}")
    public String deleteOwner(@PathVariable ("id") int id,
                              @PathVariable("patch") int patch){
        bookService.deleteOwner(id);
        if(patch!=1){
            return "redirect:/books/{id}/show";
        }
        return "redirect:/books";
    }

    @PatchMapping("/{id}/setOwner")
    public String setOwner(@PathVariable("id") int id,
                           @ModelAttribute("people") Person people ){
        peopleService.setOwner(id, people.getId());
        return "redirect:/books/{id}/show";
    }

}
