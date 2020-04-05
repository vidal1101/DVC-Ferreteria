
package logicaClass;

import java.io.Serializable;

/**
 *
 * @author Dixiana Maria ,  Carlos Miguel y Vidal Canales
 */
public class ClassProducto implements Serializable {
    
    //Atributos 
    
    private int idProducto;
    private String nombreProd;
    private String descriProd;
    private int cantidadProd;
    private double precioProd;
    private boolean prodfragil;
    private double descuentProd;
    private ClassCategoria categoria;
    private ClassProveedor provedor;
    
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
        return descriProd;
    }

    public void setDescriProd(String descriProd) {
        this.descriProd = descriProd;
    }

    public int getCantidadProd() {
        return cantidadProd;
    }

    public void setCantidadProd(int cantidadProd) {
        this.cantidadProd = cantidadProd;
    }

    public double getPrecioProd() {
        return precioProd;
    }

    public void setPrecioProd(double precioProd) {
        this.precioProd = precioProd;
    }

    public boolean isProdfragil() {
        return prodfragil;
    }

    public void setProdfragil(boolean prodfragil) {
        this.prodfragil = prodfragil;
    }

    public double getDescunetProd() {
        return descuentProd;
    }

    public void setDescunetProd(double descunetProd) {
        this.descuentProd = descunetProd;
    }

    public ClassCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(ClassCategoria categoria) {
        this.categoria = categoria;
    }

    public ClassProveedor getProvedor() {
        return provedor;
    }

    public void setProvedor(ClassProveedor provedor) {
        this.provedor = provedor;
    }
    
    
    //contructores 

    
    /**
     * contructor con todos los atributos 
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
    public ClassProducto(int idProducto, String nombreProd, String descriProd, int cantidadProd, double precioProd, boolean prodfragil, double descunetProd, ClassCategoria categoria, ClassProveedor provedor) {
        this.idProducto = idProducto;
        this.nombreProd = nombreProd;
        this.descriProd = descriProd;
        this.cantidadProd = cantidadProd;
        this.precioProd = precioProd;
        this.prodfragil = prodfragil;
        this.descuentProd = descunetProd;
        this.categoria = categoria;
        this.provedor = provedor;
    }

    
    /***
     * contructor inicializado 
     */
    public ClassProducto() {
         this.idProducto = 0;
        this.nombreProd = "";
        this.descriProd = "";
        this.cantidadProd = 0;
        this.precioProd = 0.0;
        this.prodfragil = false;
        this.descuentProd = 0.0;
        this.categoria = categoria;
        this.provedor = provedor;
    }
    
}
