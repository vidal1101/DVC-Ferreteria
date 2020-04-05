
package logicaClass;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author  Dixiana Maria, Carlos Miguel y Vidal Canales
 */
public class ClassFacturasAnuladas implements Serializable{
    
    //Atributos 
    
    private int idFacturaAnulada;
    private Date fechaAnulacion;
    private String motivoAnulacion;
    private ClassFactura idfacturaOriginal;
    
    //Setter and Getter 

    public int getIdFacturaAnulada() {
        return idFacturaAnulada;
    }

    public void setIdFacturaAnulada(int idFacturaAnulada) {
        this.idFacturaAnulada = idFacturaAnulada;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public String getMotivoAnulacion() {
        return motivoAnulacion;
    }

    public void setMotivoAnulacion(String motivoAnulacion) {
        this.motivoAnulacion = motivoAnulacion;
    }

    public ClassFactura getIdfacturaOriginal() {
        return idfacturaOriginal;
    }

    public void setIdfacturaOriginal(ClassFactura idfacturaOriginal) {
        this.idfacturaOriginal = idfacturaOriginal;
    }
    
    //contructores 

    /**
     * contructor con todos los parametros 
     * @param idFacturaAnulada
     * @param fechaAnulacion
     * @param motivoAnulacion
     * @param idfacturaOriginal 
     */
    public ClassFacturasAnuladas(int idFacturaAnulada, Date fechaAnulacion, String motivoAnulacion, ClassFactura idfacturaOriginal) {
        this.idFacturaAnulada = idFacturaAnulada;
        this.fechaAnulacion = fechaAnulacion;
        this.motivoAnulacion = motivoAnulacion;
        this.idfacturaOriginal = idfacturaOriginal;
    }

    /***
     * contructor vacio ., inicaliazado 
     */
    public ClassFacturasAnuladas() {
        this.idFacturaAnulada = 0;
        this.fechaAnulacion = null;
        this.motivoAnulacion = "";
        this.idfacturaOriginal = idfacturaOriginal;
    }
    
    
    
    
}
