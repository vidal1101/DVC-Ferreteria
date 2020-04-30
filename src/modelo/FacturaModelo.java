package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class FacturaModelo {

    /**
     * Muestra todas las facturas registradas
     *
     * @return
     */
    public ResultSet mostrarFacturas() {
        Conexion con = new Conexion();
        ResultSet rs = null;

        try {
            con.conectar();
            CallableStatement ps = con.getCon().prepareCall("{CALL pa_mostrarTablas(?)}");
            ps.setInt(1, 6);
            rs = ps.executeQuery();
            //rs.first();
            return rs;

        } catch (SQLException e) {
            System.out.println("Error del mensaje: " + e.getMessage());
            return rs;
        }
    }

    /**
     * Permite anular la factura indicada
     *
     * @param IdFactura
     * @return
     */
    public boolean anularFactura(int IdFactura) {
        Conexion con = new Conexion();

        try {
            con.conectar();
            CallableStatement ps = con.getCon().prepareCall("{CALL pa_modificarEstadoFactura(?,?)}");
            ps.setInt(1, IdFactura);
            ps.registerOutParameter(2, java.sql.Types.BOOLEAN);
            ps.executeUpdate();

            return ps.getBoolean(2);

        } catch (SQLException e) {
            System.out.println("Error del mensaje: " + e.getMessage());
            return false;
        }
    }

    /**
     * Muestra todos los detalles de la <br>
     * factura que la persona ha seleccionado
     *
     * @param idFactura
     * @return
     */
    public ResultSet mostrarDetallesxFacturas(int idFactura) {
        Conexion con = new Conexion();
        ResultSet rs = null;

        try {
            con.conectar();
            CallableStatement ps = con.getCon().prepareCall("{CALL pa_mostrarDetallesFactura(?)}");
            ps.setInt(1, idFactura);
            rs = ps.executeQuery();
            //rs.first();
            return rs;

        } catch (SQLException e) {
            System.out.println("Error del mensaje: " + e.getMessage());
            return rs;
        }
    }
}
