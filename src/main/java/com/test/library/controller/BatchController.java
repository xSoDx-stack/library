package com.test.library.controller;

import com.test.library.services.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/batch")

public class BatchController {
    private final BatchService batchService;

    @Autowired
    public BatchController(BatchService batchService){
        this.batchService = batchService;
    }

    @RequestMapping("/person")
    public String batchPerson(@ModelAttribute("atm") int atm){
        if (atm > 0){
            batchService.createPerson(atm);
            return "redirect:/";
        }
        batchService.createPerson(1);
        return "redirect:/";
    }

    @RequestMapping("/books")
    public String batchBook(@ModelAttribute("atm") int atm){
        if (atm > 0){
            batchService.createBook(atm);
            return "redirect:/books";
        }
        batchService.createBook(1);
        return "redirect:/books";
    }




}
