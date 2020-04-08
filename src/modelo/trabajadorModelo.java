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
            CallableStatement cst = con.getCon().prepareCall("{call pa_insertarTrabajador(?,?,?,?,?,?,?)}");

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
            System.out.println("Conexión cerrada");
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
            CallableStatement ps = con.getCon().prepareCall("{CALL pa_mostrarTrabajador()}");
            rs = ps.executeQuery();
            rs.first();
            return rs;

        } catch (SQLException e) {
            System.out.println("Error del mensaje: " + e.getMessage());
            return rs;
        }
    }

}
