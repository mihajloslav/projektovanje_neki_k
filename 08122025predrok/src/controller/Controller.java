/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import java.util.List;
import model.Nastavnik;
import model.OblikNastave;
import model.Predmet;

/**
 *
 * @author mihajlo
 */
public class Controller {
    private static Controller instance;
    private DBBroker dbb;
    private Nastavnik odabraniNastavnik = null;

    public Nastavnik getOdabraniNastavnik() {
        return odabraniNastavnik;
    }

    public void setOdabraniNastavnik(Nastavnik odabraniNastavnik) {
        this.odabraniNastavnik = odabraniNastavnik;
    }
    
    private Controller(){
        dbb = new DBBroker();
    }
    
    public static Controller getInstance(){
        if(instance == null)
            instance = new Controller();
        return instance;
    }

    public boolean UlogujNastavnika(String email, String sifra) {
        return dbb.UlogujNastavnika(email, sifra);
    }

    public List<Predmet> vratiListuPredmetaZaNastavnika() {
        return dbb.vratiListuPredmetaZaNastavnika();
    }

    public List<OblikNastave> vratiOblikeNastaveZaPredmet(Predmet p) {
        return dbb.vratiOblikeNastaveZaPredmet(p);
    }

    public List<Predmet> vratiListuSvihPredmeta() {
        return dbb.vratiListuSvihPredmeta();
    }

    public boolean unesiNovoAngazovanje(Predmet predmet, OblikNastave oblikNastave) {
        return dbb.unesiNovoAngazovanje(predmet, oblikNastave);
    }
    
    
    
    
    
    
    
    
}
