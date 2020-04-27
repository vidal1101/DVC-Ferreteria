package controlador;

import Vista.DlgMostrador;
import Vista.DlgInventario;
import Vista.FrmPrincipal;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaClass.ClassProducto;
import modelo.CategoriaModelo;
import modelo.InventarioModelo;
import modelo.ProveedorModelo;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class InventarioControlador implements ActionListener, KeyListener {

    private FrmPrincipal principal;
    private DlgInventario dlgivent;
    private ClassProducto producto;
    private InventarioModelo prodModelo;
    DlgMostrador mostrador;
    DefaultTableModel modeloInvent;
    private int opc;

    public InventarioControlador(FrmPrincipal principal, DlgInventario dlginvent) {
        this.modeloInvent = new DefaultTableModel();
        this.mostrador = new DlgMostrador(principal, true);
        this.principal = principal;
        this.dlgivent = dlginvent;
        this.prodModelo = new InventarioModelo();
        this.producto = new ClassProducto();
        this.opc = 0;

        this.dlgivent.getBtnNuevo().addActionListener(this);
        this.dlgivent.getBtnCalender().addActionListener(this);
        this.dlgivent.getBtnCancelar().addActionListener(this);
        this.dlgivent.getBtnEditar().addActionListener(this);
        this.dlgivent.getBtnEliminar().addActionListener(this);
        this.dlgivent.getBtnGuardarProd().addActionListener(this);
        this.dlgivent.getBtnLimpiar().addActionListener(this);
        this.dlgivent.getRbdNOfragil().addActionListener(this);
        this.dlgivent.getRbdSIfragil().addActionListener(this);
        this.dlgivent.getTxtNombProductoP().addKeyListener(this);
        this.dlgivent.getTxtPrecProductoP().addKeyListener(this);
        this.dlgivent.getSpnDescProd().addKeyListener(this);
        this.dlgivent.getTxtCantidadStockP().addKeyListener(this);
        this.dlgivent.getBtnProveedor().addActionListener(this);
        this.dlgivent.getBtnCategoria().addActionListener(this);
        this.mostrador.getBtnSeleccionar().addActionListener(this);
        this.mostrador.getBtnCancelar().addActionListener(this);

    }

    /**
     * Limpia los datos del registro
     */
    public void clear() {
        dlgivent.getTxtCantidadStockP().setText("");
        dlgivent.getTxtDescripProductoP().setText("");
        dlgivent.getSpnDescProd().setValue(0);
        dlgivent.getTxtNombProductoP().setText("");
        dlgivent.getTxtPrecProductoP().setText("");
        dlgivent.getTxtIdCategoria().setText("");
        dlgivent.getTxtIdProveedor().setText("");
        dlgivent.getCmbUnidadVenta().setSelectedIndex(0);
        dlgivent.getRbdNOfragil().setSelected(true);
    }

    public void inciarVista(String title) {
        dlgivent.setTitle(title);
        dlgivent.setLocationRelativeTo(null);
        dlgivent.getPanInventario().setSelectedIndex(0);
        System.out.println("Abriendo registros de Proveedores");
        this.mostrartabla(this.prodModelo.mostrarProductos());
        this.dlgivent.getPanInventario().setEnabledAt(1, false);
        this.dlgivent.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == dlgivent.getBtnNuevo()) {
            this.clear();
            this.dlgivent.getPanInventario().setEnabledAt(1, true);
            this.dlgivent.getPanInventario().setEnabledAt(0, false);
            this.opc = 1;
            this.dlgivent.getTxtIdProductoP().setText("NO EDITABLE");
            this.dlgivent.getPanInventario().setSelectedIndex(1);

        } else if (e.getSource() == dlgivent.getBtnLimpiar()) {
            this.clear();

        } else if (e.getSource() == dlgivent.getBtnGuardarProd()) {

            if (validacionIn()) {

                this.producto = new ClassProducto();
                producto.setCantidad(Integer.parseInt(dlgivent.getTxtCantidadStockP().getText()));
                producto.setIdCategoria(Integer.parseInt(dlgivent.getTxtIdCategoria().getText()));
                producto.setDescriProd(dlgivent.getTxtDescripProductoP().getText());
                producto.setDescuentProd(Integer.parseInt(String.valueOf(dlgivent.getSpnDescProd().getValue())));
                producto.setNombreProd(dlgivent.getTxtNombProductoP().getText());
                producto.setPrecioProd(Float.parseFloat(dlgivent.getTxtPrecProductoP().getText()));
                producto.setProdFragil(dlgivent.getRbdNOfragil().isSelected()
                        || dlgivent.getRbdSIfragil().isSelected());

                producto.setIdProveedor(Integer.parseInt(dlgivent.getTxtIdProveedor().getText()));
                producto.setUnidadVenta(String.valueOf(dlgivent.getCmbUnidadVenta().getSelectedItem()));

                if (opc == 1) {
                    if (this.prodModelo.insertarProducto(producto)) {

                        JOptionPane.showMessageDialog(dlgivent, "Se inserto con Exito");
                        this.clear();
                        // No se va a caer, porque todo está medido, hasta el mínimo detalle.
                        this.mostrartabla(this.prodModelo.mostrarProductos());
                        this.dlgivent.getPanInventario().setSelectedIndex(0);
                        this.dlgivent.getPanInventario().setEnabledAt(1, false);

                    } else {
                        JOptionPane.showMessageDialog(dlgivent, "Usuario ya existente");
                        this.clear();
                    }

                } else {

                    producto.setIdProducto(Integer.parseInt(dlgivent.getTxtIdProductoP().getText()));

                    if (prodModelo.modificarProducto(producto)) {
                        JOptionPane.showMessageDialog(dlgivent, "Se Modifico el Producto");
                        this.mostrartabla(this.prodModelo.mostrarProductos());
                        this.dlgivent.getPanInventario().setSelectedIndex(0);
                        this.dlgivent.getPanInventario().setEnabledAt(1, false);
                        this.dlgivent.getPanInventario().setEnabledAt(0, true);

                    } else {
                        JOptionPane.showMessageDialog(dlgivent, "Error al Modificar ");
                    }
                }
            }
        } else if (e.getSource() == dlgivent.getBtnCancelar()) {
            
            this.clear();
            this.dlgivent.getPanInventario().setEnabledAt(1, false);
            this.dlgivent.getPanInventario().setEnabledAt(0, true);
            this.dlgivent.getPanInventario().setSelectedIndex(0);

        } else if (e.getSource() == dlgivent.getBtnEditar()) { //modificar

            if (dlgivent.getTblInventario().getSelectedRow() != -1) {

                this.dlgivent.getPanInventario().setSelectedIndex(1);
                this.dlgivent.getPanInventario().setEnabledAt(0, false);
                int file = dlgivent.getTblInventario().getSelectedRow();

                this.dlgivent.getTxtIdProductoP().setText(dlgivent.getTblInventario().getValueAt(file, 0).toString());
                this.dlgivent.getTxtIdCategoria().setText(dlgivent.getTblInventario().getValueAt(file, 3).toString());
                this.dlgivent.getTxtIdProveedor().setText(dlgivent.getTblInventario().getValueAt(file, 2).toString());

                this.dlgivent.getTxtNombProductoP().setText(dlgivent.getTblInventario().getValueAt(file, 1).toString());
                this.dlgivent.getTxtPrecProductoP().setText(dlgivent.getTblInventario().getValueAt(file, 4).toString());
                this.dlgivent.getSpnDescProd().setValue(dlgivent.getTblInventario().getValueAt(file, 5));
                this.dlgivent.getCmbUnidadVenta().setSelectedItem(dlgivent.getTblInventario().getValueAt(file, 6).toString());
                this.dlgivent.getTxtCantidadStockP().setText(dlgivent.getTblInventario().getValueAt(file, 7).toString());
                this.dlgivent.getTxtDescripProductoP().setText(dlgivent.getTblInventario().getValueAt(file, 9).toString());
                this.dlgivent.getRbdNOfragil().isSelected();

                this.opc = 2;
                this.dlgivent.getPanInventario().setSelectedIndex(1);
                this.dlgivent.getPanInventario().setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(dlgivent, "Debe Seleccionar Fila");
            }

        } else if (e.getSource() == dlgivent.getBtnEliminar()) {

            int fila = dlgivent.getTblInventario().getSelectedRow();

            if (fila != -1) {

                int idProv = Integer.parseInt(dlgivent.getTblInventario().getValueAt(fila, 0) + "");
                int opcion = JOptionPane.showConfirmDialog(dlgivent, "Desea eliminar al trabajador con cédula "
                        + idProv + "?", "Eliminar", JOptionPane.YES_NO_OPTION);

                // Si la opción de eliminar fue SI, eliminamos
                if (opcion == JOptionPane.YES_OPTION) {
                    String respuesta = prodModelo.eliminarProducto(5, idProv);

                    if (respuesta.equals("ELIMINADO")) {

                        JOptionPane.showMessageDialog(dlgivent, "Eliminado");
                        this.mostrartabla(this.prodModelo.mostrarProductos());
                    } else if (respuesta.equals("CON REGISTROS")) {

                        JOptionPane.showMessageDialog(dlgivent, "No se puede eliminar este producto. \n"
                                + "Hay facturas enlazadas a este producto");

                    } else {
                        JOptionPane.showMessageDialog(dlgivent, "Ha habido un error al intentar eliminarse.");
                    }
                }

            } else {
                JOptionPane.showMessageDialog(dlgivent, "Selecciones un Proveedor para eliminar");
            }

        } else if (e.getSource() == dlgivent.getBtnCategoria()) {
            // abrimos la ventana para seleccionar una categoria

            mostrador.setTitle("Selecionar Categoría");
            mostrarCategorias();
            mostrador.setVisible(true);

        } else if (e.getSource() == dlgivent.getBtnProveedor()) {
            // abrimos la ventana para seleccionar un proveedor

            mostrador.setTitle("Selecionar Proveedor");
            mostrarProveedores();
            mostrador.setVisible(true);

        } else if (e.getSource() == mostrador.getBtnSeleccionar()) {
            // Seleccionar al proveedor o categoría

            int fila = mostrador.getTblMostrar().getSelectedRow();
            if (fila > -1) {

                if (mostrador.getTitle().equals("Selecionar Categoría")) {

                    dlgivent.getTxtIdCategoria().setText(mostrador.getTblMostrar().
                            getValueAt(fila, 0).toString());
                    mostrador.dispose();

                } else {

                    dlgivent.getTxtIdProveedor().setText(mostrador.getTblMostrar().
                            getValueAt(fila, 0).toString());
                    mostrador.dispose();

                }
            } else {
                JOptionPane.showMessageDialog(mostrador, "Seleccione un registro");
            }

        } else if (e.getSource() == mostrador.getBtnCancelar()) {
            // Cancela la selección del proveedor o categoría
            mostrador.dispose();
        }

    }

    /**
     * Verifica que los campos estén rellenos
     *
     * @return
     */
    private boolean validacionIn() {
        if (dlgivent.getTxtIdProveedor().getText().isEmpty()) {

            dlgivent.getTxtIdProveedor().setBackground(Color.red);

            return false;
        } else if (dlgivent.getTxtIdCategoria().getText().isEmpty()) {

            dlgivent.getTxtIdCategoria().setBackground(Color.red);

            return false;
        } else if (dlgivent.getTxtNombProductoP().getText().isEmpty()) {
            dlgivent.getTxtNombProductoP().setBackground(Color.red);

            return false;

        } else if (dlgivent.getSpnDescProd().getValue().toString().isEmpty()) {
            dlgivent.getSpnDescProd().setBackground(Color.red);

            return false;

        } else if (dlgivent.getTxtPrecProductoP().getText().isEmpty()) {
            dlgivent.getTxtPrecProductoP().setBackground(Color.red);
            return false;

        } else if (dlgivent.getTxtCantidadStockP().getText().isEmpty()) {
            dlgivent.getTxtCantidadStockP().setBackground(Color.red);
            return false;

        } else if (dlgivent.getTxtDescripProductoP().getText().isEmpty()) {
            dlgivent.getTxtDescripProductoP().setBackground(Color.red);
            return false;

        } else if (dlgivent.getCmbUnidadVenta().getSelectedIndex() == 0) {
            dlgivent.getTxtPrecProductoP().setBackground(Color.red);
            return false;
        }
        return true;
    }

    /**
     * Muestra las categorías en el mostrador
     *
     */
    private void mostrarCategorias() {
        String[] title = {"ID", "Nombre", "Descripción"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, title) {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        ResultSet rs = null;
        CategoriaModelo modelo = new CategoriaModelo();

        try {
            rs = modelo.mostrarCategorias();

            while (rs.next()) {

                Object[] objeto = {rs.getInt(1),
                    rs.getString(2), rs.getString(3)};
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
     * Muestra la tabla los datos de los productos
     *
     * @param rs
     */
    private void mostrartabla(ResultSet rs) {
        // Títulos
        String[] title = {"ID", "Nombre", "Proveedor", "Categoría", "Precio", "Descuento", "Venta por",
            "Stock", "Frágil", "Descripción"};
        modeloInvent = new DefaultTableModel(null, title) {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        try {

            while (rs.next()) {
                producto = new ClassProducto();

                Object[] objeto = {rs.getInt(1), rs.getString(4), rs.getInt(2), rs.getInt(3),
                    rs.getFloat(5), rs.getInt(6), rs.getString(7),
                    rs.getInt(8), rs.getBoolean(9), rs.getString(10)};

                modeloInvent.addRow(objeto);
            }

            System.out.println("Cerrando ResultSet");
            rs.close();
            dlgivent.getTblInventario().setModel(modeloInvent);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    /**
     * Muestra los proveedores en el mostrador
     *
     */
    private void mostrarProveedores() {

        // Títulos
        String[] title = {"Cedula", "Nombre", "Telefono", "Email", "Direccion"};
        DefaultTableModel modeloProv = new DefaultTableModel(null, title) {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        ProveedorModelo modelo = new ProveedorModelo();
        ResultSet rs = null;

        try {

            rs = modelo.mostrarProveedores();

            while (rs.next()) {

                Object[] objeto = {rs.getInt(1),
                    rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5)};

                modeloProv.addRow(objeto);
            }

            System.out.println("Cerrando ResultSet");
            rs.close();
            mostrador.getTblMostrar().setModel(modeloProv);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (e.getSource() == dlgivent.getTxtNombProductoP()) {

            char letra = e.getKeyChar();

            if (Character.isDigit(letra)) {
                e.consume();
            }
        } else if (e.getSource() == dlgivent.getTxtPrecProductoP()) {
            char letra = e.getKeyChar();

            if (!Character.isDigit(letra)) {
                e.consume();
            }
            if (dlgivent.getTxtPrecProductoP().getText().length() >= 8) {
                e.consume();
            }
        } else if (e.getSource() == dlgivent.getSpnDescProd()) {
            char letra = e.getKeyChar();

            if (!Character.isDigit(letra)) {
                e.consume();
            }

        } else if (e.getSource() == dlgivent.getTxtCantidadStockP()) {
            char letra = e.getKeyChar();

            if (!Character.isDigit(letra)) {
                e.consume();
            }

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
