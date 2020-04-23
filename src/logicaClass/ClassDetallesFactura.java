/**
 * Arreglar los atributos de esta clase por su herencia
 */
package logicaClass;

import java.io.Serializable;

/**
 *
 * @author Dixiana Maria, Carlos Miguel y Vidal Canales
 */
public class ClassDetallesFactura extends ClassProducto implements Serializable {

    //Atributos 
    private int idDetalleFact;
    private String NombProducto;
    private int cantidadProd;
    private double subtotal;
    private ClassFactura factura;
    private double descuento;
    private int idProd;

    //Setter and Getter  
    
    public String getNombProducto() {
        return NombProducto;
    }

    public void setNombProducto(String NombProducto) {
        this.NombProducto = NombProducto;
    }
    
    public int getIdDetalleFact() {
        return idDetalleFact;
    }

    public void setIdDetalleFact(int idDetalleFact) {
        this.idDetalleFact = idDetalleFact;
    }

    public int getCantidadProd() {
        return cantidadProd;
    }

    public void setCantidadProd(int cantidadProd) {
        this.cantidadProd = cantidadProd;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public ClassFactura getFactura() {
        return factura;
    }

    public void setFactura(ClassFactura factura) {
        this.factura = factura;
    }

    public double getDescuento() {
        return descuento;
    }

    /**
     * descuento en detalles de factura 
     * @param descuento 
     */
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
     public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }
    
    /**
     * contruc inicializado con el metod super del padre y todos los atributos 
     * @param idDetalleFact
     * @param cantidadProd
     * @param subtotal
     * @param factura
     * @param descuento 
     */
    public ClassDetallesFactura(int idDetalleFact, int cantidadProd, double subtotal, 
            ClassFactura factura, int descuento,String nomProducto , int idProduto) {
        super();
        this.idDetalleFact = idDetalleFact;
        this.cantidadProd = cantidadProd;
        this.subtotal = subtotal;
        this.factura = factura;
        this.descuento = descuento;
        this.NombProducto=nomProducto;
        this.idProd = idProduto;
    }

    /**
     * contructor inicializado
     */
    public ClassDetallesFactura() {
        this.idDetalleFact = 0;
        this.cantidadProd = 0;
        this.subtotal = 0.0;
        this.factura = null;
        this.descuento = 0;
        this.NombProducto="";
        this.idProd=0;
    }

    /**
     * contructor para el arrayslist de datelles factura
     * @param idDetalleFact
     * @param NombProducto
     * @param cantidadProd
     * @param subtotal
     * @param descuento
     * @param idProd 
     */
    public ClassDetallesFactura(int idDetalleFact, String NombProducto, int cantidadProd, 
            double subtotal, double descuento, int idProd) {
        this.idDetalleFact = idDetalleFact;
        this.NombProducto = NombProducto;
        this.cantidadProd = cantidadProd;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.idProd = idProd;
    }
 
}
