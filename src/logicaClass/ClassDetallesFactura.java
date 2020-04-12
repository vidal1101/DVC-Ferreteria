
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
    private double descuanto;

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

    public double getDescuanto() {
        return descuanto;
    }

    public void setDescuanto(double descuanto) {
        this.descuanto = descuanto;
    }

    //contructores 
    
    /**
     * contrcutor con todos los parametros, el super y sus atrbutos
     * @param idProducto
     * @param nombreProd
     * @param descriProd
     * @param cantidadProd
     * @param precioProd
     * @param prodfragil
     * @param descunetProd
     * @param categoria
     * @param provedor 
     */
    public ClassDetallesFactura(int idProducto, String nombreProd, String descriProd, int cantidadProd, double precioProd, boolean prodfragil, double descunetProd, ClassCategoria categoria, ClassProveedor provedor) {
        super(idProducto, nombreProd, descriProd, cantidadProd, 
                precioProd, prodfragil, cantidadProd, cantidadProd,
                idProducto, nombreProd);
        this.idDetalleFact = idDetalleFact;
        this.cantidadProd = cantidadProd;
        this.subtotal = subtotal;
        this.factura = factura;
        this.descuanto = descuanto;
        

    }

    
    /**
     * contruc inicializado con el metod super del padre 
     * @param idDetalleFact
     * @param cantidadProd
     * @param subtotal
     * @param factura
     * @param descuanto 
     */
    public ClassDetallesFactura(int idDetalleFact, int cantidadProd, double subtotal, ClassFactura factura, double descuanto) {
        super();
        this.idDetalleFact = idDetalleFact;
        this.cantidadProd = cantidadProd;
        this.subtotal = subtotal;
        this.factura = factura;
        this.descuanto = descuanto;
    }

    
}
