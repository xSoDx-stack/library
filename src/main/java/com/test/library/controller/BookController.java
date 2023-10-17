package com.test.library.controller;

import com.test.library.dao.BookDAO;
import com.test.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    public void disableFavicon() {
    }

    @GetMapping()
    public String index(Model model, @ModelAttribute("book") Book books){
        model.addAttribute("books", bookDAO.index());
        return "book/book";
    }

    @GetMapping("/{id}/show")
    public String show(Model model, @PathVariable int id) {
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

    @PostMapping("/new")
    public String save(@ModelAttribute("people") Book book){
        bookDAO.save(book);
        System.out.println("Новая книга создана");
        return "redirect:/books";
    }

}
