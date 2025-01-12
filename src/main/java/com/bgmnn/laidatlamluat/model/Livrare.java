

/** Clasa care poate stoca toate datele din tabela livrari
 * @author Calavri Mircea-Cristian
 * @version 7 ianuarie 2025
 */


package com.bgmnn.laidatlamluat.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Livrare {
    private int Livrare_ID;
    private LocalDate Livrare_Data;
    private LocalTime Livrare_Ora;
    private String Livrare_Status;
    private int Comanda_ID;
    private int Angajat_ID;
    private int Vehicul_ID;

    public int getLivrare_ID() {
        return Livrare_ID;
    }

    public void setLivrare_ID(int livrare_ID) {
        Livrare_ID = livrare_ID;
    }

    public LocalDate getLivrare_Data() {
        return Livrare_Data;
    }

    public void setLivrare_Data(LocalDate livrare_Data) {
        Livrare_Data = livrare_Data;
    }

    public LocalTime getLivrare_Ora() {
        return Livrare_Ora;
    }

    public void setLivrare_Ora(LocalTime livrare_Ora) {
        Livrare_Ora = livrare_Ora;
    }

    public String getLivrare_Status() {
        return Livrare_Status;
    }

    public void setLivrare_Status(String livrare_Status) {
        Livrare_Status = livrare_Status;
    }

    public int getComanda_ID() {
        return Comanda_ID;
    }

    public void setComanda_ID(int comanda_ID) {
        Comanda_ID = comanda_ID;
    }

    public int getAngajat_ID() {
        return Angajat_ID;
    }

    public void setAngajat_ID(int angajat_ID) {
        Angajat_ID = angajat_ID;
    }

    public int getVehicul_ID() {
        return Vehicul_ID;
    }

    public void setVehicul_ID(int vehicul_ID) {
        Vehicul_ID = vehicul_ID;
    }
}
