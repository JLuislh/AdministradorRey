/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gastos;

import org.w3c.dom.Document;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.File;
import java.math.BigDecimal;
import java.sql.*;

public class FacturaFELParser {

    public static void procesarXML(File xml, Connection conn) throws Exception {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xml);

        XPath xpath = XPathFactory.newInstance().newXPath();

        String uuid = xpath.evaluate("//*[local-name()='NumeroAutorizacion']", doc);
        String numeroFactura = xpath.evaluate("//*[local-name()='NumeroAutorizacion']/@Numero",doc).trim();
        String fecha = xpath.evaluate("//*[local-name()='DatosGenerales']/@FechaHoraEmision",doc).trim();
        String fechaSQL = fecha.substring(0,10);
        String proveedor = xpath.evaluate("//*[local-name()='Emisor']/@NombreEmisor",doc).trim();
        String nit = xpath.evaluate("//*[local-name()='Emisor']/@NITEmisor",doc).trim();
        String total = xpath.evaluate("//*[local-name()='GranTotal']/text()",doc).trim();

        String sql = """
            INSERT INTO facturas(uuid, numero_factura, fecha, proveedor, nit, total)
            VALUES (?, ?, ?, ?, ?, ?)
            ON DUPLICATE KEY UPDATE total = VALUES(total)
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, uuid);
        ps.setString(2, numeroFactura);
        ps.setDate(3, Date.valueOf(fechaSQL.substring(0, 10)));
        ps.setString(4, proveedor);
        ps.setString(5, nit);
        ps.setBigDecimal(6, new BigDecimal(total));

        ps.executeUpdate();
    }
}
