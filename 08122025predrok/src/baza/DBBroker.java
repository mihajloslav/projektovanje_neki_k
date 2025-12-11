/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import controller.Controller;
import java.util.ArrayList;
import java.util.List;
import model.Nastavnik;
import model.OblikNastave;
import model.Predmet;
import java.sql.Statement;
/**
 *
 * @author mihajlo
 */
public class DBBroker {

    public boolean UlogujNastavnika(String email, String sifra) {
        String upit = "SELECT * FROM nastavnik WHERE email=? AND sifra=?;";
        PreparedStatement ps;
        try {
            ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, email);
            ps.setString(2, sifra);
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                int rsid = rs.getInt("id");
                String rsEmail = rs.getString("email");
                String rsSifra = rs.getString("sifra");
                if(rsEmail.equals(email) && rsSifra.equals(sifra)){
                    Nastavnik odabraniNastavnik = new Nastavnik(rsid, rsEmail, rsSifra);
                    Controller.getInstance().setOdabraniNastavnik(odabraniNastavnik);
                    return true;
                }
                
            }
        } catch (SQLException ex) {
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }

        
        return false;
    }

    public List<Predmet> vratiListuPredmetaZaNastavnika() {
        List<Predmet> listaPredmeta = new ArrayList<>();
        String upit = "SELECT p.id AS \"p.id\", naziv, espb, oblik FROM predmet p JOIN angazovanje a ON(p.id = a.predmet_id) WHERE nastavnik_id=?;";
        PreparedStatement ps;
        try {
            ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, Controller.getInstance().getOdabraniNastavnik().getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                int pid = rs.getInt("p.id");
                String naziv = rs.getString("naziv");
                int espb = rs.getInt("espb");
                
                Predmet novi = new Predmet(pid,naziv,espb);
                if(!listaPredmeta.contains(novi)){
                    listaPredmeta.add(novi);
                }
                
            }
        } catch (SQLException ex) {
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return listaPredmeta;

    }

    public List<OblikNastave> vratiOblikeNastaveZaPredmet(Predmet p) {
        List<OblikNastave> listaON = new ArrayList<>();
        String upit = "SELECT oblik FROM angazovanje WHERE nastavnik_ID=? AND predmet_id=?";
        PreparedStatement ps;
        try {
            ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, Controller.getInstance().getOdabraniNastavnik().getId());
            ps.setInt(2, p.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                OblikNastave on = OblikNastave.valueOf(rs.getString("oblik"));
                listaON.add(on);
            }
        } catch (SQLException ex) {
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return listaON;
    }

    public List<Predmet> vratiListuSvihPredmeta() {
        String upit = "SELECT * FROM predmet";
        List<Predmet> listaPredmeta = new ArrayList<>();
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
                int id = rs.getInt("id");
                String naziv = rs.getString("naziv");
                int espb = rs.getInt("espb");
                
                listaPredmeta.add(new Predmet(id,naziv,espb));
                
       
            }
            
        } catch (SQLException ex) {
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return listaPredmeta;
        
    }

    public boolean unesiNovoAngazovanje(Predmet predmet, OblikNastave oblikNastave) {
       String upit = "INSERT INTO angazovanje(nastavnik_id, predmet_id, oblik)VALUES(?,?,?);";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, Controller.getInstance().getOdabraniNastavnik().getId());
            ps.setInt(2, predmet.getId());
            ps.setString(3, String.valueOf(oblikNastave));

            int rows = ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
            if(rows > 0)
                return true;
            

            
        } catch (SQLException ex) {
           try {
               Konekcija.getInstance().getConnection().rollback();
           } catch (SQLException ex1) {
               System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex1);
           }
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }
       
       
       return false;
       
    }
    
}
