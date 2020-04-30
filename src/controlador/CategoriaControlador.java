package controlador;

import Vista.DlgCategorias;
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
import logicaClass.ClassCategoria;
import modelo.CategoriaModelo;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class CategoriaControlador implements ActionListener, KeyListener {

    private DlgCategorias dlgCate;
    private ClassCategoria categoria;
    private CategoriaModelo modeloCat;
    private DefaultTableModel tablaModelo;
    private FrmPrincipal principal;

    public CategoriaControlador(FrmPrincipal principal) {
        this.principal = principal;
        this.dlgCate = new DlgCategorias(principal, true);
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
        this.dlgCate.getTxtidCatReg().setEditable(false);
        this.dlgCate.getTxtNombreCatReg().addKeyListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(dlgCate.getBtnAnadirCateg())) {

            // Añadir
            clearReg();
            dlgCate.getTbpnCategorias().setTitleAt(2, "Registrar");
            dlgCate.getTbpnCategorias().setSelectedIndex(2);
            dlgCate.getTbpnCategorias().setEnabledAt(2, true);
            dlgCate.getTbpnCategorias().setEnabledAt(0, false);

        } else if (e.getSource().equals(dlgCate.getBtnEliminarCateg())) {
            // Eliminar

            int fila = dlgCate.getTblCategorias().getSelectedRow();

            if (fila > -1) {

                if ((JOptionPane.showConfirmDialog(dlgCate, "¿Desea eliminar esta categoría?", "Eliminar",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)) {
                    int ID = (int) dlgCate.getTblCategorias().getValueAt(fila, 0);
                    //modeloCat.eliminarCategoria(ID);
                    String respuesta = modeloCat.eliminarCategoria(2, ID);

                    if (respuesta.equals("ELIMINADO")) {

                        JOptionPane.showMessageDialog(dlgCate, "Categoria eliminada exitosamente.");
                        mostrarTablaCat(modeloCat.mostrarCategorias());

                    } else if (respuesta.equals("CON REGISTROS")) {
                        JOptionPane.showMessageDialog(dlgCate, "No se puede eliminar esta categoría. \n"
                                + "Hay productos enlazados a esta Categoría");
                    } else {
                        JOptionPane.showMessageDialog(dlgCate, "Ha habido un error al intentar eliminarse.");
                    }
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
                dlgCate.getTxtNombreCatReg().setText(dlgCate.getTblCategorias().getValueAt(fila, 1).toString());
                dlgCate.getTxtDescripReg().setText(dlgCate.getTblCategorias().getValueAt(fila, 2).toString());

            } else {
                JOptionPane.showMessageDialog(dlgCate, "Seleccione una categoría para editar.");
            }

        } else if (e.getSource().equals(dlgCate.getBtnGuardar())) {
            // Guardar

            if (validacionCa()) {

                if ((JOptionPane.showConfirmDialog(dlgCate, "¿Desea guardar los datos?", "Eliminar",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)) {

                    //categoria.setIdCategoria(Integer.parseInt(dlgCate.getTxtidCatReg().getText()));
                    categoria.setNombreCateg(dlgCate.getTxtNombreCatReg().getText());
                    categoria.setDescripcionCateg(dlgCate.getTxtDescripReg().getText());

                    if (dlgCate.getTbpnCategorias().getTitleAt(2).equals("Registrar")) {

                        if (modeloCat.insertarCategoria(categoria)) {
                            JOptionPane.showMessageDialog(dlgCate, "Categoría guardada exitosamente");
                            mostrarTablaCat(modeloCat.mostrarCategorias());

                            dlgCate.getTbpnCategorias().setSelectedIndex(0);
                            dlgCate.getTbpnCategorias().setEnabledAt(0, true);
                            dlgCate.getTbpnCategorias().setEnabledAt(2, false);
                            clearReg();

                        } else {
                            JOptionPane.showMessageDialog(dlgCate, "Error al intentar guardar categoría",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } else if (dlgCate.getTbpnCategorias().getTitleAt(2).equals("Modificar")) {

                        categoria.setIdCategoria(Integer.parseInt(dlgCate.getTxtidCatReg().getText()));

                        if (modeloCat.modificarCategoria(categoria)) {
                            JOptionPane.showMessageDialog(dlgCate, "Categoría insertada exitosamente");
                            mostrarTablaCat(modeloCat.mostrarCategorias());
                            dlgCate.getTbpnCategorias().setSelectedIndex(0);
                            dlgCate.getTbpnCategorias().setEnabledAt(0, true);
                            dlgCate.getTbpnCategorias().setEnabledAt(2, false);
                            clearReg();
                        } else {
                            JOptionPane.showMessageDialog(dlgCate, "Error al intentar editar categoría",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        } else if (e.getSource() == dlgCate.getBtnBuscar()) {
            this.buscar();

        } else if (e.getSource().equals(dlgCate.getBtnCancelar())) {
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
                ResultSet rs = null;
                rs = modeloCat.mostrarProduCat((int) (dlgCate.getTblCategorias().getValueAt(fila, 0)));
                mostrarTablaProd(rs);

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

        String[] title = {"ID", "Nombre", "Proveedor", "Categoría", "Precio", "Descuento", "Venta por",
            "Stock", "Frágil", "Descripción"};
        this.tablaModelo = new DefaultTableModel(null, title) {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        try {

            while (rs.next()) {

                Object[] objeto = {rs.getInt(1), rs.getString(4), rs.getInt(2), categoria,
                    rs.getFloat(5), rs.getInt(6), rs.getString(7),
                    rs.getInt(8), rs.getBoolean(9), rs.getString(10)};

                tablaModelo.addRow(objeto);
            }

            System.out.println("Cerrando ResultSet");
            rs.close();
            dlgCate.getTblProducCategorias().setModel(tablaModelo);

        } catch (SQLException e) {
            System.out.println("Error: No hay datos. " + e.getMessage());
            dlgCate.getTblProducCategorias().setModel(tablaModelo);
        }
    }

    /**
     * Permtie realizar la búsqueda de categorías <br>
     * por medio del nombre o ID
     */
    private void buscar() {

        try {

            String[] title = {"ID", "Nombre", "Descripción"};
            this.tablaModelo = new DefaultTableModel(null, title) {

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
            ResultSet rs = modeloCat.BuscarCategorias(dlgCate.getTxtFiltroCateg().getText());

            while (rs.next()) {

                Object[] objeto = {rs.getInt(1),
                    rs.getString(2), rs.getString(3)};

                tablaModelo.addRow(objeto);
            }

            rs.close();
            System.out.println("RS cerrado");
            dlgCate.getTblCategorias().setModel(tablaModelo);
            dlgCate.getLblRegistros().setText("Total de categorias " + tablaModelo.getRowCount());

        } catch (SQLException ex) {
            System.out.println("Error al intentar obtener los datos del RS: " + ex.getMessage());
        }
    }

    /**
     * Valida que los campos no estén vacíos
     *
     * @return
     */
    private boolean validacionCa() {

        if (dlgCate.getTxtNombreCatReg().getText().isEmpty()) {

            dlgCate.getTxtNombreCatReg().setBackground(Color.red);

            return false;
        } else if (dlgCate.getTxtDescripReg().getText().isEmpty()) {

            dlgCate.getTxtDescripReg().setBackground(Color.red);

            return false;
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (e.getSource() == dlgCate.getTxtNombreCatReg()) {

            char letra = e.getKeyChar();

            if (Character.isDigit(letra)) {
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
