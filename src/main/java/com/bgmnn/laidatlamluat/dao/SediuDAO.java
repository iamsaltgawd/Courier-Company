

/** Clasa DATA ACCESS OBJECT (DAO, creata cu scopul de extrage / injecta datele din DB.) pentru sedii
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.dao;

import com.bgmnn.laidatlamluat.model.Sediu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SediuDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Sediu> findAll() {
        String query = "SELECT * FROM Sedii";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Sediu.class));
    }
    public List<Map<String, Object>> findAllSedii() {
        String query = "SELECT * FROM Sedii";
        return jdbcTemplate.queryForList(query);
    }
    public Sediu findById(int id) {
        String query = "SELECT * FROM Sedii WHERE Sediu_ID = ?";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Sediu.class), id);
    }

    public void save(Sediu sediu) {
        String query = "INSERT INTO Sedii (Sediu_Nume, Sediu_Adresa, Sediu_Telefon, Sediu_Email, Sediu_Orar_Functionare) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                sediu.getSediu_Nume(),
                sediu.getSediu_Adresa(),
                sediu.getSediu_Telefon(),
                sediu.getSediu_Email(),
                sediu.getSediu_Orar_Functionare());
    }

    public void update(Sediu sediu) {
        String query = "UPDATE Sedii SET Sediu_Nume = ?, Sediu_Adresa = ?, Sediu_Telefon = ?," +
                " Sediu_Email = ?, Sediu_Orar_Functionare = ? WHERE Sediu_ID = ?";
        jdbcTemplate.update(query,
                sediu.getSediu_Nume(),
                sediu.getSediu_Adresa(),
                sediu.getSediu_Telefon(),
                sediu.getSediu_Email(),
                sediu.getSediu_Orar_Functionare(),
                sediu.getSediu_ID());
    }

    public void deleteById(int id) {
        String query = "DELETE FROM Sedii WHERE Sediu_ID = ?";
        jdbcTemplate.update(query, id);
    }
}