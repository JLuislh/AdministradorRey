/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesElRey;

import ClasesElRey.BDConexion_Encuentro;
import java.sql.*;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author jluis
 */
public class BDProductos_Encuentro {
    
    
    int a;
    
    
    public static ArrayList<Productos> ListarProductos() {

        return consultarSQL("select id_producto,descripcion,truncate(precio,2) as precio FROM PRODUCTOS where tipo = 1");
    }
    
    public static ArrayList<Productos> ListarProductosExtra() {

        return consultarSQL("select id_adicional,descripcion,truncate(precio,2) as precio FROM ADICIONAL");
    }
    

    private static ArrayList<Productos> consultarSQL(String sql) {
        ArrayList<Productos> list = new ArrayList<Productos>();
        BDConexion_Encuentro conecta = new BDConexion_Encuentro();
        Connection cn = conecta.getConexion();
        DecimalFormat df = new DecimalFormat("#0.00");
        
        try {
            Productos p;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = new Productos();
                p.setId_producto(rs.getInt("id_producto"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio("Q "+df.format(rs.getString("precio")));
                //p.setPrecio(Double.parseDouble(df.format(rs.getDouble("precio"))));
                //System.out.println("precio ="+rs.getDouble("precio"));
                 //System.out.println(df.format(rs.getDouble("precio")));
                list.add(p);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }
    
    
 public static InsertarProducto InsertarProducto_Pedido(InsertarProducto t) throws SQLException{
        BDConexion_Encuentro conecta = new BDConexion_Encuentro();
        Connection con = conecta.getConexion();
        PreparedStatement smtp = null;
        PreparedStatement sm = null;
        smtp =con.prepareStatement("insert into productos_pedido (id_pedido,id_producto,cantidad,tipo,adicional,precio,opcion) values(?,?,?,?,1,(select precio*"+t.getCantidad()+" from productos where ID_PRODUCTO =  "+t.getId_producto()+" ),1) ",Statement.RETURN_GENERATED_KEYS);
        sm = con.prepareStatement("{call Opcion1("+t.getTipo()+","+t.getId_producto()+","+t.getCantidad()+")}");
        try {
         smtp.setInt(1,t.getId_pedido());
         smtp.setInt(2,t.getId_producto());
         smtp.setInt(3, t.getCantidad());
         smtp.setInt(4, t.getTipo());
         smtp.executeUpdate();
         sm.executeUpdate();
     } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "QUE MIERDA PASA ADENTRO =  "+e);}
        
        ResultSet rs = smtp.getGeneratedKeys();
        if(rs.next()){int id1 = rs.getInt(1);
          t.setIdregreso(id1);
        }
        
       con.close();
       smtp.close(); 
       sm.close(); 
        return t;
       
    }
 
 
 public static InsertarProducto InsertarProducto_Pedido_combo(InsertarProducto t) throws SQLException{
        BDConexion_Encuentro conecta = new BDConexion_Encuentro();
        Connection con = conecta.getConexion();
        PreparedStatement smtp = null;
        PreparedStatement sm = null;
        smtp =con.prepareStatement("insert into productos_pedido (id_pedido,id_producto,cantidad,tipo,adicional,precio,opcion) values(?,?,?,?,1,(select precio*"+t.getCantidad()+" from PRODUCTOS where ID_PRODUCTO =  "+t.getId_producto()+" ),4)",Statement.RETURN_GENERATED_KEYS);
        sm = con.prepareStatement("{call Opcion4("+t.getCantidad()+")}");
        try {
         smtp.setInt(1,t.getId_pedido());
         smtp.setInt(2,t.getId_producto());
         smtp.setInt(3, t.getCantidad());
         smtp.setInt(4, t.getTipo());
         smtp.executeUpdate();
         sm.executeUpdate();
     } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "QUE MIERDA PASA ADENTRO =  "+e);}
        ResultSet rs = smtp.getGeneratedKeys();
        if(rs.next()){int id1 = rs.getInt(1);
          t.setIdregreso(id1);
        }
        
       con.close();
       smtp.close(); 
       sm.close(); 
        return t;
       
    }
 
 
   public static InsertarProducto InsertarProducto_Pedido_tortilla(InsertarProducto t) throws SQLException{
        BDConexion_Encuentro conecta = new BDConexion_Encuentro();
        Connection con = conecta.getConexion();
        PreparedStatement smtp = null;
        PreparedStatement sm = null;
        smtp =con.prepareStatement("insert into productos_pedido (id_pedido,id_producto,cantidad,tipo,adicional,precio,opcion) values(?,?,?,3,1,(select precio*"+t.getCantidad()+"  from PRODUCTOS where ID_PRODUCTO = "+t.getId_producto()+" ),2)",Statement.RETURN_GENERATED_KEYS);
        sm = con.prepareStatement("{call Opcion2("+t.getId_producto()+","+t.getCantidad()+")}");
        try {
         smtp.setInt(1,t.getId_pedido());
         smtp.setInt(2,t.getId_producto());
         smtp.setInt(3, t.getCantidad());
         smtp.executeUpdate();
         sm.executeUpdate();
     } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "CUAL ERROR = "+e);}
        
        ResultSet rs = smtp.getGeneratedKeys();
        if(rs.next()){int id1 = rs.getInt(1);
          t.setIdregreso(id1);
        }
       con.close();
       smtp.close(); 
       sm.close(); 
        return t;
    }
 
 
    public static InsertarProducto InsertarPedido(InsertarProducto t) throws SQLException{
        BDConexion_Encuentro conecta = new BDConexion_Encuentro();
        Connection con = conecta.getConexion();
        PreparedStatement smtp = null;
        smtp =con.prepareStatement("insert into Ordenes (FECHA) values(CURRENT_TIMESTAMP)",Statement.RETURN_GENERATED_KEYS);
        try {
         smtp.executeUpdate();
     } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);}
        
        ResultSet rs = smtp.getGeneratedKeys();
        if(rs.next()){int id = rs.getInt(1);
          t.setIdregresoPedido(id);
        }
       con.close();
       smtp.close(); 
        return t;
       
    } 
    
    

 public static ArrayList<InsertarProducto> ListarProductosPedidos (int a ) {
        return SQL3("select ID_PRODUCTOS_PEDIDO,cantidad,\n" +
"if(p.adicional = 1, concat(if(p.tipo = 1,'PAN DE',if(p.tipo = 2,'MIXTA DE',if(p.tipo = 3,'GASEOSA',if(p.tipo = 4,'TORTILLA','')))),'  ',pro.DESCRIPCION,' ',\n" +
"    (select  GROUP_CONCAT(dn.descripcion SEPARATOR ' / ') as descri from  notas n inner join descripcionnotas dn on dn.id = n.ID where ID_PRODUCTOS_PEDIDO = p.ID_PRODUCTOS_PEDIDO)),pro.DESCRIPCION) as DESCRIPCION,truncate(p.precio,2) as Precio\n" +
"from productos_pedido p \n" +
"inner join productos pro on p.ID_PRODUCTO = pro.ID_PRODUCTO where p.id_pedido = "+a);    
 }  

private static ArrayList<InsertarProducto> SQL3(String sql){
    ArrayList<InsertarProducto> list = new ArrayList<InsertarProducto>();
    BDConexion_Encuentro conecta = new BDConexion_Encuentro();
    Connection cn = conecta.getConexion();
    
        try {
            InsertarProducto t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new InsertarProducto();
                 t.setId_producto_pedidos(rs.getInt("ID_PRODUCTOS_PEDIDO"));
                 t.setDescripcion(rs.getString("DESCRIPCION").toUpperCase());
                 t.setCantidad1(rs.getInt("cantidad"));
                 t.setPrecio(rs.getDouble("Precio"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta DE LA ATABLA "+e);
            return null;
        } 
        return list;
}
 
 
 

 public static ArrayList<Productos> ListarProductosInventario() {

        return ListarInventario("SELECT codigo,DESCRIPCION,CANTIDAD FROM productos_inventario order by listar");
    }

    private static ArrayList<Productos> ListarInventario(String sql) {
        ArrayList<Productos> list = new ArrayList<Productos>();
        BDConexion_Encuentro conecta = new BDConexion_Encuentro();
        Connection cn = conecta.getConexion();
        try {
            Productos c;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                c = new Productos();
                c.setCodigo(rs.getInt("codigo"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setCantidad2(rs.getDouble("cantidad"));
               

                list.add(c);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println("Error Consulta producto por nombre " + e);
            return null;
        }
        return list;
    }

    
    
    public static ArrayList<Productos> ListarProductosInventarioIngresados(int a) {

        return ListarInventarioingresados("SELECT date_format(FECHA,'%d/%m/%Y') AS FECHA,CANTIDAD FROM ingresos WHERE CODIGO ="+a);
    }

    private static ArrayList<Productos> ListarInventarioingresados(String sql) {
        ArrayList<Productos> list = new ArrayList<Productos>();
        BDConexion_Encuentro conecta = new BDConexion_Encuentro();
        Connection cn = conecta.getConexion();
        try {
            Productos c;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                c = new Productos();
                c.setFecha(rs.getString("fecha"));
                c.setCantidad2(rs.getDouble("cantidad"));
               

                list.add(c);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println("Error Consulta producto por nombre " + e);
            return null;
        }
        return list;
    }
    
    

 public static Productos BuscarProducto (int idc) throws SQLException{
    
        return buscarDescarga(idc, null);
        
    }
    
    public static Productos buscarDescarga(int idc, Productos c) throws SQLException{
        
        BDConexion_Encuentro conecta = new BDConexion_Encuentro();
        Connection cn = conecta.getConexion();
        PreparedStatement ps =null;
        ps = cn.prepareStatement("SELECT codigo,DESCRIPCION,cantidad FROM productos_inventario where codigo ="+idc);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
             if (c == null){
             c = new Productos(){   };
        
        }
        c.setCodigo(rs.getInt("codigo"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setCantidad2(rs.getDouble("cantidad"));
        }
        cn.close();
        ps.close();
        return c;
        
    }
    
    public static InsertarProducto InsertarProductoIngresoInventario(InsertarProducto t) throws SQLException{
        BDConexion_Encuentro conecta = new BDConexion_Encuentro();
        Connection con = conecta.getConexion();
        PreparedStatement smtp = null;
        smtp =con.prepareStatement("insert into ingresosproductos (idproductosinve,cantidad,precio,estado,fecha) values(?,?,?,1,CURRENT_TIMESTAMP)");
        try {
         smtp.setInt(1,t.getIdregreso());
         smtp.setInt(2,t.getCantidad());
         smtp.setDouble(3, t.getPrecio());
         smtp.executeUpdate();
     } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "CUAL ERROR = "+e);}
       con.close();
       smtp.close(); 
        return t;
    }
    
    
    public static ArrayList<InsertarProducto> Ordenes(String Fecha) {
        return Order("select ID_PEDIDO,Total,date_format(fecha,'%d/%m/%Y') as FECHA from PEDIDOS where date_format(fecha,'%d/%m/%Y')  ='"+Fecha+"'");    
 }  

    private static ArrayList<InsertarProducto> Order(String sql){
    ArrayList<InsertarProducto> list = new ArrayList<InsertarProducto>();
    BDConexion_Encuentro conecta = new BDConexion_Encuentro();
    Connection cn = conecta.getConexion();
    
        try {
            InsertarProducto t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new InsertarProducto();
                 t.setNoOrden(rs.getInt("ID_PEDIDO"));
                 t.setTotal(rs.getDouble("TOTAL"));
                 t.setFecha(rs.getString("FECHA"));
                 list.add(t);
                            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("error consulta DE LA A TABLA "+e);
            return null;
        } 
        return list;
} 
    
    
    public static InsertarProducto BuscarTotal(String a) throws SQLException{
        return buscarTotal(a ,null);
    }
    
    public static InsertarProducto buscarTotal(String a, InsertarProducto c) throws SQLException {
             
            BDConexion_Encuentro conecta = new BDConexion_Encuentro();
            Connection cn = conecta.getConexion();
            PreparedStatement ps = null;
            ps = cn.prepareStatement("select SUM(EFECTIVO) AS EFECTIVO, SUM(TARJETA) AS TARJETA,SUM(TOTAL) AS TOTAL, count(*) as ORDENES  from pedidos where date_format(fecha,'%d/%m/%Y' )  = '"+a+"';");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
               if (c==null)
               {c = new InsertarProducto(){};}
                c.setEfectivo(rs.getDouble("EFECTIVO"));
                c.setTarjeta(rs.getDouble("TARJETA"));
                c.setNoOrden(rs.getInt("ORDENES"));
                c.setTotal(rs.getDouble("TOTAL"));
               
            }
            cn.close();
            ps.close();
            return c;
}
    
    
    
    public static ArrayList<InsertarProducto> ProductosVendidos(String Fecha) {
        return PV("select concat(if(PP.TIPO = 1,'PAN DE',if(PP.TIPO = 2,'TORTILLA DE','') ),' ',PR.DESCRIPCION) as DESCRIPCION,SUM(PP.CANTIDAD) AS CANTIDAD,SUM(PP.PRECIO) AS TOTAL from PEDIDOS PE inner join PRODUCTOS_PEDIDO PP ON PE.ID_PEDIDO = PP.ID_PEDIDO \n" +
"JOIN PRODUCTOS PR ON PP.ID_PRODUCTO = PR.ID_PRODUCTO\n" +
"where date_format(fecha,'%d/%m/%Y' )  =   '"+Fecha+"'   GROUP BY PP.TIPO,PR.DESCRIPCION,PR.TIPO ORDER BY PR.tipo");    
 }  
    private static ArrayList<InsertarProducto> PV(String sql){
    ArrayList<InsertarProducto> list = new ArrayList<InsertarProducto>();
    BDConexion_Encuentro conecta = new BDConexion_Encuentro();
    Connection cn = conecta.getConexion();
        try {
            InsertarProducto t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new InsertarProducto();
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setDescripcion(rs.getString("DESCRIPCION"));
                 t.setTotal(rs.getDouble("TOTAL"));
                 list.add(t);
                            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("error consulta DE LA A TABLA "+e);
            return null;
        } 
        return list;
} 
    
 public static ArrayList<InsertarProducto> ConsumoCarnes(String Fecha) {
        return carne("Select dn.DESCRIPCION,sum(pp.CANTIDAD) as CANTIDAD from NOTAS n inner join DESCRIPCIONNOTAS dn on n.id = dn.id \n" +
"join PRODUCTOS_PEDIDO pp on n.ID_PRODUCTOS_PEDIDO = pp.ID_PRODUCTOS_PEDIDO \n" +
"join PEDIDOS p on pp.ID_PEDIDO = p.ID_PEDIDO where date_format(fecha,'%d/%m/%Y' )  =  '"+Fecha+"'   and dn.tipo = 2  group by dn.DESCRIPCION");    
 }  
    private static ArrayList<InsertarProducto> carne(String sql){
    ArrayList<InsertarProducto> list = new ArrayList<InsertarProducto>();
    BDConexion_Encuentro conecta = new BDConexion_Encuentro();
    Connection cn = conecta.getConexion();
        try {
            InsertarProducto t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new InsertarProducto();
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setDescripcion(rs.getString("DESCRIPCION"));
                 list.add(t);
                            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("error consulta DE LA A TABLA "+e);
            return null;
        } 
        return list;
}


 public static ArrayList<InsertarProducto> TodosProductosVendidos(String Fecha) {
        return TO("select cantidad,if(p.adicional = 1, concat(if(p.tipo = 1,'PAN DE',if(p.tipo = 2,'TORTILLA DE','GASEOSA')),'  ',pro.DESCRIPCION,' ',\n" +
"(select  GROUP_CONCAT(dn.descripcion SEPARATOR ' / ') as descri from  NOTAS n inner join DESCRIPCIONNOTAS dn on\n" +
"dn.id = n.ID where ID_PRODUCTOS_PEDIDO = p.ID_PRODUCTOS_PEDIDO)),pro.DESCRIPCION) as DESCRIPCION,(pro.precio*p.cantidad) as precio\n" +
"from PRODUCTOS_PEDIDO p\n" +
"inner join PRODUCTOS pro on p.ID_PRODUCTO = pro.ID_PRODUCTO where  p.id_pedido in (select id_pedido from pedidos where date_format(fecha,'%d/%m/%Y' )  =  '"+Fecha+"' ) order by DESCRIPCION;");    
 }  
    private static ArrayList<InsertarProducto> TO(String sql){
    ArrayList<InsertarProducto> list = new ArrayList<InsertarProducto>();
    BDConexion_Encuentro conecta = new BDConexion_Encuentro();
    Connection cn = conecta.getConexion();
        try {
            InsertarProducto t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new InsertarProducto();
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setDescripcion(rs.getString("DESCRIPCION"));
                 t.setPrecio(rs.getDouble("precio"));
                 list.add(t);
                            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("error consulta DE LA A TABLA "+e);
            return null;
        } 
        return list;
} 


public static ArrayList<Productos> ListarProductosHistorialInventario(String a) {

        return ListarInventarioHistorial("SELECT p.Codigo,Descripcion,c.CantidadInicio,c.CantidadFinal,(c.CantidadInicio-c.CantidadFinal) as Final, cantingreso FROM consumos c inner join productos_inventario p on c.codigo = p.codigo where c.fecha = '"+a+"' order by p.listar");
    }

    private static ArrayList<Productos> ListarInventarioHistorial(String sql) {
        ArrayList<Productos> list = new ArrayList<Productos>();
        BDConexion_Encuentro conecta = new BDConexion_Encuentro();
        Connection cn = conecta.getConexion();
        try {
            Productos c;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                c = new Productos();
                c.setCodigo(rs.getInt("codigo"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setCantidadinicial(rs.getDouble("cantidadinicio"));
                c.setCantidadfinal(rs.getDouble("cantidadfinal"));
                c.setCantidad2(rs.getDouble("Final"));
                c.setCantidad(rs.getInt("cantingreso"));
                list.add(c);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println("Error Consulta producto por nombre " + e);
            return null;
        }
        return list;
    }

public static ArrayList<InsertarProducto> ListarProductosPedidosEncuentro (int a ) {
        return SQ("select ID_PRODUCTOS_PEDIDO,cantidad,\n" +
"if(p.adicional = 1, concat(if(p.tipo = 1,'PAN DE',if(p.tipo = 2,'MIXTA DE',if(p.tipo = 3,'GASEOSA',if(p.tipo = 4,'TORTILLA','')))),'  ',pro.DESCRIPCION,' ',\n" +
"    (select  GROUP_CONCAT(dn.descripcion SEPARATOR ' / ') as descri from  notas n inner join descripcionnotas dn on dn.id = n.ID where ID_PRODUCTOS_PEDIDO = p.ID_PRODUCTOS_PEDIDO)),pro.DESCRIPCION) as DESCRIPCION,truncate(p.precio,2) as Precio\n" +
"from productos_pedido p \n" +
"inner join productos pro on p.ID_PRODUCTO = pro.ID_PRODUCTO where p.id_pedido = "+a);    
 }  

private static ArrayList<InsertarProducto> SQ(String sql){
    ArrayList<InsertarProducto> list = new ArrayList<InsertarProducto>();
    BDConexion_Encuentro conecta = new BDConexion_Encuentro();
    Connection cn = conecta.getConexion();
    
        try {
            InsertarProducto t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new InsertarProducto();
                 t.setId_producto_pedidos(rs.getInt("ID_PRODUCTOS_PEDIDO"));
                 t.setDescripcion(rs.getString("DESCRIPCION").toUpperCase());
                 t.setCantidad1(rs.getInt("cantidad"));
                 t.setPrecio(rs.getDouble("Precio"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta DE LA ATABLA "+e);
            return null;
        } 
        return list;
}


public static ArrayList<InsertarProducto> ListarProductosPedidosPinula(int a ) {
        return QL("select ID_PRODUCTOS_PEDIDO,cantidad,\n" +
"if(p.adicional = 1, concat(if(p.tipo = 1,'PAN DE',if(p.tipo = 2,'MIXTA DE',if(p.tipo = 3,'GASEOSA',if(p.tipo = 4,'TORTILLA','')))),'  ',pro.DESCRIPCION,' ',\n" +
"    (select  GROUP_CONCAT(dn.descripcion SEPARATOR ' / ') as descri from  notas n inner join descripcionnotas dn on dn.id = n.ID where ID_PRODUCTOS_PEDIDO = p.ID_PRODUCTOS_PEDIDO)),pro.DESCRIPCION) as DESCRIPCION,truncate(p.precio,2) as Precio\n" +
"from productos_pedido p \n" +
"inner join productos pro on p.ID_PRODUCTO = pro.ID_PRODUCTO where p.id_pedido = "+a);    
 }  

private static ArrayList<InsertarProducto> QL(String sql){
    ArrayList<InsertarProducto> list = new ArrayList<InsertarProducto>();
    BDConexion_Pinula conecta = new BDConexion_Pinula();
    Connection cn = conecta.getConexion();
    
        try {
            InsertarProducto t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new InsertarProducto();
                 t.setId_producto_pedidos(rs.getInt("ID_PRODUCTOS_PEDIDO"));
                 t.setDescripcion(rs.getString("DESCRIPCION").toUpperCase());
                 t.setCantidad1(rs.getInt("cantidad"));
                 t.setPrecio(rs.getDouble("Precio"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta DE LA ATABLA "+e);
            return null;
        } 
        return list;
}
    

//////////////////////////////////////VENTAS///////////////////////////////////////
    
    public static ArrayList<InsertarProducto> VentasporFecha(String Fecha1,String Fecha2) {
        return vent("select date_format(fecha,'%d/%m/%Y') as fecha,SUM(EFECTIVO) AS EFECTIVO, SUM(TARJETA) AS TARJETA,SUM(TOTAL) AS TOTAL, count(*) as ORDENES  from pedidos where FECHA between '"+Fecha1+"' and date_add('"+Fecha2+"', interval 1 day)  group by date_format(fecha,'%d/%m/%Y');");    
        }  

    private static ArrayList<InsertarProducto> vent(String sql){
    ArrayList<InsertarProducto> list = new ArrayList<InsertarProducto>();
    BDConexion_Encuentro conecta = new BDConexion_Encuentro();
    Connection cn = conecta.getConexion();
    
        try {
            InsertarProducto t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new InsertarProducto();
                 t.setFecha(rs.getString("FECHA"));
                 t.setEfectivo(rs.getDouble("EFECTIVO"));
                 t.setTarjeta(rs.getDouble("TARJETA"));
                 t.setTotal(rs.getDouble("TOTAL"));
                 t.setNoOrden(rs.getInt("ORDENES"));
                 list.add(t);
                            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("error consulta DE LA A TABLA "+e);
            return null;
        } 
        return list;
}
    
    
 public static InsertarProducto BuscarTotalRangoFecha(String Fecha1, String Fecha2) throws SQLException{
        return buscarTotalRango(Fecha1,Fecha2,null);
    }
    
    public static InsertarProducto buscarTotalRango(String Fechaa,String Fechab, InsertarProducto c) throws SQLException {
             
            BDConexion_Encuentro conecta = new BDConexion_Encuentro();
            Connection cn = conecta.getConexion();
            PreparedStatement ps = null;
            ps = cn.prepareStatement("select sum(efectivo) as efectivo,sum(TARJETA) as tarjeta,sum(Total) as total  from pedidos where FECHA between '"+Fechaa+"' and date_add('"+Fechab+"', interval 1 day);");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
               if (c==null)
               {c = new InsertarProducto(){};}
               c.setTotal(rs.getDouble("TOTAL"));
               c.setEfectivo(rs.getDouble("efectivo"));
               c.setTarjeta(rs.getDouble("tarjeta"));
               
            }
            cn.close();
            ps.close();
            return c;
}    


    

}
