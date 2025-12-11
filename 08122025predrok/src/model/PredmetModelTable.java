/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mihajlo
 */
public class PredmetModelTable extends AbstractTableModel {
    String[] kolone = {"ID", "Naziv", "ESPB"};
    private List<Predmet> listaPredmeta = new ArrayList<>();
    public PredmetModelTable(List<Predmet> lista) {
        this.listaPredmeta = lista;
    }

    public List<Predmet> getListaPredmeta() {
        return listaPredmeta;
    }

    public void setListaPredmeta(List<Predmet> listaPredmeta) {
        this.listaPredmeta = listaPredmeta;
    }
    
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public int getRowCount() {
        return listaPredmeta.size();
    }

    @Override
    public int getColumnCount() {
       return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Predmet p = listaPredmeta.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return p.getId();
            case 1:
                return p.getNaziv();
            case 2:
                return p.getEspb();
            default:
                return "N/A";
        }
        
    }
    
}
