

/** Clasa care poate stoca toate datele din tabela comenzi
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.model;

import java.util.Date;

public class Comanda {
    private int Comanda_ID;
    private Date Comanda_Data;
    private String Comanda_Status;
    private double Comanda_Total;
    private int Client_ID;
    private int Sediu_ID;

    public int getComanda_ID() {
        return Comanda_ID;
    }

    public void setComanda_ID(int comanda_ID) {
        Comanda_ID = comanda_ID;
    }

    public Date getComanda_Data() {
        return Comanda_Data;
    }

    public void setComanda_Data(Date comanda_Data) {
        Comanda_Data = comanda_Data;
    }

    public String getComanda_Status() {
        return Comanda_Status;
    }

    public void setComanda_Status(String comanda_Status) {
        Comanda_Status = comanda_Status;
    }

    public double getComanda_Total() {
        return Comanda_Total;
    }

    public void setComanda_Total(double comanda_Total) {
        Comanda_Total = comanda_Total;
    }

    public int getClient_ID() {
        return Client_ID;
    }

    public void setClient_ID(int client_ID) {
        Client_ID = client_ID;
    }

    public int getSediu_ID() {
        return Sediu_ID;
    }

    public void setSediu_ID(int sediu_ID) {
        Sediu_ID = sediu_ID;
    }
}
