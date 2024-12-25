package com.bgmnn.laidatlamluat.service;

import com.bgmnn.laidatlamluat.dao.SediuDAO;
import com.bgmnn.laidatlamluat.model.Sediu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SediuService {

    @Autowired
    private SediuDAO sediuDAO;

    public List<Sediu> getAllSedii() {
        return sediuDAO.findAll();
    }

    public Sediu getSediuById(int id) {
        return sediuDAO.findById(id);
    }

    public void addSediu(Sediu sediu) {
        sediuDAO.save(sediu);
    }

    public void updateSediu(Sediu sediu) {
        sediuDAO.update(sediu);
    }

    public void deleteSediu(int id) {
        sediuDAO.deleteById(id);
    }



}
