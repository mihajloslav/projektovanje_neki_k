/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import forme.KlijentskaForma;
import komunikacija.Komunikacija;
import operacije.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author student2
 */
public class PorukeNit extends Thread{
    private boolean kraj = false;
    @Override
    public void run() {
        while(!kraj){
            komunikacija.Komunikacija.getInstance().posaljiZahtev(new KlijentskiZahtev(Operacije.PORUKA, ""));
            ServerskiOdgovor so = (ServerskiOdgovor) komunikacija.Komunikacija.getInstance().primiOdgovor();
            Komunikacija.getInstance().prikaziPoruku((String)so.getPoruka());
        }
    }
    public void ugasiNit(){
        kraj = true;
    
    }
    
}
