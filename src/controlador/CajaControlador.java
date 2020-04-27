package controlador;

import Vista.DlgCliente;
import Vista.DlgModificarDatos;
import Vista.DlgMostrador;
import Vista.FrmPrincipal;
import Vista.FrmVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import logicaClass.ClassCliente;
import logicaClass.ClassFactura;
import logicaClass.ClassProducto;
import logicaClass.ClassTrabajador;
import modelo.CajaModelo;
import modelo.InventarioModelo;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class CajaControlador implements ActionListener {

    private FrmVentas ventanaVentas;
    private FrmPrincipal principal;
    private DlgMostrador mostrador;
    private DlgModificarDatos dlgMofidicaCantidad;
    private DlgCliente dlgCli;

    private CajaModelo cajaModelo;
    private ClassCliente cliente;
    private ClassTrabajador trabajador;
    private ClassProducto producto;

    private java.util.Date fecha;
    private ArrayList<ClassProducto> arraysDetalles;
    private int cantidad;

    public CajaControlador(FrmPrincipal principal, ClassTrabajador trabajador) {

        this.dlgCli = new DlgCliente(principal, true);
        this.principal = principal;
        this.producto = new ClassProducto();
        this.arraysDetalles = new ArrayList<>();
        this.mostrador = new DlgMostrador(this.principal, true);
        this.ventanaVentas = new FrmVentas(principal, true);
        this.cajaModelo = new CajaModelo();
        this.cantidad = 0;
        this.cliente = new ClassCliente();
        this.trabajador = trabajador;
        this.fecha = new java.util.Date();
        this.dlgMofidicaCantidad = new DlgModificarDatos(null, true);

        this.ventanaVentas.getBtnAnadirProd().addActionListener(this);
        this.ventanaVentas.getBtnCliente().addActionListener(this);
        this.ventanaVentas.getBtnQuitarPro().addActionListener(this);
        this.ventanaVentas.getBtnQuitarTodo().addActionListener(this);
        this.ventanaVentas.getBtnSalir().addActionListener(this);
        this.ventanaVentas.getBtnFacturar().addActionListener(this);
        this.ventanaVentas.getBtnModificarDatos().addActionListener(this);
        this.mostrador.getBtnSeleccionar().addActionListener(this);
        this.mostrador.getBtnCancelar().addActionListener(this);
        this.dlgMofidicaCantidad.getBtnConfirmarCambios().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.hora();

        int fila;
        if (e.getSource() == ventanaVentas.getBtnModificarDatos()) {

            fila = ventanaVentas.getTblProductosAgr().getSelectedRow();
            System.out.println("modifico datos ");

            if (fila != -1) {

                ResultSet rs = cajaModelo.mostrarDatos(arraysDetalles.get(fila).getIdProducto());
                dlgMofidicaCantidad.setTitle("Modificar Cantidad y Descuento de Produicto  " + this.arraysDetalles.get(fila).getIdProducto());

                try {

                    dlgMofidicaCantidad.getTxtCantiStock().setText("" + rs.getInt(2));
                    dlgMofidicaCantidad.getTxtDesc().setText("" + rs.getInt(3));

                } catch (SQLException ex) {
                    System.out.println("Error al intentar modificar la cantidad de stock: " + ex.getMessage());
                }
                dlgMofidicaCantidad.getTxtCantidadAgregar().setText("" + arraysDetalles.get(fila).getCantidad());
                dlgMofidicaCantidad.getTxtDescAplicar().setText("" + arraysDetalles.get(fila).getDescuentProd());
                dlgMofidicaCantidad.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Debe Seleccione un producto");
            }

        } else if (e.getSource() == dlgMofidicaCantidad.getBtnConfirmarCambios()) {
            try {

                fila = ventanaVentas.getTblProductosAgr().getSelectedRow();
                if (modificarCantDesc(fila)) {
                    dlgMofidicaCantidad.dispose();
                    this.rellenarTabla();
                }

            } catch (SQLException ex) {
                System.out.println("Error al intentar modificar el descuento o la cantidad a comprar");
            }

        } else if (e.getSource() == ventanaVentas.getBtnFacturar()) {

            System.out.println("Facturar");

            this.facturar();

        } else if (e.getSource() == ventanaVentas.getBtnCliente()) {

            ClienteControlador cliControl = new ClienteControlador(this.principal);
            cliControl.inciarVista("Seleccionar Cliente");

            // Selecciona al cliente
            if (cliControl.isSelecciono()) {

                cliente = cliControl.getCliente();

                ventanaVentas.getTxtNombreCliente().setText(cliente.getNombreCli());
                ventanaVentas.getTxtIdCliente().setText(String.valueOf(cliente.getCedulaCli()));
            }

        } else if (e.getSource() == ventanaVentas.getBtnAnadirProd()) {

            mostrador.setTitle("Selecionar Productos");
            this.mostrarProductos();
            mostrador.setVisible(true);

        } else if (e.getSource() == mostrador.getBtnSeleccionar()) {
            //a traer ese dato
            int file = mostrador.getTblMostrar().getSelectedRow();
            if (file != -1) {

                int stock = Integer.parseInt(mostrador.getTblMostrar().getValueAt(file, 7).toString());
                this.cantidad = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "¿Cuanto desea agregar?",
                        "Ingresar Cantidad", JOptionPane.PLAIN_MESSAGE));

                if (cantidad <= stock) {

                    producto = new ClassProducto();
                    producto.setCantidad(cantidad);
                    producto.setIdProducto(Integer.parseInt(mostrador.getTblMostrar().getValueAt(file, 0).toString()));
                    producto.setNombreProd(mostrador.getTblMostrar().getValueAt(file, 3).toString());

                    // En el atributo subtotal, lo que está recibiendo es el precio individual del producto
                    producto.setPrecioProd(Float.parseFloat(mostrador.getTblMostrar().getValueAt(file, 4).toString()));
                    producto.setDescuentProd(Integer.parseInt(mostrador.getTblMostrar().getValueAt(file, 5).toString()));

                    // Se calcula el subtotal de solo este producto: cantidad * precio
                    producto.setSubtotal(producto.getCantidad() * producto.getPrecioProd());

                    if (revisarid(producto.getIdProducto())) {
                        System.out.println("Insertar producto a factura");
                        this.arraysDetalles.add(producto);
                        arraysDetalles.trimToSize();

                    }

                    this.rellenarTabla();
                    cajaModelo.modificarStock(producto.getIdProducto(), 2, cantidad);
                    mostrador.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Cantidad no disponible ", "ERROR", JOptionPane.WARNING_MESSAGE);

                }

            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un producto ", "ERROR", JOptionPane.WARNING_MESSAGE);
            }

        } else if (e.getSource() == mostrador.getBtnCancelar()) {

            System.out.println("Cancelar la selección de producto o cliente");
            mostrador.dispose();

        } else if (e.getSource() == ventanaVentas.getBtnQuitarPro()) {

            System.out.println("Quitar producto");
            fila = ventanaVentas.getTblProductosAgr().getSelectedRow();

            if (fila != -1) {

                int opcion = JOptionPane.showConfirmDialog(ventanaVentas, "¿Desea quitar este producto de la compra?", "Quitar", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    this.quitarProducto(fila);
                }

            } else {
                JOptionPane.showMessageDialog(ventanaVentas, "Seleccione un producto para quitar de la compra");
            }
            
        } else if (e.getSource() == ventanaVentas.getBtnSalir()) {

            System.out.println("Salir");
            if (JOptionPane.YES_OPTION
                    == JOptionPane.showConfirmDialog(ventanaVentas, "¿Desea salir? \n"
                            + "Se perderán todos los datos.", "Salir", JOptionPane.YES_NO_OPTION)) {
                this.sumarAlStocks();
                this.limpiartodo();
                ventanaVentas.dispose();
            }
        } else if (e.getSource() == ventanaVentas.getBtnQuitarTodo()) {

            // Regresa los productos al stock
            System.out.println("Limpiar");
            if (JOptionPane.YES_OPTION
                    == JOptionPane.showConfirmDialog(ventanaVentas, "¿Desea limpiar la ventana? \n"
                            + "Se perderán todos los datos.")) {
                this.sumarAlStocks();
                this.limpiartodo();
            }
        }
    }

    /**
     * Elimina un producto
     *
     * @param fila
     */
    private void quitarProducto(int fila) {

        System.out.println("Quitar producto");

        if (fila != -1) {

            int id = Integer.parseInt(ventanaVentas.getTblProductosAgr().getValueAt(fila, 0).toString());
            System.out.println("id a eliminar " + id);

            cantidad = arraysDetalles.get(fila).getCantidad();
            cajaModelo.modificarStock(id, 1, cantidad);
            arraysDetalles.remove(fila);
            arraysDetalles.trimToSize();
            this.rellenarTabla();
        } else {
            System.out.println("No se elimina porque el producto no esá seleccionado");
        }
    }

    /**
     * Permite iniciar la vista de la ventana
     *
     */
    public void iniciarVista() {

        this.rellenarTabla();
        this.calcularMontos();
        this.ventanaVentas.setTitle("Caja");
        this.ventanaVentas.getLblNomTrab().setText("Nombre del cajero: " + trabajador.getNombreTrab());
        this.limpiartodo();

        //FECHA DEL SISTEMA
        SimpleDateFormat formato = new SimpleDateFormat("dd MMMMM YYYY");
        ventanaVentas.getTxtFecha().setText(formato.format(fecha));
        // HORA DEL SISTEMA
        Timer tiempo = new Timer(100, CajaControlador.this);
        tiempo.start();
        ventanaVentas.setVisible(true);
    }

    /**
     * Prepara la tabla para poder utilizarla con el formato
     *
     */
    private void rellenarTabla() {

        String titles[] = {"ID de producto", "Producto", "Cantidad", "Precio", "Descuento"};

        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        model.setColumnIdentifiers(titles);

        // ArrayList o extraer las mismas que están en la tabla
        for (int i = 0; i < arraysDetalles.size(); i++) {

            Object[] objeto = {arraysDetalles.get(i).getIdProducto(),
                arraysDetalles.get(i).getNombreProd(),
                arraysDetalles.get(i).getCantidad(),
                arraysDetalles.get(i).getPrecioProd(),
                arraysDetalles.get(i).getDescuentProd()};

            model.addRow(objeto);
        }

        this.ventanaVentas.getTblProductosAgr().setModel(model);
        ventanaVentas.getLblRegistrosDetalles().setText("Cantidad Productos: " + model.getRowCount());
        this.calcularMontos();
    }

    /**
     * Permite verificar si la cantidad de un producto se modifica
     *
     * @param cantidadpro
     * @param fila
     */
    private void verificaCambioCant(int cantidadpro, int fila) {

        if (cantidadpro != arraysDetalles.get(fila).getCantidad()) {

            if (cantidadpro < arraysDetalles.get(fila).getCantidad()) {

                int diferencia = arraysDetalles.get(fila).getCantidad() - cantidadpro;
                arraysDetalles.get(fila).setCantidad(cantidadpro);

                this.cajaModelo.modificarStock(
                        Integer.parseInt(ventanaVentas.getTblProductosAgr().getValueAt(fila, 0).toString()),
                        1, diferencia);

            } else {

                int diferencia = cantidadpro - arraysDetalles.get(fila).getCantidad();
                arraysDetalles.get(fila).setCantidad(cantidadpro);

                this.cajaModelo.modificarStock(
                        Integer.parseInt(ventanaVentas.getTblProductosAgr().getValueAt(fila, 0).toString()),
                        2,
                        diferencia);
            }
        }
    }

    /**
     *
     * Permite calcular el total a pagar, el total descuento y el subtotal <br>
     * con los productos agregados.
     */
    private void calcularMontos() {

        /*
        -----------------------------------------------------------------------
                                      IMPORTANTE
        La manera en que calcula el descuento es por individual, osea, si el producto vale 1000 y el
    descuento es del 50%, entonces calcula el valor con el descuento, luego ese valor lo multiplica para
    la cantidad que hay:
                             3 productos a 1000 colones
                             50 % de descuento equivale a 500 colones
                             en total se cobran 1500 porque cada producto con descuento son 500
        -----------------------------------------------------------------------
         */
        ventanaVentas.getTxtDescuento().setText("0");
        ventanaVentas.getTxtSubTotal().setText("0");
        ventanaVentas.getTxtTotalPagar().setText("0");

        float descuento = 0.0f;
        float precio = 0.0f;
        float rebaja = 0.0f;
        int cantidadpro = 0;
        float total = 0.0f;

        System.out.println("Empezando a calcular montos");
        for (int fila = 0; fila < ventanaVentas.getTblProductosAgr().getModel().getRowCount(); fila++) {

            try {
                // Se calculan el valor de cada producto de acuerdo a su descuento
                precio = Float.parseFloat(ventanaVentas.getTblProductosAgr().getValueAt(fila, 3).toString());
                descuento = (Float.parseFloat(ventanaVentas.getTblProductosAgr().getValueAt(fila, 4).toString()));
                cantidadpro = Integer.parseInt(ventanaVentas.getTblProductosAgr().getValueAt(fila, 2).toString());

                if (cantidadpro == 0) {
                    // Relanzamos la excepción
                    System.out.println("CANTTIDAD ES 0");
                    throw new NumberFormatException();
                }
                this.verificaCambioCant(cantidadpro, fila);

                rebaja = ((precio * descuento) / 100);
                total += (precio - rebaja) * cantidadpro;

                ventanaVentas.getTxtSubTotal().setText(String.format("%.2f",
                        (precio * cantidadpro) + Float.parseFloat(ventanaVentas.getTxtSubTotal().getText())));

                ventanaVentas.getTxtDescuento().setText(String.format("%.2f", (rebaja + Float.parseFloat(ventanaVentas.getTxtDescuento().getText()))));
                ventanaVentas.getTxtTotalPagar().setText(String.format("%.2f", total));

            } catch (NumberFormatException es) {

                // Verificamos cuál es el campo vacío para settearle un 0
                // DESCUENTO
                if (ventanaVentas.getTblProductosAgr().getValueAt(fila, 4).toString().isEmpty()) {
                    System.out.println("Borraron todo de la celda descuento, por lo tanto se le pone 0 de defecto");
                    ventanaVentas.getTblProductosAgr().setValueAt(0, fila, 4);

                } else {
                    this.quitarProducto(fila);
                }
                break;
            }
        }

    }

    /**
     * Permite limpiar los campos de la gráfica
     */
    public void limpiartodo() {

        this.arraysDetalles.clear();
        this.cantidad = 0;
        this.ventanaVentas.getTxtNombreCliente().setText("Sin Cliente");
        this.ventanaVentas.getTxtIdCliente().setText("-----");
        this.ventanaVentas.getTxtDireccionEnt().setText("Sin dirección");
        this.ventanaVentas.getTxtSubTotal().setText("0");
        this.ventanaVentas.getTxtTotalPagar().setText("0");
        this.ventanaVentas.getTxtDescuento().setText("0");
        this.cliente = new ClassCliente();
        this.rellenarTabla();
    }

    /**
     *
     * Regresa todos las cantidades a sus stock <br>
     * de la base de datos con el switch de suma o restar
     */
    public void sumarAlStocks() {

        if (!arraysDetalles.isEmpty()) {
            for (int i = 0; i < arraysDetalles.size(); i++) {

                if (arraysDetalles.get(i).getIdProducto() != 0) {

                    System.out.println("dato  " + arraysDetalles.get(i).getIdProducto());
                    int id = arraysDetalles.get(i).getIdProducto();
                    int canti = arraysDetalles.get(i).getCantidad();
                    System.out.println("id " + id + " cantidad " + canti);
                    cajaModelo.modificarStock(id, 1, canti);
                    this.rellenarTabla();
                }
            }
        }
    }

    /**
     *
     * Método para mostrar la hora, trabajada con la clase Timer <br>
     * y a traves de un metodo de ActionEvent recorre los segundos.
     */
    private void hora() {

        String pmAm = "hh:mm:ss a";
        SimpleDateFormat format = new SimpleDateFormat(pmAm);
        Calendar hoy = Calendar.getInstance();
        java.util.Date fe = new java.util.Date();
        ventanaVentas.getTxtHora().setText(String.format(format.format(fe), hoy));
    }

    /**
     * Revisa si el Id de los productos insertados ya existe, <br>
     * de ser así, solo suma los dato al stock.
     *
     * @param id el id del producto de la tbal de frmVentas
     * @return false is existe y si es true es que no existe
     */
    public boolean revisarid(int id) {
        System.out.println("id review " + id);
        try {
            if (!this.arraysDetalles.isEmpty()) {
                for (int i = 0; i < arraysDetalles.size(); i++) {
                    if (arraysDetalles.get(i).getIdProducto() == id) {

                        int aux = arraysDetalles.get(i).getCantidad();
                        arraysDetalles.get(i).setCantidad(cantidad + aux);

                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *
     * Muestra los productos obteniendo sus <br>
     * datos desde la base de datos
     */
    private void mostrarProductos() {
        String[] title = {"ID", "Nombre", "Proveedor", "Categoría", "Precio", "Descuento", "Venta por",
            "Stock", "Frágil", "Descripción"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, title) {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        ResultSet rs = null;
        InventarioModelo model = new InventarioModelo();

        try {
            rs = model.mostrarProductos();

            while (rs.next()) {

                Object[] objeto = {rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getFloat(5), rs.getInt(6), rs.getString(7),
                    rs.getInt(8), rs.getBoolean(9), rs.getString(10)};

                tablaModelo.addRow(objeto);
            }

            System.out.println("Cerrando ResultSet");
            rs.close();
            mostrador.getTblMostrar().setModel(tablaModelo);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Crea la factura
     */
    private void facturar() {

        if (this.cliente.getCedulaCli() != 0 && !arraysDetalles.isEmpty()) {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(ventanaVentas, "¿Desea factura?",
                    "FACTURAR", JOptionPane.YES_NO_OPTION)) {

                System.out.println("Acepta facturar");
                java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

                // Creamos el objeto de tipo Factura
                ClassFactura facturaNueva = new ClassFactura(arraysDetalles, fechaSQL, cliente, trabajador,
                        Float.parseFloat(ventanaVentas.getTxtTotalPagar().getText()),
                        Float.parseFloat(ventanaVentas.getTxtSubTotal().getText()),
                        ventanaVentas.getTxtDireccionEnt().getText());

                // Aquí enviamos el objeto de tipo factura para que sea facturada
                if (cajaModelo.insertarFactura(facturaNueva)) {
                    JOptionPane.showMessageDialog(ventanaVentas, "Facturado exitosamente");
                    this.limpiartodo();

                } else {
                    JOptionPane.showMessageDialog(ventanaVentas, "Error al intentar facturar.\n"
                            + "Comuníquese con el administrador", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(ventanaVentas, "Seleccione un cliente para poder facturar \n"
                    + "y asegúrese de tener al menos 1 producto para facturar.",
                    "Seleccion un cliente", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Cambia las cantidades y descuentos de los productos
     *
     * @param fila la fila seleccionado
     * @return false si falla en las operaciones, true true
     * @throws SQLException
     */
    public boolean modificarCantDesc(int fila) throws SQLException {
        int stock = 0;
        int desc = 0;
        ResultSet rs = cajaModelo.mostrarDatos(arraysDetalles.get(fila).getIdProducto());
        try {
            stock = rs.getInt(2);
            desc = rs.getInt(3);
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
        int descuento = Integer.parseInt(dlgMofidicaCantidad.getTxtDescAplicar().getText());
        int cantidad = Integer.parseInt(dlgMofidicaCantidad.getTxtCantidadAgregar().getText());

        if (descuento <= desc) { //primero  se verifica que el descuesto no exceda al valor de la BD
            if (cantidad <= stock) { //primer caso

                if (cantidad < arraysDetalles.get(fila).getCantidad()) {

                    int diferencia = arraysDetalles.get(fila).getCantidad() - cantidad;
                    arraysDetalles.get(fila).setCantidad(cantidad);

                    this.cajaModelo.modificarStock(arraysDetalles.get(fila).getIdProducto(), 1, diferencia);
                    arraysDetalles.get(fila).setDescuentProd(descuento);
                    return true;

                } else {

                    int diferencia = cantidad - arraysDetalles.get(fila).getCantidad();
                    arraysDetalles.get(fila).setCantidad(cantidad);
                    this.cajaModelo.modificarStock(arraysDetalles.get(fila).getIdProducto(), 2, diferencia);
                    arraysDetalles.get(fila).setDescuentProd(descuento);

                    return true;
                }
            }
            return false;
        }

        return false;
    }

    public void setTrabajador(ClassTrabajador trabajador) {
        this.trabajador = trabajador;
    }

}
