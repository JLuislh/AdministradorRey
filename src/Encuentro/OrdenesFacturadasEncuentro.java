/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Encuentro;

import ClasesElRey.BDConexion_Encuentro;
import ClasesElRey.BDProductos_Encuentro;
import ClasesElRey.InsertarProducto;
import ClasesElRey.TextAreaRenderer;
import static Encuentro.SinFelEncuentro.Pedidos;
import FelAdmin.ObtenerProductosFactura;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/**
 *
 * @author Jorge Lopez
 */
public class OrdenesFacturadasEncuentro extends javax.swing.JPanel {
   int id_orden;
    /**
     * Creates new form OrfenesFacturadas
     */
    public OrdenesFacturadasEncuentro() {
        initComponents();
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
             columna2.setPreferredWidth(275);
             TableColumn columna3 = Facturas.getColumn("NIT");
             columna3.setPreferredWidth(150);
             TableColumn columna4 = Facturas.getColumn("NOMBRE");
             columna4.setPreferredWidth(255);
             TableColumn columna5 = Facturas.getColumn("TOTAL");
             columna5.setPreferredWidth(75);
     }
     
     public  void sumaTotal() {
        DecimalFormat df = new DecimalFormat("#.00");
            try {
                 BDConexion_Encuentro conecta = new BDConexion_Encuentro();
                Connection cn = conecta.getConexion();
                java.sql.Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery("select truncate(sum(precio),2) as Total from PRODUCTOS_PEDIDO where id_pedido =" + id_orden);
                while (rs.next()) {
                     String TOTAL = df.format(rs.getInt(1));
                    total.setText(String.valueOf(TOTAL));
                }
                rs.close();
                stmt.close();
                cn.close();
            } catch (Exception error) {
                System.out.print(error);
            }
        }
     
      public  void ListarProductosPedidos(){
        id_orden = (Integer.parseInt(String.valueOf(Facturas.getModel().getValueAt(Facturas.getSelectedRow(), 0))));
        ArrayList<InsertarProducto> result = BDProductos_Encuentro.ListarProductosPedidos(id_orden);
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
             TableColumn columna1 = Pedidos.getColumn("ID");
             columna1.setPreferredWidth(-20);
             TableColumn columna2 = Pedidos.getColumn("DESCRIPCION");
             columna2.setPreferredWidth(300);
             TableColumn columna3 = Pedidos.getColumn("CANTIDAD");
             columna3.setPreferredWidth(75);
              TableColumn columna4 = Pedidos.getColumn("PRECIO");
             columna4.setPreferredWidth(75);
             sumaTotal();
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
        Facturas = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Pedidos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        FECHA = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

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

        Pedidos.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        Pedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripcion", "Cantidad", "Precio"
            }
        ));
        Pedidos.setRowHeight(30);
        jScrollPane2.setViewportView(Pedidos);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("TOTAL");

        total.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(FECHA, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(345, 345, 345))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FECHA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 62, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void FacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FacturasMouseClicked

        id_orden = (Integer.parseInt(String.valueOf(Facturas.getModel().getValueAt(Facturas.getSelectedRow(), 0))));
        ListarProductosPedidos();
    }//GEN-LAST:event_FacturasMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (FECHA.getDate() != null) {
            ListarFacturasGeneradas();
        }else {
            JOptionPane.showMessageDialog(null, "INGRESE UNA FECHA...");
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser FECHA;
    private javax.swing.JTable Facturas;
    public static javax.swing.JTable Pedidos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
}
