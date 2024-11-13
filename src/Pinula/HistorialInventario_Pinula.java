/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Pinula;

import Encuentro.*;
import ClasesElRey.BDConexion_Pinula;
import ClasesElRey.BDProductos_Pinula;
import ClasesElRey.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/**
 *
 * @author jluis
 */
public class HistorialInventario_Pinula extends javax.swing.JPanel {
    String Fechain;
    String Fechaout; 
    String FechaListar; 
    
    int existe;
    int existeFin;
    /**
     * Creates new form HistorialInventario
     */
    public HistorialInventario_Pinula() {
        initComponents();
        ListarProductos();
    }
    
     private void ListarProductos() {
        ArrayList<Productos> result = BDProductos_Pinula.ListarProductosInventario();
        recagarTabla(result);
    }

    private void recagarTabla(ArrayList<Productos> list) {

        Object[][] dato = new Object[list.size()][3];
        int f = 0;
        for (Productos a : list) {
            dato[f][0] = a.getCodigo();
            dato[f][1] = a.getDescripcion();
            dato[f][2] = a.getCantidad2();
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
    
    
    private void IniciarInventario() {
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         Fechain = df.format(FECHA.getDate());
          //System.out.println("FECHA = "+Fecha); 
        BDConexion_Pinula conecta = new BDConexion_Pinula();
        Connection con = conecta.getConexion();
        PreparedStatement sm = null;
        try {
            sm = con.prepareStatement("insert into consumos(codigo,cantidadInicio,fecha,estado) (select codigo,cantidad,'"+Fechain+"',1 from productos_inventario)");
            sm.executeUpdate();
            con.close();
            sm.close();
            JOptionPane.showMessageDialog(null, "INICIO DE INVENTARIO AGREGADO");
        } catch (SQLException ex) {
            System.out.println("ERROR ="+ex);
            //Logger.getLogger(CargaProductos_Encuentro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void FinalizarInventario() {
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         Fechaout = df.format(FECHA.getDate());
          //System.out.println("FECHA = "+Fecha); 
       BDConexion_Pinula conecta = new BDConexion_Pinula();
        Connection con = conecta.getConexion();
        PreparedStatement sm = null;
        try {
            sm = con.prepareStatement("update consumos c,productos_inventario p set c.CantidadFinal = p.cantidad,estado = 2 where c.Codigo = p.codigo and c.fecha = '"+Fechaout+"'");
            sm.executeUpdate();
            con.close();
            sm.close();
            JOptionPane.showMessageDialog(null, "INICIO DE INVENTARIO AGREGADO");
        } catch (SQLException ex) {
            System.out.println("ERROR ="+ex);
            //Logger.getLogger(CargaProductos_Encuentro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public  void ObtenerExistecian() {
        
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         Fechain = df.format(FECHA.getDate());
          //System.out.println("FECHA = "+Fecha);
            try {
                 BDConexion_Pinula conecta = new BDConexion_Pinula();
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
        }
    
    public  void ObtenerExistecianFinalizar() {
        
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         Fechaout = df.format(FECHA.getDate());
          //System.out.println("FECHA = "+Fecha);
            try {
                 BDConexion_Pinula conecta = new BDConexion_Pinula();
                Connection cn = conecta.getConexion();
                java.sql.Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as Existe FROM consumos WHERE ESTADO = 1 and FECHA = '"+ Fechaout +"'");
                while (rs.next()) {
                    existeFin = rs.getInt(1);
                }
                rs.close();
                stmt.close();
                cn.close();
                
            } catch (Exception error) {
                System.out.print(error);
            }
        }
    
    
    private void ListarHistorial() {
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         FechaListar = df.format(FECHA.getDate());
        ArrayList<Productos> result = BDProductos_Pinula.ListarProductosHistorialInventario_pinula(FechaListar);
        Lista(result);
    }

    private void Lista(ArrayList<Productos> list) {

        Object[][] dato = new Object[list.size()][5];
        int f = 0;
        for (Productos a : list) {
            dato[f][0] = a.getCodigo();
            dato[f][1] = a.getDescripcion();
            dato[f][2] = a.getCantidadinicial();
            dato[f][3] = a.getCantidadfinal();
            dato[f][4] = a.getCantidad2();
            f++;
        }
        Historial.setModel(new javax.swing.table.DefaultTableModel(
                dato,
                new String[]{
                    "CODIGO", "DESCRIPCION","CANTIDAD INICIAL","CANTIDAD FINAL","CANTIDAD"
                }) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                    return false;
                    }
                });
        
             TableColumn columna1 = Historial.getColumn("CODIGO");
             columna1.setPreferredWidth(10);
             TableColumn columna2 = Historial.getColumn("DESCRIPCION");
             columna2.setPreferredWidth(150);
              TableColumn columna3 = Historial.getColumn("CANTIDAD INICIAL");
             columna3.setPreferredWidth(50);
             TableColumn columna4 = Historial.getColumn("CANTIDAD FINAL");
             columna4.setPreferredWidth(50);
             TableColumn columna5 = Historial.getColumn("CANTIDAD");
             columna5.setPreferredWidth(30);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        Inventario = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        Guardar1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        FECHA = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Historial = new javax.swing.JTable();

        jPanel1.setPreferredSize(new java.awt.Dimension(1270, 528));

        Inventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION"
            }
        ));
        jScrollPane1.setViewportView(Inventario);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        Guardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ComponenteImagenes/Clipboard.png"))); // NOI18N
        Guardar1.setText("INICIAR INVENTARIO");
        Guardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Guardar1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ComponenteImagenes/Next.png"))); // NOI18N
        jButton2.setText("LISTAR INVENTARIO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ComponenteImagenes/No-entry.png"))); // NOI18N
        jButton3.setText("FINALIZAR INVENTARIO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FECHA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(FECHA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Guardar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FECHA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(Guardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );

        Historial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD INICIAL", "CANTIDAD FINAL", "FECHA"
            }
        ));
        jScrollPane2.setViewportView(Historial);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Guardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Guardar1ActionPerformed
    
       if(FECHA.getDate() != null)
         {
           ObtenerExistecian();
           System.out.println("EXISTENCIA = "+existe);  
           if(existe == 0)
           {    
            //JOptionPane.showMessageDialog(null, "INVENTARIO INICIADO");
             IniciarInventario();
             ListarHistorial();
           }else{
             JOptionPane.showMessageDialog(null, "INVENTARIO YA INICIADO PARA ESTA FECHA");
         }
      }else{
      JOptionPane.showMessageDialog(null, "INGRESAR UNA FECHA");
      }  
    }//GEN-LAST:event_Guardar1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       if(FECHA.getDate() != null)
         {
             ListarHistorial();
         }
       else{
      JOptionPane.showMessageDialog(null, "INGRESAR UNA FECHA");
      }  
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       if(FECHA.getDate() != null)
         {
           ObtenerExistecianFinalizar();
           System.out.println("EXISTENCIA = "+existe);  
           if(existeFin > 0)
           {    
             FinalizarInventario();
             ListarHistorial();
           }else{
             JOptionPane.showMessageDialog(null, "INVENTARIO YA SE A FINALIZADO PARA ESTA FECHA");
           }
      }else{
      JOptionPane.showMessageDialog(null, "INGRESAR UNA FECHA");
      }  
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser FECHA;
    private javax.swing.JButton Guardar1;
    private javax.swing.JTable Historial;
    private javax.swing.JTable Inventario;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
