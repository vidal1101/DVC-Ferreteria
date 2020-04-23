/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 *
 * @author Dixiana, Carlos y Vidal
 */
public class CajaModeloVidal {

    
    
    /***
     * metodo para modificar el stock 
     * @param id el id del producto seleccionado 
     * @param operacion la 1 suma , 2 resta 
     * @param valor lo que usuario toma del stock
     * @return la funcion hecha en la base de datos 
     */
    public boolean modificarStock(int id, int operacion, int valor) {
        System.out.println("id "+id+" operacion "+operacion+" valor "+valor);
        Conexion con = new Conexion();
        try {
            System.out.println("Abrriendo conexión");
            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{CALL pa_modificarStock(?,?,?,?)}");

            cst.setInt(1, id);
            cst.setInt(2, operacion);
            cst.setInt(3, valor);

            cst.registerOutParameter(4, java.sql.Types.BOOLEAN);
            System.out.println("Insertando datos");
            cst.execute();

            return cst.getBoolean(4);

        } catch (SQLException e) {
            System.out.println("Error al intentar enviar los datos: " + e.getMessage());
            return false;
        } finally {
            con.desconectar();
        }

    }

}
