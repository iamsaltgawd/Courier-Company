package com.bgmnn.laidatlamluat.service;

import com.bgmnn.laidatlamluat.dao.AngajatDAO;
import com.bgmnn.laidatlamluat.model.Angajat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AngajatService {

    @Autowired
    private AngajatDAO angajatDAO;

    public List<Angajat> getAllAngajati() {
        return angajatDAO.findAll();
    }

    public List<Map<String, Object>> getAllAngajatiWithSediu() {
        return angajatDAO.findAllWithSediu();
    }

    public Angajat getAngajatById(int id) {
        return angajatDAO.findById(id);
    }

    public void addAngajat(Angajat angajat) {
        if (angajatDAO.emailExists(angajat.getAngajat_Email())) {
            throw new IllegalArgumentException("Emailul exista deja!");
        }
        angajatDAO.save(angajat);
    }

    public void updateAngajat(Angajat angajat) {
        if (isDuplicateEmail(angajat.getAngajat_Email(), angajat.getAngajat_ID())) {
            throw new IllegalArgumentException("Emailul exista deja la alt utilizator!");
        }
        angajatDAO.update(angajat);
    }

    public void deleteAngajat(int id) {
        angajatDAO.deleteById(id);
    }

    public boolean isDuplicateEmail(String email, int angajatId) {
        String query = "SELECT COUNT(*) FROM Angajati WHERE Angajat_Email = ? AND Angajat_ID != ?";
        Integer count = angajatDAO.getJdbcTemplate().queryForObject(query, Integer.class, email, angajatId);
        return count != null && count > 0;
    }
}
