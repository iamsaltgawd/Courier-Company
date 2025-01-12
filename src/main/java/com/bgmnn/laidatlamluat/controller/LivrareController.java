

/** Clasa pentru controllerul livrarilor
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.controller;

import com.bgmnn.laidatlamluat.model.Livrare;
import com.bgmnn.laidatlamluat.service.LivrareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/livrari")
public class LivrareController {

    @Autowired
    private LivrareService livrareService;

    @GetMapping
    public String listLivrari(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(required = false) Integer id,
                              @RequestParam(required = false) String data,
                              @RequestParam(required = false) String status,
                              @RequestParam(required = false) Integer comandaId,
                              @RequestParam(required = false) Integer angajatId,
                              @RequestParam(required = false) Integer vehiculId,
                              Model model,
                              @ModelAttribute("successMessage") String successMessage,
                              @ModelAttribute("errorMessage") String errorMessage) {

        List<Map<String, Object>> livrari = livrareService.filterLivrari(id, data, status, comandaId, angajatId, vehiculId, page, size);
        int totalItems = livrareService.countFilteredLivrari(id, data, status, comandaId, angajatId, vehiculId);

        int startItem = page * size + 1;
        int endItem = Math.min((page + 1) * size, totalItems);

        model.addAttribute("title", "Livrari");
        model.addAttribute("activePage", "livrari");
        model.addAttribute("livrari", livrari);
        model.addAttribute("currentPage", page);
        model.addAttribute("itemsPerPage", size);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("startItem", totalItems == 0 ? 0 : startItem);
        model.addAttribute("endItem", totalItems == 0 ? 0 : endItem);
        model.addAttribute("id", id);
        model.addAttribute("data", data);
        model.addAttribute("status", status);
        model.addAttribute("comandaId", comandaId);
        model.addAttribute("angajatId", angajatId);
        model.addAttribute("vehiculId", vehiculId);

        if (successMessage != null && !successMessage.isEmpty()) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null && !errorMessage.isEmpty()) {
            model.addAttribute("errorMessage", errorMessage);
        }

        return "livrari/index";
    }

    @PostMapping("/add")
    public String addLivrare(@ModelAttribute Livrare livrare, RedirectAttributes redirectAttributes) {
        try {
            livrareService.addLivrare(livrare);
            redirectAttributes.addFlashAttribute("successMessage", "Livrare adăugată cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Eroare la adăugarea livrării: " + e.getMessage());
        }
        return "redirect:/livrari";
    }

    @PostMapping("/edit/{id}")
    public String updateLivrare(@PathVariable int id, @ModelAttribute Livrare livrare, RedirectAttributes redirectAttributes) {
        try {
            livrare.setLivrare_ID(id);
            livrareService.updateLivrare(livrare);
            redirectAttributes.addFlashAttribute("successMessage", "Livrare actualizată cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Eroare la actualizarea livrării: " + e.getMessage());
        }
        return "redirect:/livrari";
    }

    @GetMapping("/delete/{id}")
    public String deleteLivrare(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            livrareService.deleteLivrare(id);
            redirectAttributes.addFlashAttribute("successMessage", "Livrare ștearsă cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/livrari";
    }
}
