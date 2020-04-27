package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logicaClass.ClassCategoria;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
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
    public String eliminarCategoria(int casoEvaluar, int id) {

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
     * Mostramos los productos de la categoría indicada por medio del parámetro
     *
     * @param IDTabla
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
            //rs.first();
            return rs;
            
        } catch (SQLException e) {
            System.out.println("Error del mensaje: " + e.getMessage());
            return rs;
        }
    }

    /**
     * Permite buscar las categorías por medio del nombre y del ID.
     *
     * @param dato
     * @return
     */
    public ResultSet BuscarCategorias(String dato) {
        
        Conexion con = new Conexion();
        ResultSet s = null;
        
        try {
            
            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{call pa_buscarCategorias(?)}");
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
