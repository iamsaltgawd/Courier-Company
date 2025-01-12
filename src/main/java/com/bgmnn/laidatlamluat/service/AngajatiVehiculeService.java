

/** Clasa care apeleaza functiile din fisierele sursa de tipul [*]DAO
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.service;

import com.bgmnn.laidatlamluat.dao.AngajatiVehiculeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AngajatiVehiculeService {

    @Autowired
    private AngajatiVehiculeDAO angajatiVehiculeDAO;

    public List<Map<String, Object>> getAllDistribuiri() {
        return angajatiVehiculeDAO.findAllDistribuiri();
    }

    public void addDistribuire(int angajatId, int vehiculId) {
        angajatiVehiculeDAO.save(angajatId, vehiculId);
    }

    public void deleteDistribuire(int angajatId, int vehiculId) {
        angajatiVehiculeDAO.deleteById(angajatId, vehiculId);
    }
}
