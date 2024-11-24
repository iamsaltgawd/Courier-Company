package com.bgmnn.laidatlamluat.controller;

import com.bgmnn.laidatlamluat.model.Angajat;
import com.bgmnn.laidatlamluat.model.Sediu;
import com.bgmnn.laidatlamluat.service.AngajatService;
import com.bgmnn.laidatlamluat.service.SediuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String listAngajati(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               Model model, @ModelAttribute("successMessage") String successMessage,
                               @ModelAttribute("errorMessage") String errorMessage) {
        List<Map<String, Object>> angajati = angajatService.getAngajatiWithPagination(page, size);
        List<Sediu> sedii = sediuService.getAllSedii();
        int totalItems = angajatService.countAngajati();

        int startItem = page * size + 1;
        int endItem = Math.min((page + 1) * size, totalItems);

        model.addAttribute("title", "Angajati");
        model.addAttribute("activePage", "angajati");
        model.addAttribute("angajati", angajati);
        model.addAttribute("sedii", sedii);
        model.addAttribute("currentPage", page);
        model.addAttribute("itemsPerPage", size);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("startItem", startItem);
        model.addAttribute("endItem", endItem);

        if (successMessage != null && !successMessage.isEmpty()) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null && !errorMessage.isEmpty()) {
            model.addAttribute("errorMessage", errorMessage);
        }

        return "angajati/index";
    }

    @PostMapping("/add")
    public String addAngajat(@ModelAttribute Angajat angajat, RedirectAttributes redirectAttributes) {
        try {
            angajatService.addAngajat(angajat);
            redirectAttributes.addFlashAttribute("successMessage", "Angajat adaugat cu succes!");
            return "redirect:/angajati";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/angajati";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateAngajat(@PathVariable int id, @ModelAttribute Angajat angajat, RedirectAttributes redirectAttributes) {
        try {
            angajat.setAngajat_ID(id);
            angajatService.updateAngajat(angajat);
            redirectAttributes.addFlashAttribute("successMessage", "Angajat actualizat cu succes!");
            return "redirect:/angajati";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/angajati";
        }
    }


    @GetMapping("/delete/{id}")
    public String deleteAngajat(@PathVariable int id, RedirectAttributes redirectAttributes) {
        angajatService.deleteAngajat(id);
        redirectAttributes.addFlashAttribute("successMessage", "Angajat sters cu succes!");
        return "redirect:/angajati";
    }

    // add.html
    /*
    @GetMapping("/add")
    public String showAddForm(Model model) {
        List<Sediu> sedii = sediuService.getAllSedii();
        model.addAttribute("sedii", sedii);
        model.addAttribute("angajat", new Angajat());
        model.addAttribute("title", "Adauga angajat");
        return "angajati/add";
    }
    */

    // edit.html
    /*
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Angajat angajat = angajatService.getAngajatById(id);
        List<Sediu> sedii = sediuService.getAllSedii();
        model.addAttribute("sedii", sedii);
        model.addAttribute("angajat", angajat);
        model.addAttribute("title", "Editeaza angajat");
        return "angajati/edit";
    }
    */
}
