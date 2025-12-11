/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author mihajlo
 */
public class Konekcija {
    private static Connection connection;
    private static Konekcija instance;
     
    
    private Konekcija(){
        String url = "jdbc:mysql://localhost:3306/08122025_broj_indeksa";
        try {
            connection = DriverManager.getConnection(url, "root", "");
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.getLogger(Konekcija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public static Konekcija getInstance(){
        if(instance == null)
            instance = new Konekcija();
        return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
}
