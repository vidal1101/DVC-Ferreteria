
package logicaClass;

import java.io.Serializable;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class ClassTrabajador implements Serializable{
    
    
    private int cedulaTrab;
    private String nombreTrab;
    private String emailTrab;
    private String puesto;
    private String telefonoTrab;
    private String contrasenia;
    private boolean abministrador;
    

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

    public String getTelefonoTrab() {
        return telefonoTrab;
    }

    public void setTelefonoTrab(String telefonoTrab) {
        this.telefonoTrab = telefonoTrab;
    }

    public boolean getAbministrador() {
        return abministrador;
    }

    public void setAbministrador(boolean abministrador) {
        this.abministrador = abministrador;
    }
    
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    public String getContrasenia() {
        return contrasenia;
    }
    
    /**
     * contruct con todos los atributos de la clase
     * @param cedulaTrab
     * @param nombreTrab
     * @param emailTrab
     * @param puesto
     * @param telefonoTrab
     * @param abministrador 
     */
    public ClassTrabajador(int cedulaTrab, String nombreTrab, String emailTrab, String puesto, String telefonoTrab, boolean abministrador) {
        this.cedulaTrab = cedulaTrab;
        this.nombreTrab = nombreTrab;
        this.emailTrab = emailTrab;
        this.puesto = puesto;
        this.telefonoTrab = telefonoTrab;
        this.abministrador = abministrador;
    }

    
    public ClassTrabajador() {
         this.cedulaTrab = 0;
        this.nombreTrab = "";
        this.emailTrab = "";
        this.puesto = "";
        this.telefonoTrab = "";
        this.abministrador = false;
    }
     
}
