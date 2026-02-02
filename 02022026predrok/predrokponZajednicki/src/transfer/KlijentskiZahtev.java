/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;
import java.io.Serializable;
import operacije.Operacije;
/**
 *
 * @author mihajlo
 */
public class KlijentskiZahtev implements Serializable{
    private Operacije operacija;
    private Object parametar;

    public KlijentskiZahtev() {
    }

    public KlijentskiZahtev(Operacije operacija, Object parametar) {
        this.operacija = operacija;
        this.parametar = parametar;
    }

    public Operacije getOperacija() {
        return operacija;
    }

    public void setOperacija(Operacije operacija) {
        this.operacija = operacija;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }
    
    
}
