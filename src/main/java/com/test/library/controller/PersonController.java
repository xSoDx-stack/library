package com.test.library.controller;

import com.test.library.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class PersonController {
    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    public void disableFavicon(){
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "person/people";
    }

    @GetMapping("/{id}")
    public String edit(Model model, @PathVariable int id){
        if(personDAO.showPerson(id) != null){
            model.addAttribute("people", personDAO.showPerson(id));
            return "person/peopleShow";
        }
        return  "redirect:/";
    }

}
