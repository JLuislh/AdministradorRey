/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesElRey;

/**
 *
 * @author jluis
 */
public class InsertarProducto {
    
    private int id_pedido;
    private int id_producto;
    private int id_producto_pedidos;
    private int cantidad;
    private int id_nota;
    private int id_adicional;
    private Object[] id_extras;
    private int idregreso;
    private String fecha;
    private int idregresoPedido;
    private int tipo;
    private String sin;
    private double precio;
    private double Total;
    private int Codigo;
    private int NoOrden;
    private int Status;
    private double Efectivo;
    private double Tarjeta;

    public double getEfectivo() {
        return Efectivo;
    }

    public void setEfectivo(double Efectivo) {
        this.Efectivo = Efectivo;
    }

    public double getTarjeta() {
        return Tarjeta;
    }

    public void setTarjeta(double Tarjeta) {
        this.Tarjeta = Tarjeta;
    }

    
    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
     
    
    public int getNoOrden() {
        return NoOrden;
    }

    public void setNoOrden(int NoOrden) {
        this.NoOrden = NoOrden;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }
    

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }
    public int getId_producto_pedidos() {
        return id_producto_pedidos;
    }

    public void setId_producto_pedidos(int id_producto_pedidos) {
        this.id_producto_pedidos = id_producto_pedidos;
    }
    
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }
   
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    

    public int getIdregresoPedido() {
        return idregresoPedido;
    }
    public void setIdregresoPedido(int idregresoPedido) {
        this.idregresoPedido = idregresoPedido;
    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    

    public int getIdregreso() {
        return idregreso;
    }

    public void setIdregreso(int idregreso) {
        this.idregreso = idregreso;
    }
    
    

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_nota() {
        return id_nota;
    }

    public void setId_nota(int id_nota) {
        this.id_nota = id_nota;
    }

    public int getId_adicional() {
        return id_adicional;
    }

    public void setId_adicional(int id_adicional) {
        this.id_adicional = id_adicional;
    }

    public Object[] getId_extras() {
        return id_extras;
    }

    public void setId_extras(Object[] id_extras) {
        this.id_extras = id_extras;
    }  
    
    
    
    private String descripcion;
    private String tipodeproducto;
    private int cantidad1;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipodeproducto() {
        return tipodeproducto;
    }

    public void setTipodeproducto(String tipodeproducto) {
        this.tipodeproducto = tipodeproducto;
    }

    public int getCantidad1() {
        return cantidad1;
    }

    public void setCantidad1(int cantidad1) {
        this.cantidad1 = cantidad1;
    }
    
    
    
 }   