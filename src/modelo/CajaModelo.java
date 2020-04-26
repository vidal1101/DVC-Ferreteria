/**
 * FALTA COMPLETAR ESTA CLASE
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import logicaClass.ClassFactura;
import logicaClass.ClassProducto;

/**
 *
 * @author Dixiana, Carlos y Vidal
 */
public class CajaModelo {

    /**
     * metodo para modificar el stock
     *
     * @param id el id del producto seleccionado
     * @param operacion la 1 suma , 2 resta
     * @param valor lo que usuario toma del stock
     * @return la funcion hecha en la base de datos
     */
    public boolean modificarStock(int id, int operacion, int valor) {
        System.out.println("id " + id + " operacion " + operacion + " valor " + valor);
        Conexion con = new Conexion();

        try {
            System.out.println("Abrriendo conexión");
            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{CALL pa_modificarStock(?,?,?,?)}");

            cst.setInt(1, id);
            cst.setInt(2, operacion);
            cst.setInt(3, valor);

            cst.registerOutParameter(4, java.sql.Types.BOOLEAN);
            System.out.println("Insertando datos");
            cst.execute();

            return cst.getBoolean(4);

        } catch (SQLException e) {
            System.out.println("Error al intentar enviar los datos: " + e.getMessage());
            return false;
        } finally {
            con.desconectar();
        }
    }

    /**
     * Permite insertar una nueva factura, retorna en <br> su execute el id de la nueva factura para ingresar sus
     * detalles
     *
     * @param factura
     * @return
     */
    public boolean insertarFactura(ClassFactura factura) {

        Conexion con = new Conexion();

        try {
            System.out.println("Abrriendo conexión");
            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{CALL pa_insertarFactura(?,?,?,?,?,?,?,?)}");

            // Inserta los datos principales de la factura
            cst.setInt(1, factura.getCliente().getCedulaCli());
            cst.setInt(2, factura.getTrabajador().getCedulaTrab());
            cst.setString(3, factura.getDireccionEntrega());
            cst.setDate(4, factura.getFecha());
            cst.setFloat(5, factura.getTotalPagar());
            cst.setFloat(6, factura.getSubTotal());
            cst.setString(7, factura.getEstado());
            cst.registerOutParameter(8, java.sql.Types.BOOLEAN);

            System.out.println("Insertando datos");
            ResultSet rs = cst.executeQuery();
            rs.first();

            int idFac = rs.getInt(1);
            System.out.println("Nueva factura N: " + idFac);

            if (cst.getBoolean(8)) {

                // Inserta detalles, si ocurrre un error entonces elimina la factura recién creada
                if (!insertarDetalles(con, factura.getDetalleFactura(), idFac)) {

                    System.out.println("Error al intentar insertar los detalles de factura");
                    if (eliminarFactura(idFac, con)) {

                        System.out.println("Factura eliminada de la base de datos");

                    } else {
                        System.out.println("ATENCIÓN: Factura no eliminada, debería ser ANULADA");
                    }

                    return false;

                } else {
                    return cst.getBoolean(8);
                }
                
            } else {
                System.out.println("Error al intentar insertar la factura");
                return cst.getBoolean(8);
            }

        } catch (SQLException e) {

            System.out.println("Error al intentar registrar factura: " + e.getMessage());
            return false;

        } finally {
            con.desconectar();
        }
    }

    /**
     * Procedimiento que permite eliminar una factura. <br>
     * Antes de eliminar la factura se eliminan los detalles y se regresan al stock los productos <br>
     * que se intentaron comprar.
     *
     * @return
     */
    private boolean eliminarFactura(int idFactura, Conexion con) {

        // Vamos primero a eliminar los detalles de factura
        try {

            System.out.println("Abrriendo conexión");
            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{CALL pa_eliminarFactura(?,?)}");
            cst.setInt(1, idFactura);
            cst.registerOutParameter(2, java.sql.Types.BOOLEAN);

            cst.execute();
            return cst.getBoolean(1);

        } catch (SQLException e) {

            System.out.println("Error al intentar registrar factura: " + e.getMessage());
            return false;

        }
    }

    /**
     * Inserta cada detalle de factura
     *
     * @param con
     */
    private boolean insertarDetalles(Conexion con, ArrayList<ClassProducto> productos, int idFac) {

        try {

            System.out.println("Intentando enviar los detalles");
            boolean insertados = true;
            //con.conectar();

            // Llamamos al metodo que inserta el detalle
            for (int i = 0; i < productos.size(); i++) {

                CallableStatement inD = con.getCon().prepareCall("{CALL pa_insertarDetallesCompra(?,?,?,?,?,?)}");
                // El ID de detalle se crea automáticamente
                inD.setInt(1, idFac); // ID de Factura al que se relacionaran estos productos
                inD.setInt(2, productos.get(i).getIdProducto());
                inD.setInt(3, productos.get(i).getCantidad());
                inD.setInt(4, productos.get(i).getDescuentProd());
                inD.setFloat(5, productos.get(i).getSubtotal()); // El total calculado por la cantidad de este producto
                inD.registerOutParameter(6, java.sql.Types.BOOLEAN);

                inD.execute();

                if (!inD.getBoolean(6)) {
                    System.out.println("No se inserta un detalle de factura");
                    insertados = false;
                    break;
                }
            }

            return insertados;

        } catch (SQLException e) {
            System.out.println("Error al intentar insertar un detalle: " + e.getMessage());
            return false;
        }
    }

    /**
     * Llama al método que contiene el select para obtener los datos de la tabla
     *
     * @param idProd
     * @return
     */
    public ResultSet mostrarDatos(int idProd) {
        Conexion con = new Conexion();
        ResultSet rs = null;

        try {
            con.conectar();
            CallableStatement ps = con.getCon().prepareCall("{CALL pa_mostrarDatosProd(?)}");
            ps.setInt(1, idProd);

            rs = ps.executeQuery();
            rs.first();

            return rs;

        } catch (SQLException e) {
            System.out.println("Error del mensaje: " + e.getMessage());
            return rs;
        }
    }
}
