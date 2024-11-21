package com.bgmnn.laidatlamluat.service;

import com.bgmnn.laidatlamluat.dao.AngajatDAO;
import com.bgmnn.laidatlamluat.model.Angajat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AngajatService {

    private final AngajatDAO angajatDAO;

    // Constructor prin care injectăm DAO-ul
    @Autowired
    public AngajatService(AngajatDAO angajatDAO) {
        this.angajatDAO = angajatDAO;
    }

    // Metodă pentru a obține toți angajații
    public List<Angajat> getAllAngajati() {
        return angajatDAO.getAllAngajati();
    }

    // Metodă pentru a adăuga un nou angajat
    public void addAngajat(Angajat angajat) {
        angajatDAO.addAngajat(angajat);
    }

    // Metodă pentru a șterge un angajat după ID
    public void deleteAngajatById(int id) {
        angajatDAO.deleteAngajatById(id);
    }

    // Metodă pentru a găsi un angajat după ID
    public Angajat findAngajatById(int id) {
        return angajatDAO.findAngajatById(id);
    }

    // Metodă pentru a actualiza un angajat
    public void updateAngajat(Angajat angajat) {
        angajatDAO.updateAngajat(angajat);
    }
}
