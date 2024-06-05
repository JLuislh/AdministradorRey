/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FelAdmin;

import ClasesElRey.BDConexion_Encuentro;
import ClasesElRey.TextAreaRenderer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author jluis
 */
public class Reimprimir extends javax.swing.JFrame {
    int id_orden = 0;
     String Usuario;
    /**
     * Creates new form Reimprimir
     */
    public Reimprimir() {
        initComponents();
        setLocationRelativeTo(null);
        TokenLocal();
        imprime.setEnabled(false);
    }
    
     public  void TokenLocal() {
      //  DecimalFormat df = new DecimalFormat("#.00");
            try {
                BDConexion_Encuentro conecta = new BDConexion_Encuentro();
                Connection cn = conecta.getConexion();
                java.sql.Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery("select usuario  from TOKEN where idToken = 1");
                while (rs.next()) {
                      Usuario = rs.getString("usuario");
                }
                rs.close();
                stmt.close();
                cn.close();
            } catch (Exception error) {
                System.out.print(error);
            }
           
        }
    
     
    public void ListarFacturasGeneradas(){
        
        Date date = FECHA.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String fecha = sdf.format(date);
     
        ArrayList<ObtenerProductosFactura> result = ObtenerProductosFactura.ListarFacturasGeneradas(fecha);
        RecargarTabla(result);  
    }
     public  void RecargarTabla(ArrayList<ObtenerProductosFactura> list) {
              DecimalFormat df = new DecimalFormat("#.00");
              Object[][] datos = new Object[list.size()][5];
              int i = 0;
              for(ObtenerProductosFactura t : list)
              {
                  datos[i][0] = t.getId_pedido();
                  datos[i][1] = t.getFechaCertifica();
                  datos[i][2] = t.getNit();
                  datos[i][3] = t.getNombre();
                  datos[i][4] = df.format(t.getTotal());
                  i++;
              }    
             Facturas.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                "No. Orden","FECHA CERTIFICACION","NIT","NOMBRE","TOTAL"
             })
             {  
                 @Override
                 public boolean isCellEditable(int row, int column){
                 return false;

             }
             });
             Facturas.getColumnModel().getColumn(1).setCellRenderer(new TextAreaRenderer());
             TableColumn columna1 = Facturas.getColumn("No. Orden");
             columna1.setPreferredWidth(-20);
             TableColumn columna2 = Facturas.getColumn("FECHA CERTIFICACION");
             columna2.setPreferredWidth(75);
             TableColumn columna3 = Facturas.getColumn("NIT");
             columna3.setPreferredWidth(75);
             TableColumn columna4 = Facturas.getColumn("NOMBRE");
             columna4.setPreferredWidth(155);
     }
     
      private void imprimirEncuentro(){
 
      BDConexion_Encuentro con= new BDConexion_Encuentro();
         Connection conexion= con.getConexion();
        try {
            JasperReport jasperReport=(JasperReport)JRLoader.loadObjectFromFile("C:\\Reportes\\FEL\\FELElEncuentro.jasper");
            Map parametros= new HashMap();
            parametros.put("ID_ORDEN", id_orden);
            JasperPrint print = JasperFillManager.fillReport(jasperReport,parametros, conexion);
            JasperPrintManager.printReport(print, true);
        } catch (Exception e) {System.out.println("F"+e);
           JOptionPane.showMessageDialog(null, "ERROR EJECUTAR REPORTES =  "+e);
        }
        
    }
    
    private void imprimirZona4(){
 
      BDConexion_Encuentro con= new BDConexion_Encuentro();
         Connection conexion= con.getConexion();
        try {
            JasperReport jasperReport=(JasperReport)JRLoader.loadObjectFromFile("C:\\Reportes\\FEL\\FELZona4.jasper");
            Map parametros= new HashMap();
            parametros.put("ID_ORDEN", id_orden);
            JasperPrint print = JasperFillManager.fillReport(jasperReport,parametros, conexion);
            JasperPrintManager.printReport(print, true);
        } catch (Exception e) {System.out.println("F"+e);
           JOptionPane.showMessageDialog(null, "ERROR EJECUTAR REPORTES =  "+e);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        FECHA = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        Facturas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        imprime = new javax.swing.JButton();
        imprime1 = new javax.swing.JButton();
        imprime2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FECHA DE FACTURACION");

        Facturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Orden", "FECHA CERTIFICACION", "NIT", "NOMBRE"
            }
        ));
        Facturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FacturasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Facturas);

        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        imprime.setText("IMPRIMIR");
        imprime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimeActionPerformed(evt);
            }
        });

        imprime1.setText("GENERAR");
        imprime1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprime1ActionPerformed(evt);
            }
        });

        imprime2.setText("SALIR");
        imprime2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprime2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(FECHA, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1)
                            .addGap(179, 179, 179)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(imprime, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(imprime1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imprime2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FECHA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imprime, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(imprime1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imprime2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        if (FECHA.getDate() != null) {
            ListarFacturasGeneradas();
        }else {
            JOptionPane.showMessageDialog(null, "INGRESE UNA FECHA...");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void imprimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimeActionPerformed
      if(id_orden == 0){JOptionPane.showMessageDialog(null, "SELECCIONAR UNA FACTURA...");}else{
        if(Usuario.equalsIgnoreCase("ENCUENTRO")){imprimirEncuentro();}
            else if(Usuario.equalsIgnoreCase("ZONA4")){imprimirZona4();}}
    }//GEN-LAST:event_imprimeActionPerformed

    private void FacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FacturasMouseClicked
        
        id_orden = (Integer.parseInt(String.valueOf(Facturas.getModel().getValueAt(Facturas.getSelectedRow(), 0))));
        imprime.setEnabled(true);
    }//GEN-LAST:event_FacturasMouseClicked

    private void imprime1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprime1ActionPerformed
         GenerarFel F = new GenerarFel();
         F.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_imprime1ActionPerformed

    private void imprime2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprime2ActionPerformed
         
    }//GEN-LAST:event_imprime2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Reimprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reimprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reimprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reimprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reimprimir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser FECHA;
    private javax.swing.JTable Facturas;
    private javax.swing.JButton imprime;
    private javax.swing.JButton imprime1;
    private javax.swing.JButton imprime2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
