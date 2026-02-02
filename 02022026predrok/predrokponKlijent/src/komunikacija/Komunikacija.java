/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Korisnik;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KlijentskiZahtev;

/**
 *
 * @author mihajlo
 */
public class Komunikacija {
    private static Komunikacija instance;
    private final String url = "localhost";
    private final int port = 9000;
    private Socket s;
    private Korisnik ulogovani = null;
    private Komunikacija(){
        try {
            s = new Socket(url, port);
        } catch (IOException ex) {
            System.getLogger(Komunikacija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    
    }
    public static Komunikacija getInstance(){
        if(instance == null)
            instance = new Komunikacija();
        return instance;
    }

    public Korisnik getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Korisnik ulogovani) {
        this.ulogovani = ulogovani;
    }
    
    public void posaljiZahtev(KlijentskiZahtev kz){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(kz);
            oos.flush();
        } catch (IOException ex) {
            System.getLogger(Komunikacija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    public Object primiOdgovor(){
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return ois.readObject();
        } catch (IOException ex) {
            System.getLogger(Komunikacija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(Komunikacija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
}
