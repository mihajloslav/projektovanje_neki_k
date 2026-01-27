/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;
import domen.Korisnik;
import domen.Poruka;
import java.util.List;
import komunikacija.Komunikacija;
import operacije.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
/**
 *
 * @author mihajlo
 */
public class AzurirajPodatke extends Thread{
    private boolean kraj = false;

    public boolean isKraj() {
        return kraj;
    }

    public void setKraj(boolean kraj) {
        this.kraj = kraj;
    }
    
    @Override
    public void run() {
        while(!kraj){
            try {
                Komunikacija.getInstance().posaljiZahtev(new KlijentskiZahtev(Operacije.LISTA_ULOGOVANIH, null));
                ServerskiOdgovor so = (ServerskiOdgovor) Komunikacija.getInstance().primiOdgovor();
                List<Korisnik> korisnici = (List<Korisnik>) so.getOdgovor();
                Komunikacija.getInstance().setKorisnici(korisnici);
                System.out.println("UCITAO KORISNIKE");
                sleep(1000);
                Komunikacija.getInstance().posaljiZahtev(new KlijentskiZahtev(Operacije.LISTA_PORUKA, null));
                ServerskiOdgovor so2 = (ServerskiOdgovor) Komunikacija.getInstance().primiOdgovor();
                List<Poruka> poruke = (List<Poruka>) so2.getOdgovor();
                Komunikacija.getInstance().setPoruke(poruke);
                System.out.println("UCITAO PORUKE");
                Komunikacija.getInstance().getKf().osveziPodatke();
                sleep(5000);
            } catch (InterruptedException ex) {
                System.getLogger(AzurirajPodatke.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            
        }
    }
    
}
