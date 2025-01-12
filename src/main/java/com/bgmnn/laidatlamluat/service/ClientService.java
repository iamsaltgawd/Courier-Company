

/** Clasa care apeleaza functiile din fisierele sursa de tipul [*]DAO
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.service;

import com.bgmnn.laidatlamluat.dao.ClientDAO;
import com.bgmnn.laidatlamluat.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClientService {

    @Autowired
    private ClientDAO clientDAO;

    public List<Map<String, Object>> getAllClients() {
        return clientDAO.findAll();
    }

    public void addClient(Client client) {
        clientDAO.save(client);
    }

    public void updateClient(Client client) {
        clientDAO.update(client);
    }

    public void deleteClient(int id) {
        clientDAO.deleteById(id);
    }

    public Client getClientById(int id) {
        return clientDAO.findById(id);
    }

    public List<Map<String, Object>> filterClienti(String nume, String email, String adresa, String telefon, int page, int size) {
        return clientDAO.filterClienti(nume, email, adresa, telefon, page, size);
    }

    public int countFilteredClienti(String nume, String email, String adresa, String telefon) {
        return clientDAO.countFilteredClienti(nume, email, adresa, telefon);
    }
}
