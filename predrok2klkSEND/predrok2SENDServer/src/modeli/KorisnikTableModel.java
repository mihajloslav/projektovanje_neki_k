/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Korisnik;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author student2
 */
public class KorisnikTableModel extends AbstractTableModel{
    String[] kolone = {"Username", "Status"};
    List<Korisnik> lista;

    public KorisnikTableModel(List<Korisnik> lista) {
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
        Korisnik k = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getUsername();
            case 1:
                if(k.isStatus())
                    return true;
                else return false;
            default:
                return "GRESKA";
        }
    }
    
    
}
