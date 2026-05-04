/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlanillaBoletas;

/**
 *
 * @author it
 */
public class NominaDetalle {

    private String NOMBRES;
    private String PUESTO;
    private String PERIODO;
    private double TOTAL;
    private double BONIFICACION;
    private double BONOPRODUCTIVIDAD;
    private double IGSS;
    private double DESCUENTOS;
    private double LIQUIDO;
    private String TIEMPO_TRABAJADO;
    private String HORAS_EXTRAS;
    private String DESCUENTOS_DESC;

    public NominaDetalle() {}

    // ========== GETTERS Y SETTERS CON MAYÚSCULAS ==========

    public String getNOMBRES() { return NOMBRES; }
    public void setNOMBRES(String nombres) { this.NOMBRES = nombres; }

    public String getPUESTO() { return PUESTO; }
    public void setPUESTO(String puesto) { this.PUESTO = puesto; }

    public String getPERIODO() { return PERIODO; }
    public void setPERIODO(String periodo) { this.PERIODO = periodo; }

    public double getTOTAL() { return TOTAL; }
    public void setTOTAL(double total) { this.TOTAL = total; }

    public double getBONIFICACION() { return BONIFICACION; }
    public void setBONIFICACION(double bonificacion) { this.BONIFICACION = bonificacion; }

    public double getBONOPRODUCTIVIDAD() { return BONOPRODUCTIVIDAD; }
    public void setBONOPRODUCTIVIDAD(double bonoProductividad) { this.BONOPRODUCTIVIDAD = bonoProductividad; }

    public double getIGSS() { return IGSS; }
    public void setIGSS(double igss) { this.IGSS = igss; }

    public double getDESCUENTOS() { return DESCUENTOS; }
    public void setDESCUENTOS(double descuentos) { this.DESCUENTOS = descuentos; }

    public double getLIQUIDO() { return LIQUIDO; }
    public void setLIQUIDO(double liquido) { this.LIQUIDO = liquido; }

    public String getTIEMPO_TRABAJADO() {return TIEMPO_TRABAJADO;}
    public void setTIEMPO_TRABAJADO(String TIEMPO_TRABAJADO) { this.TIEMPO_TRABAJADO = TIEMPO_TRABAJADO;}

    public String getHORAS_EXTRAS() {return HORAS_EXTRAS;}
    public void setHORAS_EXTRAS(String HORAS_EXTRAS) {this.HORAS_EXTRAS = HORAS_EXTRAS;}

    public String getDESCUENTOS_DESC() {return DESCUENTOS_DESC;}
    public void setDESCUENTOS_DESC(String DESCUENTOS_DESC) {this.DESCUENTOS_DESC = DESCUENTOS_DESC;}
      
}

