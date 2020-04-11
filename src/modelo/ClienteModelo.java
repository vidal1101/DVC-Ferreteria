/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logicaClass.ClassCliente;

/**
 *
 * @author Dixiana, Carlos, Vidal
 */
public class ClienteModelo {

    /**
     * *
     * metodo que inserta un dato en la base de dato (tabla de Cliente)
     *
     * @param cli objeto tipó Cliente
     * @return true si logra insertar con exito y false sino inserta
     */
    public boolean insertarCliente(ClassCliente cli) {
        Conexion con = new Conexion();

        try {
            System.out.println("Abrriendo conexión");
            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{CALL pa_insertarCliente(?,?,?,?,?)}");

            cst.setInt(1, cli.getCedulaCli());
            cst.setString(2, cli.getNombreCli());
            cst.setString(3, cli.getTelefonoCli());
            cst.setString(4, cli.getEmailCli());

            cst.registerOutParameter(5, java.sql.Types.BOOLEAN);
            
            System.out.println("Insertando datos");
            cst.execute();

            return cst.getBoolean(5);

        } catch (SQLException e) {
            System.out.println("Error al intentar enviar los datos: " + e.getMessage());
            return false;
        } finally {
            con.desconectar();
        }

    }

    /**
     * *
     * metodo para modificar un trabajador
     *
     * @param cli
     * @return
     */
    public boolean modificarCliente(ClassCliente cli) {
        Conexion con = new Conexion();

        try {
            System.out.println("Abrriendo conexión");
            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{CALL pa_editarCliente(?,?,?,?,?)}");

            cst.setInt(1, cli.getCedulaCli());
            cst.setString(2, cli.getNombreCli());
            cst.setString(3, cli.getTelefonoCli());
            cst.setString(4, cli.getEmailCli());

            cst.registerOutParameter(5, java.sql.Types.BOOLEAN);

            System.out.println("Modificando datos");

            cst.execute();

            return cst.getBoolean(5);

        } catch (SQLException e) {
            System.out.println("Error al intentar enviar los datos: " + e.getMessage());
            return false;
        } finally {
            con.desconectar();
        }

    }

    /**
     * Se le envía la cédula del Cliente a eliminar
     *
     * @param cedula
     * @return
     */
    public boolean eliminarCliente(int cedula) {
        Conexion con = new Conexion();

        try {
            con.conectar();
            CallableStatement ps = con.getCon().prepareCall("{CALL pa_eliminarCliente(?,?)}");
            ps.setInt(1, cedula);
            ps.registerOutParameter(2, java.sql.Types.BOOLEAN);
            ps.executeUpdate();

            System.out.println("Usuario eliminado");
            return ps.getBoolean(2);

        } catch (SQLException e) {
            System.out.println("Error del mensaje: " + e.getMessage());
            return false;
        } finally {
            con.desconectar();
        }
    }

    /**
     * Llama al método que contiene el select para obtener los datos de la tabla
     *
     * @return
     */
    public ResultSet mostrarClientes() {
        Conexion con = new Conexion();
        ResultSet rs = null;

        try {
            con.conectar();
            CallableStatement ps = con.getCon().prepareCall("{CALL pa_mostrarTablas(?)}");
            ps.setInt(1,4);
            rs = ps.executeQuery();
            return rs;

        } catch (SQLException e) {
            System.out.println("Error del mensaje: " + e.getMessage());
            return rs;
        }
    }

}
