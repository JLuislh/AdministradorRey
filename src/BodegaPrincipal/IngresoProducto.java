/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package BodegaPrincipal;

import ClasesElRey.BDConexion;
import ClasesElRey.InsertarProducto;
import ClasesElRey.TextAreaRenderer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/**
 *
 * @author jluis
 */
public class IngresoProducto extends javax.swing.JPanel {

    /**
     * Creates new form IngresoProducto
     */
    public IngresoProducto() {
        initComponents();
        ListarProductos();
    }
    
    private void ListarProductos(){
     
        ArrayList<InsertarProducto> result = BDBodegaP.ListaProductosBodegaPrincipal();
        RecargarTabla(result);  
    }
     private void RecargarTabla(ArrayList<InsertarProducto> list) {
        // DecimalFormat df = new DecimalFormat("#.00");
              Object[][] datos = new Object[list.size()][4];
              int i = 0;
              for(InsertarProducto t : list)
              {
                  datos[i][0] = t.getCodigo();
                  datos[i][1] = t.getDescripcion();
                  datos[i][2] = t.getUMedida();
                  datos[i][3] = t.getCantidad();
                  i++;
              }    
             PRO.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                "CODIGO","DESCRIPCION","UNIDAD MEDIDA","CANTIDAD"
             })
             {  
                 @Override
                 public boolean isCellEditable(int row, int column){
                 return false;

             }
             });
             PRO.getColumnModel().getColumn(1).setCellRenderer(new TextAreaRenderer());
             TableColumn columna1 = PRO.getColumn("CODIGO");
             columna1.setPreferredWidth(-20);
             TableColumn columna2 = PRO.getColumn("DESCRIPCION");
             columna2.setPreferredWidth(225);
             TableColumn columna3 = PRO.getColumn("UNIDAD MEDIDA");
             columna3.setPreferredWidth(35);
             TableColumn columna4 = PRO.getColumn("CANTIDAD");
             columna4.setPreferredWidth(35);
             
     }
     public void limpiar(){
     CODIGO.setText("");
     DESCRI.setText("");
     MEDIDA.setText("");
     CANTIDAD.setText("");
     CANTIDADIN.setText("");
     NOTA.setText("");
     PRECIO.setText("");
     }
     
     public void ingresonuevo() throws SQLException{
    
        BDConexion conecta = new BDConexion();
        PreparedStatement smtp;
        try (Connection con = conecta.getConexion()) {
            smtp = null;
            smtp =con.prepareStatement("call INGRESOCANTIDADBP('"+CODIGO.getText()+"','"+CANTIDADIN.getText()+"','"+PRECIO.getText()+"','"+NOTA.getText()+"')");
            smtp.executeUpdate();
        }
        smtp.close(); 
        JOptionPane.showMessageDialog(null, "CANTIDAD AGREGADA");
        ListarProductos();
        limpiar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PRO = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        CODIGO = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        DESCRI = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        MEDIDA = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        CANTIDAD = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        CANTIDADIN = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        NOTA = new javax.swing.JTextArea();
        GUARDAR = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        PRECIO = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INGRESAR PRODUCTO");

        PRO.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION"
            }
        ));
        PRO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PROMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(PRO);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("CODIGO");

        CODIGO.setEditable(false);
        CODIGO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        CODIGO.setForeground(new java.awt.Color(51, 51, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("DESCRIPCION");

        DESCRI.setEditable(false);
        DESCRI.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DESCRI.setForeground(new java.awt.Color(51, 51, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("UNIDAD DE MEDIDA");

        MEDIDA.setEditable(false);
        MEDIDA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        MEDIDA.setForeground(new java.awt.Color(51, 51, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CANTIDAD DISPONIBLE");

        CANTIDAD.setEditable(false);
        CANTIDAD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CANTIDAD.setForeground(new java.awt.Color(51, 204, 0));
        CANTIDAD.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MEDIDA)
                    .addComponent(DESCRI)
                    .addComponent(CODIGO)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(CANTIDAD))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CODIGO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DESCRI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MEDIDA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CANTIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INGRESO DE CANTIDAD Y ANOTACIONES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        CANTIDADIN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CANTIDADIN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CANTIDADIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CANTIDADINActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("CANTIDAD DE INGRESO");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("NOTA");

        NOTA.setColumns(20);
        NOTA.setRows(5);
        jScrollPane2.setViewportView(NOTA);

        GUARDAR.setText("INGRESO");
        GUARDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUARDARActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("PRECIO");

        PRECIO.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PRECIO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PRECIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PRECIOActionPerformed(evt);
            }
        });
        PRECIO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PRECIOKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PRECIO, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CANTIDADIN)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addComponent(GUARDAR)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CANTIDADIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PRECIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(GUARDAR, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PROMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PROMouseClicked
        CODIGO.setText(String.valueOf(PRO.getModel().getValueAt(PRO.getSelectedRow(), 0)));
        DESCRI.setText(String.valueOf(PRO.getModel().getValueAt(PRO.getSelectedRow(), 1)));
        MEDIDA.setText(String.valueOf(PRO.getModel().getValueAt(PRO.getSelectedRow(), 2)));
        CANTIDAD.setText(String.valueOf(PRO.getModel().getValueAt(PRO.getSelectedRow(), 3)));
        CANTIDADIN.requestFocus();
    }//GEN-LAST:event_PROMouseClicked

    private void GUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GUARDARActionPerformed
        try {
            ingresonuevo();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(IngresoProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_GUARDARActionPerformed

    private void PRECIOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PRECIOKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9') && (car<'.')) 
        evt.consume();
    }//GEN-LAST:event_PRECIOKeyTyped

    private void CANTIDADINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CANTIDADINActionPerformed
        PRECIO.requestFocus();
    }//GEN-LAST:event_CANTIDADINActionPerformed

    private void PRECIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PRECIOActionPerformed
       GUARDAR.requestFocus();
    }//GEN-LAST:event_PRECIOActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CANTIDAD;
    private javax.swing.JTextField CANTIDADIN;
    private javax.swing.JTextField CODIGO;
    private javax.swing.JTextField DESCRI;
    private javax.swing.JButton GUARDAR;
    private javax.swing.JTextField MEDIDA;
    private javax.swing.JTextArea NOTA;
    private javax.swing.JTextField PRECIO;
    private javax.swing.JTable PRO;
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
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
