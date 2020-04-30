
/*
-- 					PROYECTO PROGRAMADO # 2
-- Profesor: Jonathan Moreno Núñez
-- Integrantes: Dixiana Gomez
-- 				Carlos Mairena
-- 				Rodrigo Vidal
-- 
-- DATOS USUARIO INICIAL PARA ACCEDER AL PROGRAMA
-- cedula: 12345  contraseña: user123
-- NOMBRE DE LA BASE DE DATOS: Ferreteri-DVC
--
-- --------------------------------------------------------------------------
--              Números que utilizamos para referirnos a las tablas que conforman la BD
-- #1 -> Trabajador 
-- #2 -> Categoria 
-- #3 -> Proveedor 
-- #4 -> Cliente 
-- #5 -> Producto 
-- #6 -> Factura 
-- #7 -> DetallesCompra 
-- --------------------------------------------------------------------------
*/

package DVC_Ferreteria;

import controlador.ControladorPrincipal;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class Arranque {

    public static void main(String[] args) {

        ControladorPrincipal controlframe = new ControladorPrincipal();
        controlframe.muestraVentanaPrincipal();
    }

}
