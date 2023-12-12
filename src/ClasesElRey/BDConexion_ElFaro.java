/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesElRey;


import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author jluis
 */

public class BDConexion_ElFaro {
    
    public static final String URL = "jdbc:mysql://26.175.57.65:3306/elrey?useTimezone=true&serverTimezone=UTC";//EL FARO
    public static final String USER = "elrey";
    public static final String CLAVE = "campana";
     
    public Connection getConexion(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USER, CLAVE);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(null,"LA MAQUINA NO ESTA ENCENDIDA");
        }
        return con;
    }
   
    }
    
    
    
    

