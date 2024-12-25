package com.bgmnn.laidatlamluat.controller;

import com.bgmnn.laidatlamluat.model.Sediu;
import com.bgmnn.laidatlamluat.service.SediuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/sedii")
public class SediuController {

    @Autowired
    private SediuService sediuService;

    @GetMapping
    public String listSedii(Model model,
                            @ModelAttribute("successMessage") String successMessage,
                            @ModelAttribute("errorMessage") String errorMessage) {
        List<Sediu> sedii = sediuService.getAllSedii();
        model.addAttribute("title", "Lista Sedii");
        model.addAttribute("activePage", "sedii");
        model.addAttribute("sedii", sedii);
        model.addAttribute("successMessage", successMessage);
        model.addAttribute("errorMessage", errorMessage);
        return "sedii/index";
    }

    @PostMapping("/add")
    public String addSediu(@ModelAttribute Sediu sediu, RedirectAttributes redirectAttributes) {
        try {
            sediuService.addSediu(sediu);
            redirectAttributes.addFlashAttribute("successMessage", "Sediul a fost adaugat cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Eroare: " + e.getMessage());
        }
        return "redirect:/sedii";
    }

    @PostMapping("/edit/{id}")
    public String updateSediu(@PathVariable int id, @ModelAttribute Sediu sediu, RedirectAttributes redirectAttributes) {
        try {
            sediu.setSediu_ID(id);
            sediuService.updateSediu(sediu);
            redirectAttributes.addFlashAttribute("successMessage", "Sediul a fost actualizat cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Eroare: " + e.getMessage());
        }
        return "redirect:/sedii";
    }

    @GetMapping("/delete/{id}")
    public String deleteSediu(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            sediuService.deleteSediu(id);
            redirectAttributes.addFlashAttribute("successMessage", "Sediul a fost sters cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Eroare: " + e.getMessage());
        }
        return "redirect:/sedii";
    }
}
