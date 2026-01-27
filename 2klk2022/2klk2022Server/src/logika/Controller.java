/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import domen.Korisnik;
import domen.Poruka;
import forme.ServerskaForma;
import java.util.ArrayList;
import java.util.List;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author mihajlo
 */
public class Controller {
    private static Controller instance;
    private ServerskaForma sf;

    public ServerskaForma getSf() {
        return sf;
    }

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }
    private List<ObradaKlijentskihZahteva> klijenti;
    private List<Korisnik> korisnici;
    private List<Poruka> poruke;
    private List<Korisnik> ulogovaniKorisnici;
    private Controller(){
        klijenti = new ArrayList<>();
        korisnici = new ArrayList<>();
        poruke = new ArrayList<>();
        ulogovaniKorisnici = new ArrayList<>();
        korisnici.add(new Korisnik("pera1@gmail.com", "k1", "Pera 1", "Peric 1"));
        korisnici.add(new Korisnik("pera2@gmail.com", "k2", "Pera 2", "Peric 2"));
        korisnici.add(new Korisnik("pera3@gmail.com", "k3", "Pera 3", "Peric 3"));
    }
    public static Controller getInstance() {
        if(instance == null)
            instance = new Controller();
        return instance;
    }

    public List<ObradaKlijentskihZahteva> getKlijenti() {
        return klijenti;
    }

    public void setKlijenti(List<ObradaKlijentskihZahteva> klijenti) {
        this.klijenti = klijenti;
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public List<Poruka> getPoruke() {
        return poruke;
    }

    public void setPoruke(List<Poruka> poruke) {
        this.poruke = poruke;
    }

    public List<Korisnik> getUlogovaniKorisnici() {
        return ulogovaniKorisnici;
    }

    public void setUlogovaniKorisnici(List<Korisnik> ulogovaniKorisnici) {
        this.ulogovaniKorisnici = ulogovaniKorisnici;
    }

    
}
