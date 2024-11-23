package com.bgmnn.laidatlamluat.dao;

import com.bgmnn.laidatlamluat.model.Angajat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AngajatDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public List<Angajat> findAll() {
        String query = "SELECT * FROM Angajati";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Angajat.class));
    }

    public List<Map<String, Object>> findAllWithSediu() {
        String query = "SELECT a.*, s.Sediu_Nume " +
                "FROM Angajati a " +
                "LEFT JOIN Sedii s ON a.Sediu_ID = s.Sediu_ID";

        return jdbcTemplate.queryForList(query);
    }

    public Angajat findById(int id) {
        String query = "SELECT * FROM Angajati WHERE Angajat_ID = ?";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Angajat.class), id);
    }

    public void save(Angajat angajat) {
        String query = "INSERT INTO Angajati (Angajat_Nume, Angajat_Prenume, Angajat_Rol, Angajat_Telefon, Angajat_Email, Angajat_Data_Angajare, Sediu_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                angajat.getAngajat_Nume(),
                angajat.getAngajat_Prenume(),
                angajat.getAngajat_Rol(),
                angajat.getAngajat_Telefon(),
                angajat.getAngajat_Email(),
                angajat.getAngajat_Data_Angajare(),
                angajat.getSediu_ID());
    }

    public void update(Angajat angajat) {
        String query = "UPDATE Angajati SET Angajat_Nume = ?, Angajat_Prenume = ?, Angajat_Rol = ?, Angajat_Telefon = ?, Angajat_Email = ?, Angajat_Data_Angajare = ?, Sediu_ID = ? WHERE Angajat_ID = ?";
        jdbcTemplate.update(query,
                angajat.getAngajat_Nume(),
                angajat.getAngajat_Prenume(),
                angajat.getAngajat_Rol(),
                angajat.getAngajat_Telefon(),
                angajat.getAngajat_Email(),
                angajat.getAngajat_Data_Angajare(),
                angajat.getSediu_ID(),
                angajat.getAngajat_ID());
    }

    public void deleteById(int id) {
        String query = "DELETE FROM Angajati WHERE Angajat_ID = ?";
        jdbcTemplate.update(query, id);
    }

    public boolean emailExists(String email) {
        String query = "SELECT COUNT(*) FROM Angajati WHERE Angajat_Email = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, email);
        return count != null && count > 0;
    }

}