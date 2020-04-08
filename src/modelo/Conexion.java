package modelo;

import com.mysql.cj.protocol.Resultset;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Maria, Carlos y Vidal
 */
public class Conexion {

    private final String base = "Ferreteria-DVC";
    private final String user = "root";
    private final String password = "Lbrtr2m20!";
    private final String url = "jdbc:mysql://localhost/" + base + "?useSSL=false&serverTimezone=UTC";

    private Connection con;
    private Statement sql;
    private Resultset rs;

    public Connection getCon() {
        return con;
    }

    public Resultset getRs() {
        return rs;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    private boolean cargarControlador() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return true;
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error...No se cargó el controlador de Bases de Datos " + ex.getMessage());
            return false;
        }
    }

    public boolean conectar() {
        this.cargarControlador();
        try {
            this.con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
            this.sql = this.con.createStatement();
            System.out.println("conexion exitosa");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error...No hay conexión con la Base de Datos " + ex.getCause());
            return false;
        }
    }

    public void desconectar() {
        try {

            this.sql.close();
            this.con.close();
        } catch (Exception e) {
        }
    }

}
