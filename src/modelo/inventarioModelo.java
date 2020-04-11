/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logicaClass.ClassProducto;

/**
 *
 * @author Dixi Maria, Carlos y Vidal
 */
public class inventarioModelo {

    public boolean insertarProducto(ClassProducto prod) {
        Conexion con = new Conexion();

        try {
            System.out.println("Abrriendo conexión");
            con.conectar();
            CallableStatement cst = con.getCon().prepareCall("{CALL pa_insertarProducto(?,?,?,?,?,?,?,?,?,?,?)}");

            cst.setInt(1, prod.getIdProducto());
            cst.setInt(2, prod.getProvedor());
            cst.setInt(3, prod.getCategoria());
            cst.setString(4, prod.getNombreProd());
            cst.setDouble(5, prod.getPrecioProd());
            cst.setInt(6, (int) prod.getDescuentProd());
            cst.setString(7, prod.getUnidadVenta());
            cst.setInt(8, prod.getCantidadProd());
            cst.setBoolean(9, prod.getProdfragil());
            cst.setString(10, prod.getDescriProd());

            cst.registerOutParameter(11, java.sql.Types.BOOLEAN);
            System.out.println("Insertando datos");
            cst.executeUpdate();

            return cst.getBoolean(11);

        } catch (SQLException e) {
            System.out.println("Error al intentar enviar los datos: " + e.getMessage());
            return false;
        } finally {
            con.desconectar();
        }

    }

    /***
     * metodo que devuelve el resultado de la consulta al mostrar tabla de productos 
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

            return rs;

        } catch (SQLException e) {
            System.out.println("Error del mensaje: " + e.getMessage());
            return rs;
        }
    }

}
