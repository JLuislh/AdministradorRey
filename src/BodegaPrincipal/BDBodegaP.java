/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BodegaPrincipal;

import ClasesElRey.BDConexion;
import ClasesElRey.InsertarProducto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jluis
 */
public class BDBodegaP {
    
    
    
////////////////////////////////////////////////////////INGRESOS DESCARGAS CUARTO FRIO////////////////////////////////////////////////

public static ArrayList<InsertarProducto>ListaProductosBodegaPrincipal () {
        return BPin("call ListaProductos"); 
 }  
    private static ArrayList<InsertarProducto> BPin(String sql){
    ArrayList<InsertarProducto> list = new ArrayList<>();
    BDConexion conecta = new BDConexion();
    Connection cn = conecta.getConexion();
    
        try {
            InsertarProducto t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new InsertarProducto();
                 t.setCodigo(rs.getInt("CODIGOBP"));
                 t.setDescripcion(rs.getString("DESCRIPCION").toUpperCase());
                 t.setNota(rs.getString("NOTA").toUpperCase());
                 t.setUMedida(rs.getString("UNIDADMEDIDA"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 list.add(t);
                            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta DE LA TABLA "+e);
            return null;
        } 
        return list;
}
    
    
    
public static ArrayList<InsertarProducto>ListaHistorialIngresosDescargas (int a,int b) {
        return BPout("call HistorialIngresosDescargas("+a+","+b+")"); 
        
 }  

    private static ArrayList<InsertarProducto> BPout(String sql){
    ArrayList<InsertarProducto> list = new ArrayList<>();
    BDConexion conecta = new BDConexion();
    Connection cn = conecta.getConexion();
    
        try {
            InsertarProducto t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new InsertarProducto();
                 t.setCantidad(rs.getInt("cantidad"));
                 t.setNota(rs.getString("NOTA").toUpperCase());
                 t.setFecha(rs.getString("fecha"));
                 list.add(t);
                            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta DE LA TABLA "+e);
            return null;
        } 
        return list;
}          
        
    

    
}
