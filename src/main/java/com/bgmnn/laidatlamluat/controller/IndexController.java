package com.bgmnn.laidatlamluat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Index Page");
        model.addAttribute("activePage", "home");
        return "index";
    }
}
