/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;
import domen.Poruka;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mihajlo
 */
public class PorukaTableModel extends AbstractTableModel{
    private List<Poruka> poruke;
    private String[] kolone = {"Posiljalac", "Primalac", "Vreme", "Kratak tekst"};
    public PorukaTableModel(List<Poruka> poruke) {
        this.poruke = poruke;
    }
    
    

    @Override
    public int getRowCount() {
        return poruke.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Poruka poruka = poruke.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return poruka.getPosiljalacEmail();
            case 1:
               if(poruka.getPrimalacEmail().size() > 1)
                   return "SVIMA";
               else
                   return poruka.getPrimalacEmail().get(0);
            case 2:
                return poruka.getVreme();
            case 3:
                String tekst = poruka.getTekst();
                if(tekst.length() <= 20)
                    return tekst;
                else
                    return tekst.substring(0, 20);
            default:
                return "GRESKA";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Poruka> getPoruke() {
        return poruke;
    }

    public void setPoruke(List<Poruka> poruke) {
        this.poruke = poruke;
    }
    
}
