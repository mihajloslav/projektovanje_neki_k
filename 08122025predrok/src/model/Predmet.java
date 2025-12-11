/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author mihajlo
 */
public class Predmet {
    private int id;
    private String naziv;
    private int espb;

    @Override
    public String toString() {
        return naziv;
    }

    public int getId() {
        return id;
    }

    public Predmet(int id, String naziv, int espb) {
        this.id = id;
        this.naziv = naziv;
        this.espb = espb;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.naziv);
        hash = 53 * hash + this.espb;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Predmet other = (Predmet) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.espb != other.espb) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }
    
}
