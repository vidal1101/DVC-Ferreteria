package logicaClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class ClassFactura implements Serializable {

    //Atributos 
    private ArrayList<ClassProducto> detalleFactura;
    private int idFactura;
    private Date fecha;
    private ClassCliente cliente;
    private ClassTrabajador trabajador;
    private float totalPagar;
    private float subTotal;
    private String direccionEntrega;
    private String Estado;

    public ArrayList<ClassProducto> getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(ArrayList<ClassProducto> detalleFactura) {
        this.detalleFactura = detalleFactura;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ClassCliente getCliente() {
        return cliente;
    }

    public void setCliente(ClassCliente cliente) {
        this.cliente = cliente;
    }

    public ClassTrabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(ClassTrabajador trabajador) {
        this.trabajador = trabajador;
    }

    public float getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(float totalPagar) {
        this.totalPagar = totalPagar;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public ClassFactura(ArrayList<ClassProducto> detalleFactura, Date fecha, ClassCliente cliente,
            ClassTrabajador trabajador, float totalPagar, float subTotal, String direccionEntrega) {
        this.detalleFactura = detalleFactura;
        this.fecha = fecha;
        this.cliente = cliente;
        this.trabajador = trabajador;
        this.totalPagar = totalPagar;
        this.subTotal = subTotal;
        this.direccionEntrega = direccionEntrega;
        this.Estado = "Facturada";
    }
    
    

    /**
     * *
     * contructor con todos los atributos
     *
     * @param detalleFactura
     * @param fecha
     * @param cliente
     * @param trabajador
     * @param totalPagar
     * @param direccionEntrega
     */
    public ClassFactura(ArrayList<ClassProducto> detalleFactura, Date fecha, ClassCliente cliente,
            ClassTrabajador trabajador, float totalPagar, String direccionEntrega) {

        this.detalleFactura = detalleFactura;
        this.idFactura = 0;
        this.fecha = fecha;
        this.cliente = cliente;
        this.trabajador = trabajador;
        this.totalPagar = totalPagar;
        this.direccionEntrega = direccionEntrega;
        this.Estado = "Facturada";
    }

    public ClassFactura() {

        this.detalleFactura = null;
        this.idFactura = 0;
        this.fecha = new java.sql.Date(new java.util.Date().getTime());
        this.cliente = new ClassCliente();
        this.trabajador = new ClassTrabajador();
        this.totalPagar = 0.0f;
        this.direccionEntrega = "Sin entrega express";
        this.Estado = "Facturada";
    }

}
