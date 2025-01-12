

/** Clasa DATA ACCESS OBJECT (DAO, creata cu scopul de extrage / injecta datele din DB.) pentru angajati-vehicule
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AngajatiVehiculeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAllDistribuiri() {
        String query = "SELECT av.Angajat_ID, CONCAT(a.Angajat_Nume, ' ', a.Angajat_Prenume) AS Angajat_Nume, " +
                "v.Vehicul_Num_Inmatriculare, v.Vehicul_ID " +
                "FROM Angajati_Vehicule av " +
                "JOIN Angajati a ON av.Angajat_ID = a.Angajat_ID " +
                "JOIN Vehicule v ON av.Vehicul_ID = v.Vehicul_ID";

        return jdbcTemplate.queryForList(query);
    }

    public void save(int angajatId, int vehiculId) {
        String query = "INSERT INTO Angajati_Vehicule (Angajat_ID, Vehicul_ID) VALUES (?, ?)";
        jdbcTemplate.update(query, angajatId, vehiculId);
    }

    public void deleteById(int angajatId, int vehiculId) {
        String query = "DELETE FROM Angajati_Vehicule WHERE Angajat_ID = ? AND Vehicul_ID = ?";
        jdbcTemplate.update(query, angajatId, vehiculId);
    }
}
