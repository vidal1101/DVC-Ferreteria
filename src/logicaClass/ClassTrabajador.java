
package logicaClass;

import java.io.Serializable;

/**
 *
 * @author Dixiana Maria, Carlos Miguel y Vidal Canales
 */
public class ClassTrabajador implements Serializable{
    
    //atributos 
    
    private int cedulaTrab;
    private String nombreTrab;
    private String emailTrab;
    private String puesto;
    private int telefonoTrab;
    private boolean abministrador;
    
    //Setter and Getter

    public int getCedulaTrab() {
        return cedulaTrab;
    }

    public void setCedulaTrab(int cedulaTrab) {
        this.cedulaTrab = cedulaTrab;
    }

    public String getNombreTrab() {
        return nombreTrab;
    }

    public void setNombreTrab(String nombreTrab) {
        this.nombreTrab = nombreTrab;
    }

    public String getEmailTrab() {
        return emailTrab;
    }

    public void setEmailTrab(String emailTrab) {
        this.emailTrab = emailTrab;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getTelefonoTrab() {
        return telefonoTrab;
    }

    public void setTelefonoTrab(int telefonoTrab) {
        this.telefonoTrab = telefonoTrab;
    }

    public boolean getAbministrador() {
        return abministrador;
    }

    public void setAbministrador(boolean abministrador) {
        this.abministrador = abministrador;
    }
    
    //Contructores

    /**
     * contruct con todos los atributos de la clase
     * @param cedulaTrab
     * @param nombreTrab
     * @param emailTrab
     * @param puesto
     * @param telefonoTrab
     * @param abministrador 
     */
    public ClassTrabajador(int cedulaTrab, String nombreTrab, String emailTrab, String puesto, int telefonoTrab, boolean abministrador) {
        this.cedulaTrab = cedulaTrab;
        this.nombreTrab = nombreTrab;
        this.emailTrab = emailTrab;
        this.puesto = puesto;
        this.telefonoTrab = telefonoTrab;
        this.abministrador = abministrador;
    }

    /***
     * contruct inicializado
     */
    public ClassTrabajador() {
         this.cedulaTrab = 0;
        this.nombreTrab = "";
        this.emailTrab = "";
        this.puesto = "";
        this.telefonoTrab = 0;
        this.abministrador = false;
    }
     
}
