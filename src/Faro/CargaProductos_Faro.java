/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Faro;

import Encuentro.*;
import ClasesElRey.BDConexion_ElFaro;
import ClasesElRey.BDProductos_Faro;
import ClasesElRey.Productos;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author jluis
 */
public class CargaProductos_Faro extends javax.swing.JPanel {
      int codigo1;
      String Fechain;
      String Fecha;
      int existe;
    /**
     * Creates new form CargaProductos
     */
    public CargaProductos_Faro() {
        initComponents();
        ListarProductos();
        Guardar.setEnabled(false);
    }
    
    private void ListarProductos() {
        ArrayList<Productos> result = BDProductos_Faro.ListarProductosInventario();
        recagarTabla(result);
    }

    private void recagarTabla(ArrayList<Productos> list) {

        Object[][] dato = new Object[list.size()][3];
        int f = 0;
        for (Productos a : list) {
            dato[f][0] = a.getCodigo();
            dato[f][1] = a.getDescripcion();
            dato[f][2] = a.getCantidad();
            f++;
        }
        Inventario.setModel(new javax.swing.table.DefaultTableModel(
                dato,
                new String[]{
                    "CODIGO", "DESCRIPCION","CANTIDAD"
                }) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                    return false;
                    }
                });
        
             TableColumn columna1 = Inventario.getColumn("CODIGO");
             columna1.setPreferredWidth(0);
             TableColumn columna2 = Inventario.getColumn("DESCRIPCION");
             columna2.setPreferredWidth(275);
              TableColumn columna3 = Inventario.getColumn("CANTIDAD");
             columna3.setPreferredWidth(50);
    }
    private void BuscarProducto() {
    
     try {
           

            Productos ca = BDProductos_Faro.BuscarProducto(Integer.parseInt(String.valueOf(Inventario.getModel().getValueAt(Inventario.getSelectedRow(), 0))));
            //LaDescrip.setText(ca.getDescripcion());
            //unidadMedida.setText(ca.getPresentacion());
            codigo.setText(String.valueOf(ca.getCodigo()));
            descripcion.setText(ca.getDescripcion());
            cantidad.setText(String.valueOf(ca.getCantidad()));
          
        } catch (Exception e) {
            System.out.println("ERROR REPORTE AL ADMINISTRADOR DE SISTEMA" + e);
        }
    }
    
    private void Limpiar(){
    
    codigo.setText("");
    descripcion.setText("");
    cantidad.setText("");
    cantidadCarga.setText("");
    ListarProductos();
    ListarProductosIngresados();
    
    }
    
    private void ListarProductosIngresados() {
        ArrayList<Productos> result = BDProductos_Faro.ListarProductosInventarioIngresados(codigo1);
        ingresos(result);
    }

    private void ingresos(ArrayList<Productos> list) {

        Object[][] dato = new Object[list.size()][2];
        int f = 0;
        for (Productos a : list) {
            dato[f][0] = a.getFecha();
            dato[f][1] = a.getCantidad();
            f++;
        }
        ingresos.setModel(new javax.swing.table.DefaultTableModel(
                dato,
                new String[]{
                    "FECHA","CANTIDAD"
                }) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                    return false;
                    }
                });
        
             TableColumn columna1 = ingresos.getColumn("FECHA");
             columna1.setPreferredWidth(50);
             TableColumn columna2 = ingresos.getColumn("CANTIDAD");
             columna2.setPreferredWidth(50);
    }
    
    public  void ObtenerExistecian() {
         Date Fecha = new Date();
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         Fechain = df.format(Fecha);
            try {
                BDConexion_ElFaro conecta = new BDConexion_ElFaro();
                Connection cn = conecta.getConexion();
                java.sql.Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as Existe FROM consumos WHERE FECHA = '"+ Fechain +"'");
                while (rs.next()) {
                    existe = rs.getInt(1);
                }
                rs.close();
                stmt.close();
                cn.close();
                
            } catch (Exception error) {
                System.out.print(error);
            }
            System.out.println("Existe = "+existe);
        }
    
    
    public void actualizarCantidadHistorial(){
        
         Date Fe = new Date();
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         Fecha = df.format(Fe);
        if(cantidadCarga.getText().compareTo("")!=0){       
        BDConexion_ElFaro conecta = new BDConexion_ElFaro();
        Connection con = conecta.getConexion();
        PreparedStatement sm = null;
        PreparedStatement s = null;
        try {
            sm = con.prepareStatement("{call IngresoInventario("+codigo.getText()+","+cantidadCarga.getText()+")}"); //,"+fecha.getDateFormatString()+"
            s = con.prepareStatement("{call IngresoInventarioHistorial("+codigo.getText()+","+cantidadCarga.getText()+",'"+Fecha+"')}");
            sm.executeUpdate();
            s.executeUpdate();
            con.close();
            sm.close();
            JOptionPane.showMessageDialog(null, "INGRESO AGREGADO");
            Limpiar();
        } catch (SQLException ex) {
            System.out.println("ERROR ="+ex);
            //Logger.getLogger(CargaProductos_Encuentro.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{JOptionPane.showMessageDialog(null, "NO SE CARGA NINGUNA CANTIDAD");}
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ingresos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        codigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        descripcion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cantidad = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cantidadCarga = new javax.swing.JTextField();
        Guardar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Inventario = new javax.swing.JTable();

        jPanel1.setPreferredSize(new java.awt.Dimension(1270, 528));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        ingresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "CANTIDAD"
            }
        ));
        jScrollPane2.setViewportView(ingresos);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("CODIGO");

        jLabel6.setText("DESCRIPCION");

        descripcion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        descripcion.setForeground(new java.awt.Color(0, 102, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setText("CANTIDAD");

        cantidad.setEditable(false);
        cantidad.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        cantidad.setForeground(new java.awt.Color(255, 0, 0));
        cantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(128, 128, 128))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CANTIDAD INGRESO");

        cantidadCarga.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cantidadCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadCargaActionPerformed(evt);
            }
        });

        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save2.png"))); // NOI18N
        Guardar.setText("GUARDAR");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ComponenteImagenes/Print.png"))); // NOI18N
        jButton1.setText("IMPRIMIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cantidadCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cantidadCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        Inventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION"
            }
        ));
        Inventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InventarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Inventario);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cantidadCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadCargaActionPerformed
        Guardar.requestFocus();
    }//GEN-LAST:event_cantidadCargaActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        ObtenerExistecian();
        if(existe == 0){
        if(cantidadCarga.getText().compareTo("")!=0){ 
        BDConexion_ElFaro conecta = new BDConexion_ElFaro();
        Connection con = conecta.getConexion();
        PreparedStatement sm = null;
        try {
            sm = con.prepareStatement("{call IngresoInventario("+codigo.getText()+","+cantidadCarga.getText()+")}"); //,"+fecha.getDateFormatString()+"
            sm.executeUpdate();

            con.close();
            sm.close();
            JOptionPane.showMessageDialog(null, "INGRESO AGREGADO");
            Limpiar();
        } catch (SQLException ex) {
            System.out.println("ERROR ="+ex);
            //Logger.getLogger(CargaProductos_Encuentro.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{JOptionPane.showMessageDialog(null, "NO SE CARGA NINGUNA CANTIDAD");}
         
        }else{
            actualizarCantidadHistorial();
        }
       

    }//GEN-LAST:event_GuardarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        BDConexion_ElFaro con= new BDConexion_ElFaro();
        Connection conexion= con.getConexion();
        try {
            JasperReport jasperReport=(JasperReport)JRLoader.loadObjectFromFile("C:\\Reportes\\Inventario.jasper");
            //JasperReport jasperReport2=(JasperReport)JRLoader.loadObjectFromFile("\\\\SRVANATEK\\Bases de Datos\\Sistema\\Recursos Humanos\\Reportes\\EvaluacionDesempeñoImprime2.jasper");
            Map parametros= new HashMap();
            JasperPrint print = JasperFillManager.fillReport(jasperReport,parametros, conexion);
            //JasperPrint print2 = JasperFillManager.fillReport(jasperReport2,parametros, conexion);
            JasperPrintManager.printReport(print, true);
            //view2.setVisible(true);
        } catch (Exception e) {System.out.println("F"+e);
            JOptionPane.showMessageDialog(null, "ERROR EJECUTAR REPORTES =  "+e);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void InventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventarioMouseClicked
        codigo1 = Integer.parseInt(String.valueOf(Inventario.getModel().getValueAt(Inventario.getSelectedRow(), 0)));
        BuscarProducto();
        ListarProductosIngresados();
        cantidadCarga.requestFocus();
        Guardar.setEnabled(true);

    }//GEN-LAST:event_InventarioMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Guardar;
    private javax.swing.JTable Inventario;
    private javax.swing.JTextField cantidad;
    private javax.swing.JTextField cantidadCarga;
    private javax.swing.JTextField codigo;
    private javax.swing.JTextField descripcion;
    private javax.swing.JTable ingresos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
