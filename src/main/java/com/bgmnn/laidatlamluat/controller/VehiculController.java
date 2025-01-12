

/** Clasa pentru controllerul vehiculelor
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.controller;

import com.bgmnn.laidatlamluat.model.Vehicul;
import com.bgmnn.laidatlamluat.service.VehiculService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/vehicule")
public class VehiculController {

    @Autowired
    private VehiculService vehiculService;

    @GetMapping
    public String listVehicule(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(required = false) Integer id,
                               @RequestParam(required = false) String tip,
                               @RequestParam(required = false) String status,
                               @RequestParam(required = false) Integer capacitate,
                               @RequestParam(required = false) Integer sediu,
                               @RequestParam(required = false) String numarInmatriculare,
                               Model model,
                               @ModelAttribute("successMessage") String successMessage,
                               @ModelAttribute("errorMessage") String errorMessage) {
        List<Map<String, Object>> vehicule = vehiculService.filterVehicule(id, tip, status, capacitate, sediu, numarInmatriculare, page, size);
        int totalItems = vehiculService.countFilteredVehicule(id, tip, status, capacitate, sediu, numarInmatriculare);

        List<Map<String, Object>> sedii = vehiculService.getAllSedii();

        model.addAttribute("title", "Vehicule");
        model.addAttribute("activePage", "vehicule");
        model.addAttribute("vehicule", vehicule);
        model.addAttribute("sedii", sedii);
        model.addAttribute("currentPage", page);
        model.addAttribute("itemsPerPage", size);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("startItem", page * size + 1);
        model.addAttribute("endItem", Math.min((page + 1) * size, totalItems));
        model.addAttribute("id", id);
        model.addAttribute("tip", tip);
        model.addAttribute("status", status);
        model.addAttribute("capacitate", capacitate);
        model.addAttribute("sediu", sediu);
        model.addAttribute("numarInmatriculare", numarInmatriculare);

        if (successMessage != null && !successMessage.isEmpty()) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null && !errorMessage.isEmpty()) {
            model.addAttribute("errorMessage", errorMessage);
        }

        return "vehicule/index";
    }

    @PostMapping("/add")
    public String addVehicul(@ModelAttribute Vehicul vehicul, RedirectAttributes redirectAttributes) {
        try {
            vehiculService.addVehicul(vehicul);
            redirectAttributes.addFlashAttribute("successMessage", "Vehicul adăugat cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/vehicule";
    }

    @PostMapping("/edit/{id}")
    public String updateVehicul(@PathVariable int id, @ModelAttribute Vehicul vehicul, RedirectAttributes redirectAttributes) {
        try {
            vehicul.setVehicul_ID(id);
            vehiculService.updateVehicul(vehicul);
            redirectAttributes.addFlashAttribute("successMessage", "Vehicul actualizat cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/vehicule";
    }

    @GetMapping("/delete/{id}")
    public String deleteVehicul(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            vehiculService.deleteVehicul(id);
            redirectAttributes.addFlashAttribute("successMessage", "Vehicul șters cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/vehicule";
    }
}
