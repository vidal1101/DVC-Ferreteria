
package logicaClass;

import java.io.Serializable;

/**
 *
 * @author Dixiana Maria, Carlos Miguel y Vidal Canales
 */
public class ClassProveedor implements Serializable {

    //Atributos 
    
    private int idProvedor;
    private String nombreProv;
    private int telefonoProv;
    private String emailProv;
    private String direccionProv;

    
    //setter and Getter
    
    public int getIdProvedor() {
        return idProvedor;
    }

    public void setIdProvedor(int idProvedor) {
        this.idProvedor = idProvedor;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    public int getTelefonoProv() {
        return telefonoProv;
    }

    public void setTelefonoProv(int telefonoProv) {
        this.telefonoProv = telefonoProv;
    }

    public String getEmailProv() {
        return emailProv;
    }

    public void setEmailProv(String emailProv) {
        this.emailProv = emailProv;
    }

    public String getDireccionProv() {
        return direccionProv;
    }

    public void setDireccionProv(String direccionProv) {
        this.direccionProv = direccionProv;
    }

    //Contructores
    
    /**
     * *
     * contructor con todso los atributos de la clase
     *
     * @param idProvedor
     * @param nombreProv
     * @param telefonoProv
     * @param emailProv
     * @param direccionProv
     */
    public ClassProveedor(int idProvedor, String nombreProv, int telefonoProv, String emailProv, String direccionProv) {
        this.idProvedor = idProvedor;
        this.nombreProv = nombreProv;
        this.telefonoProv = telefonoProv;
        this.emailProv = emailProv;
        this.direccionProv = direccionProv;
    }

    /***
     * contructor inicializado 
     */
    public ClassProveedor() {
        this.idProvedor = 0;
        this.nombreProv = "";
        this.telefonoProv = 0;
        this.emailProv = "";
        this.direccionProv = "";
    }

}
