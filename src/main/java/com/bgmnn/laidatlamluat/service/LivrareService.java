

/** Clasa care apeleaza functiile din fisierele sursa de tipul [*]DAO
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.service;

import com.bgmnn.laidatlamluat.dao.LivrareDAO;
import com.bgmnn.laidatlamluat.model.Livrare;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class LivrareService {

    @Autowired
    private LivrareDAO livrareDAO;

    public List<Map<String, Object>> getAllLivrari() {
        return livrareDAO.findAll();
    }

    public void addLivrare(Livrare livrare) {
        validateLivrare(livrare);
        livrareDAO.save(livrare);
    }

    public void updateLivrare(Livrare livrare) {
        validateLivrare(livrare);
        livrareDAO.update(livrare);
    }

    public void deleteLivrare(int id) {
        livrareDAO.deleteById(id);
    }

    public Livrare getLivrareById(int id) {
        return livrareDAO.findById(id);
    }

    public List<Map<String, Object>> filterLivrari(Integer id, String data, String status, Integer comandaId, Integer angajatId, Integer vehiculId, int page, int size) {
        return livrareDAO.filterLivrari(id, data, status, comandaId, angajatId, vehiculId, page, size);
    }

    public int countFilteredLivrari(Integer id, String data, String status, Integer comandaId, Integer angajatId, Integer vehiculId) {
        return livrareDAO.countFilteredLivrari(id, data, status, comandaId, angajatId, vehiculId);
    }

    private void validateLivrare(Livrare livrare) {
        if (livrare.getLivrare_Data() == null) {
            throw new IllegalArgumentException("Data livrării este obligatorie.");
        }
        if (livrare.getLivrare_Status() == null || livrare.getLivrare_Status().isEmpty()) {
            throw new IllegalArgumentException("Statusul livrării este obligatoriu.");
        }
        if (livrare.getComanda_ID() <= 0) {
            throw new IllegalArgumentException("ID-ul comenzii trebuie să fie mai mare decât 0.");
        }
        if (livrare.getAngajat_ID() <= 0) {
            throw new IllegalArgumentException("ID-ul angajatului trebuie să fie mai mare decât 0.");
        }
        if (livrare.getVehicul_ID() <= 0) {
            throw new IllegalArgumentException("ID-ul vehiculului trebuie să fie mai mare decât 0.");
        }
    }
}
