
package logicaClass;

import java.io.Serializable;

/**
 *
 * @author Dixiana Maria , Carlos Miguel y Vidal Canales
 */
public class ClassCliente implements Serializable {

    //Atributos 
    
    private int cedulaCli;
    private String nombreCli;
    private int telefonoCli;
    private String emailCli;
    private String direccionCli;

    //Setter and Getter
    public int getCedulaCli() {
        return cedulaCli;
    }

    public void setCedulaCli(int cedulaCli) {
        this.cedulaCli = cedulaCli;
    }

    public String getNombreCli() {
        return nombreCli;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
    }

    public int getTelefonoCli() {
        return telefonoCli;
    }

    public void setTelefonoCli(int telefonoCli) {
        this.telefonoCli = telefonoCli;
    }

    public String getEmailCli() {
        return emailCli;
    }

    public void setEmailCli(String emailCli) {
        this.emailCli = emailCli;
    }

    public String getDireccionCli() {
        return direccionCli;
    }

    public void setDireccionCli(String direccionCli) {
        this.direccionCli = direccionCli;
    }

    //Contructores
    /**
     * 
     * contruct con todso los parametros de la clase
     *
     * @param cedulaCli
     * @param nombreCli
     * @param telefonoCli
     * @param emailCli
     * @param direccionCli
     */
    public ClassCliente(int cedulaCli, String nombreCli, int telefonoCli, String emailCli, String direccionCli) {
        this.cedulaCli = cedulaCli;
        this.nombreCli = nombreCli;
        this.telefonoCli = telefonoCli;
        this.emailCli = emailCli;
        this.direccionCli = direccionCli;
    }

    
    /**
     * **
     * contructor inicializado con los atributos por valores predeterminados
     */
    public ClassCliente() {
        this.cedulaCli = 0;
        this.nombreCli = "";
        this.telefonoCli = 0;
        this.emailCli = "";
        this.direccionCli = "";
    }

}
