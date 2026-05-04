/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Planilla;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author it
 */
public class ClassNomina {
    
    @SerializedName("CODIGO")
    private int codigo;

    @SerializedName("NOMBRES")
    private String nombre;
    
    @SerializedName("ID_NOMINA")
    private int id_nomina;
    @SerializedName("FECHAINICIO")
    private String fechaincio;
    @SerializedName("FECHAFIN")
    private String fechafin;
    @SerializedName("MONTO")
    private String monto;

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_nomina() {
        return id_nomina;
    }

    public void setId_nomina(int id_nomina) {
        this.id_nomina = id_nomina;
    }

    public String getFechaincio() {
        return fechaincio;
    }

    public void setFechaincio(String fechaincio) {
        this.fechaincio = fechaincio;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }
    
}
