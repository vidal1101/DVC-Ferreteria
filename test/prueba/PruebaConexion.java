/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import modelo.Conexion;

/**
 *
 * @author User
 */
public class PruebaConexion {
    public static void main(String[] args) {
        Conexion con = new Conexion();
        con.conectar();
        
        
    }
}
