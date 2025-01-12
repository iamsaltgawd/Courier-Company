

/** Clasa care apeleaza functiile din fisierele sursa de tipul [*]DAO
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.service;

import com.bgmnn.laidatlamluat.dao.SediuDAO;
import com.bgmnn.laidatlamluat.dao.VehiculDAO;
import com.bgmnn.laidatlamluat.model.Vehicul;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VehiculService {

    @Autowired
    private VehiculDAO vehiculDAO;
    @Autowired
    private SediuDAO sediuDAO;

    public List<Map<String, Object>> getAllSedii() {
        return sediuDAO.findAllSedii();
    }

    public List<Map<String, Object>> getAllVehicule() {
        return vehiculDAO.findAllWithSediu();
    }

    public Vehicul getVehiculById(int id) {
        return vehiculDAO.findById(id);
    }

    public void addVehicul(Vehicul vehicul) {
        if (vehiculDAO.isDuplicateNumarInmatriculare(vehicul.getVehicul_Num_Inmatriculare())) {
            throw new IllegalArgumentException("Numărul de înmatriculare există deja!");
        }
        vehiculDAO.save(vehicul);
    }

    public void updateVehicul(Vehicul vehicul) {
        if (vehiculDAO.isDuplicateNumarInmatriculareForOtherVehicul(vehicul.getVehicul_Num_Inmatriculare(), vehicul.getVehicul_ID())) {
            throw new IllegalArgumentException("Numărul de înmatriculare există la alt vehicul!");
        }
        vehiculDAO.update(vehicul);
    }

    public void deleteVehicul(int id) {
        vehiculDAO.deleteById(id);
    }

    public boolean isDuplicateNumarInmatriculare(String numInmatriculare) {
        return vehiculDAO.isDuplicateNumarInmatriculare(numInmatriculare);
    }

    public boolean isDuplicateNumarInmatriculareForOtherVehicul(String numInmatriculare, int vehiculId) {
        return vehiculDAO.isDuplicateNumarInmatriculareForOtherVehicul(numInmatriculare, vehiculId);
    }


    public List<Map<String, Object>> filterVehicule(Integer id, String tip, String status, Integer capacitate, Integer sediu,
                                                    String numarInmatriculare, int page, int size) {
        int offset = page * size;
        return vehiculDAO.filterVehicule(id, tip, status, capacitate, sediu, numarInmatriculare, size, offset);
    }

    public int countFilteredVehicule(Integer id, String tip, String status, Integer capacitate, Integer sediu, String numarInmatriculare) {
        return vehiculDAO.countFilteredVehicule(id, tip, status, capacitate, sediu, numarInmatriculare);
    }


}
