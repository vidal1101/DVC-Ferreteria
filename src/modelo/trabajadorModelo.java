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
     * metodo que inserta un daton en la base de dato (tabla d trabajadores)
     *
     * @param trab objeto tipó trabajador
     * @return true si logra insertar con exito y false sino inserta
     */
    public boolean insertarTrabajador(ClassTrabajador trab) {
        Conexion con = new Conexion();

        try {
            con.conectar();
            CallableStatement cst
                    = (CallableStatement) con.getCon().prepareCall("{call pa_insertarTrabajador(?,?,?,?,?,?,?)}");
            System.out.println("inserccion ....");
            cst.setInt(1, trab.getCedulaTrab());
            cst.setString(2, trab.getNombreTrab());
            cst.setString(3, trab.getPuesto());
            cst.setString(4, trab.getTelefonoTrab());
            cst.setString(5, trab.getEmailTrab());
            cst.setBoolean(6, trab.getAbministrador());

            cst.registerOutParameter(7, java.sql.Types.BOOLEAN);
            cst.executeUpdate();

            return cst.getBoolean(7);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
//            con.desconectar();
        }

    }

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
            System.out.println("Error humano ");
            return rs;
        }
    }

}
