package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logicaClass.ClassProducto;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class InventarioModelo {

    
    /**
     * Permite buscar un producto por medio de nombre, id y precio del producto
     *
     * @param dato
     * @return
     */
    public ResultSet BuscarProducto(String dato) {

        Conexion con = new Conexion();
        ResultSet s = null;

        try {

            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{call pa_buscarProducto(?)}");
            cst.setString(1, dato);
            s = cst.executeQuery();

            return s;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error al intentar llamar procedimiento");
            return s;
        }
    }
    
    /**
     * Inserta un nuevo producto a la base de datos
     *
     * @param prod
     * @return
     */
    public boolean insertarProducto(ClassProducto prod) {
        Conexion con = new Conexion();

        try {
            System.out.println("Abrriendo conexión");
            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{CALL pa_insertarProducto(?,?,?,?,?,?,?,?,?,?)}");

            cst.setInt(1, prod.getIdProveedor());
            cst.setInt(2, prod.getIdCategoria());
            cst.setString(3, prod.getNombreProd());
            cst.setDouble(4, prod.getPrecioProd());
            cst.setInt(5, prod.getDescuentProd());
            cst.setString(6, prod.getUnidadVenta());
            cst.setInt(7, prod.getCantidad());
            cst.setBoolean(8, prod.getProdFragil());
            cst.setString(9, prod.getDescriProd());

            cst.registerOutParameter(10, java.sql.Types.BOOLEAN);
            System.out.println("Insertando datos");
            cst.executeUpdate();

            return cst.getBoolean(10);

        } catch (SQLException e) {
            System.out.println("Error al intentar enviar los datos: " + e.getMessage());
            return false;
        } finally {
            con.desconectar();
        }
    }

    /**
     *
     * metodo que devuelve el resultado de la consulta al mostrar tabla de productos
     *
     * @return
     */
    public ResultSet mostrarProductos() {
        Conexion con = new Conexion();
        ResultSet rs = null;

        try {
            con.conectar();
            CallableStatement ps = con.getCon().prepareCall("{CALL pa_mostrarTablas(?)}");
            ps.setInt(1, 5);
            rs = ps.executeQuery();

            //rs.first();
            return rs;

        } catch (SQLException e) {
            System.out.println("Error del mensaje: " + e.getMessage());
            return rs;
        }
    }

    /**
     *
     * Modificar un Proveedor
     *
     * @param prod
     * @return
     */
    public boolean modificarProducto(ClassProducto prod) {
        Conexion con = new Conexion();

        try {
            System.out.println("Abrriendo conexión");
            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{CALL pa_editarProducto(?,?,?,?,?,?,?,?,?,?,?)}");

            System.out.println("ID recibido: " + prod.getIdProducto());

            cst.setInt(1, prod.getIdProducto());
            cst.setInt(2, prod.getIdProveedor());
            cst.setInt(3, prod.getIdCategoria());
            cst.setString(4, prod.getNombreProd());
            cst.setDouble(5, prod.getPrecioProd());
            cst.setInt(6, prod.getDescuentProd());
            cst.setString(7, prod.getUnidadVenta());
            cst.setInt(8, prod.getCantidad());
            cst.setBoolean(9, prod.getProdFragil());
            cst.setString(10, prod.getDescriProd());
            cst.registerOutParameter(11, java.sql.Types.BOOLEAN);

            System.out.println("Modificando datos \n");
            cst.executeUpdate();

            return cst.getBoolean(11);

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
    public String eliminarProducto(int casoEvaluar, int id) {

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

}
