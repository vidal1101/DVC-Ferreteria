
package logicaClass;

import java.io.Serializable;

/**
 *
 * @author Dixiana Maria, Carlos Miguel y Vidal Canales
 */
public class ClassProducto implements Serializable {

    // Atributos
    private int idProducto;
    private String nombreProd;
    private String descripcion;
    private int cantidadStock;
    private double precioProd;
    private boolean prodFragil;
    private double descuentProd;
    private String unidadVenta;
    private int idCategoria;
    private int idProveedor;

    //Setter and Getter 
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public String getDescriProd() {
        return descripcion;
    }

    public void setDescriProd(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioProd() {
        return precioProd;
    }

    public void setPrecioProd(double precioProd) {
        this.precioProd = precioProd;
    }

    public boolean isProdFragil() {
        return prodFragil;
    }

    public void setProdFragil(boolean prodFragil) {
        this.prodFragil = prodFragil;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public double getDescuentProd() {
        return descuentProd;
    }

    public void setDescuentProd(double descuentProd) {
        this.descuentProd = descuentProd;
    }

    public String getUnidadVenta() {
        return unidadVenta;
    }

    public void setUnidadVenta(String unidadVenta) {
        this.unidadVenta = unidadVenta;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    /**
     * contructor con todos los atributos
     * 
     * @param idProducto
     * @param idProveedor
     * @param idCategoria
     * @param nombreProd
     * @param precioProd
     * @param descuentProd
     * @param unidadVenta
     * @param cantidadStock
     * @param prodFragil
     * @param descripcion
     */
    public ClassProducto(int idProducto, int idProveedor, int idCategoria, String nombreProd, double precioProd,
            int descuentProd, String unidadVenta, int cantidadStock, boolean prodFragil, String descripcion) {
        this.idProducto = idProducto;
        this.nombreProd = nombreProd;
        this.descripcion = descripcion;
        this.cantidadStock = cantidadStock;
        this.precioProd = precioProd;
        this.prodFragil = prodFragil;
        this.descuentProd = descuentProd;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
        this.unidadVenta = unidadVenta;
    }

    /**
     * *
     * contructor inicializado
     */
    public ClassProducto() {
        this.idProducto = 0;
        this.nombreProd = "sin nombre";
        this.descripcion = "sin descripción";
        this.cantidadStock = 0;
        this.precioProd = 0.0;
        this.prodFragil = false;
        this.descuentProd = 0.0;
        this.idCategoria = 0;
        this.idProveedor = 0;
    }

}
