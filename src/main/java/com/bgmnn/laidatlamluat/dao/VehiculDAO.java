

/** Clasa DATA ACCESS OBJECT (DAO, creata cu scopul de extrage / injecta datele din DB.) pentru vehicule
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.dao;

import com.bgmnn.laidatlamluat.model.Vehicul;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class VehiculDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAllWithSediu() {
        String query = "SELECT v.*, s.Sediu_Nume FROM Vehicule v LEFT JOIN Sedii s ON v.Sediu_ID = s.Sediu_ID";
        return jdbcTemplate.queryForList(query);
    }

    public List<Map<String, Object>> findAllSedii() {
        String query = "SELECT * FROM Sedii";
        return jdbcTemplate.queryForList(query);
    }

    public void save(Vehicul vehicul) {
        String query = "INSERT INTO Vehicule (Vehicul_Tip, Vehicul_Capacitate, Vehicul_Num_Inmatriculare, Vehicul_Status, Sediu_ID) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, vehicul.getVehicul_Tip(), vehicul.getVehicul_Capacitate(),
                vehicul.getVehicul_Num_Inmatriculare(), vehicul.getVehicul_Status(), vehicul.getSediu_ID());
    }

    public void update(Vehicul vehicul) {
        String query = "UPDATE Vehicule SET Vehicul_Tip = ?, Vehicul_Capacitate = ?, Vehicul_Num_Inmatriculare = ?, Vehicul_Status = ?, Sediu_ID = ? WHERE Vehicul_ID = ?";
        jdbcTemplate.update(query, vehicul.getVehicul_Tip(), vehicul.getVehicul_Capacitate(),
                vehicul.getVehicul_Num_Inmatriculare(), vehicul.getVehicul_Status(), vehicul.getSediu_ID(), vehicul.getVehicul_ID());
    }

    public void deleteById(int id) {
        String query = "DELETE FROM Vehicule WHERE Vehicul_ID = ?";
        jdbcTemplate.update(query, id);
    }

    public Vehicul findById(int id) {
        String query = "SELECT * FROM Vehicule WHERE Vehicul_ID = ?";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Vehicul.class), id);
    }

    public List<Map<String, Object>> filterVehicule(Integer id, String tip, String status, Integer capacitate, Integer sediu,
                                                    String numarInmatriculare, int size, int offset) {
        StringBuilder query = new StringBuilder("SELECT v.*, s.Sediu_Nume FROM Vehicule v " +
                "LEFT JOIN Sedii s ON v.Sediu_ID = s.Sediu_ID WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (id != null) {
            query.append(" AND v.Vehicul_ID = ?");
            params.add(id);
        }

        if (tip != null && !tip.isEmpty()) {
            query.append(" AND v.Vehicul_Tip LIKE ?");
            params.add("%" + tip + "%");
        }

        if (status != null && !status.isEmpty()) {
            query.append(" AND v.Vehicul_Status = ?");
            params.add(status);
        }

        if (capacitate != null) {
            query.append(" AND v.Vehicul_Capacitate = ?");
            params.add(capacitate);
        }

        if (sediu != null) {
            query.append(" AND v.Sediu_ID = ?");
            params.add(sediu);
        }

        if (numarInmatriculare != null && !numarInmatriculare.isEmpty()) {
            query.append(" AND v.Vehicul_Num_Inmatriculare LIKE ?");
            params.add("%" + numarInmatriculare + "%");
        }

        query.append(" LIMIT ? OFFSET ?");
        params.add(size);
        params.add(offset);

        return jdbcTemplate.queryForList(query.toString(), params.toArray());
    }

    public int countFilteredVehicule(Integer id, String tip, String status, Integer capacitate, Integer sediu, String numarInmatriculare) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM Vehicule v WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (id != null) {
            query.append(" AND v.Vehicul_ID = ?");
            params.add(id);
        }

        if (tip != null && !tip.isEmpty()) {
            query.append(" AND v.Vehicul_Tip LIKE ?");
            params.add("%" + tip + "%");
        }

        if (status != null && !status.isEmpty()) {
            query.append(" AND v.Vehicul_Status = ?");
            params.add(status);
        }

        if (capacitate != null) {
            query.append(" AND v.Vehicul_Capacitate = ?");
            params.add(capacitate);
        }

        if (sediu != null) {
            query.append(" AND v.Sediu_ID = ?");
            params.add(sediu);
        }

        if (numarInmatriculare != null && !numarInmatriculare.isEmpty()) {
            query.append(" AND v.Vehicul_Num_Inmatriculare LIKE ?");
            params.add("%" + numarInmatriculare + "%");
        }

        return jdbcTemplate.queryForObject(query.toString(), params.toArray(), Integer.class);
    }

    public List<Map<String, Object>> findAllWithPagination(int size, int offset) {
        String query = "SELECT v.*, s.Sediu_Nume FROM Vehicule v " +
                "LEFT JOIN Sedii s ON v.Sediu_ID = s.Sediu_ID LIMIT ? OFFSET ?";
        return jdbcTemplate.queryForList(query, size, offset);
    }

    public int count() {
        String query = "SELECT COUNT(*) FROM Vehicule";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    public boolean isDuplicateNumarInmatriculare(String numInmatriculare) {
        String query = "SELECT COUNT(*) FROM Vehicule WHERE Vehicul_Num_Inmatriculare = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, numInmatriculare);
        return count != null && count > 0;
    }

    public boolean isDuplicateNumarInmatriculareForOtherVehicul(String numInmatriculare, int vehiculId) {
        String query = "SELECT COUNT(*) FROM Vehicule WHERE Vehicul_Num_Inmatriculare = ? AND Vehicul_ID != ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, numInmatriculare, vehiculId);
        return count != null && count > 0;
    }
}
