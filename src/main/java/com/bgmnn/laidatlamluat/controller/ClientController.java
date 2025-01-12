

/** Clasa pentru controllerul clienti
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.controller;

import com.bgmnn.laidatlamluat.model.Client;
import com.bgmnn.laidatlamluat.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clienti")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String listClienti(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(required = false) String nume,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String adresa,
                              @RequestParam(required = false) String telefon,
                              Model model) {
        List<Map<String, Object>> clienti = clientService.filterClienti(nume, email, adresa, telefon, page, size);
        int totalItems = clientService.countFilteredClienti(nume, email, adresa, telefon);

        int startItem = page * size + 1;
        int endItem = Math.min((page + 1) * size, totalItems);

        model.addAttribute("title", "Clienti");
        model.addAttribute("activePage", "clienti");
        model.addAttribute("clienti", clienti);
        model.addAttribute("currentPage", page);
        model.addAttribute("itemsPerPage", size);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("startItem", totalItems == 0 ? 0 : startItem);
        model.addAttribute("endItem", totalItems == 0 ? 0 : endItem);
        model.addAttribute("nume", nume);
        model.addAttribute("email", email);
        model.addAttribute("adresa", adresa);
        model.addAttribute("telefon", telefon);

        return "clienti/index";
    }


    @PostMapping("/add")
    public String addClient(@ModelAttribute Client client, RedirectAttributes redirectAttributes) {
        clientService.addClient(client);
        redirectAttributes.addFlashAttribute("successMessage", "Client adăugat cu succes!");
        return "redirect:/clienti";
    }

    @PostMapping("/edit/{id}")
    public String updateClient(@PathVariable int id, @ModelAttribute Client client, RedirectAttributes redirectAttributes) {
        try {
            client.setClient_ID(id);
            clientService.updateClient(client);
            redirectAttributes.addFlashAttribute("successMessage", "Client actualizat cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Eroare la actualizarea clientului: " + e.getMessage());
        }
        return "redirect:/clienti";
    }


    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            clientService.deleteClient(id);
            redirectAttributes.addFlashAttribute("successMessage", "Client șters cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Eroare la ștergerea clientului: " + e.getMessage());
        }
        return "redirect:/clienti";
    }

}
