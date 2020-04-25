package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dixiana, Carlos y Vidal
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

}
