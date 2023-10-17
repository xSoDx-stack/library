package com.test.library.controller;

import com.test.library.dao.BatchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/batch")

public class BatchController {
    private final BatchDao batchDao;

    @Autowired
    public BatchController(BatchDao batchDao){
        this.batchDao = batchDao;
    }

    @RequestMapping("/person")
    public String batchPerson(@RequestParam(value = "atm") int atm){
        if (atm > 0){
            batchDao.createPerson(atm);
            return "redirect:/";
        }
        batchDao.createPerson(1);
        return "redirect:/";
    }

    @RequestMapping("/books")
    public String batchBook(@RequestParam(value = "atm") int atm){
        if (atm > 0){
            batchDao.createBook(atm);
            return "redirect:/books";
        }
        batchDao.createBook(1);
        return "redirect:/books";
    }




}
