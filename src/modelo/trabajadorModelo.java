package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logicaClass.ClassTrabajador;

/**
 *
 * @author Maria, Carlos y Vidal
 */
public class trabajadorModelo {

    /**
     * *
     * metodo que inserta un dato en la base de dato (tabla d trabajadores)
     *
     * @param trab objeto tipó trabajador
     * @return true si logra insertar con exito y false sino inserta
     */
    public boolean insertarTrabajador(ClassTrabajador trab) {
        Conexion con = new Conexion();

        try {
            System.out.println("Abrriendo conexión");
            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{CALL pa_insertarTrabajador(?,?,?,?,?,?,?)}");

            cst.setInt(1, trab.getCedulaTrab());
            cst.setString(2, trab.getNombreTrab());
            cst.setString(3, trab.getPuesto());
            cst.setString(4, trab.getTelefonoTrab());
            cst.setString(5, trab.getEmailTrab());
            cst.setBoolean(6, trab.getAbministrador());

            cst.registerOutParameter(7, java.sql.Types.BOOLEAN);
            System.out.println("Insertando datos");
            cst.executeUpdate();

            return cst.getBoolean(7);

        } catch (SQLException e) {
            System.out.println("Error al intentar enviar los datos: " + e.getMessage());
            return false;
        } finally {
            con.desconectar();
        }

    }

    /***
     * metodo para modificar un trabajador 
     * @param trab
     * @return 
     */
     public boolean modificarTrabajador(ClassTrabajador trab) {
        Conexion con = new Conexion();

        try {
            System.out.println("Abrriendo conexión");
            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{CALL pa_editarTrabajador(?,?,?,?,?,?,?)}");

            cst.setInt(1, trab.getCedulaTrab());
            cst.setString(2, trab.getNombreTrab());
            cst.setString(3, trab.getPuesto());
            cst.setString(4, trab.getTelefonoTrab());
            cst.setString(5, trab.getEmailTrab());
            cst.setBoolean(6, trab.getAbministrador());

            cst.registerOutParameter(7, java.sql.Types.BOOLEAN);
            System.out.println("Modificando datos");
            
            cst.execute();

            return cst.getBoolean(7);

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
    public ResultSet mostrarTrabajadores() {
        Conexion con = new Conexion();
        ResultSet rs = null;

        try {
            con.conectar();
            CallableStatement ps = con.getCon().prepareCall("{CALL pa_mostrarTablas(?)}");
            ps.setInt(1,1);
            rs = ps.executeQuery();
            //rs.first();
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
    public boolean eliminarTrabajadores(int cedula) {
        Conexion con = new Conexion();

        try {
            con.conectar();
            CallableStatement ps = con.getCon().prepareCall("{CALL pa_eliminarTrabajador(?,?)}");
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

}
