/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Korisnik;
import domen.Poruka;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import logika.Controller;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import operacije.Operacije;

/**
 *
 * @author mihajlo
 */
public class ObradaKlijentskihZahteva extends Thread{
    private Socket s;
    private boolean kraj = false;
    private Korisnik ulogovani;
    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    public Korisnik getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Korisnik ulogovani) {
        this.ulogovani = ulogovani;
    }
    

    @Override
    public void run() {
        while(!kraj){
            KlijentskiZahtev kz = (KlijentskiZahtev) primiZahtev();
            
            switch (kz.getOperacija()) {
                case LOGIN:
                    Korisnik k = (Korisnik)kz.getParametar();
                    boolean postoji = false;
                    for(Korisnik korisnik : Controller.getInstance().getKorisnici()){
                        if(korisnik.getEmail().equals(k.getEmail()) && korisnik.getLozinka().equals(k.getLozinka())){
                            posaljiOdgovor(new ServerskiOdgovor(korisnik, "USPEŠNO"));
                            
                            postoji = true;
                            ulogovani = korisnik;
                            Controller.getInstance().getUlogovaniKorisnici().add(korisnik);
                        }
                    }
                    if(!postoji)
                        posaljiOdgovor(new ServerskiOdgovor(null, "NEUSPEŠNO"));
                    break;
                case SEND:
                    Poruka p = (Poruka)kz.getParametar();
                    Controller.getInstance().getPoruke().add(p);
                    Controller.getInstance().getSf().osveziTabelu();
                    posaljiOdgovor(new ServerskiOdgovor(true, "PORUKA JE USPEŠNO POSLATA"));
                    break;
                case LISTA_ULOGOVANIH:
                    posaljiOdgovor(new ServerskiOdgovor(Controller.getInstance().getUlogovaniKorisnici(), ""));
                    break;
                case LISTA_PORUKA:
                    posaljiOdgovor(new ServerskiOdgovor(Controller.getInstance().getPoruke(), ""));
                    break;
                default:
                    throw new AssertionError();
                
            }
        }
    }

    public boolean isKraj() {
        return kraj;
    }

    public void setKraj(boolean kraj) {
        this.kraj = kraj;
    }
    
    public void ugasiKlijenta(){
        kraj = true;
        if(s != null && !s.isClosed()){
            try {
                s.close();
            } catch (IOException ex) {
                System.getLogger(ObradaKlijentskihZahteva.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    
    }
    
    private Object primiZahtev(){
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return ois.readObject();
        } catch (IOException ex) {
            System.getLogger(ObradaKlijentskihZahteva.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(ObradaKlijentskihZahteva.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
    private void posaljiOdgovor(ServerskiOdgovor so){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            System.getLogger(ObradaKlijentskihZahteva.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
