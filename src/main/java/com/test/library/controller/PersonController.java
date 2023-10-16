package com.test.library.controller;

import com.test.library.dao.PersonDAO;
import com.test.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class PersonController {
    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
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
    public String show(Model model, @PathVariable int id) {
        model.addAttribute("people", personDAO.index(id));
        return "person/peopleShow";
    }

    @PostMapping("/{id}/edit")
    public String edit(@ModelAttribute("people") Person people,
                       @PathVariable int id){
        personDAO.editPerson(id, people);
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

}



