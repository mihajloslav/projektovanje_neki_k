/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import forme.KlijentskaForma;

/**
 *
 * @author mihajlo
 */
public class SekundeNit extends Thread{
    private KlijentskaForma kF;
    private int sekunde = 0;

    public SekundeNit(KlijentskaForma kF) {
        this.kF = kF;
    }

    @Override
    public void run() {
        while(sekunde < 10){
            sekunde++;
            kF.proveri(sekunde);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.getLogger(SekundeNit.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }

    }
    
    
    
}
