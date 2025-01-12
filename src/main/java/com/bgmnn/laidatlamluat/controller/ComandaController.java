

/** Clasa pentru controllerul comenzilor
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.controller;

import com.bgmnn.laidatlamluat.model.Comanda;
import com.bgmnn.laidatlamluat.service.ComandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comenzi")
public class ComandaController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    private ComandaService comandaService;

    @GetMapping
    public String listComenzi(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(required = false) Integer comandaId,
                              @RequestParam(required = false) Date data,
                              @RequestParam(required = false) String status,
                              @RequestParam(required = false) Integer clientId,
                              @RequestParam(required = false) Integer sediuId,
                              Model model) {
        List<Map<String, Object>> comenzi = comandaService.filterComenzi(comandaId, data, status, clientId, sediuId, page, size);
        int totalItems = comandaService.countFilteredComenzi(comandaId, data, status, clientId, sediuId);

        int startItem = page * size + 1;
        int endItem = Math.min((page + 1) * size, totalItems);

        model.addAttribute("title", "Comenzi");
        model.addAttribute("activePage", "comenzi");
        model.addAttribute("comenzi", comenzi);
        model.addAttribute("currentPage", page);
        model.addAttribute("itemsPerPage", size);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("startItem", totalItems == 0 ? 0 : startItem);
        model.addAttribute("endItem", totalItems == 0 ? 0 : endItem);
        model.addAttribute("comandaId", comandaId);
        model.addAttribute("status", status);
        model.addAttribute("clientId", clientId);
        model.addAttribute("sediuId", sediuId);

        return "comenzi/index";
    }


    @PostMapping("/add")
    public String addComanda(@ModelAttribute Comanda comanda, RedirectAttributes redirectAttributes) {
        if (comanda.getComanda_Data() == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Data comenzii este obligatorie!");
            return "redirect:/comenzi";
        }
        try {
            comandaService.addComanda(comanda);
            redirectAttributes.addFlashAttribute("successMessage", "Comanda adăugată cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Eroare la adăugarea comenzii: " + e.getMessage());
        }
        return "redirect:/comenzi";
    }

    @PostMapping("/edit/{id}")
    public String updateComanda(@PathVariable("id") int id, @ModelAttribute Comanda comanda, RedirectAttributes redirectAttributes) {
        try {
            comanda.setComanda_ID(id);
            comandaService.updateComanda(comanda);
            redirectAttributes.addFlashAttribute("successMessage", "Comanda actualizată cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Eroare la actualizarea comenzii: " + e.getMessage());
        }
        return "redirect:/comenzi";
    }


    @GetMapping("/delete/{id}")
    public String deleteComanda(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            comandaService.deleteComanda(id);
            redirectAttributes.addFlashAttribute("successMessage", "Comanda ștearsă cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/comenzi";
    }
}
