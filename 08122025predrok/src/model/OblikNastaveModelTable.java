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
public class OblikNastaveModelTable extends AbstractTableModel {
     String[] kolone = {"Oblik nastave"};
    private List<OblikNastave> lista = new ArrayList<>();
    public OblikNastaveModelTable(List<OblikNastave> lista) {
        this.lista = lista;
    }
    
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
       return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OblikNastave o = lista.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return o.toString();
            default:
                return "N/A";
        }
        
    }
}
