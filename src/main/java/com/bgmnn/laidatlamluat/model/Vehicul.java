

/** Clasa care poate stoca toate datele din tabela vehicule
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.model;

public class Vehicul {
    private int vehicul_ID;
    private String vehicul_Tip;
    private int vehicul_Capacitate;
    private String vehicul_Num_Inmatriculare;
    private String vehicul_Status;
    private int sediu_ID;

    public Vehicul() {}

    public Vehicul(int vehicul_ID, String vehicul_Tip, int vehicul_Capacitate, String vehicul_Num_Inmatriculare, String vehicul_Status, int sediu_ID) {
        this.vehicul_ID = vehicul_ID;
        this.vehicul_Tip = vehicul_Tip;
        this.vehicul_Capacitate = vehicul_Capacitate;
        this.vehicul_Num_Inmatriculare = vehicul_Num_Inmatriculare;
        this.vehicul_Status = vehicul_Status;
        this.sediu_ID = sediu_ID;
    }

    public int getVehicul_ID() {
        return vehicul_ID;
    }

    public void setVehicul_ID(int vehicul_ID) {
        this.vehicul_ID = vehicul_ID;
    }

    public String getVehicul_Tip() {
        return vehicul_Tip;
    }

    public void setVehicul_Tip(String vehicul_Tip) {
        this.vehicul_Tip = vehicul_Tip;
    }

    public int getVehicul_Capacitate() {
        return vehicul_Capacitate;
    }

    public void setVehicul_Capacitate(int vehicul_Capacitate) {
        this.vehicul_Capacitate = vehicul_Capacitate;
    }

    public String getVehicul_Num_Inmatriculare() {
        return vehicul_Num_Inmatriculare;
    }

    public void setVehicul_Num_Inmatriculare(String vehicul_Num_Inmatriculare) {
        this.vehicul_Num_Inmatriculare = vehicul_Num_Inmatriculare;
    }

    public String getVehicul_Status() {
        return vehicul_Status;
    }

    public void setVehicul_Status(String vehicul_Status) {
        this.vehicul_Status = vehicul_Status;
    }

    public int getSediu_ID() {
        return sediu_ID;
    }

    public void setSediu_ID(int sediu_ID) {
        this.sediu_ID = sediu_ID;
    }
}
