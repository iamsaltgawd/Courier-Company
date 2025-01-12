

/** Clasa DATA ACCESS OBJECT (DAO, creata cu scopul de extrage / injecta datele din DB.) pentru livrari
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.dao;

import com.bgmnn.laidatlamluat.model.Livrare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class LivrareDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAll() {
        String query = "SELECT * FROM Livrari";
        return jdbcTemplate.queryForList(query);
    }

    public void save(Livrare livrare) {
        String query = "INSERT INTO Livrari (Livrare_Data, Livrare_Ora, Livrare_Status, Comanda_ID, Angajat_ID, Vehicul_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                livrare.getLivrare_Data(),
                livrare.getLivrare_Ora(),
                livrare.getLivrare_Status(),
                livrare.getComanda_ID(),
                livrare.getAngajat_ID(),
                livrare.getVehicul_ID());
    }

    public void update(Livrare livrare) {
        String query = "UPDATE Livrari SET Livrare_Data = ?, Livrare_Ora = ?, Livrare_Status = ?, Comanda_ID = ?, Angajat_ID = ?, Vehicul_ID = ? " +
                "WHERE Livrare_ID = ?";
        jdbcTemplate.update(query,
                livrare.getLivrare_Data(),
                livrare.getLivrare_Ora(),
                livrare.getLivrare_Status(),
                livrare.getComanda_ID(),
                livrare.getAngajat_ID(),
                livrare.getVehicul_ID(),
                livrare.getLivrare_ID());
    }

    public void deleteById(int id) {
        String query = "DELETE FROM Livrari WHERE Livrare_ID = ?";
        jdbcTemplate.update(query, id);
    }

    public Livrare findById(int id) {
        String query = "SELECT * FROM Livrari WHERE Livrare_ID = ?";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Livrare.class), id);
    }

    public List<Map<String, Object>> filterLivrari(Integer id, String data, String status, Integer comandaId, Integer angajatId, Integer vehiculId, int page, int size) {
        StringBuilder query = new StringBuilder("SELECT * FROM Livrari WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (id != null) {
            query.append(" AND Livrare_ID = ?");
            params.add(id);
        }
        if (data != null && !data.isEmpty()) {
            query.append(" AND DATE(Livrare_Data) = ?");
            params.add(data);
        }
        if (status != null && !status.isEmpty()) {
            query.append(" AND Livrare_Status = ?");
            params.add(status);
        }
        if (comandaId != null) {
            query.append(" AND Comanda_ID = ?");
            params.add(comandaId);
        }
        if (angajatId != null) {
            query.append(" AND Angajat_ID = ?");
            params.add(angajatId);
        }
        if (vehiculId != null) {
            query.append(" AND Vehicul_ID = ?");
            params.add(vehiculId);
        }

        query.append(" LIMIT ? OFFSET ?");
        params.add(size);
        params.add(page * size);

        return jdbcTemplate.queryForList(query.toString(), params.toArray());
    }

    public int countFilteredLivrari(Integer id, String data, String status, Integer comandaId, Integer angajatId, Integer vehiculId) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM Livrari WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (id != null) {
            query.append(" AND Livrare_ID = ?");
            params.add(id);
        }
        if (data != null && !data.isEmpty()) {
            query.append(" AND DATE(Livrare_Data) = ?");
            params.add(data);
        }
        if (status != null && !status.isEmpty()) {
            query.append(" AND Livrare_Status = ?");
            params.add(status);
        }
        if (comandaId != null) {
            query.append(" AND Comanda_ID = ?");
            params.add(comandaId);
        }
        if (angajatId != null) {
            query.append(" AND Angajat_ID = ?");
            params.add(angajatId);
        }
        if (vehiculId != null) {
            query.append(" AND Vehicul_ID = ?");
            params.add(vehiculId);
        }

        return jdbcTemplate.queryForObject(query.toString(), params.toArray(), Integer.class);
    }
}
