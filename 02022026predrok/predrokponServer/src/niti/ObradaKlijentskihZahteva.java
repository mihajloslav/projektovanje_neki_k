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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import logika.Controller;
import static operacije.Operacije.LOGIN;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

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

    @Override
    public void run() {

        while(!kraj){
            KlijentskiZahtev kz = (KlijentskiZahtev) primiZahtev();
            String tekst = (String) kz.getParametar();
            String[] split = tekst.split(" ");
            switch (kz.getOperacija()) {
                case LOGIN:
                    if(ulogovani == null){
                        if(Controller.getInstance().getKlijenti().size() >= 2){
                            posaljiOdgovor(new ServerskiOdgovor(null, "ERROR:Server Full"));
                            break;
                        }
                        boolean postoji = false;
                        for(ObradaKlijentskihZahteva k : Controller.getInstance().getKlijenti()){
                            String kUsername = k.getUlogovani().getUsername().toLowerCase();
                            if(kUsername.equals(split[1].toLowerCase())){
                                postoji = true;
                            }
                        }
                        if(postoji){
                            posaljiOdgovor(new ServerskiOdgovor(null, "ERROR:User already logged"));
                            break;
                        }

                        for(Korisnik k : Controller.getInstance().getKorisnici()){
                            String kUsername = k.getUsername().toLowerCase();
                            if(kUsername.equals(split[1].toLowerCase())){
                                System.out.println("VALJA");
                                ulogovani = k;
                            }
                        }
                        if(ulogovani != null){
                            ulogovani.setStatus(true);
                            Controller.getInstance().getsF().refreshuj();
                            Controller.getInstance().getKlijenti().add(this);
                            posaljiOdgovor(new ServerskiOdgovor(ulogovani, "USPEŠNO STE SE ULOGOVALI!!!"));
                            break;
                        }
                        else
                            posaljiOdgovor(new ServerskiOdgovor(ulogovani, "ERROR:Unknown user"));
                        break;
                    }
                    posaljiOdgovor(new ServerskiOdgovor(null, "VEĆ STE PRIJAVLJENI"));
                    break;

                case ADD:
                    if(split.length != 3){
                        posaljiOdgovor(new ServerskiOdgovor(null, "NISTE LEPO UNELI KOMANDU!"));
                        break;
                    }
                    int a;
                    int b;
                    try{
                        a = Integer.parseInt(split[1]);
                        b = Integer.parseInt(split[2]);
                    }catch(NumberFormatException n){
                        posaljiOdgovor(new ServerskiOdgovor(null, "NISTE LEPO UNELI ARGUMENTE!"));
                        break;
                    }
                    int c = a+b;
                    posaljiOdgovor(new ServerskiOdgovor(c, "RESULT " + c));
                    break;

                case MINMAX:
                    List<Integer> brojevi = new ArrayList<>();
                    try{
                        for(int i = 1; i < split.length;i++){
                            brojevi.add(Integer.valueOf(split[i]));
                        }
                    }catch(NumberFormatException n){
                        posaljiOdgovor(new ServerskiOdgovor(null, "NISTE LEPO UNELI ARGUMENTE"));
                        break;
                    }
                    int min = Collections.min(brojevi);
                    int max = Collections.max(brojevi);
                    String odg = "MIN " + min + " MAX " + max;
                    posaljiOdgovor(new ServerskiOdgovor(odg, odg));
                    break;
                case DATE:
                    DateTimeFormatter formatD = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                    String datum = LocalDate.now().format(formatD);
                    posaljiOdgovor(new ServerskiOdgovor(datum, "CURRENT DATE " + datum));                
                    break;
                case LOGOUT:
                    Controller.getInstance().getKlijenti().remove(this);
                    for(Korisnik k : Controller.getInstance().getKorisnici()){
                        if(k.getUsername().equals(ulogovani.getUsername()))
                            k.setStatus(false);
                    }
                    Controller.getInstance().refreshuj();
                    ugasiKlijenta(); 
                    break;

                default:
                    posaljiOdgovor(new ServerskiOdgovor(null, "UNELI STE NEPOSTOJEĆU KOMANDU"));
                    break;
            }
        }
    }
    
    public void ugasiKlijenta(){
        kraj = true;
        if(s != null && s.isClosed()){
            try {
                s.close();
            } catch (IOException ex) {
                System.getLogger(ObradaKlijentskihZahteva.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    
    
    
    public Object primiZahtev(){
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
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

    public Korisnik getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Korisnik ulogovani) {
        this.ulogovani = ulogovani;
    }
}
