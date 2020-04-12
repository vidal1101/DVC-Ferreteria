/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vista.DlgCliente;
import Vista.FrmPrincipal;
import Vista.dlgPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaClass.ClassCliente;
import modelo.ClienteModelo;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dixiana, Carlos, Vidal
 */
public class ClienteControlador implements ActionListener, WindowListener, KeyListener {

    private FrmPrincipal principal;
    private DlgCliente dlgCli;
    private ClassCliente cliente;
    private ClienteModelo cliModelo;
    DefaultTableModel modelCli;
    private int opc;

    public ClienteControlador(FrmPrincipal principal, DlgCliente dlgCli, ClassCliente cliente,
            ClienteModelo cliModelo) {

        this.modelCli = new DefaultTableModel();
        this.principal = principal;
        this.dlgCli = dlgCli;
        this.cliente = cliente;
        this.cliModelo = cliModelo;
        this.opc = 0;
//        this.principal.getBtnClientes().addActionListener(this);
        this.dlgCli.getBtnGuardarCli().addActionListener(this);
        this.dlgCli.getBtnInsertarCli().addActionListener(this);
        this.dlgCli.getBtnModificarCli().addActionListener(this);
        this.dlgCli.getBtnEliminarCli().addActionListener(this);
        this.dlgCli.getBtnLimpiarCli().addActionListener(this);
        this.dlgCli.getBtnCancelarCli().addActionListener(this);
    }

    /**
     * Limpia los datos del registro
     */
    public void clear() {
        dlgCli.getTxtCedulaCli().setText("");
        dlgCli.getTxtNombreCli().setText("");
        dlgCli.getTxtTelefonoCli().setText("");
        dlgCli.getTxtEmailCli().setText("");
    }

    /**
     * INICIA el dialog
     *
     * @param title
     */
    public void inciarVista(String title) {
        dlgCli.setTitle(title);
        dlgCli.setLocationRelativeTo(null);
        dlgCli.getPanCliente().setSelectedIndex(0);
        System.out.println("Abriendo registros de trabajadores");
        this.mostrartabla(this.cliModelo.mostrarClientes());
        this.dlgCli.getPanCliente().setEnabledAt(1, false);
        this.dlgCli.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Aquí solamente comparamos el evento con los componentes del dialog y NO DEL PRINCIPAL
        if (e.getSource() == dlgCli.getBtnInsertarCli()) {
            this.clear();
            this.dlgCli.getPanCliente().setEnabledAt(1, true);
            this.dlgCli.getPanCliente().setEnabledAt(0, false);
            this.opc = 1;
            this.dlgCli.getTxtCedulaCli().setEnabled(true);
            this.dlgCli.getPanCliente().setSelectedIndex(1);

        } else if (e.getSource() == dlgCli.getBtnLimpiarCli()) {

            this.clear();
        } else if (e.getSource() == dlgCli.getBtnGuardarCli()) {

            cliente.setCedulaCli(Integer.parseInt(dlgCli.getTxtCedulaCli().getText()));
            cliente.setNombreCli(dlgCli.getTxtNombreCli().getText());
            cliente.setTelefonoCli(dlgCli.getTxtTelefonoCli().getText());
            cliente.setEmailCli(dlgCli.getTxtEmailCli().getText());

            // Revisa si va a editar o guardar
            if (opc == 1) {
                if (cliModelo.insertarCliente(cliente)) {
                    JOptionPane.showMessageDialog(dlgCli, "Se inserto con Exito");
                    this.clear();
                    // No se va a caer, porque todo está medido, hasta el mínimo detalle.
                    this.mostrartabla(this.cliModelo.mostrarClientes());
                    this.dlgCli.getPanCliente().setSelectedIndex(0);

                } else {
                    JOptionPane.showMessageDialog(dlgCli, "Usuario ya existente");
                    this.clear();
                }

            } else {
                //el metodo de modificar
                if (this.cliModelo.modificarCliente(cliente)) {
                    JOptionPane.showMessageDialog(dlgCli, "Se Modifico el Cliente");
                    this.mostrartabla(this.cliModelo.mostrarClientes());
                    this.dlgCli.getPanCliente().setSelectedIndex(0);
                    this.dlgCli.getPanCliente().setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(dlgCli, "Error al Modificar ");
                }

            }
        } //Si ha pulsado eliminar
        else if (e.getSource() == dlgCli.getBtnEliminarCli()) {

            // Obtenemos la cédula del registro a eliminar
            int fila = dlgCli.getTblCliente().getSelectedRow();

            if (fila > -1) {

                int cedula = Integer.parseInt(dlgCli.getTblCliente().getValueAt(fila, 0) + "");
                int opcion = JOptionPane.showConfirmDialog(dlgCli, "Desea eliminar al cliente con cédula "
                        + cedula + "?", "Eliminar", JOptionPane.YES_NO_OPTION);

                // Si la opción de eliminar fue SI, eliminamos
                if (opcion == JOptionPane.YES_OPTION) {
                    cliModelo.eliminarCliente(cedula);
                    // No se va a caer, porque todo está medido, hasta el mínimo detalle.
                    JOptionPane.showMessageDialog(dlgCli, "Eliminado");
                    this.mostrartabla(this.cliModelo.mostrarClientes());
                }

            } else {
                JOptionPane.showMessageDialog(dlgCli, "Seleccione un cliente para eliminar");
            }

        } else if (e.getSource() == dlgCli.getBtnModificarCli()) {

            if (dlgCli.getTblCliente().getSelectedRow() != -1) {

                this.dlgCli.getPanCliente().setSelectedIndex(1);
                this.dlgCli.getPanCliente().setEnabledAt(0, false);
                int file = dlgCli.getTblCliente().getSelectedRow();

                this.dlgCli.getTxtCedulaCli().setText(dlgCli.getTblCliente().getValueAt(file, 0).toString());
                this.dlgCli.getTxtCedulaCli().setEditable(false);
                this.dlgCli.getTxtNombreCli().setText(dlgCli.getTblCliente().getValueAt(file, 1).toString());
                this.dlgCli.getTxtTelefonoCli().setText(dlgCli.getTblCliente().getValueAt(file, 2).toString());
                this.dlgCli.getTxtEmailCli().setText(dlgCli.getTblCliente().getValueAt(file, 3).toString());

                this.opc = 2;
                this.dlgCli.getPanCliente().setSelectedIndex(1);
                this.dlgCli.getPanCliente().setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(dlgCli, "Debe Seleccionar Fila");
            }

        } else if (e.getSource() == dlgCli.getBtnCancelarCli()) {
            this.clear();
            this.dlgCli.getPanCliente().setEnabledAt(1, false);
            this.dlgCli.getPanCliente().setEnabledAt(0, true);
            this.dlgCli.getPanCliente().setSelectedIndex(0);
            this.dlgCli.getPanCliente().setEnabled(true);
        }
    }

    /**
     * Recibe los datos desde la tabla y los imprime en la tabla
     *
     * @param rs
     */
    public void mostrartabla(ResultSet rs) {
        // Títulos
        String[] title = {"Cedula", "Nombre", "Telefono", "Email"};
        modelCli = new DefaultTableModel(null, title) {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        try {

            while (rs.next()) {
                this.cliente = new ClassCliente(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4));

                Object[] objeto = {cliente.getCedulaCli(), cliente.getNombreCli(),
                    cliente.getTelefonoCli(), cliente.getEmailCli()};
                modelCli.addRow(objeto);
            }

            System.out.println("Cerrando ResultSet");
            rs.close();
            dlgCli.getTblCliente().setModel(modelCli);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------");
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
