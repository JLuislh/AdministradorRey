/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Planilla;

import ClasesElRey.TextAreaRenderer;
import static Planilla.ApiPlanilla.obtenerPagoNomina;
import PlanillaBoletas.NominaDetalle;
import PlanillaBoletas.ServicioNomina;
import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.json.JSONObject;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author it
 */
public class Nominas_Historial extends javax.swing.JInternalFrame {

    private TableRowSorter<DefaultTableModel> sorter;
    private DefaultTableModel modelo;
    private ArrayList<ClassNomina> result;
    int idNomina;

    /**
     * Creates new form Nominas
     */
    public Nominas_Historial() throws Exception {
        initComponents();
        ListarNominas();

       ////////////////llena tabla /////////////
        tablaNomina.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                int fila = tablaNomina.getSelectedRow();

                if (fila >= 0) {
                    // Tomar el valor de la PRIMER columna (columna 0)
                    Object valor = tablaNomina.getValueAt(fila, 0);

                    if (valor != null) {
                        try {
                            idNomina = Integer.parseInt(valor.toString());
                            // Ejecutar tu método
                            llenarTablaPagos(idNomina);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error al procesar ID: " + e.getMessage());
                        }
                    }
                }
            }
        });
        
        
        
        btnImprimir.addActionListener(e -> {
            int fila = tablaNomina.getSelectedRow();

            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una nómina.");
                return;
            }

            // Obtener ID de la primera columna
             idNomina = Integer.parseInt(tablaNomina.getValueAt(fila, 0).toString());

            // Llamar tu método
            generar(idNomina);
            try {
            } catch (Exception ex) {
                Logger.getLogger(Nominas_Historial.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
    
    
    
    public void llenarTablaPagos(int idNomina) {
        try {
            List<EmpleadoPago> datos = obtenerPagoNomina(idNomina);

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Codigo");
            modelo.addColumn("Nombre");
            modelo.addColumn("Puesto");
            modelo.addColumn("Liquido");

            for (EmpleadoPago ep : datos) {
                modelo.addRow(new Object[]{
                    ep.CODIGO,
                    ep.NOMBRES,
                    ep.PUESTO,
                    ep.LIQUIDO});
            }

            tablaPagosNomina.setModel(modelo);
            tablaPagosNomina.getColumn("Codigo").setPreferredWidth(0);
            tablaPagosNomina.getColumn("Nombre").setPreferredWidth(150);
            tablaPagosNomina.getColumn("Puesto").setPreferredWidth(75);
            tablaPagosNomina.getColumn("Liquido").setPreferredWidth(50);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error cargando datos:\n" + e.getMessage());
        }
    }
    

    private void ListarNominas() throws Exception {
        result = ApiPlanilla.cargarNominas(2);
        RecargarTabla(result);
    }

    private void RecargarTabla(ArrayList<ClassNomina> list) {
        Object[][] datos = new Object[list.size()][4];
        int i = 0;
        for (ClassNomina t : list) {
            datos[i][0] = t.getId_nomina();
            datos[i][1] = t.getFechaincio();
            datos[i][2] = t.getFechafin();
            datos[i][3] = t.getMonto();
            i++;
        }

        modelo = new DefaultTableModel(
                datos,
                new String[]{"No Nomina", "DE", "A","MONTO TOTAL"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Permitir la edición solo de la columna descripción
                return column == 1;
            }
        };

        tablaNomina.setModel(modelo);

        // ⭐ IMPORTANTE → Asignar sorter
        sorter = new TableRowSorter<>(modelo);
        tablaNomina.setRowSorter(sorter);

        tablaNomina.getColumnModel().getColumn(1).setCellRenderer(new TextAreaRenderer());
        tablaNomina.getColumn("No Nomina").setPreferredWidth(20);
        tablaNomina.getColumn("DE").setPreferredWidth(125);
        tablaNomina.getColumn("A").setPreferredWidth(125);
        tablaNomina.getColumn("MONTO TOTAL").setPreferredWidth(125);
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
        tablaNomina = new javax.swing.JTable();
        btnImprimir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPagosNomina = new javax.swing.JTable();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tablaNomina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaNomina);

        btnImprimir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ComponenteImagenes/Print.png"))); // NOI18N
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        tablaPagosNomina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaPagosNomina);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
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


    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        
    
        
    }//GEN-LAST:event_btnImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaNomina;
    private javax.swing.JTable tablaPagosNomina;
    // End of variables declaration//GEN-END:variables

public static void generar(int idNomina) {
    try {
        // 1) Obtener datos desde API
        System.out.println("DATOS= " + ServicioNomina.obtenerDatos(idNomina));
        List<NominaDetalle> datos = ServicioNomina.obtenerDatos(idNomina);

        // 2) Convertir a datasource para Jasper
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(datos);
        System.out.println("Planilla.Nominas.generar()" + datos);

        // 3) Parámetros
        Map<String, Object> params = new HashMap<>();
        params.put("ID_NOMINA", idNomina);

        // 4) Cargar archivo .jasper
        JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile("reportes/Recibo.jasper");

        // 5) Llenar reporte
        JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);

        // 6) Exportar PDF
        String ruta = "reporte_nomina.pdf";
        JasperExportManager.exportReportToPdfFile(jp, ruta);

        System.out.println("PDF generado con éxito.");

        // 7) Abrir el PDF automáticamente
        File archivo = new File(ruta);
        if (archivo.exists()) {
            Desktop.getDesktop().open(archivo);  // 🔥 Abre el PDF
        } else {
            System.out.println("El archivo PDF no existe y no se pudo abrir.");
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }
}




}
