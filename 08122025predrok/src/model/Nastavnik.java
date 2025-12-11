package model;


import java.util.Objects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mihajlo
 */
public class Nastavnik {
    private int id;
    private String email;
    private String sifra;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Nastavnik(int id, String email, String sifra) {
        this.id = id;
        this.email = email;
        this.sifra = sifra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return "Nastavnik{" + "id=" + id + ", email=" + email + ", sifra=" + sifra + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.email);
        hash = 41 * hash + Objects.hashCode(this.sifra);
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
        final Nastavnik other = (Nastavnik) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }
    
    
}
