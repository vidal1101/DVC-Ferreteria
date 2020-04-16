/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vista.FrmPrincipal;
import Vista.DlgProveedores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaClass.ClassProveedor;
import modelo.ProveedorModelo;

/**
 *
 * @author Dixi, Carlos y Vidal
 */
public class ProveedoresControlador implements ActionListener, WindowListener, KeyListener {

    private FrmPrincipal principal;
    private DlgProveedores dlgprov;
    private ClassProveedor proveedor;
    private ProveedorModelo provModelo;
    DefaultTableModel modeloProv;
    private int opc;

    public ProveedoresControlador(FrmPrincipal principal, DlgProveedores dlgprov, ClassProveedor proveedor,
            ProveedorModelo provModelo) {

        this.modeloProv = new DefaultTableModel();
        this.principal = principal;
        this.dlgprov = dlgprov;
        this.proveedor = proveedor;
        this.provModelo = provModelo;
        this.opc = 0;
       // this.principal.getBtnProveedores().addActionListener(this);
        this.dlgprov.getBtnGuardar().addActionListener(this);
        this.dlgprov.getBtnInsertar().addActionListener(this);
        this.dlgprov.getBtnEditar().addActionListener(this);
        this.dlgprov.getBtnEliminar().addActionListener(this);
        this.dlgprov.getBtnLimpiar().addActionListener(this);
        this.dlgprov.getBtnCancelar().addActionListener(this);
    }

    /**
     * Limpia los datos del registro
     */
    public void clear() {
        dlgprov.getTxtCedula().setText("");
        dlgprov.getTxtEmail().setText("");
        dlgprov.getTxtNombre().setText("");
        dlgprov.getTxtTelefono().setText("");
        dlgprov.getTxtDireccion().setText("");

    }

    /**
     * INICIA el dialog
     *
     * @param title
     */
    public void inciarVista(String title) {
        dlgprov.setTitle(title);
        dlgprov.setLocationRelativeTo(null);
        dlgprov.getPanProveedores().setSelectedIndex(0);
        System.out.println("Abriendo registros de Proveedores");
        this.mostrartabla(this.provModelo.mostrarProveedores());
        this.dlgprov.getPanProveedores().setEnabledAt(1, false);
        this.dlgprov.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == dlgprov.getBtnInsertar()) {
            this.clear();
            this.dlgprov.getTxtCedula().setEditable(true);
            this.dlgprov.getPanProveedores().setEnabledAt(1, true);
            this.dlgprov.getPanProveedores().setEnabledAt(0, false);
            this.opc = 1;
            this.dlgprov.getPanProveedores().setSelectedIndex(1);

        } else if (e.getSource() == dlgprov.getBtnLimpiar()) {
            this.clear();
            
        } else if (e.getSource() == dlgprov.getBtnGuardar()) {

            proveedor.setIdProvedor(Integer.parseInt(dlgprov.getTxtCedula().getText()));
            proveedor.setNombreProv(dlgprov.getTxtNombre().getText());
            proveedor.setTelefonoProv(dlgprov.getTxtTelefono().getText());
            proveedor.setEmailProv(dlgprov.getTxtEmail().getText());
            proveedor.setDireccionProv(dlgprov.getTxtDireccion().getText());

            if (opc == 1) {//insertar un nuevo Proveedor 

                if (provModelo.insertarTrabajador(proveedor)) {
                    JOptionPane.showMessageDialog(dlgprov, "Se inserto con Exito");
                    this.clear();
                    // No se va a caer, porque todo está medido, hasta el mínimo detalle.
                    this.mostrartabla(this.provModelo.mostrarProveedores());
                    this.dlgprov.getPanProveedores().setSelectedIndex(0);
                    this.dlgprov.getPanProveedores().setEnabledAt(1, false);

                } else {
                    JOptionPane.showMessageDialog(dlgprov, "Usuario ya existente");
                    this.clear();
                }

            } else {//modificar un proveedor 

                if (this.provModelo.modificarProveedor(proveedor)) {
                    JOptionPane.showMessageDialog(dlgprov, "Se Modifico el trabajador");
                    this.mostrartabla(this.provModelo.mostrarProveedores());
                    this.dlgprov.getPanProveedores().setSelectedIndex(0);
                    this.dlgprov.getPanProveedores().setEnabledAt(1, false);
                    this.dlgprov.getPanProveedores().setEnabledAt(0, true);

                } else {
                    JOptionPane.showMessageDialog(dlgprov, "Error al Modificar ");
                }

            }

        } else if (e.getSource() == dlgprov.getBtnCancelar()) {
            this.clear();
            this.dlgprov.getPanProveedores().setEnabledAt(1, false);
            this.dlgprov.getPanProveedores().setEnabledAt(0, true);
            this.dlgprov.getPanProveedores().setSelectedIndex(0);

        } else if (e.getSource() == dlgprov.getBtnEliminar()) {

            int fila = dlgprov.getTblProveedores().getSelectedRow();

            if (fila != -1) {

                int idProv = Integer.parseInt(dlgprov.getTblProveedores().getValueAt(fila, 0) + "");
                int opcion = JOptionPane.showConfirmDialog(dlgprov, "Desea eliminar al trabajador con cédula "
                        + idProv + "?", "Eliminar", JOptionPane.YES_NO_OPTION);

                // Si la opción de eliminar fue SI, eliminamos
                if (opcion == JOptionPane.YES_OPTION) {
                    provModelo.eliminarProveedores(idProv);
                    // No se va a caer, porque todo está medido, hasta el mínimo detalle.
                    JOptionPane.showMessageDialog(dlgprov, "Eliminado");
                    this.mostrartabla(this.provModelo.mostrarProveedores());
                }

            } else {
                JOptionPane.showMessageDialog(dlgprov, "Selecciones un Proveedor para eliminar");
            }

        } else if (e.getSource() == dlgprov.getBtnEditar()) {

            if (dlgprov.getTblProveedores().getSelectedRow() != -1) {

                this.dlgprov.getPanProveedores().setSelectedIndex(1);
                this.dlgprov.getPanProveedores().setEnabledAt(0, false);
                int file = dlgprov.getTblProveedores().getSelectedRow();

                this.dlgprov.getTxtCedula().setText(dlgprov.getTblProveedores().getValueAt(file, 0).toString());
                this.dlgprov.getTxtCedula().setEditable(false);
                this.dlgprov.getTxtNombre().setText(dlgprov.getTblProveedores().getValueAt(file, 1).toString());
                this.dlgprov.getTxtTelefono().setText(dlgprov.getTblProveedores().getValueAt(file, 2).toString());
                this.dlgprov.getTxtEmail().setText(dlgprov.getTblProveedores().getValueAt(file, 3).toString());
                this.dlgprov.getTxtDireccion().setText(dlgprov.getTblProveedores().getValueAt(file, 4).toString());

                this.opc = 2;
                this.dlgprov.getPanProveedores().setSelectedIndex(1);
                this.dlgprov.getPanProveedores().setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(dlgprov, "Debe Seleccionar Fila");
            }

        }

    }

    /**
     * Recibe los datos desde la tabla y los imprime en la tabla de los
     * proveedores
     *
     * @param rs
     */
    public void mostrartabla(ResultSet rs) {
        // Títulos
        String[] title = {"Cedula", "Nombre", "Telefono", "Email", "Direccion"};
        modeloProv = new DefaultTableModel(null, title);

        try {

            while (rs.next()) {
                this.proveedor = new ClassProveedor(rs.getInt(1),
                        rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5));

                Object[] objeto = {proveedor.getIdProvedor(), proveedor.getNombreProv(),
                    proveedor.getTelefonoProv(), proveedor.getDireccionProv(), proveedor.getEmailProv()};
                modeloProv.addRow(objeto);
            }

            System.out.println("Cerrando ResultSet");
            rs.close();
            dlgprov.getTblProveedores().setModel(modeloProv);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------");
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
