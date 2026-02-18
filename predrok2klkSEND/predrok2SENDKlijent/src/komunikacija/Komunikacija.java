/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import forme.KlijentskaForma;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import niti.PorukeNit;
import transfer.KlijentskiZahtev;

/**
 *
 * @author student2
 */
public class Komunikacija {
    private KlijentskaForma kF;
    private static Komunikacija instance;
    private final String url = "localhost";
    private final int port = 9000;
    private Socket s;
    private PorukeNit pn;
    private Komunikacija(){
        try {
            s = new Socket(url, port);
            pn = new PorukeNit();
        } catch (IOException ex) {
            System.getLogger(Komunikacija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
               
    }
    
    public static Komunikacija getInstance() {
        if(instance == null)
            instance = new Komunikacija();
        return instance;
    }
    
    
    public Object primiOdgovor(){
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.getLogger(Komunikacija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
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

    public void prikaziPoruku(String string) {
        kF.prikaziPoruku(string);
    }

    public KlijentskaForma getkF() {
        return kF;
    }

    public void setkF(KlijentskaForma kF) {
        this.kF = kF;
    }

    public PorukeNit getPn() {
        return pn;
    }

    public void setPn(PorukeNit pn) {
        this.pn = pn;
    }
    
}
