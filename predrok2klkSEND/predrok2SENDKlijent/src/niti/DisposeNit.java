/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import forme.KlijentskaForma;

/**
 *
 * @author student2
 */
public class DisposeNit extends Thread{
    private int sekunde = 0;
    private KlijentskaForma kF;

    @Override
    public void run() {
        while(sekunde <= 10){
            try {
                kF.proveri(sekunde);
                sekunde++;
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.getLogger(DisposeNit.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            
        }
    }

    public KlijentskaForma getkF() {
        return kF;
    }

    public void setkF(KlijentskaForma kF) {
        this.kF = kF;
    }
    
    
}
