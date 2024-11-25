package com.bgmnn.laidatlamluat.dao;

import com.bgmnn.laidatlamluat.model.Angajat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    public List<Map<String, Object>> findAllWithPagination(int size, int offset) {
        String query = "SELECT a.*, s.Sediu_Nume FROM Angajati a LEFT JOIN Sedii s ON a.Sediu_ID = s.Sediu_ID LIMIT ? OFFSET ?";
        return jdbcTemplate.queryForList(query, size, offset);
    }

    public int count() {
        String query = "SELECT COUNT(*) FROM Angajati";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    public List<Map<String, Object>> filterAngajati(String nume, String prenume, String rol, String email, String telefon, Integer sediu, int size, int offset) {
        StringBuilder query = new StringBuilder("SELECT a.*, s.Sediu_Nume FROM Angajati a LEFT JOIN Sedii s ON a.Sediu_ID = s.Sediu_ID WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (nume != null && !nume.isEmpty()) {
            query.append(" AND a.Angajat_Nume LIKE ?");
            params.add("%" + nume + "%");
        }

        if (prenume != null && !prenume.isEmpty()) {
            query.append(" AND a.Angajat_Prenume LIKE ?");
            params.add("%" + prenume + "%");
        }

        if (rol != null && !rol.isEmpty()) {
            query.append(" AND a.Angajat_Rol LIKE ?");
            params.add("%" + rol + "%");
        }

        if (email != null && !email.isEmpty()) {
            query.append(" AND a.Angajat_Email LIKE ?");
            params.add("%" + email + "%");
        }

        if (telefon != null && !telefon.isEmpty()) {
            query.append(" AND a.Angajat_Telefon LIKE ?");
            params.add("%" + telefon + "%");
        }

        if (sediu != null) {
            query.append(" AND a.Sediu_ID = ?");
            params.add(sediu);
        }

        query.append(" LIMIT ? OFFSET ?");
        params.add(size);
        params.add(offset);

        return jdbcTemplate.queryForList(query.toString(), params.toArray());
    }

    public int countFilteredAngajati(String nume, String prenume, String rol, String email, String telefon, Integer sediu) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM Angajati a WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (nume != null && !nume.isEmpty()) {
            query.append(" AND a.Angajat_Nume LIKE ?");
            params.add("%" + nume + "%");
        }

        if (prenume != null && !prenume.isEmpty()) {
            query.append(" AND a.Angajat_Prenume LIKE ?");
            params.add("%" + prenume + "%");
        }

        if (rol != null && !rol.isEmpty()) {
            query.append(" AND a.Angajat_Rol LIKE ?");
            params.add("%" + rol + "%");
        }

        if (email != null && !email.isEmpty()) {
            query.append(" AND a.Angajat_Email LIKE ?");
            params.add("%" + email + "%");
        }

        if (telefon != null && !telefon.isEmpty()) {
            query.append(" AND a.Angajat_Telefon LIKE ?");
            params.add("%" + telefon + "%");
        }

        if (sediu != null) {
            query.append(" AND a.Sediu_ID = ?");
            params.add(sediu);
        }

        return jdbcTemplate.queryForObject(query.toString(), params.toArray(), Integer.class);
    }
}