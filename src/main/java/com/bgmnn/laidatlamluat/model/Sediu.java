

/** Clasa care poate stoca toate datele din tabela sedii
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.model;

public class Sediu {
    private int sediu_ID;
    private String sediu_Nume;
    private String sediu_Adresa;
    private String sediu_Telefon;
    private String sediu_Email;
    private String sediu_Orar_Functionare;

    public Sediu() {}

    public Sediu(int sediu_ID, String sediu_Nume, String sediu_Adresa, String sediu_Telefon, String sediu_Email, String sediu_Orar_Functionare) {
        this.sediu_ID = sediu_ID;
        this.sediu_Nume = sediu_Nume;
        this.sediu_Adresa = sediu_Adresa;
        this.sediu_Telefon = sediu_Telefon;
        this.sediu_Email = sediu_Email;
        this.sediu_Orar_Functionare = sediu_Orar_Functionare;
    }

    public int getSediu_ID() {
        return sediu_ID;
    }

    public void setSediu_ID(int sediu_ID) {
        this.sediu_ID = sediu_ID;
    }

    public String getSediu_Nume() {
        return sediu_Nume;
    }

    public void setSediu_Nume(String sediu_Nume) {
        this.sediu_Nume = sediu_Nume;
    }

    public String getSediu_Adresa() {
        return sediu_Adresa;
    }

    public void setSediu_Adresa(String sediu_Adresa) {
        this.sediu_Adresa = sediu_Adresa;
    }

    public String getSediu_Telefon() {
        return sediu_Telefon;
    }

    public void setSediu_Telefon(String sediu_Telefon) {
        this.sediu_Telefon = sediu_Telefon;
    }

    public String getSediu_Email() {
        return sediu_Email;
    }

    public void setSediu_Email(String sediu_Email) {
        this.sediu_Email = sediu_Email;
    }

    public String getSediu_Orar_Functionare() {
        return sediu_Orar_Functionare;
    }

    public void setSediu_Orar_Functionare(String sediu_Orar_Functionare) {
        this.sediu_Orar_Functionare = sediu_Orar_Functionare;
    }
}