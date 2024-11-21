package com.bgmnn.laidatlamluat.dao;

import com.bgmnn.laidatlamluat.model.Angajat;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AngajatDAO {

    private final String url = "jdbc:mariadb://localhost:3306/db_laidatlamluat";
    private final String username = "root";
    private final String password = "mircea.calavri";

    // Metodă pentru a obține toți angajații
    public List<Angajat> getAllAngajati() {
        List<Angajat> angajati = new ArrayList<>();
        String query = "SELECT * FROM Angajati";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Angajat angajat = new Angajat();
                angajat.setAngajatId(resultSet.getInt("Angajat_ID"));
                angajat.setNume(resultSet.getString("Angajat_Nume"));
                angajat.setPrenume(resultSet.getString("Angajat_Prenume"));
                angajat.setRol(resultSet.getString("Angajat_Rol"));
                angajat.setTelefon(resultSet.getString("Angajat_Telefon"));
                angajat.setEmail(resultSet.getString("Angajat_Email"));
                angajat.setDataAngajare(resultSet.getDate("Angajat_Data_Angajare").toLocalDate());
                angajat.setSediuId(resultSet.getInt("Sediu_ID"));
                angajati.add(angajat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return angajati;
    }

    // Metodă pentru a adăuga un nou angajat
    public void addAngajat(Angajat angajat) {
        String query = "INSERT INTO Angajati (Angajat_Nume, Angajat_Prenume, Angajat_Rol, Angajat_Telefon, Angajat_Email, Angajat_Data_Angajare, Sediu_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, angajat.getNume());
            preparedStatement.setString(2, angajat.getPrenume());
            preparedStatement.setString(3, angajat.getRol());
            preparedStatement.setString(4, angajat.getTelefon());
            preparedStatement.setString(5, angajat.getEmail());
            preparedStatement.setDate(6, Date.valueOf(angajat.getDataAngajare()));
            preparedStatement.setInt(7, angajat.getSediuId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodă pentru a șterge un angajat după ID
    public void deleteAngajatById(int id) {
        String query = "DELETE FROM Angajati WHERE Angajat_ID = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodă pentru a găsi un angajat după ID
    public Angajat findAngajatById(int id) {
        String query = "SELECT * FROM Angajati WHERE Angajat_ID = ?";
        Angajat angajat = null;

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                angajat = new Angajat();
                angajat.setAngajatId(resultSet.getInt("Angajat_ID"));
                angajat.setNume(resultSet.getString("Angajat_Nume"));
                angajat.setPrenume(resultSet.getString("Angajat_Prenume"));
                angajat.setRol(resultSet.getString("Angajat_Rol"));
                angajat.setTelefon(resultSet.getString("Angajat_Telefon"));
                angajat.setEmail(resultSet.getString("Angajat_Email"));
                angajat.setDataAngajare(resultSet.getDate("Angajat_Data_Angajare").toLocalDate());
                angajat.setSediuId(resultSet.getInt("Sediu_ID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return angajat;
    }

    // Metodă pentru a actualiza un angajat
    public void updateAngajat(Angajat angajat) {
        String query = "UPDATE Angajati SET Angajat_Nume = ?, Angajat_Prenume = ?, Angajat_Rol = ?, Angajat_Telefon = ?, Angajat_Email = ?, Angajat_Data_Angajare = ?, Sediu_ID = ? WHERE Angajat_ID = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, angajat.getNume());
            preparedStatement.setString(2, angajat.getPrenume());
            preparedStatement.setString(3, angajat.getRol());
            preparedStatement.setString(4, angajat.getTelefon());
            preparedStatement.setString(5, angajat.getEmail());
            preparedStatement.setDate(6, Date.valueOf(angajat.getDataAngajare()));
            preparedStatement.setInt(7, angajat.getSediuId());
            preparedStatement.setInt(8, angajat.getAngajatId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
