package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logicaClass.ClassCategoria;

/**
 *
 * @author Dixiana Gómez, Rodrigo Vidal, Carlos Mairena
 */
public class CategoriaModelo {

    /**
     * Obtiene los datos de las categorías desde la base de datos
     *
     * @return
     */
    public ResultSet mostrarCategorias() {
        Conexion con = new Conexion();
        ResultSet rs = null;

        try {
            con.conectar();
            CallableStatement ps = con.getCon().prepareCall("{CALL pa_mostrarTablas(?)}");
            ps.setInt(1, 2);
            rs = ps.executeQuery();
            //rs.first();
            return rs;

        } catch (SQLException e) {
            System.out.println("Error del mensaje: " + e.getMessage());
            return rs;
        }
    }

    /**
     * Se llama para insertar una categoría
     *
     * @param categoria
     * @return
     */
    public boolean insertarCategoria(ClassCategoria categoria) {
        Conexion con = new Conexion();

        try {
            System.out.println("Abrriendo conexión");
            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{CALL pa_insertarCategoria(?,?,?)}");

            //cst.setInt(1, categoria.getIdCategoria());
            cst.setString(1, categoria.getNombreCateg());
            cst.setString(2, categoria.getDescripcionCateg());

            cst.registerOutParameter(3, java.sql.Types.BOOLEAN);
            System.out.println("Insertando datos");
            cst.executeUpdate();

            return cst.getBoolean(3);

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
     * @param categoria
     * @return
     */
    public boolean modificarCategoria(ClassCategoria categoria) {
        Conexion con = new Conexion();

        try {
            System.out.println("Abrriendo conexión");
            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{CALL pa_editarCategoria(?,?,?,?)}");

            cst.setInt(1, categoria.getIdCategoria());
            cst.setString(2, categoria.getNombreCateg());
            cst.setString(3, categoria.getDescripcionCateg());

            cst.registerOutParameter(4, java.sql.Types.BOOLEAN);
            System.out.println("Modificando datos");

            cst.executeUpdate();
            return cst.getBoolean(4);

        } catch (SQLException e) {
            System.out.println("Error al intentar enviar los datos: " + e.getMessage());
            return false;
        } finally {
            con.desconectar();
        }
    }

    /**
     * Se le envía la cédula del trabajador a eliminar
     *
     * @param id
     * @return
     */
    public boolean eliminarCategoria(int id) {
        Conexion con = new Conexion();

        try {
            
            con.conectar();
            CallableStatement ps = con.getCon().prepareCall("{CALL pa_eliminarCategoria(?,?)}");
            ps.setInt(1, id);
            ps.registerOutParameter(2, java.sql.Types.BOOLEAN);
            System.out.println("Eliminando categoría");
            ps.executeUpdate();

            return ps.getBoolean(2);

        } catch (SQLException e) {
            System.out.println("Error del mensaje: " + e.getMessage());
            return false;
        } finally {
            con.desconectar();
        }
    }

    /**
     * Mostramos los productos de la categoría
     *
     * @return
     */
    public ResultSet mostrarProduCat(int IDTabla) {
        Conexion con = new Conexion();
        ResultSet rs = null;

        try {
            con.conectar();
            CallableStatement ps = con.getCon().prepareCall("{CALL pa_mostrarProdCategorias(?)}");
            ps.setInt(1, IDTabla);
            rs = ps.executeQuery();
            rs.first();
            return rs;

        } catch (SQLException e) {
            System.out.println("Error del mensaje: " + e.getMessage());
            return rs;
        }
    }

}
