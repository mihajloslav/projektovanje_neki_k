/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import logika.Controller;

/**
 *
 * @author mihajlo
 */
public class PokreniServer extends Thread{
    private ServerSocket ss;
    private boolean kraj = false;

    public PokreniServer() {
        
    }

    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);
            System.out.println("SERVER JE POKRENUT NA PORTU 9000");
            while(!kraj){
                Socket s = ss.accept();
                System.out.println("NOVI KLIJENT JE POVEZAN NA SERVER!");
                ObradaKlijentskihZahteva klijent = new ObradaKlijentskihZahteva(s);
                klijent.start();
                Controller.getInstance().getKlijenti().add(klijent);
                
                
            }
        } catch (IOException ex) {
            System.getLogger(PokreniServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        while(!kraj){
            
        }
    }
    
    public void ugasiServer(){
        kraj = true;
        for(ObradaKlijentskihZahteva klijent : Controller.getInstance().getKlijenti()){
            klijent.ugasiKlijenta();
        }
        Controller.getInstance().getKlijenti().clear();
        if(ss != null && !ss.isClosed()){
            try {
                ss.close();
            } catch (IOException ex) {
                System.getLogger(PokreniServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }

    public boolean isKraj() {
        return kraj;
    }

    public void setKraj(boolean kraj) {
        this.kraj = kraj;
    }
    
    
}
