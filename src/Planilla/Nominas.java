/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Planilla;

import ClasesElRey.TextAreaRenderer;
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
public class Nominas extends javax.swing.JInternalFrame {

    private TableRowSorter<DefaultTableModel> sorter;
    private DefaultTableModel modelo;
    private ArrayList<ClassNomina> result;
    int idNomina;

    /**
     * Creates new form Nominas
     */
    public Nominas() throws Exception {
        initComponents();
        ListarNominas();

        btnCerrar.addActionListener(e -> {
            int fila = tablaNomina.getSelectedRow();

            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una nómina.");
                return;
            }

            // Obtener ID de la primera columna
             idNomina = Integer.parseInt(tablaNomina.getValueAt(fila, 0).toString());

            // Llamar tu método
            CerrarNomina(idNomina);
            try {
                ListarNominas();
            } catch (Exception ex) {
                Logger.getLogger(Nominas.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Nominas.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    private void ListarNominas() throws Exception {
        result = ApiPlanilla.cargarNominas(1);
        RecargarTabla(result);
    }

    private void RecargarTabla(ArrayList<ClassNomina> list) {
        Object[][] datos = new Object[list.size()][3];
        int i = 0;
        for (ClassNomina t : list) {
            datos[i][0] = t.getId_nomina();
            datos[i][1] = t.getFechaincio();
            datos[i][2] = t.getFechafin();
            i++;
        }

        modelo = new DefaultTableModel(
                datos,
                new String[]{"No Nomina", "DE", "A"}
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
        tablaNomina.getColumn("No Nomina").setPreferredWidth(100);
        tablaNomina.getColumn("DE").setPreferredWidth(150);
        tablaNomina.getColumn("A").setPreferredWidth(150);
    }

    private void crearNomina() {
        try {
            int dias = 15;
            int estado = 1;

            // FECHA DESDE EL JDATECHOOSER
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechainicio = sdf.format(this.fechainicio.getDate());
            String fechafin = sdf.format(this.fechafin.getDate());

            String respuesta = ApiPlanilla.crearNomina(fechainicio, fechafin, dias, estado);

            JOptionPane.showMessageDialog(
                    null,
                    "Ingreso guardado correctamente",
                    "OK",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar ingreso: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void CerrarNomina(int id) {

        try {
            JSONObject json = new JSONObject();
            json.put("MONTO", 0);
            json.put("ESTADO", 2);
            String respuesta = ApiPlanilla.CerrarNomina(id, json.toString());
        } catch (Exception ex) {
            System.out.println(ex);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fechainicio = new com.toedter.calendar.JDateChooser();
        fechafin = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaNomina = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nueva Nomina", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("FECHA INICIO");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("FECHA FIN");

        jButton1.setText("GENERAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(fechainicio, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                            .addComponent(fechafin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechainicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fechafin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );

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

        btnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCerrar.setText("CERRAR NOMINA");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnImprimir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ComponenteImagenes/Print.png"))); // NOI18N
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCerrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21))
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
        try {
            crearNomina();
            ListarNominas();
            fechafin.setDate(null);
            fechainicio.setDate(null);
        } catch (Exception ex) {
            Logger.getLogger(Nominas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        
    
        
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnImprimir;
    private com.toedter.calendar.JDateChooser fechafin;
    private com.toedter.calendar.JDateChooser fechainicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaNomina;
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
