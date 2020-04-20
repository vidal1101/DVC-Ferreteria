
package logicaClass;

import java.io.Serializable;

/**
 *
 * @author Dixiana Maria, Carlos Miguel y Vidal Canales
 */
public class ClassDetallesFactura extends ClassProducto implements Serializable {

    //Atributos 
    private int idDetalleFact;
    private int cantidadProd;
    private double subtotal;
    private ClassFactura factura;
    private double descuento;

    //Setter and Getter  
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

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
    /**
     * contruc inicializado con el metod super del padre 
     * @param idDetalleFact
     * @param cantidadProd
     * @param subtotal
     * @param factura
     * @param descuento 
     */
    public ClassDetallesFactura(int idDetalleFact, int cantidadProd, double subtotal, ClassFactura factura, int descuento) {
        super();
        this.idDetalleFact = idDetalleFact;
        this.cantidadProd = cantidadProd;
        this.subtotal = subtotal;
        this.factura = factura;
        this.descuento = descuento;
    }
    
}
