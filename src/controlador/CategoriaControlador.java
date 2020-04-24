package controlador;

import Vista.DlgCategorias;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaClass.ClassCategoria;
import logicaClass.ClassProducto;
import modelo.CategoriaModelo;

/**
 *
 * @author Dixiana Gómez, Rodrigo Vidal, Carlos Mairena
 */
public class CategoriaControlador implements ActionListener, WindowListener {

    private DlgCategorias dlgCate;
    private ClassCategoria categoria;
    private CategoriaModelo modeloCat;
    private DefaultTableModel tablaModelo;

    public CategoriaControlador(DlgCategorias dlgCate) {
        this.dlgCate = dlgCate;
        this.categoria = new ClassCategoria();
        this.modeloCat = new CategoriaModelo();
        this.tablaModelo = new DefaultTableModel();

        this.dlgCate.getBtnAnadirCateg().addActionListener(this);
        this.dlgCate.getBtnCancelar().addActionListener(this);
        this.dlgCate.getBtnEliminarCateg().addActionListener(this);
        this.dlgCate.getBtnGuardar().addActionListener(this);
        this.dlgCate.getBtnLimpiar().addActionListener(this);
        this.dlgCate.getBtnMasDetalles().addActionListener(this);
        this.dlgCate.getBtnModificarCateg().addActionListener(this);
        this.dlgCate.getBtnRegresar().addActionListener(this);
        this.dlgCate.getBtnBuscar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(dlgCate.getBtnAnadirCateg())) {
            // Añadir

            clearReg();
            dlgCate.getTbpnCategorias().setTitleAt(2, "Registrar");
            dlgCate.getTbpnCategorias().setSelectedIndex(2);
            dlgCate.getTxtIdCategoria1().setEnabled(true);
            dlgCate.getTbpnCategorias().setEnabledAt(2, true);
            dlgCate.getTbpnCategorias().setEnabledAt(0, false);

        } else if (e.getSource().equals(dlgCate.getBtnEliminarCateg())) {
            // Eliminar

            int fila = dlgCate.getTblCategorias().getSelectedRow();

            if (fila > -1) {

                if ((JOptionPane.showConfirmDialog(dlgCate, "¿Desea eliminar esta categoría?", "Eliminar",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)) {
                    int ID = (int) dlgCate.getTblCategorias().getValueAt(fila, 0);
                    modeloCat.eliminarCategoria(ID);
                    JOptionPane.showMessageDialog(dlgCate, "Categoria eliminada exitosamente.");
                    mostrarTablaCat(modeloCat.mostrarCategorias());

                }

            } else {
                JOptionPane.showMessageDialog(dlgCate, "Seleccione una categoría para eliminar.");
            }

        } else if (e.getSource().equals(dlgCate.getBtnModificarCateg())) {
            // Modificar

            clearReg();
            int fila = dlgCate.getTblCategorias().getSelectedRow();

            if (fila > -1) {

                dlgCate.getTbpnCategorias().setTitleAt(2, "Modificar");
                dlgCate.getTbpnCategorias().setSelectedIndex(2);
                dlgCate.getTbpnCategorias().setEnabledAt(2, true);
                dlgCate.getTbpnCategorias().setEnabledAt(0, false);

                // Obtenemos los datos de la tabla
                dlgCate.getTxtidCatReg().setText(dlgCate.getTblCategorias().getValueAt(fila, 0).toString());
                dlgCate.getTxtidCatReg().setEnabled(false);
                dlgCate.getTxtNombreCatReg().setText(dlgCate.getTblCategorias().getValueAt(fila, 1).toString());
                dlgCate.getTxtDescripReg().setText(dlgCate.getTblCategorias().getValueAt(fila, 2).toString());

            } else {
                JOptionPane.showMessageDialog(dlgCate, "Seleccione una categoría para editar.");
            }

        } else if (e.getSource().equals(dlgCate.getBtnGuardar())) {
            // Guardar

            if (datosListos()) {

                if ((JOptionPane.showConfirmDialog(dlgCate, "¿Desea guardar los datos?", "Eliminar",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)) {

                    //categoria.setIdCategoria(Integer.parseInt(dlgCate.getTxtidCatReg().getText()));
                    categoria.setNombreCateg(dlgCate.getTxtNombreCatReg().getText());
                    categoria.setDescripcionCateg(dlgCate.getTxtDescripReg().getText());

                    if (dlgCate.getTbpnCategorias().getTitleAt(2).equals("Registrar")) {

                        if (modeloCat.insertarCategoria(categoria)) {
                            JOptionPane.showMessageDialog(dlgCate, "Categoría insertada exitosamente");
                            mostrarTablaCat(modeloCat.mostrarCategorias());

                            dlgCate.getTbpnCategorias().setSelectedIndex(0);
                            dlgCate.getTbpnCategorias().setEnabledAt(0, true);
                            dlgCate.getTbpnCategorias().setEnabledAt(2, false);
                            clearReg();

                        } else {
                            JOptionPane.showMessageDialog(dlgCate, "La categoría no ha sido insertada");
                        }

                    } else if (dlgCate.getTbpnCategorias().getTitleAt(2).equals("Modificar")) {
                        
                        categoria.setIdCategoria(Integer.parseInt(dlgCate.getTxtidCatReg().getText()));
                        
                        modeloCat.modificarCategoria(categoria);
                        mostrarTablaCat(modeloCat.mostrarCategorias());

                        dlgCate.getTbpnCategorias().setSelectedIndex(0);
                        dlgCate.getTbpnCategorias().setEnabledAt(0, true);
                        dlgCate.getTbpnCategorias().setEnabledAt(2, false);
                        clearReg();
                    }
                }
            }
        } else if(e.getSource() == dlgCate.getBtnBuscar()){
            buscar();
            dlgCate.getTxtFiltroCateg().setText("");
            
        }else if (e.getSource().equals(dlgCate.getBtnCancelar())) {
            // Cancelar
            dlgCate.getTbpnCategorias().setSelectedIndex(0);
            dlgCate.getTbpnCategorias().setEnabledAt(0, true);
            dlgCate.getTbpnCategorias().setEnabledAt(2, false);
            clearReg();

        } else if (e.getSource().equals(dlgCate.getBtnLimpiar())) {
            // Limpiar
            clearReg();

        } else if (e.getSource().equals(dlgCate.getBtnMasDetalles())) {
            // Más detalles

            int fila = dlgCate.getTblCategorias().getSelectedRow();

            if (fila > -1) {

                // Obtenemos los datos de la tabla
                dlgCate.getTxtIdCategoria1().setText(dlgCate.getTblCategorias().getValueAt(fila, 0).toString());
                dlgCate.getTxtIdCategoria1().setEnabled(false);

                dlgCate.getTxtNombCateg1().setText(dlgCate.getTblCategorias().getValueAt(fila, 1).toString());
                dlgCate.getTxtDescripcion1().setText(dlgCate.getTblCategorias().getValueAt(fila, 2).toString());

                // Enviamos el ID de la categoría y traemos los productos con ese ID
                mostrarTablaProd(modeloCat.mostrarProduCat((int) (dlgCate.getTblCategorias().getValueAt(fila, 0))));

                dlgCate.getTbpnCategorias().setSelectedIndex(1);
                dlgCate.getTbpnCategorias().setEnabledAt(1, true);
                dlgCate.getTbpnCategorias().setEnabledAt(0, false);

            } else {
                JOptionPane.showMessageDialog(dlgCate, "Seleccione una categoría para ver sus detalles");
            }

        } else if (e.getSource().equals(dlgCate.getBtnRegresar())) {
            // Regresar a la tabla de Categorías

            mostrarTablaCat(modeloCat.mostrarCategorias());
            dlgCate.getTbpnCategorias().setSelectedIndex(0);
            dlgCate.getTbpnCategorias().setEnabledAt(0, true);
            dlgCate.getTbpnCategorias().setEnabledAt(1, false);

        } else {
            System.out.println("Nada seleccionado");

        }

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    /**
     * Verifica que los campos del registro no estén vacíos
     *
     * @return
     */
    private boolean datosListos() {

//        if (dlgCate.getTxtidCatReg().getText().isEmpty()) {
//            return false;
//        } else 
        if (dlgCate.getTxtNombreCatReg().getText().isEmpty()) {
            return false;
        } else if (dlgCate.getTxtDescripReg().getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Limpia los datos del registro
     */
    private void clearReg() {
        dlgCate.getTxtNombreCatReg().setText("");
        dlgCate.getTxtidCatReg().setText("");
        dlgCate.getTxtDescripReg().setText("");
    }

    /**
     * INICIA el dialog
     *
     * @param title
     */
    public void inciarVista(String title) {

        dlgCate.setTitle(title);
        dlgCate.setLocationRelativeTo(null);
        dlgCate.getTbpnCategorias().setSelectedIndex(0);
        System.out.println("Abriendo registros de categorías");
        this.mostrarTablaCat(this.modeloCat.mostrarCategorias());
        this.dlgCate.getTbpnCategorias().setEnabledAt(1, false);
        this.dlgCate.getTbpnCategorias().setEnabledAt(2, false);
        this.dlgCate.setVisible(true);
    }

    /**
     * Recibe los datos desde la tabla y los imprime en la tabla
     *
     * @param rs
     */
    public void mostrarTablaCat(ResultSet rs) {

        String[] title = {"ID", "Nombre", "Descripción"};
        this.tablaModelo = new DefaultTableModel(null, title) {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        try {

            while (rs.next()) {
                this.categoria = new ClassCategoria(rs.getInt(1),
                        rs.getString(2), rs.getString(3));

                Object[] objeto = {categoria.getIdCategoria(), categoria.getNombreCateg(),
                    categoria.getDescripcionCateg()};
                tablaModelo.addRow(objeto);
            }

            System.out.println("Cerrando ResultSet");
            rs.close();
            dlgCate.getTblCategorias().setModel(tablaModelo);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------");
        }
    }

    /**
     * Rellenamos la tabla de los productos que pertenecen
     *
     * @param rs
     */
    public void mostrarTablaProd(ResultSet rs) {

        // Obtenemos el nombre de la categoria
        int fila = dlgCate.getTblCategorias().getSelectedRow();
        String categoria = dlgCate.getTblCategorias().getValueAt(fila, 1).toString();

        String[] title = {"ID", "Nombre", "Proveedor", "Categoría", "Precio", "Descuento", "Venta por", "Stock",
            "Frágil", "Descripción"};
        this.tablaModelo = new DefaultTableModel(null, title) {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        try {

            while (rs.next()) {

                //int proveedor = rs.getInt(2);
                ClassProducto producto = new ClassProducto(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
                        rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getString(10));

                Object[] objeto = {producto.getIdProducto(), producto.getNombreProd(), producto.getIdProveedor(),
                    categoria, producto.getPrecioProd(), producto.getDescuentProd(), producto.getUnidadVenta(),
                    producto.getCantidad(), producto.getProdFragil(), producto.getDescriProd()};

                tablaModelo.addRow(objeto);
            }

            System.out.println("Cerrando ResultSet");
            rs.close();
            dlgCate.getTblProducCategorias().setModel(tablaModelo);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------");
            e.printStackTrace();
        }
    }
    
    private void buscar() {

        try {
            
            DefaultTableModel modeloTabla = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndez) {
                    return false;
                }
            };

            String titulos[] = {"ID", "Nombre", "Proveedor", "Categoría", "Precio", "Descuento", "Venta por", "Stock",
            "Frágil", "Descripción"};
            modeloTabla.setColumnIdentifiers(titulos);

            ResultSet rs = modeloCat.BuscarCategorias(dlgCate.getTxtFiltroCateg().getText());

            while (rs.next()) {

                Object nextElement[] = {rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
                        rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getString(10)};

                modeloTabla.addRow(nextElement);
            }

            rs.close();
            System.out.println("RS cerrado");
            dlgCate.getTblCategorias().setModel(modeloTabla);
            dlgCate.getLblRegistros().setText("Total de categorias " + modeloTabla.getRowCount());

        } catch (SQLException ex) {
            System.out.println("Error al intentar obtener los datos del RS: " + ex.getMessage());
        }
    }
}
