

/** Clasa care apeleaza functiile din fisierele sursa de tipul [*]DAO
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */



package com.bgmnn.laidatlamluat.service;

import com.bgmnn.laidatlamluat.dao.ComandaDAO;
import com.bgmnn.laidatlamluat.model.Comanda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ComandaService {

    @Autowired
    private ComandaDAO comandaDAO;

    public List<Map<String, Object>> getAllComenzi() {
        return comandaDAO.findAll();
    }

    public void addComanda(Comanda comanda) {
        if (comanda.getClient_ID() <= 0 || comanda.getSediu_ID() <= 0) {
            throw new IllegalArgumentException("ID Client și ID Sediu trebuie să fie mai mari decât 1.");
        }
        comandaDAO.save(comanda);
    }


    public void updateComanda(Comanda comanda) {
        comandaDAO.update(comanda);
    }

    public void deleteComanda(int id) {
        comandaDAO.deleteById(id);
    }

    public Comanda getComandaById(int id) {
        return comandaDAO.findById(id);
    }

    public List<Map<String, Object>> filterComenzi(Integer comandaId, Date data, String status, Integer clientId, Integer sediuId, int page, int size) {
        return comandaDAO.filterComenzi(comandaId, data, status, clientId, sediuId, page, size);
    }

    public int countFilteredComenzi(Integer comandaId, Date data, String status, Integer clientId, Integer sediuId) {
        return comandaDAO.countFilteredComenzi(comandaId, data, status, clientId, sediuId);
    }

}
