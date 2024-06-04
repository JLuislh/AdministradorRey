/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FelAdmin;

import java.sql.*;
import ClasesElRey.BDConexion_Encuentro;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author jluis
 */
public class ObtenerProductosFactura {

    private int cantidad;
    private String Descripcion2;
    private double precio;
    private double total;
    private double grantotal;
    private String nit;
    private String Nombre;
    private int idNit;
    private int idFel;
    private String Serie;
    private String numero;
    private String autorizacion;
    private String fechaCertifica;
    private String fecha;
    private int id_pedido;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdFel() {
        return idFel;
    }

    public void setIdFel(int idFel) {
        this.idFel = idFel;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String Serie) {
        this.Serie = Serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public String getFechaCertifica() {
        return fechaCertifica;
    }

    public void setFechaCertifica(String fechaCertifica) {
        this.fechaCertifica = fechaCertifica;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getIdNit() {
        return idNit;
    }

    public void setIdNit(int idNit) {
        this.idNit = idNit;
    }

    public double getGrantotal() {
        return grantotal;
    }

    public void setGrantotal(double grantotal) {
        this.grantotal = grantotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion2() {
        return Descripcion2;
    }

    public void setDescripcion2(String Descripcion2) {
        this.Descripcion2 = Descripcion2;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public static ArrayList<ObtenerProductosFactura> ListaProductosFactura(String a) {
        return SQLP("SELECT pe.cantidad,p.Descripcion,p.precio,pe.PRECIO as total FROM productos_pedido pe inner join productosFEL p on pe.ID_PRODUCTO = p.ID_PRODUCTO where ID_PEDIDO =" + a);
    }

    private static ArrayList<ObtenerProductosFactura> SQLP(String sql) {
        ArrayList<ObtenerProductosFactura> list = new ArrayList<ObtenerProductosFactura>();
        BDConexion_Encuentro conecta = new BDConexion_Encuentro();
        Connection cn = conecta.getConexion();

        try {
            ObtenerProductosFactura t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                t = new ObtenerProductosFactura();
                t.setCantidad(rs.getInt("cantidad"));
                t.setDescripcion2(rs.getString("Descripcion"));
                t.setPrecio(rs.getDouble("precio"));
                t.setTotal(rs.getDouble("total"));
                list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta DE LA ATABLA " + e);
            return null;
        }
        return list;
    }

    public static ObtenerProductosFactura InsertarNit(ObtenerProductosFactura t) throws SQLException {
        BDConexion_Encuentro conecta = new BDConexion_Encuentro();
        Connection con = conecta.getConexion();
        PreparedStatement smtp = null;
        smtp = con.prepareStatement("insert into compradornit(nombre,nit) values('" + t.getNombre() + "','" + t.getNit() + "')", Statement.RETURN_GENERATED_KEYS);
        try {
            smtp.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        ResultSet rs = smtp.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            t.setIdNit(id);
        }
        con.close();
        smtp.close();
        return t;

    }

    public static ObtenerProductosFactura InsertarFEL(ObtenerProductosFactura t) throws SQLException {
        BDConexion_Encuentro conecta = new BDConexion_Encuentro();
        Connection con = conecta.getConexion();
        PreparedStatement smtp = null;
        smtp = con.prepareStatement("insert into fel(serie,numero,autorizacion,fechacertificacion,id_pedido,idNit) values('" + t.getSerie() + "','" + t.getNumero() + "','" + t.getAutorizacion() + "','" + t.getFechaCertifica() + "'," + t.getId_pedido() + "," + t.getIdNit() + ")");
        try {
            smtp.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        con.close();
        smtp.close();
        return t;

    }

    public static ArrayList<ObtenerProductosFactura> ListarFacturasGeneradas(String a) {

        return SQL3("SELECT pedidos.id_pedido,TOTAL,FECHACERTIFICACION,compradornit.NIT,compradornit.NOMBRE FROM pedidos inner join fel on pedidos.ID_PEDIDO = fel.id_pedido join compradornit on fel.idNit = compradornit.idNit   where date_format(fecha,'%m/%d/%Y') = '"+a+"'order by fel.ID_PEDIDO desc");

    }

    private static ArrayList<ObtenerProductosFactura> SQL3(String sql) {
        ArrayList<ObtenerProductosFactura> list = new ArrayList<ObtenerProductosFactura>();
        BDConexion_Encuentro conecta = new BDConexion_Encuentro();
        Connection cn = conecta.getConexion();

        try {
            ObtenerProductosFactura t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                t = new ObtenerProductosFactura();
                t.setId_pedido(rs.getInt("ID_PEDIDO"));
                t.setFechaCertifica(rs.getString("fechacertificacion").toUpperCase());
                t.setNit(rs.getString("nit"));
                t.setNombre(rs.getString("nombre"));
                t.setTotal(rs.getDouble("TOTAL"));
                list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta DE LA TABLA " + e);
            return null;
        }
        return list;
    }
    
    public static ArrayList<ObtenerProductosFactura> ListarOrdenesSinFel(String a) {

        return sin("SELECT pedidos.id_pedido,pedidos.FECHA,pedidos.TOTAL FROM pedidos left join fel on pedidos.ID_PEDIDO = fel.id_pedido   where fel.id_pedido is null and  date_format(fecha,'%m/%d/%Y') = '"+a+"' and para in(1,2) order by pedidos.ID_PEDIDO");

    }

    private static ArrayList<ObtenerProductosFactura> sin(String sql) {
        ArrayList<ObtenerProductosFactura> list = new ArrayList<ObtenerProductosFactura>();
        BDConexion_Encuentro conecta = new BDConexion_Encuentro();
        Connection cn = conecta.getConexion();

        try {
            ObtenerProductosFactura t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                t = new ObtenerProductosFactura();
                t.setId_pedido(rs.getInt("ID_PEDIDO"));
                t.setFecha(rs.getString("fecha"));
                t.setTotal(rs.getDouble("TOTAL"));
                list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta DE LA TABLA " + e);
            return null;
        }
        return list;
    }

}
