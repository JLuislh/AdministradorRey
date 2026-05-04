/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Encuentro;

import ClasesElRey.BDConexion_Encuentro;
import ClasesElRey.InsertarProducto;
import ClasesElRey.Totales;
import java.lang.System.Logger.Level;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.log4j.Logger;

/**
 *
 * @author it
 */
public class TotalesVentasMes extends javax.swing.JInternalFrame {

    String FECHAINs;
    String FECHAFINs;

    /**
     * Creates new form TotalesVentasMes
     */
    public TotalesVentasMes() {
        initComponents();
    }

    private void generar() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            FECHAINs = df.format(FECHAIN.getDate());
            FECHAFINs = df.format(FECHAFIN.getDate());

            BDConexion_Encuentro con = new BDConexion_Encuentro();
            Connection conexion = con.getConexion();
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile("Reportes/ReportesIngresos/ResumenIngresosSantaInes.jasper");
            Map parametros = new HashMap();
            parametros.put("FECHAIN", FECHAINs);
            parametros.put("FECHAFIN", FECHAFINs);
            parametros.put("SUBREPORT_DIR", "Reportes/ReportesIngresos/subreportes/");
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros, conexion);
            JasperPrintManager.printReport(print, true);

        } catch (Exception e) {
            System.out.println("F" + e);
            JOptionPane.showMessageDialog(null, "ERROR EJECUTAR REPORTES =  " + e);
        }

    }

    private void imprimegastos() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            FECHAINs = df.format(FECHAIN.getDate());
            FECHAFINs = df.format(FECHAFIN.getDate());

            BDConexion_Encuentro con = new BDConexion_Encuentro();
            Connection conexion = con.getConexion();
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile("Reportes/ReportesGastos/ResumenGastosDiarios.jasper");
            Map parametros = new HashMap();
            parametros.put("FECHAIN", FECHAINs);
            parametros.put("FECHAFIN", FECHAFINs);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros, conexion);
            JasperPrintManager.printReport(print, true);

        } catch (Exception e) {
            System.out.println("F" + e);
            JOptionPane.showMessageDialog(null, "ERROR EJECUTAR REPORTES =  " + e);
        }

    }

    public void ListarTotales() throws SQLException {
        DefaultTableModel model = Totales.TotalesEncuentro(FECHAINs, FECHAFINs);
        CUENTA.setModel(model);
        /*
        // Ocultar columna del ID (columna 0)
        CUENTA.getColumnModel().getColumn(0).setMinWidth(0);
        CUENTA.getColumnModel().getColumn(0).setMaxWidth(0);
        CUENTA.getColumnModel().getColumn(0).setPreferredWidth(0);
         */
        // (Opcional) Ajustar ancho de otras columnas
        CUENTA.getColumnModel().getColumn(0).setMaxWidth(130);
        CUENTA.getColumnModel().getColumn(1).setMaxWidth(130);
        CUENTA.getColumnModel().getColumn(2).setMaxWidth(130);
        CUENTA.getColumnModel().getColumn(3).setMaxWidth(130);
        CUENTA.getColumnModel().getColumn(4).setMaxWidth(130);
        CUENTA.getColumnModel().getColumn(5).setMaxWidth(130);

    }

    private void BuscarTotal() {
        //SumaTotalGastos();
        DecimalFormat df = new DecimalFormat("#0.00");

        try {
            InsertarProducto c = BDSuperAdmin.BuscarTotalMesEncuentro(FECHAINs, FECHAFINs);
            EFECTIVO.setText(String.valueOf(df.format(c.getEfectivo())));
            TARJETA.setText(String.valueOf(df.format(c.getTarjeta())));
            Total.setText((df.format(c.getTotal())));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error mas" + e);
        }
    }

    public void ejecutarCuentasDiasMes(String fecha1, String fecha2) {
        String sql = "{CALL CUENTASDIASMES(?, ?)}";

        try (
                Connection conn = new BDConexion_Encuentro().getConexion(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, fecha1); // ejemplo: "2025/04/01"
            stmt.setString(2, fecha2); // ejemplo: "2025/04/30"
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al ejecutar procedimiento: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Total = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TRANFERENCIA = new javax.swing.JTextField();
        TARJETA = new javax.swing.JTextField();
        EFECTIVO = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        GASTOS = new javax.swing.JTextField();
        EFECTIVOMENOSGASTOS = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CUENTA = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        FECHAFIN = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        FECHAIN = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("TOTAL ");

        Total.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Total.setForeground(new java.awt.Color(255, 51, 51));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("EFECTIVO");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("TARJETA");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("TRANSFERENCIA");

        TRANFERENCIA.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TRANFERENCIA.setForeground(new java.awt.Color(51, 102, 255));

        TARJETA.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TARJETA.setForeground(new java.awt.Color(51, 102, 255));

        EFECTIVO.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EFECTIVO.setForeground(new java.awt.Color(51, 102, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("GASTOS");

        GASTOS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        GASTOS.setForeground(new java.awt.Color(51, 102, 255));

        EFECTIVOMENOSGASTOS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EFECTIVOMENOSGASTOS.setForeground(new java.awt.Color(51, 102, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("TOTAL EFECTIVO MENOS GASTOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TARJETA)
                    .addComponent(TRANFERENCIA)
                    .addComponent(GASTOS)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(EFECTIVO)
                    .addComponent(Total, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(EFECTIVOMENOSGASTOS))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EFECTIVO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TARJETA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TRANFERENCIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GASTOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EFECTIVOMENOSGASTOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        CUENTA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "FECHA", "EFECTIVO", "TARJETA", "GASTOS", "TOTAL", "EFECTIVO MENOS GASTOS"
            }
        ));
        jScrollPane1.setViewportView(CUENTA);

        jButton1.setText("IMPRIMIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("BUSCAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("AL");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FECHA DEL ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FECHAIN, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FECHAFIN, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 803, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(FECHAFIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(FECHAIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (FECHAIN.getDate() != null && FECHAFIN.getDate() != null) {
            generar();
        } else {

            JOptionPane.showMessageDialog(null, "INGRESE UNA FECHA...");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        FECHAINs = df.format(FECHAIN.getDate());
        FECHAFINs = df.format(FECHAFIN.getDate());

        if (FECHAIN.getDate() != null && FECHAFIN.getDate() != null) {
            ejecutarCuentasDiasMes(FECHAINs, FECHAFINs);
            BuscarTotal();
            try {
                ListarTotales();
            } catch (SQLException ex) {
                // Logger.getLogger(ReporteMensualEncuentro.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "INGRESE UNA FECHA...");
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CUENTA;
    private javax.swing.JTextField EFECTIVO;
    private javax.swing.JTextField EFECTIVOMENOSGASTOS;
    private com.toedter.calendar.JDateChooser FECHAFIN;
    private com.toedter.calendar.JDateChooser FECHAIN;
    private javax.swing.JTextField GASTOS;
    private javax.swing.JTextField TARJETA;
    private javax.swing.JTextField TRANFERENCIA;
    private javax.swing.JTextField Total;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
