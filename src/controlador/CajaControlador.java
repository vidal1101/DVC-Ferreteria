package controlador;

import Vista.DlgCliente;
import Vista.DlgMostrador;
import Vista.FrmVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import logicaClass.ClassCliente;
import logicaClass.ClassDetallesFactura;
import modelo.CajaModelo;
import modelo.ClienteModelo;
import modelo.InventarioModelo;

/**
 *
 * @author Dixiana, Carlos y Vidal
 */
public class CajaControlador implements PropertyChangeListener, ActionListener {

    private FrmVentas ventanaVentas;
    private ArrayList<ClassDetallesFactura> arraysDetalles;
    private CajaModelo cajaModelo;
    private ClassDetallesFactura detaFactura;
    private DlgMostrador mostrador;
    private ClassCliente cliente;
    private ClienteModelo cliModelo;
    private DlgCliente dlgCli;
    private int ced;
    private String name;
    private int cantidad;

    public CajaControlador(FrmVentas ventanaVentas) {

        this.detaFactura = new ClassDetallesFactura();
        this.arraysDetalles = new ArrayList<>();
        this.mostrador = new DlgMostrador(null, true);
        this.ventanaVentas = ventanaVentas;
        this.ventanaVentas.getTblProductosAgr().addPropertyChangeListener(this);
        this.cajaModelo = new CajaModelo();
        this.cantidad = 0;
        this.cliModelo = new ClienteModelo();
        this.cliente = new ClassCliente();
        this.dlgCli = new DlgCliente(null, true);

        ventanaVentas.getBtnAnadirProd().addActionListener(this);
        ventanaVentas.getBtnCliente().addActionListener(this);
        ventanaVentas.getBtnQuitarPro().addActionListener(this);
        ventanaVentas.getBtnQuitarTodo().addActionListener(this);
        ventanaVentas.getBtnSalir().addActionListener(this);
        this.mostrador.getBtnSeleccionar().addActionListener(this);
        this.mostrador.getBtnCancelar().addActionListener(this);

    }

    // Método de la interfaz PropertyChangeListener
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        System.out.println("Celda modificada");
        this.calcularMontos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == ventanaVentas.getBtnCliente()) {

            ClienteControlador cliControl = new ClienteControlador(null, dlgCli, cliente, cliModelo);
            // Hacer un botón que seleccione o una alternativa
            cliControl.inciarVista("Seleccionar Cliente");
            int fila = dlgCli.getTblCliente().getSelectedRow();

            if (fila != -1) {

                ced = Integer.parseInt(dlgCli.getTblCliente().getValueAt(fila, 0).toString());
                name = dlgCli.getTblCliente().getValueAt(fila, 1).toString();
                ventanaVentas.getTxtNombreCliente().setText(name);
                ventanaVentas.getTxtIdCliente().setText(String.valueOf(ced));

            }

        } else if (e.getSource() == ventanaVentas.getBtnAnadirProd()) {

            mostrador.setTitle("Selecionar Productos");
            this.mostrarProductos();
            mostrador.setVisible(true);

        } else if (e.getSource() == mostrador.getBtnSeleccionar()) {
            //a traer ese daot
            int file = mostrador.getTblMostrar().getSelectedRow();
            if (file != -1) {

                int stock = Integer.parseInt(mostrador.getTblMostrar().getValueAt(file, 7).toString());
                this.cantidad = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "¿Cuanto desea agregar?",
                        "Ingresar Cantidad", JOptionPane.PLAIN_MESSAGE));

                if (cantidad <= stock) {

                    detaFactura = new ClassDetallesFactura();
                    detaFactura.setCantidadProd(cantidad);
                    detaFactura.setIdProd(Integer.parseInt(mostrador.getTblMostrar().getValueAt(file, 0).toString()));
                    detaFactura.setNombProducto(mostrador.getTblMostrar().getValueAt(file, 3).toString());
                    // En el atributo subtotal, lo que está recibiendo es el precio individual del producto
                    detaFactura.setSubtotal(Double.valueOf(mostrador.getTblMostrar().getValueAt(file, 4).toString()));
                    detaFactura.setDescuento(Double.valueOf(mostrador.getTblMostrar().getValueAt(file, 5).toString()));

                    if (revisarid(detaFactura.getIdProd())) {
                        System.out.println("inserccion  ..");
                        this.arraysDetalles.add(detaFactura);
                        arraysDetalles.trimToSize();

                    }

                    this.rellenarTabla();
                    cajaModelo.modificarStock(detaFactura.getIdProd(), 2, cantidad);
                    mostrador.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Cantidad no disponible ", "ERROR", JOptionPane.WARNING_MESSAGE);

                }

            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un producto ", "ERROR", JOptionPane.WARNING_MESSAGE);
            }

        } else if (e.getSource() == mostrador.getBtnCancelar()) {

            System.out.println("Cancelar la selección de producto");
            mostrador.dispose();

        } else if (e.getSource() == ventanaVentas.getBtnQuitarPro()) {

            System.out.println("Quitar producto");
            int fila = ventanaVentas.getTblProductosAgr().getSelectedRow();

            if (fila != -1) {
                
                int opcion = JOptionPane.showConfirmDialog(ventanaVentas, "¿Desea quitar este producto de la compra?", "Quitar", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    this.quitarProducto(fila);
                    JOptionPane.showMessageDialog(null, "Eliminado correctamente");
                }
                
            } else {
                JOptionPane.showMessageDialog(ventanaVentas, "Seleccione un producto");
            }
        } else if (e.getSource() == ventanaVentas.getBtnSalir()) {

            System.out.println("Salir");
            this.sumarAlStocks();
            ventanaVentas.dispose();

        } else if (e.getSource() == ventanaVentas.getBtnQuitarTodo()) {

            // Regresa los productos al stock
            System.out.println("Limpiar");
            this.sumarAlStocks();
            this.limpiartodo();
        }
    }

    // -----------------------------------------------------------------------
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
        ventanaVentas.setTitle("Caja");

        Timer tiempo = new Timer(100, new CajaControlador.hora());
        tiempo.start();

        //FECHA DEL SISTEMA
        Date sistFecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd MMMMM YYYY");
        ventanaVentas.getTxtFecha().setText(formato.format(sistFecha));
        ventanaVentas.setVisible(true);
    }

    /**
     * Prepara la tabla para poder utilizarla con el formato
     *
     */
    private void rellenarTabla() {

        String titles[] = {"ID de producto", "Producto", "Cantidad", "Precio", "Descuento"};

        // Creamos el arreglo con la misma cantidad de columnas,
        // indicando el lugar que será editable con true
        boolean[] editables = new boolean[]{false, false, true, false, true};

        // Preparamos al modelo, ciertas columnas editables y otras no
        DefaultTableModel model = new javax.swing.table.DefaultTableModel() {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return editables[columnIndex];
            }
        };

        model.setColumnIdentifiers(titles);

        // ArrayList o extraer las mismas que están en la tabla
        for (int i = 0; i < arraysDetalles.size(); i++) {

            Object[] objeto = {arraysDetalles.get(i).getIdProd(), arraysDetalles.get(i).getNombProducto(),
                arraysDetalles.get(i).getCantidadProd(),
                // Debemos revisar el subtotal, esa debería se la suma multiplicada por la cantidad
                arraysDetalles.get(i).getSubtotal(), arraysDetalles.get(i).getDescuento()};

            model.addRow(objeto);
        }

        this.ventanaVentas.getTblProductosAgr().setModel(model);
        this.calcularMontos();
    }

    /**
     * Permite calcular el total a pagar, el total descuento y el subtotal
     *
     */
    private void calcularMontos() {

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

                // Eliminamos el producto si la cantidad es 0
                if (cantidadpro == 0) {
                    // Relanzamos la excepción
                    throw new NumberFormatException();
                }
                rebaja = ((precio * descuento) / 100);

                /* ------------------------------------------------------------------
                                      IMPORTANTE
        La manera en que calcula el descuento es por individual, osea, si el producto vale 1000 y el
       descuento es del 50%, entonces calcula el valor con el descuento, luego ese valor lo multiplica para
       la cantidad que hay:
                             3 productos a 1000 colones
                             50 % de descuento equivale a 500 colones
                             en total se cobran 1500 porque cada producto con descuento son 500
    -----------------------------------------------------------------------
                 */
                total += (precio - rebaja) * cantidadpro;

                ventanaVentas.getTxtSubTotal().setText(String.format("%.2f",
                        (precio * cantidadpro) + Float.parseFloat(ventanaVentas.getTxtSubTotal().getText())));

                // El String.format("%.2f",valor) lo que hace es dar formato de 2 decimales al valor de tipo float
                ventanaVentas.getTxtDescuento().setText(String.format("%.2f", (rebaja + Float.parseFloat(ventanaVentas.getTxtDescuento().getText()))));
                ventanaVentas.getTxtTotalPagar().setText(String.format("%.2f", total));

            } catch (NumberFormatException es) {

                System.out.println("Borraron todo de la celda descuento, por lo tanto se le pone 0 de defecto");

                // Verificamos cuál es el campo vacío para settearle un 0
                // DESCUENTO
                if (ventanaVentas.getTblProductosAgr().getValueAt(fila, 4).toString().isEmpty()) {
                    ventanaVentas.getTblProductosAgr().setValueAt(0, fila, 4);

                } else {
                    this.quitarProducto(fila);
                }
                break;
            }
        }
        System.out.println("Montos calculados");
    }

    /**
     * Permite limpiar los campos de la gráfica
     */
    public void limpiartodo() {
        this.arraysDetalles = new ArrayList<>();
        this.cantidad = 0;
        this.ventanaVentas.getTxtNombreCliente().setText("");
        this.ventanaVentas.getTxtIdCliente().setText("");
        this.ventanaVentas.getTxtDireccionEnt().setText("");
        this.ventanaVentas.getTxtSubTotal().setText("");
        this.ventanaVentas.getTxtTotalPagar().setText("");
        this.ventanaVentas.getTxtDescuento().setText("");
        this.rellenarTabla();
    }

    /**
     *
     * regresa todos las cantidades a sus stock de la base de datos con el switch de suma o restar
     */
    public void sumarAlStocks() {

        if (!arraysDetalles.isEmpty()) {
            for (int i = 0; i < arraysDetalles.size(); i++) {

                if (arraysDetalles.get(i).getIdProd() != 0) {

                    System.out.println("dato  " + arraysDetalles.get(i).getIdProd());
                    int id = arraysDetalles.get(i).getIdProd();
                    int canti = arraysDetalles.get(i).getCantidadProd();
                    System.out.println("id " + id + " cantidad " + canti);
                    cajaModelo.modificarStock(id, 1, canti);
                    this.rellenarTabla();
                }
            }
        }
    }

    /**
     * *
     * metodo para visualizar la hora, trabajada con un hilo de timer y a traves de un metodo del event para correr
     * mienttas la ventana este en uso
     */
    class hora implements ActionListener {

        Date sistHora = new Date();
        String pmAm = "hh:mm:ss a";
        SimpleDateFormat format = new SimpleDateFormat(pmAm);
        Calendar hoy = Calendar.getInstance();

        @Override
        public void actionPerformed(ActionEvent e) {

            //System.out.println("Obteniendo hora");
            ventanaVentas.getTxtHora().setText(String.format(format.format(sistHora), hoy));
        }
    }

    /**
     * revisa si el Id de los productos insertados ya exite de ser asi solo suma los dato al stock
     *
     * @param id el id del producto de la tbal de frmVentas
     * @return false is existe y si es true es que no existe
     */
    public boolean revisarid(int id) {
        System.out.println("id review " + id);
        try {
            if (!this.arraysDetalles.isEmpty()) {
                for (int i = 0; i < arraysDetalles.size(); i++) {
                    if (arraysDetalles.get(i).getIdProd() == id) {

                        int aux = arraysDetalles.get(i).getCantidadProd();
                        arraysDetalles.get(i).setCantidadProd(cantidad + aux);

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
     * metodo para mostrar los prodcutos
     */
    private void mostrarProductos() {
        String[] title = {"ID", "Proveedor", "Categoria", "Nombre Prod ", "Precio", "Descuento", "Venta por",
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

                Object[] objeto = {rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
                    rs.getDouble(5), rs.getInt(6), rs.getString(7),
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
}
