/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Korisnik;
import forme.ServerskaForma;
import java.util.ArrayList;
import java.util.List;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author student2
 */
public class Controller {
    private static Controller instance;
    private ServerskaForma sF;

    public static Controller getInstance() {
        if(instance == null)
            instance = new Controller();
        return instance;
    }
    private List<Korisnik> korisnici;
    private List<ObradaKlijentskihZahteva> ulogovani;
    
    private Controller(){
        korisnici = new ArrayList<>();
        ulogovani = new ArrayList<>();
        korisnici.add(new Korisnik("marko", false));
        korisnici.add(new Korisnik("zarko", false));
        korisnici.add(new Korisnik("petar", false));
        korisnici.add(new Korisnik("ivan", false));
        korisnici.add(new Korisnik("jovan", false));
        korisnici.add(new Korisnik("aljosa", false));
    
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public List<ObradaKlijentskihZahteva> getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(List<ObradaKlijentskihZahteva> ulogovani) {
        this.ulogovani = ulogovani;
    }

    public ServerskaForma getsF() {
        return sF;
    }

    public void setsF(ServerskaForma sF) {
        this.sF = sF;
    }
    public void osveziTabelu(){
        sF.osveziTabelu();
    }
    
    
}
