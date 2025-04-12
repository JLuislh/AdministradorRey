/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Pinula;

import Encuentro.*;
import ClasesElRey.BDConexion_Pinula;
import ClasesElRey.BDProductos_Pinula;
import ClasesElRey.InsertarProducto;
import ClasesElRey.TextAreaRenderer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/**
 * @author jluis
 */
public class EliminarPedido_Pinula extends javax.swing.JPanel {
    int tipo;
    int opcion;
    int id_producto_pedido;
    int id_producto;
    String QueryOpcion;
    int cantidad;
    double totalBD;
    /**
     * Creates new form EliminarPedido
     */
    public EliminarPedido_Pinula() {
        initComponents();
    }
    
    public  void ListarProductosPedidos(){
        
        ArrayList<InsertarProducto> result = BDProductos_Pinula.ListarProductosPedidos(Integer.parseInt(Pedido.getText()));
        RecargarTabla(result);  
    }
     public  void RecargarTabla(ArrayList<InsertarProducto> list) {
          DecimalFormat df = new DecimalFormat("#.00");
              Object[][] datos = new Object[list.size()][4];
              int i = 0;
              for(InsertarProducto t : list)
              {
                  datos[i][0] = t.getId_producto_pedidos();
                  datos[i][1] = t.getDescripcion();
                  datos[i][2] = t.getCantidad1();
                  datos[i][3] = df.format(t.getPrecio());
                  i++;
              }    
             
             Pedidos.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                "ID","DESCRIPCION","CANTIDAD","PRECIO"
             })
             {  
                 @Override
                 public boolean isCellEditable(int row, int column){
                 return false;
             }
             });
             Pedidos.getColumnModel().getColumn(0).setCellRenderer(new TextAreaRenderer());// ajustar texcto a ancho de celda
             TableColumn columna1 = Pedidos.getColumn("DESCRIPCION");
             columna1.setPreferredWidth(100);
             TableColumn columna2 = Pedidos.getColumn("CANTIDAD");
             columna2.setPreferredWidth(10);
             TableColumn columna3 = Pedidos.getColumn("PRECIO");
             columna3.setPreferredWidth(10);
             sumaTotal();
            }
     
          public  void sumaTotal() {
        DecimalFormat df = new DecimalFormat("#.00");
            try {
                 BDConexion_Pinula conecta = new BDConexion_Pinula();
                Connection cn = conecta.getConexion();
                java.sql.Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery("select truncate(sum(precio),2) as Total from PRODUCTOS_PEDIDO where id_pedido =" + Pedido.getText());
                while (rs.next()) {
                     String TOTAL = df.format(rs.getInt(1));
                    Total.setText(String.valueOf(TOTAL));
                    totalBD = Double.parseDouble(TOTAL);
                }
                rs.close();
                stmt.close();
                cn.close();
            } catch (Exception error) {
                System.out.print(error);
            }
        }
     
     public  void ObtenerOpcion() {
            try {
                 BDConexion_Pinula conecta = new BDConexion_Pinula();
                Connection cn = conecta.getConexion();
                java.sql.Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery("select opcion,tipo,id_producto from productos_pedido where id_productos_pedido = " + id_producto_pedido);
                while (rs.next()) {
                    opcion = rs.getInt(1);
                    tipo = rs.getInt(2);
                    id_producto = rs.getInt(3);
                }
                rs.close();
                stmt.close();
                cn.close();
                
            } catch (Exception error) {
                System.out.print(error);
            }
        }

     
     
     private void eliminarproducto(){
         ObtenerOpcion();
        try {
            BDConexion_Pinula conecta = new BDConexion_Pinula();
            Connection con = conecta.getConexion();
            if(opcion ==1){
             QueryOpcion = "{call Opcion1_regresarinventario("+tipo+","+id_producto+","+cantidad+")}";
            }else if (opcion ==2){
             QueryOpcion = "{call Opcion2_regresarainventario("+cantidad+","+id_producto_pedido+")}";
            }else if (opcion ==3){
             QueryOpcion = "{call Opcion3_regresarainventario("+cantidad+","+id_producto_pedido+")}"; 
            }else if (opcion ==4){
             QueryOpcion = "{call Opcion4_regresarainventario("+cantidad+","+id_producto+","+tipo+")}";  
            }else if (opcion ==5){
                QueryOpcion = "{call Opcion5_regresarinventario("+cantidad+","+id_producto+")}"; 
            }else if(opcion >= 6) 
            {
                 QueryOpcion = "{call Opcion6_regresarinventario("+cantidad+","+id_producto+")}"; 
            }
            PreparedStatement ps = null;
            PreparedStatement pse = null;
            //ps= con.prepareStatement("ROLLBACK to savepoint menu"+id_producto_pedido);
            //pse= con.prepareStatement("{call Opcion2_regresarainventario("+cantidad+","+id_producto_pedido+")}");
            pse= con.prepareStatement(QueryOpcion);
            ps= con.prepareStatement("delete  from PRODUCTOS_PEDIDO  where ID_PRODUCTOS_PEDIDO ="+id_producto_pedido);
            pse.executeUpdate();                   
            ps.executeUpdate();
            con.close();
            ps.close();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"ERROR = "+ex);
        }
 }
     
      private void eliminarOrden(){
        try {
            BDConexion_Pinula conecta = new BDConexion_Pinula();
            Connection con = conecta.getConexion();
            PreparedStatement ps = null;
            ps= con.prepareStatement("delete from PEDIDOS where id_pedido="+Pedido.getText());
            ps.executeUpdate();
            con.close();
            ps.close();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"ERROr = "+ex);
        }
        JOptionPane.showMessageDialog(null, "ORDEN ELIMINADA");
        ListarProductosPedidos();
        Pedido.setText("");
        Total.setText("");
 }
      
       private void finalizar(){
   
        try {
                 BDConexion_Pinula conecta = new BDConexion_Pinula();
                 Connection con = conecta.getConexion();
                 PreparedStatement smtp = null;
                 smtp = con.prepareStatement("update PEDIDOS SET TOTAL = "+totalBD+" WHERE ID_PEDIDO ="+Pedido.getText());
                 smtp.executeUpdate();
                 con.close();
                 smtp.close();
               // JOptionPane.showMessageDialog(null, "Guardado...");
            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog(null, ex);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        Pedidos = new javax.swing.JTable();
        Total = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Pedido = new javax.swing.JTextField();
        Buscar = new javax.swing.JButton();
        panelRound2 = new ClasesElRey.PanelRound();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Pedidos.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        Pedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripcion", "Cantidad", "Precio"
            }
        ));
        Pedidos.setRowHeight(30);
        Pedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PedidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Pedidos);

        Total.setEditable(false);
        Total.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Total.setForeground(new java.awt.Color(255, 0, 0));
        Total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("TOTAL");

        Pedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PedidoMouseClicked(evt);
            }
        });
        Pedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PedidoActionPerformed(evt);
            }
        });

        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });

        panelRound2.setBackground(new java.awt.Color(255, 102, 102));
        panelRound2.setRoundBottomLeft(20);
        panelRound2.setRoundBottomRight(20);
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(20);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ELIMINAR");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Pedido, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Buscar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(361, 361, 361)
                        .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Buscar)
                    .addComponent(Pedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PedidosMouseClicked
     
    id_producto_pedido = (Integer.parseInt(String.valueOf(Pedidos.getModel().getValueAt(Pedidos.getSelectedRow(), 0))));
    cantidad = (Integer.parseInt(String.valueOf(Pedidos.getModel().getValueAt(Pedidos.getSelectedRow(), 2))));
     if (evt.getClickCount() > 1) {
     
            int resp=JOptionPane.showConfirmDialog(null,"DESEA ELIMINAR EL PRODUCTO");
            if (JOptionPane.OK_OPTION == resp){
                eliminarproducto();
                ListarProductosPedidos();
                finalizar();
            }
    
     }
        
    }//GEN-LAST:event_PedidosMouseClicked

    private void TotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalActionPerformed

    private void PedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PedidoMouseClicked

    }//GEN-LAST:event_PedidoMouseClicked

    private void PedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PedidoActionPerformed
        ListarProductosPedidos();
        Buscar.requestFocus();
    }//GEN-LAST:event_PedidoActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        if(Pedido.getText().compareTo("") !=0)
        {
        ListarProductosPedidos();
        }else{JOptionPane.showMessageDialog(null, "INGRESE NO DE ORDEN");}
    }//GEN-LAST:event_BuscarActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
       if(Pedidos.getRowCount()>0){
      JOptionPane.showMessageDialog(null, "ELIMINE TODOS LOS PRODUCTOS"); 
       }else{ eliminarOrden();}
    }//GEN-LAST:event_jLabel3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    public static javax.swing.JTextField Pedido;
    public static javax.swing.JTable Pedidos;
    public static javax.swing.JTextField Total;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private ClasesElRey.PanelRound panelRound2;
    // End of variables declaration//GEN-END:variables
}
