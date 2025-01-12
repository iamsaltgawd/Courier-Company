

/** Clasa pentru controllerul angajati-vehicule
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.controller;

import com.bgmnn.laidatlamluat.service.AngajatService;
import com.bgmnn.laidatlamluat.service.AngajatiVehiculeService;
import com.bgmnn.laidatlamluat.service.VehiculService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/distribuiri")
public class AngajatiVehiculeController {

    @Autowired
    private AngajatiVehiculeService angajatiVehiculeService;

    @Autowired
    private VehiculService vehiculService;

    @Autowired
    private AngajatService angajatService;

    @GetMapping
    public String listDistribuiri(Model model) {
        List<Map<String, Object>> distribuiri = angajatiVehiculeService.getAllDistribuiri();
        List<Map<String, Object>> vehicule = vehiculService.getAllVehicule();

        // Convert Angajat to Map<String, Object>
        List<Map<String, Object>> angajati = angajatService.getAllAngajati()
                .stream()
                .map(angajat -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("Angajat_ID", angajat.getAngajat_ID());
                    map.put("Angajat_Nume", angajat.getAngajat_Nume());
                    return map;
                })
                .toList();

        model.addAttribute("title", "Distribuiri");
        model.addAttribute("distribuiri", distribuiri);
        model.addAttribute("activePage", "distribuiri");
        model.addAttribute("vehicule", vehicule);
        model.addAttribute("angajati", angajati);
        return "distribuiri/index";
    }


    @PostMapping("/add")
    public String addDistribuire(@RequestParam int angajatId, @RequestParam int vehiculId, RedirectAttributes redirectAttributes) {
        try {
            angajatiVehiculeService.addDistribuire(angajatId, vehiculId);
            redirectAttributes.addFlashAttribute("successMessage", "Distribuire adăugată cu succes!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Eroare: " + e.getMessage());
        }
        return "redirect:/distribuiri";
    }

    @PostMapping("/delete")
    public String deleteDistribuire(@RequestParam(required = false) Integer angajatId,
                                    @RequestParam(required = false) Integer vehiculId,
                                    RedirectAttributes redirectAttributes) {

        try {
            angajatiVehiculeService.deleteDistribuire(angajatId, vehiculId);
            redirectAttributes.addFlashAttribute("successMessage", "Distribuire ștearsă cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Eroare: " + e.getMessage());
        }
        return "redirect:/distribuiri";
    }


}
