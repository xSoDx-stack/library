package com.test.library.controller;

import com.test.library.dao.PersonDAO;
import com.test.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "person/people";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable int id) {
        if (personDAO.showPerson(id) != null) {
            model.addAttribute("people", personDAO.showPerson(id));
            return "person/peopleShow";
        }
        return "redirect:/";
    }

    @RequestMapping("/{id}/edit")
    public String edit(@ModelAttribute("people") Person people,
                       BindingResult bindingResult,
                       @PathVariable("id") int id) {
        personDAO.editPerson(id, people);
    return "redirect:/";
    }
}



