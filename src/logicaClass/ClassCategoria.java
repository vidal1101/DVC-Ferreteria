
package logicaClass;

import java.io.Serializable;

/**
 *
 * @author Dixiana Maria ,  Carlos Miguel y Vidal Canales
 */
public class ClassCategoria implements Serializable {

    //atributos 
    private int idCategoria;
    private String nombreCateg;
    private String descripcionCateg;

    //Setter and Getter
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCateg() {
        return nombreCateg;
    }

    public void setNombreCateg(String nombreCateg) {
        this.nombreCateg = nombreCateg;
    }

    public String getDescripcionCateg() {
        return descripcionCateg;
    }

    public void setDescripcionCateg(String descripcionCateg) {
        this.descripcionCateg = descripcionCateg;
    }

    //contructores
    
    /***¡
     * contruct con todos los atributos 
     * @param idCategoria
     * @param nombreCateg
     * @param descripcionCateg 
     */
    public ClassCategoria(int idCategoria, String nombreCateg, String descripcionCateg) {
        this.idCategoria = idCategoria;
        this.nombreCateg = nombreCateg;
        this.descripcionCateg = descripcionCateg;
    }

    /***
     * contruct inicializado
     */
    public ClassCategoria() {
        this.idCategoria = 0;
        this.nombreCateg = "";
        this.descripcionCateg = "";
    }

}
