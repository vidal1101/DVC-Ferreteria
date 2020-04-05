
package logicaClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Dixiana Maria , Carlos Miguel y Vidal Canales
 */
public class ClassFactura implements Serializable {

    //Atributos 
    
    private ArrayList<ClassDetallesFactura> detalleFactura;
    private int idFactura;
    private Date fecha;
    private ClassCliente cliente;
    private ClassTrabajador trabajador;
    private double totalPagar;
    private String direccionEntrega;
    private String Estado;
   
    
    //Setter and Getter

    public ArrayList<ClassDetallesFactura> getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(ArrayList<ClassDetallesFactura> detalleFactura) {
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

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
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
    
    //contructores

    
    /***
     * contructor con todos los atributos
     * @param detalleFactura
     * @param idFactura
     * @param fecha
     * @param cliente
     * @param trabajador
     * @param totalPagar
     * @param direccionEntrega
     * @param Estado 
     */
    public ClassFactura(ArrayList<ClassDetallesFactura> detalleFactura, int idFactura, Date fecha, ClassCliente cliente, ClassTrabajador trabajador, double totalPagar, String direccionEntrega, String Estado) {
        this.detalleFactura = detalleFactura;
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.cliente = cliente;
        this.trabajador = trabajador;
        this.totalPagar = totalPagar;
        this.direccionEntrega = direccionEntrega;
        this.Estado = Estado;
    }

    
    /**
     * contructor vacio ., inicializado 
     */
    public ClassFactura() {
         this.detalleFactura = detalleFactura;
        this.idFactura = 0;
        this.fecha = null;
        this.cliente = cliente;
        this.trabajador = trabajador;
        this.totalPagar = 0.0;
        this.direccionEntrega = "";
        this.Estado = "";
    }
         
}
