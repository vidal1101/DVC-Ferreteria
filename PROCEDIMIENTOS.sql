-- Integrantes: Dixiana Gomez
-- Carlos Mairena
-- Rodrigo Vidal
-- 
-- DATOS USUARIO INICIAL PARA ACCEDER AL PROGRAMA
-- cedula: 12345  contraseña: user123
-- --------------------------------------------------------------------------
-- 				Números que utilizamos para referirnos a las tablas
-- #1 -> Trabajador 
-- #2 -> Categoria 
-- #3 -> Proveedor 
-- #4 -> Cliente 
-- #5 -> Producto 
-- #6 -> Factura 
-- #7 -> DetallesCompra 
-- --------------------------------------------------------------------------
USE `Ferreteria-DVC`;

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_mostrarDetallesFactura`
($id INT)
BEGIN 
    IF EXISTS(SELECT idFactura FROM Factura WHERE idFactura = $id )THEN
       
       SELECT f.idFactura, f.cedulaCliente, f.cedulaTrabajador , f.direccionEntrega,
       dc.idDetallesCompra, dc.idProducto, dc.cantidadProducto, dc.descuento , dc.subTotal
       FROM Factura AS f INNER JOIN DetallesCompra AS dc ON f.idFactura = dc.idFactura
       WHERE f.idFactura = $id;
       
	ELSE
		SELECT 0;
    END IF;
END//

COMMENT 'Procedimiento para buscar un producto por nombre o precio o descuento o id producto
		 nombre de categoria o nombre del proveedor'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_buscarProducto
(IN $datos TEXT
)
BEGIN
		SELECT p.idProducto, p.nombre, pr.nombre AS 'Proveedor', c.nombre AS 'Categoria', 
					   p.precioProducto, p.descuentoProducto, p.unidadVenta,
					   p.cantidadStock, p.prodFragil, p.descripcion
		FROM Producto AS p
			INNER JOIN Categoria As c ON c.idCategoria = p.idCategoria
			INNER JOIN Proveedor AS pr ON p.idProveedor = pr.idProveedor
		WHERE 
			   p.idProducto LIKE CONCAT('%',$datos,'%')
			OR p.nombre LIKE CONCAT('%',$datos,'%')
            OR p.precioProducto LIKE CONCAT('%',$datos,'%')
            OR p.descuentoProducto LIKE CONCAT('%',$datos,'%')
            OR pr.nombre LIKE CONCAT('%',$datos,'%')
            OR c.nombre LIKE CONCAT('%',$datos,'%');
END $$
DELIMITER ;

CALL pa_buscarProducto("herramientas");

SELECT p.idProducto, p.nombre, pr.nombre AS 'Proveedor', c.nombre AS 'Categoria', 
					   p.precioProducto, p.descuentoProducto, p.unidadVenta,
					   p.cantidadStock, p.prodFragil, p.descripcion
				FROM Producto As p INNER JOIN Categoria As c ON c.idCategoria = p.idCategoria
				INNER JOIN Proveedor AS pr ON p.idProveedor = pr.idProveedor;

DELIMITER //
CREATE DEFINER = `root`@`localhost` PROCEDURE `pa_verificarEliminar`(
IN $caso INT,
IN $idRevisar INT,
OUT $resultado VARCHAR(45),
OUT $recibido BOOLEAN)

COMMENT 'Este procedimiento es para eliminar datos de la BD, la importancia que tiene es
	que podemos verificar si el registro tiene otros registros que hacen referencia a este,
    por lo que evitamos que haya un inconveniente al intentar eliminarlo teniéndolo 
    controlado.'

BEGIN
    -- Al booleano le decimo que si hemos recibido la petición de eliminar un registro
    -- Mas que todo para saber si se hizo la conexión o el procedimiento ha sido ejecutado
    SET $recibido = 1;

    CASE $caso
		WHEN 1 THEN  -- Eliminar un trabajador que no tenga registros de facturas
            IF EXISTS (SELECT * FROM Factura WHERE cedulaTrabajador = $idRevisar) THEN
			    SET $resultado='CON REGISTROS';
			ELSE
               DELETE FROM Trabajador WHERE cedula = $idRevisar;
			   SET $resultado = 'ELIMINADO';
			END IF;
            
		WHEN 2 THEN -- Eliminar una categoría que no tenga registros de productos
			IF EXISTS (select * from Producto where idCategoria = $idRevisar)then
			    SET $resultado='CON REGISTROS';
			ELSE
               DELETE FROM Categoria WHERE idCategoria = $idRevisar;
			   SET $resultado = 'ELIMINADO';
			END IF;
            
		WHEN 3 THEN -- Eliminar un Proveedor que no tenga registros de productos
			IF EXISTS (SELECT * FROM Producto WHERE idProveedor = $idRevisar) THEN
			    SET $resultado='CON REGISTROS';
			ELSE
               DELETE FROM Proveedor WHERE idProveedor  = $idRevisar;
			   SET $resultado = 'ELIMINADO';
			END IF;
            
		WHEN 4 THEN  -- Eliminar un Cliente que no tenga registros de facturas
			IF EXISTS (SELECT * FROM Factura WHERE cedulaCliente = $idRevisar) THEN
				SET $resultado='CON REGISTROS';
			ELSE
				DELETE FROM Cliente WHERE cedula = $idRevisar;
				SET $resultado = 'ELIMINADO';
			END IF;
            
		WHEN 5 THEN  -- Eliminar un Producto que no tenga registros de DetallesCompra
			IF EXISTS (SELECT * FROM DetallesCompra WHERE idProducto = $idRevisar) THEN
				SET $resultado='CON REGISTROS';
			ELSE
				DELETE FROM Producto WHERE idProducto = $idRevisar;
				SET $resultado = 'ELIMINADO';
			END IF;
		ELSE 
			SELECT 'No hay ningún número de tabla que coincdia';
		END CASE;
END//
DELIMITER ;

DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_eliminarFactura
(IN $idFactura INT, OUT $eliminado BOOLEAN)

COMMENT 'Para poder eliminar la factura, debemos asegurarnos de eliminar los detalles
de factura que tengan una relación con la factura enviada actualmente'
BEGIN
	IF EXISTS (SELECT idFactura FROM DetallesCompra WHERE idFact) THEN
        -- Este elimina de todos los productos de una factura
		DELETE FROM DetallesCompra WHERE idFactura = $idFactura;
        
        -- Este elimina la factura que se envia
        DELETE FROM Factura Where idFactura = $idFactura;
        SET $eliminado = 1;
        
	ELSE
    
		SELECT 'No se ha eliminado la factura porque no existe';
		SET $eliminado = 1;
        
	END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE `pa_insertarDetallesCompra`(
IN $idFactura INT,
IN $idProducto INT,
IN $cantidad INT,
IN $descuento INT,
in $subTotal float,
OUT $registrado BOOLEAN)
begin

    insert into DetallesCompra(idFactura,idProducto,cantidadProducto,descuento,subTotal)
    values($idFactura,$idProducto,$cantidad,$descuento,$subTotal);
    
    SET $registrado = 1;
    
end $$
DELIMITER ;

COMMENT 'Procedimiento que muestra la cantidad de stock y descuento de un producto'
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_mostrarDatosProd`(
IN $idProducto INT)
BEGIN
   SELECT idProducto,cantidadStock,descuentoProducto FROM Producto
   WHERE idProducto=$idProducto;
END $$
DELIMITER ;

COMMENT 'Procedimiento que inserta una factura'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE `pa_insertarFactura`
(IN $cedCliente INT,
 IN $cedTrabajador INT,
 IN $direccEntre TEXT,
 IN $fecha DATETIME,
 IN $totalPago FLOAT,
 IN $subTotal FLOAT,
 IN $estado VARCHAR(20),
 OUT $editado boolean)
BEGIN

	INSERT INTO Factura (cedulaCliente,cedulaTrabajador,direccionEntrega,fecha,totalPago,subTotal,estado) 
	VALUES($cedCliente,$cedTrabajador,$direccEntre,$fecha,$totalPago,$subTotal,$estado);
    
    SELECT MAX(idFactura) FROM Factura;
    SET $editado = 1;

END $$
DELIMITER ;

COMMENT 'Procedimiento que modifica el estado de la factura'
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_modificarEstadoFactura`
(IN $idFactura INT,
 OUT $editado boolean)
 
BEGIN
    IF EXISTS (SELECT idFactura FROM Factura WHERE idFactura = $idFactura
    AND estado = "Facturada") THEN
	
				UPDATE Factura SET
						estado = "Anulada"
				WHERE idFactura = $idFactura;
			SET $editado = 1;
            
         ELSE
				SELECT 'La factura con id '+ $idFactura +' no existe';
                SET $editado = 0;
		END IF;
END $$
DELIMITER ;

COMMENT 'Procedimiento para mostrar todas las tablas'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_mostrarTablas
($tabla int)
BEGIN
	 CASE $tabla
     -- En la tabla trabajador evitamos enviar el campo contrasenia
		WHEN 1 THEN SELECT cedula,nombre,puesto,email,telefono,admin FROM Trabajador; 
        WHEN 2 THEN SELECT * FROM Categoria;
        WHEN 3 THEN SELECT * FROM Proveedor;
        WHEN 4 THEN SELECT * FROM Cliente;
        -- Inner Join para mostrar los datos de los productos
        WHEN 5 THEN 
				SELECT p.idProducto, p.nombre, pr.nombre AS 'Proveedor', c.nombre AS 'Categoria', 
					   p.precioProducto, p.descuentoProducto, p.unidadVenta,
					   p.cantidadStock, p.prodFragil, p.descripcion
				FROM Producto As p INNER JOIN Categoria As c ON c.idCategoria = p.idCategoria
				INNER JOIN Proveedor AS pr ON p.idProveedor = pr.idProveedor;
                
        WHEN 6 THEN SELECT * FROM Factura;
        WHEN 7 THEN SELECT * FROM DetallesCompra;
     ELSE
		SELECT "No existe el número de tabla recibido";
     END CASE;
END $$
DELIMITER ;

CALL pa_mostrarTablas(5);

COMMENT 'Procedimiento para registrar un trabajador'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_insertarTrabajador
(IN $cedula INT,
IN $nombre VARCHAR(45),
IN $puesto VARCHAR(30),
IN $telefono VARCHAR(14),
IN $email VARCHAR(35),
IN $admin BOOLEAN,
IN $contrasenia TEXT,
OUT $registrado BOOLEAN)

BEGIN
	IF NOT EXISTS (SELECT cedula FROM Trabajador WHERE cedula = $cedula) THEN
		
        INSERT INTO Trabajador (cedula,nombre,puesto,telefono,email,admin,contrasenia)
			VALUES ($cedula,$nombre,$puesto,$telefono,$email,$admin,$contrasenia);
		SET $registrado = 1;
    ELSE
        SELECT 'EL USUARIO CON CÉDULA '+$cedula+' YA EXISTE';
        SET $registrado = 0;
    END IF;
END $$
DELIMITER ;

COMMENT 'Procedimiento para modificar un trabajador'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_editarTrabajador
(IN $cedula INT,
IN $nombre VARCHAR(45),
IN $puesto VARCHAR(30),
IN $telefono VARCHAR(14),
IN $email VARCHAR(35),
IN $admin BOOLEAN,
OUT $editado BOOLEAN)

BEGIN
		IF EXISTS (SELECT cedula FROM Trabajador WHERE cedula = $cedula) THEN
        
				UPDATE Trabajador SET
						nombre = $nombre,
						puesto = $puesto,
						telefono = $telefono,
						email = $email,
						admin = $admin
				WHERE cedula = $cedula;
			SET $editado = 1;
            
         ELSE 
				SELECT 'El trabajador con cédula '+$cedula+' no existe';
                SET $editado = 0;
		END IF;
END $$
DELIMITER ;

COMMENT 'Procedimiento para eliminar un trabajador'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_eliminarTrabajador
(IN $cedula INT,
OUT $eliminado BOOLEAN)

BEGIN
		IF EXISTS (SELECT cedula FROM Trabajador WHERE cedula = $cedula) THEN
		
			DELETE FROM Trabajador WHERE cedula = $cedula;
			SET $eliminado = 1;
            
		ELSE
			SELECT 'El trabajador con cédula '+$cedula+' no está registrado';
			SET $eliminado = 0;
		END IF;
END $$
DELIMITER ;

COMMENT 'Procedimiento para comprobar si un trabajador está registrado'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_sesionTrabajador
(IN $cedula INT,
 IN $contrasenia TEXT)
 
BEGIN 
		IF EXISTS (SELECT cedula,contrasenia FROM Trabajador WHERE contrasenia = $contrasenia 
        AND cedula = $cedula) THEN
        
			SELECT cedula, nombre, puesto, email, telefono, admin FROM Trabajador WHERE contrasenia = $contrasenia 
			AND cedula = $cedula;

        ELSE
			SELECT 0;
		END IF;
END $$
DELIMITER ;

COMMENT 'Procedimiento para buscar trabajadores'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_buscarTrabajador
(IN $datos TEXT)
BEGIN
		SELECT * FROM Trabajador WHERE cedula LIKE CONCAT('%',$datos,'%')
                    OR nombre LIKE CONCAT('%',$datos,'%');
END $$
DELIMITER ;
call pa_buscarTrabajador("j");

COMMENT 'Procedimiento para insertar clientes'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_insertarCliente
(IN $cedula INT,
IN $nombre VARCHAR(45),
IN $telefono VARCHAR(14),
IN $email VARCHAR(35),
OUT $registrado BOOLEAN)

BEGIN
	IF NOT EXISTS (SELECT cedula FROM Cliente WHERE cedula = $cedula) THEN
		
        INSERT INTO Cliente (cedula,nombre,telefono,email)
			VALUES ($cedula,$nombre,$telefono,$email);
            SET $registrado = 1;
    ELSE
         SELECT 'EL USUARIO CON CÉDULA '+$cedula+' YA EXISTE';
        SET $registrado = 0;
        
    END IF;
END $$
DELIMITER ;

COMMENT 'Procedimiento para modificar clientes'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_editarCliente
(IN $cedula INT,
IN $nombre VARCHAR(45),
IN $telefono VARCHAR(14),
IN $email VARCHAR(35),
OUT $editado BOOLEAN)

BEGIN
		IF EXISTS (SELECT cedula FROM Cliente WHERE cedula = $cedula) THEN
        
				UPDATE Cliente SET
						nombre = $nombre,
						telefono = $telefono,
						email = $email
				WHERE cedula = $cedula;
			SET $editado = 1;
            
         ELSE 
				SELECT 'El usuario con cédula '+ $cedula +' no existe';
                SET $editado = 0;
		END IF;
END $$
DELIMITER ;

COMMENT 'Procedimiento para eliminar clientes'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_eliminarCliente
(IN $cedula INT,
OUT $eliminado BOOLEAN)

BEGIN
		IF EXISTS (SELECT cedula FROM Cliente WHERE cedula = $cedula) THEN
		
			DELETE FROM Cliente WHERE cedula = $cedula;
			SET $eliminado = 1;
            
		ELSE
			SELECT 'El usario con cédula '+$cedula+' no está registrado';
			SET $eliminado = 0;
		END IF;
END $$
DELIMITER ;

COMMENT 'Procedimiento para buscar cliente'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_buscarCliente
(IN $datos TEXT
)
BEGIN
		SELECT * FROM Cliente WHERE cedula LIKE CONCAT('%',$datos,'%')
                    OR nombre LIKE CONCAT('%',$datos,'%');
                    

END $$
DELIMITER ;

COMMENT 'Procedimiento para insertar un proveedor'
delimiter $$ 
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_insertarProveedor`
(in $idProveedor int,
in $nombre varchar(50),
in $telefono varchar(14),
in $direccion text,
in $email varchar(45),
OUT  $registrado BOOLEAN)
BEGIN

   if not exists(select idProveedor from Proveedor where idProveedor = $idProveedor)then
    
     insert into Proveedor(idProveedor,nombre,telefono,direccion,email)
     values ( $idProveedor, $nombre, $telefono,$direccion,$email );
     set  $registrado = 1;
   else
    SELECT 'EL PROVEEDOR CON CÉDULA '+$idProveedor+' YA EXISTE';
	set  $registrado = 0;
  END IF;
END$$
delimiter ;

COMMENT 'Procedimiento para modificar un proveedor'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE `pa_editarProveedor`
(in $idProveedor int,
 in $nombre varchar(50),
 in $telefono varchar(14),
 in $direccion text,
 in $email varchar(45),
 OUT $editado BOOLEAN)
BEGIN
    IF EXISTS (SELECT idProveedor FROM Proveedor WHERE idProveedor = $idProveedor)
    THEN
        
				UPDATE Proveedor SET
						nombre = $nombre,
						telefono = $telefono,
                        direccion = $direccion,
						email = $email
               WHERE idProveedor = $idProveedor;
			SET $editado = 1;
		ELSE 
				SELECT 'El proveedor con id '+$idProveedor+' no existe';
                SET $editado = 0;
	END IF;
END $$
DELIMITER ;

COMMENT 'Procedimiento para eliminar un proveedor'
delimiter $$ 
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_eliminarProveedor`(in $idProveedor int,
OUT $eliminado BOOLEAN)
BEGIN
     if exists(select idProveedor from Proveedor where idProveedor = $idProveedor )then
         delete  from Proveedor where idProveedor = $idProveedor;
         set $eliminado=1;
      
	  else
            SELECT 'El proveedor con idProve '+$idProveedor+' no está registrado';
			 SET $eliminado = 0;
		END IF;
END$$
delimiter ;

COMMENT 'Procedimiento para buscar proveedor'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_buscarProveedor
(IN $datos TEXT
)
BEGIN
		SELECT * FROM Proveedor WHERE idProveedor LIKE CONCAT('%',$datos,'%')
                    OR nombre LIKE CONCAT('%',$datos,'%');

END $$
DELIMITER ;

 
COMMENT 'Procedimiento para insertar una categoria'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_insertarCategoria
(IN $nombre VARCHAR(45),
IN $descripcion TEXT,
OUT $insertado BOOLEAN
)
BEGIN
		INSERT INTO Categoria (nombre,descripcion) VALUES ($nombre,$descripcion);
		SET $insertado = 1;
END $$
DELIMITER ;

COMMENT 'Procedimiento para modificar una categoria'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_editarCategoria
(IN $idCategoria INT,
IN $nombre VARCHAR(45),
IN $descripcion TEXT,
OUT $editado BOOLEAN)

BEGIN
		IF EXISTS (SELECT idCategoria FROM Categoria WHERE idCategoria = $idCategoria) THEN
        
				UPDATE Categoria SET
						nombre = $nombre,
						descripcion = $descripcion
				WHERE idCategoria = $idCategoria;
                
			SET $editado = 1;
         ELSE 
				SELECT 'La categoría'+ $idCategoria +' no existe';
                SET $editado = 0;
		END IF;
END $$
DELIMITER ;


COMMENT 'Procedimiento para eliminar una categoria'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_eliminarCategoria
(IN $idCategoria INT,
OUT $eliminado BOOLEAN)

BEGIN
		IF EXISTS (SELECT idCategoria FROM Categoria WHERE idCategoria = $idCategoria) THEN
		
			DELETE FROM Categoria WHERE idCategoria = $idCategoria;
			SET $eliminado = 1;
            
		ELSE
			SELECT 'La categoría '+$idCategoria+' no está registrada';
			SET $eliminado = 0;
		END IF;
END $$
DELIMITER ;

COMMENT 'Procedimiento para buscar productos de una categoria'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_mostrarProdCategorias
(IN $idCategoria INT)
BEGIN
	IF EXISTS (SELECT idCategoria FROM Producto WHERE idCategoria = $idCategoria) THEN
		SELECT * FROM Producto WHERE idCategoria = $idCategoria;
	ELSE
		SELECT 'No existen productos con esta categoría con el ID: '+$idCategoria;
    END IF;
END $$
DELIMITER ;

COMMENT 'Procedimiento para buscar categoria'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_buscarCategorias
(IN $datos TEXT)
BEGIN
		SELECT * FROM Categoria WHERE idCategoria LIKE CONCAT('%',$datos,'%')
                    OR nombre LIKE CONCAT('%',$datos,'%');
END $$
DELIMITER ;

COMMENT 'Procedimiento para insertar un producto'
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE pa_insertarProducto
(
in $idProveed int,
in $idCateg int,
in $nombProducto varchar(45),
in $preProduct double,
in descProducto int(11),
in $unidadVenta varchar(45),
in $cantStok int(11),
in prodFragil tinyint(1),
in $descrip text,
OUT  $registrado BOOLEAN)
BEGIN
    
     insert into Producto(idProveedor ,idCategoria,
     nombre,precioProducto,descuentoProducto,unidadVenta,
     cantidadStock,prodFragil,descripcion)
     
     values ($idProveed,$idCateg,$nombProducto ,$preProduct ,
     descProducto,$unidadVenta ,$cantStok,prodFragil,$descrip);
     
     set  $registrado = 1;
   
END $$
DELIMITER ;

COMMENT 'Procedimiento para modificar un productos'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_editarProducto
(	in $idProducto int,
	in $idProveed int,
	in $idCateg int,
	in $nombProducto varchar(45),
	in $preProduct double,
	in $descProducto int(11),
	in $unidadVenta varchar(45),
	in $cantStok int(11),
	in $prodFragil tinyint(1),
	in $descrip text,
	OUT $editado BOOLEAN)

BEGIN
		IF EXISTS (SELECT idProducto FROM Producto WHERE 
        idProducto = $idProducto) THEN
        
				UPDATE Producto SET
                
					idProveedor = $idProveed,
					idCategoria = $idCateg,
					nombre = $nombProducto,
					precioProducto = $preProduct,
					descuentoProducto = $descProducto,
                    unidadVenta = $unidadVenta,
					cantidadStock = $cantStok,
                    prodFragil = $prodFragil,
                    descripcion = $descrip
                        
				WHERE idProducto = $idProducto;
			SET $editado = 1;
            
         ELSE
				SELECT 'El producto con ID '+$idProducto+' no existe';
                SET $editado = 0;
		END IF;
END $$
DELIMITER ;

COMMENT 'Procedimiento para eliminar un producto'
DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE pa_eliminarProducto
(IN $idProducto INT,
OUT $eliminado BOOLEAN)

BEGIN
		IF EXISTS (SELECT idProducto FROM Producto WHERE idProducto = $idProducto) THEN
		
			DELETE FROM Producto WHERE idProducto = $idProducto;
			SET $eliminado = 1;
            
		ELSE
			SELECT 'El producto con el ID '+$idProducto+' no está registrado';
			SET $eliminado = 0;
		END IF;
END $$
DELIMITER ;

COMMENT 'Procedimiento modificar la cantidad de stock segun se añada o se quite'
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_modificarStock`
(in $idProducto int,
in $opc int,
in $valor  int,
out $editado boolean)
begin
    IF EXISTS (SELECT idProducto FROM Producto WHERE idProducto = $idProducto) THEN
	CASE $opc
      	WHEN 1 THEN -- 1 se suma al stock
                update Producto set 
		            cantidadStock = cantidadStock+$valor
                    WHERE idProducto = $idProducto;
		      SET $editado = 1;
	    WHEN 2 THEN -- 2 se resta al stock 
			     update Producto set 
		              cantidadStock = cantidadStock-$valor
                      WHERE idProducto = $idProducto;
		       SET $editado = 1;
	   ELSE SELECT "No existe operacion valida ";
	   END CASE;    
    else 
       SELECT 'El producto con ID '+$idProducto+' no pudo ser editado ';
		SET $editado = 0;
    end if;
end $$
DELIMITER ;

INSERT INTO Trabajador VALUES (12345, "Profe Jonathan", "Administrador", 
"prof12@gmail.com", "98765", 1, "6ad14ba9986e3615423dfca256d04e3f");

INSERT INTO Trabajador VALUES (1122, "Profe Jonathan", "Administrador", 
"prof12@gmail.com", "98765", 1, "6ad14ba9986e3615423dfca256d04e3f");

INSERT INTO Cliente VALUES(504024584,"Cliente 1","+506-9988-9087","ass@me.com");

CALL pa_insertarFactura(504024584,1122,"Sin dirección",'2020-02-12',
23000,30000,"Facturada",@editado);

CALL pa_insertarFactura(504024584,12345,"Liberia Centro",'2020-02-12',
23000,30000,"Facturada",@editado);
