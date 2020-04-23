/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logicaClass.ClassProveedor;

/**
 *
 * @author User
 */
public class proveedorModelo {

    /**
     * metodo de inserccion de proveedores con un objeto tipo proveedor
     *
     * @param prov object tipo proveedor
     * @return si se inserto es true ., sino false
     */
    public boolean insertarTrabajador(ClassProveedor prov) {
        Conexion con = new Conexion();

        try {
            System.out.println("Abrriendo conexión");
            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{CALL pa_insertarProveedor(?,?,?,?,?,?)}");

            cst.setInt(1, prov.getIdProvedor());
            cst.setString(2, prov.getNombreProv());
            cst.setString(3, prov.getTelefonoProv());
            cst.setString(4, prov.getDireccionProv());
            cst.setString(5, prov.getEmailProv());

            cst.registerOutParameter(6, java.sql.Types.BOOLEAN);
            System.out.println("Insertando datos");
            cst.executeUpdate();

            return cst.getBoolean(6);

        } catch (SQLException e) {
            System.out.println("Error al intentar enviar los datos: " + e.getMessage());
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
    public ResultSet mostrarProveedores() {
        Conexion con = new Conexion();
        ResultSet rs = null;

        try {
            con.conectar();
            CallableStatement ps = con.getCon().prepareCall("{CALL pa_mostrarTablas(?)}");
            ps.setInt(1, 3);
            rs = ps.executeQuery();
            
            return rs;

        } catch (SQLException e) {
            System.out.println("Error del mensaje: " + e.getMessage());
            return rs;
        }
    }

    /**
     * Se le envía la cédula del trabajador a eliminar
     *
     * @param cedula
     * @return
     */
    public boolean eliminarProveedores(int idpro) {
        Conexion con = new Conexion();

        try {
            con.conectar();
            CallableStatement ps = con.getCon().prepareCall("{CALL pa_eliminarProveedor(?,?)}");
            ps.setInt(1, idpro);
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
     * *
     * metodo para modificar un Proveedor
     *
     * @param trab
     * @return
     */
    public boolean modificarProveedor(ClassProveedor prov) {
        Conexion con = new Conexion();

        try {
            System.out.println("Abrriendo conexión");
            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{CALL pa_editarProveedor(?,?,?,?,?,?)}");

            cst.setInt(1, prov.getIdProvedor());
            cst.setString(2, prov.getNombreProv());
            cst.setString(3, prov.getTelefonoProv());
            cst.setString(4, prov.getDireccionProv());
            cst.setString(5, prov.getEmailProv());

            cst.registerOutParameter(6, java.sql.Types.BOOLEAN);
            System.out.println("Modificando datos");

            cst.execute();

            return cst.getBoolean(6);

        } catch (SQLException e) {
            System.out.println("Error al intentar enviar los datos: " + e.getMessage());
            return false;
        } finally {
            con.desconectar();
        }

    }
    
    public ResultSet BuscarProveedor(String dato) {

        System.out.println("Intentando buscar proveedor");
        Conexion con = new Conexion();
        ResultSet s = null;

        try {

            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{call pa_buscarProveedor(?)}");
            cst.setString(1, dato);
            s = cst.executeQuery();

            return s;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error al intentar llamar procedimiento");
            return s;
        }
    }
    
}
