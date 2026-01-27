/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author mihajlo
 */
public class Poruka implements Serializable{
    private String posiljalacEmail;
    private List<String> primalacEmail;
    private LocalDateTime vreme;
    private String tekst;

    public String getPosiljalacEmail() {
        return posiljalacEmail;
    }

    public void setPosiljalacEmail(String posiljalacEmail) {
        this.posiljalacEmail = posiljalacEmail;
    }

    public List<String> getPrimalacEmail() {
        return primalacEmail;
    }

    public void setPrimalacEmail(List<String> primalacEmail) {
        this.primalacEmail = primalacEmail;
    }

    public LocalDateTime getVreme() {
        return vreme;
    }

    public void setVreme(LocalDateTime vreme) {
        this.vreme = vreme;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Poruka() {
    }

    public Poruka(String posiljalacEmail, List<String> primalacEmail, LocalDateTime vreme, String tekst) {
        this.posiljalacEmail = posiljalacEmail;
        this.primalacEmail = primalacEmail;
        this.vreme = vreme;
        this.tekst = tekst;
    }
    
}
