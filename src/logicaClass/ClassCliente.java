
package logicaClass;

import java.io.Serializable;

/**
 *
 * @author Dixiana Maria, Carlos Miguel y Vidal Canales
 */
public class ClassCliente implements Serializable {

    //Atributos 
    
    private int cedulaCli;
    private String nombreCli;
    private String telefonoCli;
    private String emailCli;

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

    public String getTelefonoCli() {
        return telefonoCli;
    }

    public void setTelefonoCli(String telefonoCli) {
        this.telefonoCli = telefonoCli;
    }

    public String getEmailCli() {
        return emailCli;
    }

    public void setEmailCli(String emailCli) {
        this.emailCli = emailCli;
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
     */
    public ClassCliente(int cedulaCli, String nombreCli, String telefonoCli, String emailCli) {
        this.cedulaCli = cedulaCli;
        this.nombreCli = nombreCli;
        this.telefonoCli = telefonoCli;
        this.emailCli = emailCli;
    }

    
    /**
     * **
     * contructor inicializado con los atributos por valores predeterminados
     */
    public ClassCliente() {
        this.cedulaCli = 0;
        this.nombreCli = "";
        this.telefonoCli = "";
        this.emailCli = "";
    }

}
