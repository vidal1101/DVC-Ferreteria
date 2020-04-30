package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logicaClass.ClassCliente;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
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
    public String eliminarCliente(int casoEvaluar, int id) {

        //ResultSet rs = null;
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
            ps.setInt(1, 4);
            rs = ps.executeQuery();
            return rs;

        } catch (SQLException e) {
            System.out.println("Error del mensaje: " + e.getMessage());
            return rs;
        }
    }

    /**
     * Permite buscar un cliente por su nombre o número de cédula
     *
     * @param dato
     * @return
     */
    public ResultSet BuscarCliente(String dato) {

        System.out.println("Intentando buscar cliente");
        Conexion con = new Conexion();
        ResultSet s = null;

        try {

            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{call pa_buscarCliente(?)}");
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
