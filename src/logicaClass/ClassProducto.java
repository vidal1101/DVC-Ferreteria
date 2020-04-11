
package logicaClass;

import java.io.Serializable;

/**
 *
 * @author Dixiana Maria,  Carlos Miguel y Vidal Canales
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
    private int categoria;
    private int provedor;
    private String unidadVenta;
    
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

    public boolean getProdfragil() {
        return prodfragil;
    }

    public void setProdfragil(boolean prodfragil) {
        this.prodfragil = prodfragil;
    }

      public double getDescuentProd() {
        return descuentProd;
    }

    public void setDescuentProd(double descuentProd) {
        this.descuentProd = descuentProd;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getProvedor() {
        return provedor;
    }

    public void setProvedor(int provedor) {
        this.provedor = provedor;
    }
    
    public String getUnidadVenta() {
        return unidadVenta;
    }

    public void setUnidadVenta(String unidadVenta) {
        this.unidadVenta = unidadVenta;
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
    public ClassProducto(int idProducto, String nombreProd, String descriProd, 
            int cantidadProd, double precioProd, boolean prodfragil, double descunetProd, 
            int categoria, int provedor, String unidadVenta) {
        this.idProducto = idProducto;
        this.nombreProd = nombreProd;
        this.descriProd = descriProd;
        this.cantidadProd = cantidadProd;
        this.precioProd = precioProd;
        this.prodfragil = prodfragil;
        this.descuentProd = descunetProd;
        this.categoria = categoria;
        this.provedor = provedor;
        this.unidadVenta=unidadVenta;
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
        this.categoria = 0;
        this.provedor = 0;
        this.unidadVenta="";
    }

    
}
