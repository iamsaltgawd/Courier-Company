package com.bgmnn.laidatlamluat.controller;

import com.bgmnn.laidatlamluat.model.Angajat;
import com.bgmnn.laidatlamluat.model.Sediu;
import com.bgmnn.laidatlamluat.service.AngajatService;
import com.bgmnn.laidatlamluat.service.SediuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/angajati")
public class AngajatController {

    @Autowired
    private AngajatService angajatService;

    @Autowired
    private SediuService sediuService;

    @GetMapping
    public String listAngajati(Model model) {
        List<Map<String, Object>> angajati = angajatService.getAllAngajatiWithSediu();
        model.addAttribute("title", "Angajati");
        model.addAttribute("angajati", angajati);
        model.addAttribute("activePage", "angajati");
        return "angajati/index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        List<Sediu> sedii = sediuService.getAllSedii();
        model.addAttribute("sedii", sedii);
        model.addAttribute("angajat", new Angajat());
        model.addAttribute("title", "Adauga angajat");
        return "angajati/add";
    }

    @PostMapping("/add")
    public String addAngajat(@ModelAttribute Angajat angajat, Model model) {
        try {
            angajatService.addAngajat(angajat);
            return "redirect:/angajati";
        } catch (IllegalArgumentException e) {
            List<Sediu> sedii = sediuService.getAllSedii();
            model.addAttribute("sedii", sedii);
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("angajat", angajat);
            return "angajati/add";
        }
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Angajat angajat = angajatService.getAngajatById(id);
        List<Sediu> sedii = sediuService.getAllSedii();
        model.addAttribute("sedii", sedii);
        model.addAttribute("angajat", angajat);
        model.addAttribute("title", "Editeaza angajat");
        return "angajati/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateAngajat(@PathVariable int id, @ModelAttribute Angajat angajat, Model model) {
        try {
            angajat.setAngajat_ID(id);
            angajatService.updateAngajat(angajat);
            return "redirect:/angajati";
        } catch (IllegalArgumentException e) {
            List<Sediu> sedii = sediuService.getAllSedii();
            model.addAttribute("sedii", sedii);
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("angajat", angajat);
            return "angajati/edit";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteAngajat(@PathVariable int id) {
        angajatService.deleteAngajat(id);
        return "redirect:/angajati";
    }


}