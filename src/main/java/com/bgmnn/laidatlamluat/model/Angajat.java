package com.bgmnn.laidatlamluat.model;

import java.util.Date;

public class Angajat {
    private int angajat_ID;
    private String angajat_Nume;
    private String angajat_Prenume;
    private String angajat_Rol;
    private String angajat_Telefon;
    private String angajat_Email;
    private Date angajat_Data_Angajare;
    private int sediu_ID;

    public Angajat() {}

    public Angajat(int angajat_ID, String angajat_Nume, String angajat_Prenume, String angajat_Rol, String angajat_Telefon, String angajat_Email, Date angajat_Data_Angajare, int sediu_ID) {
        this.angajat_ID = angajat_ID;
        this.angajat_Nume = angajat_Nume;
        this.angajat_Prenume = angajat_Prenume;
        this.angajat_Rol = angajat_Rol;
        this.angajat_Telefon = angajat_Telefon;
        this.angajat_Email = angajat_Email;
        this.angajat_Data_Angajare = angajat_Data_Angajare;
        this.sediu_ID = sediu_ID;
    }

    public int getAngajat_ID() {
        return angajat_ID;
    }

    public void setAngajat_ID(int angajat_ID) {
        this.angajat_ID = angajat_ID;
    }

    public String getAngajat_Nume() {
        return angajat_Nume;
    }

    public void setAngajat_Nume(String angajat_Nume) {
        this.angajat_Nume = angajat_Nume;
    }

    public String getAngajat_Prenume() {
        return angajat_Prenume;
    }

    public void setAngajat_Prenume(String angajat_Prenume) {
        this.angajat_Prenume = angajat_Prenume;
    }

    public String getAngajat_Rol() {
        return angajat_Rol;
    }

    public void setAngajat_Rol(String angajat_Rol) {
        this.angajat_Rol = angajat_Rol;
    }

    public String getAngajat_Telefon() {
        return angajat_Telefon;
    }

    public void setAngajat_Telefon(String angajat_Telefon) {
        this.angajat_Telefon = angajat_Telefon;
    }

    public String getAngajat_Email() {
        return angajat_Email;
    }

    public void setAngajat_Email(String angajat_Email) {
        this.angajat_Email = angajat_Email;
    }

    public Date getAngajat_Data_Angajare() {
        return angajat_Data_Angajare;
    }

    public void setAngajat_Data_Angajare(Date angajat_Data_Angajare) {
        this.angajat_Data_Angajare = angajat_Data_Angajare;
    }

    public int getSediu_ID() {
        return sediu_ID;
    }

    public void setSediu_ID(int sediu_ID) {
        this.sediu_ID = sediu_ID;
    }

    // ToString (pentru debug)
    @Override
    public String toString() {
        return "Angajat{" +
                "angajat_ID=" + angajat_ID +
                ", angajat_Nume='" + angajat_Nume + '\'' +
                ", angajat_Prenume='" + angajat_Prenume + '\'' +
                ", angajat_Rol='" + angajat_Rol + '\'' +
                ", angajat_Telefon='" + angajat_Telefon + '\'' +
                ", angajat_Email='" + angajat_Email + '\'' +
                ", angajat_Data_Angajare=" + angajat_Data_Angajare +
                ", sediu_ID=" + sediu_ID +
                '}';
    }
}