

/** Clasa DATA ACCESS OBJECT (DAO, creata cu scopul de extrage / injecta datele din DB.) pentru comenzi
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.dao;

import com.bgmnn.laidatlamluat.model.Comanda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ComandaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAll() {
        String query = "SELECT c.*, s.Sediu_Nume FROM Comenzi c LEFT JOIN Sedii s ON c.Sediu_ID = s.Sediu_ID";
        return jdbcTemplate.queryForList(query);
    }

    public void save(Comanda comanda) {
        String query = "INSERT INTO Comenzi (Comanda_Data, Comanda_Status, Comanda_Total, Client_ID, Sediu_ID) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, comanda.getComanda_Data(), comanda.getComanda_Status(),
                comanda.getComanda_Total(), comanda.getClient_ID(), comanda.getSediu_ID());
    }

    public void update(Comanda comanda) {
        String query = "UPDATE Comenzi SET Comanda_Data = ?, Comanda_Status = ?, Comanda_Total = ?, Client_ID = ?, Sediu_ID = ? " +
                "WHERE Comanda_ID = ?";
        jdbcTemplate.update(query, comanda.getComanda_Data(), comanda.getComanda_Status(),
                comanda.getComanda_Total(), comanda.getClient_ID(), comanda.getSediu_ID(), comanda.getComanda_ID());
    }

    public void deleteById(int id) {
        String query = "DELETE FROM Comenzi WHERE Comanda_ID = ?";
        jdbcTemplate.update(query, id);
    }

    public Comanda findById(int id) {
        String query = "SELECT * FROM Comenzi WHERE Comanda_ID = ?";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Comanda.class), id);
    }

    public List<Map<String, Object>> filterComenzi(Integer comandaId, Date data, String status, Integer clientId, Integer sediuId, int page, int size) {
        StringBuilder query = new StringBuilder("SELECT c.*, s.Sediu_Nume FROM Comenzi c " +
                "LEFT JOIN Sedii s ON c.Sediu_ID = s.Sediu_ID WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (comandaId != null) {
            query.append(" AND c.Comanda_ID = ?");
            params.add(comandaId);
        }

        if (data != null) {
            query.append(" AND DATE(c.Comanda_Data) = ?");
            params.add(data);
        }

        if (status != null && !status.isEmpty()) {
            query.append(" AND c.Comanda_Status = ?");
            params.add(status);
        }

        if (clientId != null) {
            query.append(" AND c.Client_ID = ?");
            params.add(clientId);
        }

        if (sediuId != null) {
            query.append(" AND c.Sediu_ID = ?");
            params.add(sediuId);
        }

        query.append(" LIMIT ? OFFSET ?");
        params.add(size);
        params.add(page * size);

        return jdbcTemplate.queryForList(query.toString(), params.toArray());
    }


    public int countFilteredComenzi(Integer comandaId, Date data, String status, Integer clientId, Integer sediuId) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM Comenzi c WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (comandaId != null) {
            query.append(" AND c.Comanda_ID = ?");
            params.add(comandaId);
        }

        if (status != null && !status.isEmpty()) {
            query.append(" AND c.Comanda_Status = ?");
            params.add(status);
        }

        if (clientId != null) {
            query.append(" AND c.Client_ID = ?");
            params.add(clientId);
        }

        if (sediuId != null) {
            query.append(" AND c.Sediu_ID = ?");
            params.add(sediuId);
        }

        return jdbcTemplate.queryForObject(query.toString(), params.toArray(), Integer.class);
    }

}
