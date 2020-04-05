/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaClass;

import java.io.Serializable;

/**
 *
 * @author Dixiana Maria ,  Carlos Miguel y Vidal Canales 
 */
public class ClassCliente implements Serializable{
    
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
    
    
    
    
}
