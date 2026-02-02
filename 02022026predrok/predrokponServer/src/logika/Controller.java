/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import domen.Korisnik;
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
    private List<ObradaKlijentskihZahteva> klijenti;
    private List<Korisnik> korisnici;
    private ServerskaForma sF;

    private Controller() {
        klijenti = new ArrayList<>();
        korisnici = new ArrayList<>();
        korisnici.add(new Korisnik("zoran", false));
        korisnici.add(new Korisnik("petar", false));
        korisnici.add(new Korisnik("marko", false));
        korisnici.add(new Korisnik("zarko", false));
        korisnici.add(new Korisnik("darko", false));
        korisnici.add(new Korisnik("jovan", false));
    }
    
    public static Controller getInstance() {
        if(instance == null)
            instance = new Controller();
        return instance;
    }

    public List<ObradaKlijentskihZahteva> getKlijenti() {
        return klijenti;
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }


    public void refreshuj() {
        sF.refreshuj();
    }

    public ServerskaForma getsF() {
        return sF;
    }

    public void setsF(ServerskaForma sF) {
        this.sF = sF;
    }
    
}
