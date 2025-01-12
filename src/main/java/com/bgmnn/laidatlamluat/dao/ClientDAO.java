

/** Clasa DATA ACCESS OBJECT (DAO, creata cu scopul de extrage / injecta datele din DB.) pentru clienti
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.dao;

import com.bgmnn.laidatlamluat.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ClientDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAll() {
        String query = "SELECT * FROM Clienti";
        return jdbcTemplate.queryForList(query);
    }

    public void save(Client client) {
        String query = "INSERT INTO Clienti (Client_Nume, Client_Adresa, Client_Telefon, Client_Email) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, client.getClient_Nume(), client.getClient_Adresa(), client.getClient_Telefon(), client.getClient_Email());
    }

    public void update(Client client) {
        String query = "UPDATE Clienti SET Client_Nume = ?, Client_Adresa = ?, Client_Telefon = ?, Client_Email = ? WHERE Client_ID = ?";
        jdbcTemplate.update(query, client.getClient_Nume(), client.getClient_Adresa(), client.getClient_Telefon(), client.getClient_Email(), client.getClient_ID());
    }

    public void deleteById(int id) {
        String query = "DELETE FROM Clienti WHERE Client_ID = ?";
        jdbcTemplate.update(query, id);
    }

    public Client findById(int id) {
        String query = "SELECT * FROM Clienti WHERE Client_ID = ?";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Client.class), id);
    }

    public List<Map<String, Object>> filterClienti(String nume, String email, String adresa, String telefon, int page, int size) {
        StringBuilder query = new StringBuilder("SELECT * FROM Clienti WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (nume != null && !nume.isEmpty()) {
            query.append(" AND Client_Nume LIKE ?");
            params.add("%" + nume + "%");
        }

        if (email != null && !email.isEmpty()) {
            query.append(" AND Client_Email LIKE ?");
            params.add("%" + email + "%");
        }

        if (adresa != null && !adresa.isEmpty()) {
            query.append(" AND Client_Adresa LIKE ?");
            params.add("%" + adresa + "%");
        }

        if (telefon != null && !telefon.isEmpty()) {
            query.append(" AND Client_Telefon LIKE ?");
            params.add("%" + telefon + "%");
        }

        query.append(" LIMIT ? OFFSET ?");
        params.add(size);
        params.add(page * size);

        return jdbcTemplate.queryForList(query.toString(), params.toArray());
    }

    public int countFilteredClienti(String nume, String email, String adresa, String telefon) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM Clienti WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (nume != null && !nume.isEmpty()) {
            query.append(" AND Client_Nume LIKE ?");
            params.add("%" + nume + "%");
        }

        if (email != null && !email.isEmpty()) {
            query.append(" AND Client_Email LIKE ?");
            params.add("%" + email + "%");
        }

        if (adresa != null && !adresa.isEmpty()) {
            query.append(" AND Client_Adresa LIKE ?");
            params.add("%" + adresa + "%");
        }

        if (telefon != null && !telefon.isEmpty()) {
            query.append(" AND Client_Telefon LIKE ?");
            params.add("%" + telefon + "%");
        }

        return jdbcTemplate.queryForObject(query.toString(), params.toArray(), Integer.class);
    }
}
