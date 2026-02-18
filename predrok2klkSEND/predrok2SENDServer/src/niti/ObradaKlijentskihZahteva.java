/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Korisnik;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import static operacije.Operacije.LOGIN;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import controller.Controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import operacije.Operacije;
/**
 *
 * @author student2
 */
public class ObradaKlijentskihZahteva extends Thread{
    private Socket s;
    private Korisnik ulogovani;
    private boolean kraj = false;
    private String poruka = null;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {     
        while(!kraj){
            KlijentskiZahtev kz = (KlijentskiZahtev) primiZahtev();
            String parametar = (String) kz.getParametar();
            System.out.println(parametar);
           
            parametar = parametar.trim();
            String[] split = parametar.split(" ");
            switch (kz.getOperacija()) {
                case LOGIN:
                    if(split.length != 2){
                        poruka = "[SERVER]: NISTE LEPO UNELI KOMANDU";
                        posaljiOdgovor(new ServerskiOdgovor(null, poruka));
                        break;
                    }
                    
                    if(Controller.getInstance().getUlogovani().size() >= 3){
                        poruka = "[SERVER]: ERROR: Server je pun";
                        posaljiOdgovor(new ServerskiOdgovor(null, poruka));
                        break;
                    }
                    
                    boolean postoji = false;
                    for(ObradaKlijentskihZahteva o : Controller.getInstance().getUlogovani()){
                        if(o.getUlogovani().getUsername().equals(split[1])){
                            postoji = true;
                            break;
                        }
                    }
                    if(postoji){
                        poruka = "[SERVER]: ERROR: Korisnik je već ulogovan";
                        posaljiOdgovor(new ServerskiOdgovor(null, poruka));
                        break;
                    }
                    boolean moze = false;
                    for(Korisnik k : Controller.getInstance().getKorisnici()){
                        if(k.getUsername().equals(split[1])){
                            moze = true;
                            ulogovani = k;
                            break;
                        }
                    
                    }
                    if(!moze){
                        poruka = "[SERVER]: ERROR: Korisnik ne postoji";
                        posaljiOdgovor(new ServerskiOdgovor(null, poruka));
                        break;
                    }
                    poruka = "[SERVER]: USPEŠNO STE SE ULOGOVALI";
                    Controller.getInstance().getUlogovani().add(this);
                    ulogovani.setStatus(true);
                    Controller.getInstance().osveziTabelu();
                    System.out.println("USPESNO LOGOVANJE");
                    posaljiOdgovor(new ServerskiOdgovor(ulogovani, poruka));
                    break;
                case SEND:
                    if(split.length < 2){
                        poruka = "[SERVER]: NISTE LEPO UNELI KOMANDU";
                        break;
                    }
                    ObradaKlijentskihZahteva klijent = null;
                    for(ObradaKlijentskihZahteva o : Controller.getInstance().getUlogovani()){
                        if(o.getUlogovani().getUsername().equals(split[1])){
                            klijent = o;
                        }
                    }
                    if(klijent == null){
                        poruka = "[SERVER]: ERROR: KORISNIK NIJE ULOGOVAN ILI NE POSTOJI!";
                        break;
                    }
                    
                    String zaSlanje = "["+ ulogovani.getUsername() + "]: ";
                    for(int i = 2 ; i < split.length; i++){
                        zaSlanje += split[i] + " ";
                    }
                    this.poruka = zaSlanje;
                    klijent.poruka = zaSlanje;
                    
                    
                    
                    break;
                case PORUKA:
                    posaljiOdgovor(new ServerskiOdgovor(poruka, poruka));
                    poruka = "";
                    break;
                case BROADCAST:
                    if(split.length < 2){
                        poruka = "[SERVER]: NISTE LEPO UNELI KOMANDU";
                        break;
                    }
                    String zaSlanje2 = "";
                    for(int i = 1 ; i < split.length; i++){
                        zaSlanje2 += split[i] + " ";
                    }
                    for(ObradaKlijentskihZahteva o : Controller.getInstance().getUlogovani()){
                        /*if(!o.ulogovani.getUsername().equals(ulogovani.getUsername())){ //OVO AKO NE ŽELITE SEBI DA ŠALJETE PORUKU
                            o.setPoruka("["+ ulogovani.getUsername() + "]: " + zaSlanje2); 
                        }*/
                        o.setPoruka("["+ ulogovani.getUsername() + "]: " + zaSlanje2); 
                    }
                    break;
                case LOGOUT:
                    ugasiKlijenta();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
    

    public Korisnik getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Korisnik ulogovani) {
        this.ulogovani = ulogovani;
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
    
    private Object primiZahtev(){
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return ois.readObject(); 
        } catch (IOException | ClassNotFoundException ex) {
            System.getLogger(ObradaKlijentskihZahteva.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    
    }
    public void ugasiKlijenta(){
        kraj = true;
        Controller.getInstance().getUlogovani().remove(this);
        for(Korisnik k : Controller.getInstance().getKorisnici()){
            if(k.getUsername().equals(ulogovani.getUsername()))
                k.setStatus(false);
        }
        Controller.getInstance().osveziTabelu();
        if(s != null && !s.isClosed()){
            try {
                s.close();
            } catch (IOException ex) {
                System.getLogger(ObradaKlijentskihZahteva.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    
    }

    public boolean isKraj() {
        return kraj;
    }

    public void setKraj(boolean kraj) {
        this.kraj = kraj;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
    
}
