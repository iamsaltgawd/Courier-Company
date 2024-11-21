package com.bgmnn.laidatlamluat.model;

import java.time.LocalDate;

public class Angajat {
    private int angajatId; // Cheia primară
    private String nume; // Angajat_Nume
    private String prenume; // Angajat_Prenume
    private String rol; // Angajat_Rol
    private String telefon; // Angajat_Telefon
    private String email; // Angajat_Email (Unique)
    private LocalDate dataAngajare; // Angajat_Data_Angajare
    private int sediuId; // Sediu_ID (Foreign Key)

    // Constructor fără parametri
    public Angajat() {}

    // Constructor cu parametri
    public Angajat(int angajatId, String nume, String prenume, String rol, String telefon, String email, LocalDate dataAngajare, int sediuId) {
        this.angajatId = angajatId;
        this.nume = nume;
        this.prenume = prenume;
        this.rol = rol;
        this.telefon = telefon;
        this.email = email;
        this.dataAngajare = dataAngajare;
        this.sediuId = sediuId;
    }

    // Getter pentru angajatId
    public int getAngajatId() {
        return angajatId;
    }

    // Setter pentru angajatId
    public void setAngajatId(int angajatId) {
        this.angajatId = angajatId;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataAngajare() {
        return dataAngajare;
    }

    public void setDataAngajare(LocalDate dataAngajare) {
        this.dataAngajare = dataAngajare;
    }

    public int getSediuId() {
        return sediuId;
    }

    public void setSediuId(int sediuId) {
        this.sediuId = sediuId;
    }

    // Metodă pentru a verifica dacă doi angajați sunt egali (după email, fiind unic)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Angajat angajat = (Angajat) obj;
        return email != null && email.equals(angajat.email);
    }

    // Metodă pentru generarea unui hashCode (bazat pe email, fiind unic)
    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    // Metodă toString pentru debugging și afișare
    @Override
    public String toString() {
        return "Angajat{" +
                "angajatId=" + angajatId +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", rol='" + rol + '\'' +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                ", dataAngajare=" + dataAngajare +
                ", sediuId=" + sediuId +
                '}';
    }
}
