package logicaClass;

import java.io.Serializable;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class ClassProducto implements Serializable {

    // Atributos
    private int idProducto;
    private String nombreProd;
    private String descripcion;
    private int cantidad;
    private float precioProd;
    private float subtotal;
    private boolean prodFragil;
    private int descuentProd;
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

    public float getPrecioProd() {
        return precioProd;
    }

    public void setPrecioProd(float precioProd) {
        this.precioProd = precioProd;
    }

    public boolean getProdFragil() {
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

    public int getDescuentProd() {
        return descuentProd;
    }

    public void setDescuentProd(int descuentProd) {
        this.descuentProd = descuentProd;
    }

    public String getUnidadVenta() {
        return unidadVenta;
    }

    public void setUnidadVenta(String unidadVenta) {
        this.unidadVenta = unidadVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
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
     * @param cantidad
     * @param prodFragil
     * @param descripcion
     */
    public ClassProducto(int idProducto, int idProveedor, int idCategoria, String nombreProd, float precioProd,
            int descuentProd, String unidadVenta, int cantidad, boolean prodFragil, String descripcion) {
        this.idProducto = idProducto;
        this.nombreProd = nombreProd;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
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
        this.cantidad = 0;
        this.precioProd = 0.0f;
        this.prodFragil = false;
        this.descuentProd = 0;
        this.idCategoria = 0;
        this.idProveedor = 0;
    }

}
