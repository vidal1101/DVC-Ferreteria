package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logicaClass.ClassProveedor;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class ProveedorModelo {

    /**
     * metodo de inserccion de proveedores con un objeto tipo proveedor
     *
     * @param prov object tipo proveedor
     * @return si se inserto es true ., sino false
     */
    public boolean insertarProveedor(ClassProveedor prov) {
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
    public String eliminarProveedor(int casoEvaluar, int id) {

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
