/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesElRey;


import javax.swing.table.DefaultTableModel; // Para el modelo de tabla
import java.sql.Connection;                 // Para la conexión a la base de datos
import java.sql.PreparedStatement;          // Para consultas parametrizadas
import java.sql.ResultSet;                  // Para manejar los resultados de la consulta
import java.sql.SQLException;

/**
 *
 * @author it
 */
public class Totales {
    
  public static DefaultTableModel TotalesEncuentro(String fecha1, String fecha2) throws SQLException {
    DefaultTableModel model = new DefaultTableModel(
        new Object[]{"FECHA", "EFECTIVO", "TARJETA", "GASTOS", "TOTAL", "EFECTIVOTOTAL"},0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    String sql = "SELECT date_format(FECHA,'%d/%m/%Y') AS FECHA, EFECTIVO, TARJETA, GASTOS, TOTAL, EFECTIVOTOTAL " +
                 "FROM totaldiario WHERE FECHA BETWEEN ? AND ? ORDER BY FECHA";

    try (
        Connection cn = new BDConexion_Encuentro().getConexion();
        PreparedStatement stmt = cn.prepareStatement(sql)
    ) {
        stmt.setString(1, fecha1);
        stmt.setString(2, fecha2);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{
    rs.getString("FECHA"),
    String.format("%.2f", rs.getDouble("EFECTIVO")),
    String.format("%.2f", rs.getDouble("TARJETA")),
    String.format("%.2f", rs.getDouble("GASTOS")),
    String.format("%.2f", rs.getDouble("TOTAL")),
    String.format("%.2f", rs.getDouble("EFECTIVOTOTAL"))
});
            }
        }
    }

    return model;
}

  
 public static DefaultTableModel TotalesPinula(String fecha1, String fecha2) throws SQLException {
    DefaultTableModel model = new DefaultTableModel(
        new Object[]{"FECHA", "EFECTIVO", "TARJETA", "GASTOS", "TOTAL", "EFECTIVOTOTAL"},0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    String sql = "SELECT date_format(FECHA,'%d/%m/%Y') AS FECHA, EFECTIVO, TARJETA, GASTOS, TOTAL, EFECTIVOTOTAL " +
                 "FROM totaldiario WHERE FECHA BETWEEN ? AND ? ORDER BY FECHA";

    try (
        Connection cn = new BDConexion_Pinula().getConexion();
        PreparedStatement stmt = cn.prepareStatement(sql)
    ) {
        stmt.setString(1, fecha1);
        stmt.setString(2, fecha2);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{
    rs.getString("FECHA"),
    String.format("%.2f", rs.getDouble("EFECTIVO")),
    String.format("%.2f", rs.getDouble("TARJETA")),
    String.format("%.2f", rs.getDouble("GASTOS")),
    String.format("%.2f", rs.getDouble("TOTAL")),
    String.format("%.2f", rs.getDouble("EFECTIVOTOTAL"))
});
            }
        }
    }

    return model;
}
  
    
}
