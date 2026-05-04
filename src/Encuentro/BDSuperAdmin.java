/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encuentro;

import ClasesElRey.BDConexion_Encuentro;
import ClasesElRey.BDConexion_Pinula;
import ClasesElRey.InsertarProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author it
 */
public class BDSuperAdmin {
    

    
    
     public static InsertarProducto BuscarTotalMesEncuentro(String a,String b) throws SQLException{
        return buscarTotalmesSantain(a ,b,null);
    }
     
    public static InsertarProducto buscarTotalmesSantain(String a,String b, InsertarProducto c) throws SQLException {
             
            BDConexion_Encuentro conecta = new BDConexion_Encuentro();
            Connection cn = conecta.getConexion();
            PreparedStatement ps = null;
            ps = cn.prepareStatement("select SUM(TOTAL) AS TOTAL,SUM(efectivo) AS efectivo,SUM(Tarjeta) AS Tarjeta, count(*) as ORDENES from pedidos where  FECHA between '"+a+"' and date_add('"+b+"', interval 1 day);");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
               if (c==null)
               {c = new InsertarProducto(){};}
               c.setNoOrden(rs.getInt("ORDENES"));
               c.setTotal(rs.getDouble("TOTAL"));
               c.setEfectivo(rs.getDouble("efectivo"));
               c.setTarjeta(rs.getDouble("Tarjeta"));
            }
            cn.close();
            ps.close();
            return c;
} 
     
     
     public static InsertarProducto BuscarTotalMesPalencia(String a,String b) throws SQLException{
        return buscarTotalmesPalencia(a ,b,null);
    }
     
      public static InsertarProducto buscarTotalmesPalencia(String a,String b, InsertarProducto c) throws SQLException {
             
            BDConexion_Pinula conecta = new BDConexion_Pinula();
            Connection cn = conecta.getConexion();
            PreparedStatement ps = null;
            ps = cn.prepareStatement("select SUM(TOTAL) AS TOTAL,SUM(efectivo) AS efectivo,SUM(Tarjeta) AS Tarjeta, count(*) as ORDENES from ordenes where FECHA between '"+a+"' and date_add('"+b+"', interval 1 day);");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
               if (c==null)
               {c = new InsertarProducto(){};}
               c.setNoOrden(rs.getInt("ORDENES"));
               c.setTotal(rs.getDouble("TOTAL"));
               c.setEfectivo(rs.getDouble("efectivo"));
               c.setTarjeta(rs.getDouble("Tarjeta"));
            }
            cn.close();
            ps.close();
            return c;
} 
     
  
}
