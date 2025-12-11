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
public class Angazovanje {
    private int id;
    private Nastavnik nastavnik;
    private Predmet predmet;

    public Angazovanje(int id, Nastavnik nastavnik, Predmet predmet, OblikNastave oblikNastave) {
        this.id = id;
        this.nastavnik = nastavnik;
        this.predmet = predmet;
        this.oblikNastave = oblikNastave;
    }
    private OblikNastave oblikNastave;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.nastavnik);
        hash = 47 * hash + Objects.hashCode(this.predmet);
        hash = 47 * hash + Objects.hashCode(this.oblikNastave);
        return hash;
    }

    @Override
    public String toString() {
        return "Angazovanje{" + "id=" + id + ", nastavnik=" + nastavnik + ", predmet=" + predmet + ", oblikNastave=" + oblikNastave + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public OblikNastave getOblikNastave() {
        return oblikNastave;
    }

    public void setOblikNastave(OblikNastave oblikNastave) {
        this.oblikNastave = oblikNastave;
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
        final Angazovanje other = (Angazovanje) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nastavnik, other.nastavnik)) {
            return false;
        }
        if (!Objects.equals(this.predmet, other.predmet)) {
            return false;
        }
        return this.oblikNastave == other.oblikNastave;
    }
    
    
}
