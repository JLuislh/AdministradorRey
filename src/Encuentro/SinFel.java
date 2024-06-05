/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Encuentro;

import ClasesElRey.BDConexion_Encuentro;
import ClasesElRey.BDProductos_Encuentro;
import ClasesElRey.InsertarProducto;
import ClasesElRey.TextAreaRenderer;
import static Encuentro.EliminarPedido_Encuentro.Pedido;
import FelAdmin.ObtenerProductosFactura;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author Jorge Lopez
 */
public class SinFel extends javax.swing.JPanel {
     int id_odren;
     double totalBD;
    /**
     * Creates new form SinFel
     */
    public SinFel() {
        initComponents();
    }
    
    public void ListarOrdenSinFEL(){
        
        Date date = FECHA.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String fecha = sdf.format(date);
     
        ArrayList<ObtenerProductosFactura> result = ObtenerProductosFactura.ListarOrdenesSinFel(fecha);
        RecargarTabla(result);  
    }
     public  void RecargarTabla(ArrayList<ObtenerProductosFactura> list) {
              DecimalFormat df = new DecimalFormat("#.00");
              Object[][] datos = new Object[list.size()][3];
              int i = 0;
              for(ObtenerProductosFactura t : list)
              {
                  datos[i][0] = t.getId_pedido();
                  datos[i][1] = t.getFecha();
                  datos[i][2] = df.format(t.getTotal());
                  i++;
              }    
             Facturas.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                "No. Orden","FECHA","TOTAL"
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
             TableColumn columna2 = Facturas.getColumn("FECHA");
             columna2.setPreferredWidth(75);
             TableColumn columna3 = Facturas.getColumn("TOTAL");
             columna3.setPreferredWidth(75);
           
     }
     
     public  void ListarProductosPedidos(){
        id_odren = (Integer.parseInt(String.valueOf(Facturas.getModel().getValueAt(Facturas.getSelectedRow(), 0))));
        ArrayList<InsertarProducto> result = BDProductos_Encuentro.ListarProductosPedidos(id_odren);
        Recarga(result);  
    }
     public  void Recarga(ArrayList<InsertarProducto> list) {
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
                 BDConexion_Encuentro conecta = new BDConexion_Encuentro();
                Connection cn = conecta.getConexion();
                java.sql.Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery("select truncate(sum(precio),2) as Total from PRODUCTOS_PEDIDO where id_pedido =" + id_odren);
                while (rs.next()) {
                     String TOTAL = df.format(rs.getInt(1));
                    total.setText(String.valueOf(TOTAL));
                    totalBD = Double.parseDouble(TOTAL);
                }
                rs.close();
                stmt.close();
                cn.close();
            } catch (Exception error) {
                System.out.print(error);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        Pedidos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FECHA DE ORDENES SIN FACTURA");

        Facturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Orden", "FECHA", "TOTAL"
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
        jScrollPane2.setViewportView(Pedidos);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("TOTAL");

        total.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 27, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(FECHA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(FECHA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 239, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void FacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FacturasMouseClicked
       
        ListarProductosPedidos();
        
    }//GEN-LAST:event_FacturasMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (FECHA.getDate() != null) {
           ListarOrdenSinFEL();
        }else {
            JOptionPane.showMessageDialog(null, "INGRESE UNA FECHA...");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void PedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PedidosMouseClicked

       //int id_producto_pedido = (Integer.parseInt(String.valueOf(Pedidos.getModel().getValueAt(Pedidos.getSelectedRow(), 0))));
       //int cantidad = (Integer.parseInt(String.valueOf(Pedidos.getModel().getValueAt(Pedidos.getSelectedRow(), 2))));
        if (evt.getClickCount() > 1) {

            int resp=JOptionPane.showConfirmDialog(null,"DESEA ELIMINAR EL PRODUCTO");
            if (JOptionPane.OK_OPTION == resp){
                
            }

        }

    }//GEN-LAST:event_PedidosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser FECHA;
    private javax.swing.JTable Facturas;
    public static javax.swing.JTable Pedidos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
}
