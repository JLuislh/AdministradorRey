/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Asistencia;

import ClasesElRey.BDConexion_Asistencia;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author it
 */
public class TablaReloj {

    public static void llenarTabla(JTable tabla, String fecha) {
        // Define las columnas que tendrá tu JTable
        String[] columnas = {"NOMBRES", "SUCURSAL", "INGRESO", "SALIDA", "HORAS"};

        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        String sql = "SELECT NOMBRES,"
                + "CASE r.SUCURSAL "
                + " WHEN 'encuentro' THEN 'EL ENCUENTRO'\n" 
                + " WHEN 'pinula' THEN 'ZONA 4 PINULA' END as SUCURSAL, "
                + "DATE_FORMAT(INGRESO, '%d/%m/%Y %H:%i') AS INGRESO, "
                + "DATE_FORMAT(SALIDA, '%d/%m/%Y %H:%i') AS SALIDA, "
                + "HORAS "
                + "FROM reloj r "
                + "INNER JOIN empleados l ON r.CODIGO = l.CODIGO "
                + "WHERE DATE_FORMAT(FECHA, '%d/%m/%Y') = ?";

        try (Connection conn = BDConexion_Asistencia.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, fecha); // Fecha en formato "dd/MM/yyyy"
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("NOMBRES"),
                    rs.getString("SUCURSAL"),
                    rs.getString("INGRESO"),
                    rs.getString("SALIDA"),
                    rs.getString("HORAS")
                });
            }

            tabla.setModel(modelo);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar datos: " + e.getMessage());
        }
    }
    
 
    
     public static void HorasTrabajadas(JTable tabla, String fecha1,String fecha2) {
        // Define las columnas que tendrá tu JTable
        String[] columnas = {"CODIGO", "NOMBRES", "HORAS LABORADAS"};

        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        String sql = "SELECT \n" +
" r.codigo, l.NOMBRES,\n" +
"  CONCAT(\n" +
"    FLOOR(SUM(TIME_TO_SEC(TIMEDIFF(r.salida, r.ingreso))) / 3600), ' HORAS ',\n" +
"    MOD(FLOOR(SUM(TIME_TO_SEC(TIMEDIFF(r.salida, r.ingreso))) / 60), 60), ' MINUTOS'\n" +
"  ) AS HORAS\n" +
"FROM reloj r inner join empleados l on r.CODIGO = l.CODIGO\n" +
"WHERE DATE(r.ingreso) BETWEEN ? AND ?\n" +
"GROUP BY codigo";

        try (Connection conn = BDConexion_Asistencia.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, fecha1); // Fecha en formato "dd/MM/yyyy"
            pst.setString(2, fecha2);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("CODIGO"),
                    rs.getString("NOMBRES"),
                    rs.getString("HORAS"),
                });
            }

            tabla.setModel(modelo);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar datos: " + e.getMessage());
        }
    }
    
}
