package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logicaClass.ClassTrabajador;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class TrabajadorModelo {

    /**
     * Este permite hacer el login usuario
     *
     * @param usuario
     * @param contrasenia
     * @return true Si los datos son correctos
     */
    public ResultSet iniciarSesion(int usuario, String contrasenia) {

        Conexion con = new Conexion();
        ResultSet rs = null;

        try {

            System.out.println("Abrriendo conexión");
            con.conectar();

            CallableStatement cst = con.getCon().prepareCall("{CALL pa_sesionTrabajador(?,?)}");
            cst.setInt(1, usuario);
            cst.setString(2, contrasenia);

            rs = cst.executeQuery();
            rs.first();

            return rs;

        } catch (SQLException e) {

            System.out.println("Error al intentar enviar los datos: " + e.getMessage());
            return rs;
        }

    }

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
            CallableStatement cst = con.getCon().prepareCall("{CALL pa_insertarTrabajador(?,?,?,?,?,?,?,?)}");

            cst.setInt(1, trab.getCedulaTrab());
            cst.setString(2, trab.getNombreTrab());
            cst.setString(3, trab.getPuesto());
            cst.setString(4, trab.getTelefonoTrab());
            cst.setString(5, trab.getEmailTrab());
            cst.setBoolean(6, trab.getAbministrador());
            cst.setString(7, trab.getContrasenia());

            cst.registerOutParameter(8, java.sql.Types.BOOLEAN);
            System.out.println("Insertando datos");
            cst.executeUpdate();

            return cst.getBoolean(8);

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
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            //rs.first();
            return rs;

        } catch (SQLException e) {
            System.out.println("Error del mensaje: " + e.getMessage());
            return rs;
        }
    }

    /**
     * NOTA: Revisar el número de tablas para referirse a cada tabla de la BD <br>
     * ******** <br>
     * Se envia el numero de la caso a evaluar para ELIMINAR <br>
     * 1 = relacion entre Trabajador y Factura <br>
     * 2 = relacion entre Categoria y Producto <br>
     * 3 = relacion entre Proveedor y Producto <br>
     * 4 = relacion entre Cliente y Factura <br>
     * 5 = relacion entre Producto y DetallesCompra
     *
     * @param casoEvaluar 1 , 2 , 3, 4, 5
     * @param id id del caso a evaluar
     * @return verdadero si existe relacion , false si se elimino
     */
    public String eliminarTrabajador(int casoEvaluar, int id) {

        Conexion con = new Conexion();

        try {

            con.conectar();
            CallableStatement ps = con.getCon().prepareCall("{CALL pa_verificarEliminar(?,?,?,?)}");
            ps.setInt(1, casoEvaluar);
            ps.setInt(2, id);

            ps.registerOutParameter(3, java.sql.Types.VARCHAR);
            ps.registerOutParameter(4, java.sql.Types.BOOLEAN);
            System.out.println("comprobando eliminacion categoría");
            ps.executeUpdate();

            if (ps.getBoolean(4)) {
                System.out.println("Resultado: " + ps.getString(3));
                return ps.getString(3);
            } else {
                return "SIN EJECUTAR";
            }
            //return ps.getBoolean(3);
        } catch (SQLException e) {

            System.out.println("Error del mensaje: " + e.getMessage());
            return "Sin conexión";

        } finally {
            con.desconectar();
        }
    }

    /**
     * Permite buscar al trabajador por medio del nombre o cédula
     *
     * @param dato
     * @return
     */
    public ResultSet BuscarTrabajador(String dato) {

        Conexion con = new Conexion();
        ResultSet s = null;

        try {

            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{call pa_buscarTrabajador(?)}");
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
