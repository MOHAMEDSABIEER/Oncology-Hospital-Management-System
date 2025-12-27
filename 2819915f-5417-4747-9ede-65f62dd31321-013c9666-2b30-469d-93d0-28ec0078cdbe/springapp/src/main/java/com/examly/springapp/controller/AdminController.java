package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")  
public class AdminController {

    @RequestMapping("/home")   
    public String admin() {
        return "Admin";
    }
}
