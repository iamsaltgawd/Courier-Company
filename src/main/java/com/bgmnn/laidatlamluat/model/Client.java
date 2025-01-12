

/** Clasa care poate stoca toate datele din tabela clienti
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.model;

public class Client {
    private int client_ID;
    private String client_Nume;
    private String client_Adresa;
    private String client_Telefon;
    private String client_Email;

    public int getClient_ID() {
        return client_ID;
    }

    public void setClient_ID(int client_ID) {
        this.client_ID = client_ID;
    }

    public String getClient_Nume() {
        return client_Nume;
    }

    public void setClient_Nume(String client_Nume) {
        this.client_Nume = client_Nume;
    }

    public String getClient_Adresa() {
        return client_Adresa;
    }

    public void setClient_Adresa(String client_Adresa) {
        this.client_Adresa = client_Adresa;
    }

    public String getClient_Telefon() {
        return client_Telefon;
    }

    public void setClient_Telefon(String client_Telefon) {
        this.client_Telefon = client_Telefon;
    }

    public String getClient_Email() {
        return client_Email;
    }

    public void setClient_Email(String client_Email) {
        this.client_Email = client_Email;
    }
}
