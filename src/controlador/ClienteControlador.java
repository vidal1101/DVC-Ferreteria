/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vista.DlgCliente;
import Vista.FrmPrincipal;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaClass.ClassCliente;
import modelo.ClienteModelo;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class ClienteControlador implements ActionListener, KeyListener {

    private FrmPrincipal principal;
    private DlgCliente dlgCli;
    private ClassCliente cliente;
    private ClienteModelo cliModelo;
    private DefaultTableModel modelCli;
    private int opc;
    private boolean selecciono;

    public ClienteControlador(FrmPrincipal principal, DlgCliente dlgCli) {

        this.modelCli = new DefaultTableModel();
        this.principal = principal;
        this.dlgCli = dlgCli;
        this.cliente = new ClassCliente();
        this.cliModelo = new ClienteModelo();

        this.opc = 0;
        this.selecciono = false;
        this.dlgCli.getBtnGuardarCli().addActionListener(this);
        this.dlgCli.getBtnInsertarCli().addActionListener(this);
        this.dlgCli.getBtnModificarCli().addActionListener(this);
        this.dlgCli.getBtnEliminarCli().addActionListener(this);
        this.dlgCli.getBtnLimpiarCli().addActionListener(this);
        this.dlgCli.getBtnCancelarCli().addActionListener(this);
        this.dlgCli.getBtnBuscar().addActionListener(this);
        this.dlgCli.getBtnSleccinar().addActionListener(this);
        this.dlgCli.getBtnCancelar().addActionListener(this);

        this.dlgCli.getTxtNombreCli().addKeyListener(this);
        this.dlgCli.getTxtCedulaCli().addKeyListener(this);
        this.dlgCli.getTxtTelefonoCli().addKeyListener(this);
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

        if (!dlgCli.getTitle().equals("Seleccionar Cliente")) {

            this.dlgCli.getPnlOpciones().remove(dlgCli.getBtnSleccinar());
            this.dlgCli.getPnlOpciones().remove(dlgCli.getBtnCancelar());
        }

        dlgCli.getPanCliente().setSelectedIndex(0);
        System.out.println("Abriendo registros de trabajadores");
        this.mostrartabla(this.cliModelo.mostrarClientes());
        this.dlgCli.getPanCliente().setEnabledAt(1, false);
        this.dlgCli.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // SELECCIONA AL CLIENTE
        if (e.getSource() == dlgCli.getBtnSleccinar()) {
            System.out.println("selecciona");

            int fila = dlgCli.getTblCliente().getSelectedRow();
            if (fila != -1) {

                cliente = new ClassCliente(Integer.parseInt(dlgCli.getTblCliente().getValueAt(fila, 0).toString()),
                        dlgCli.getTblCliente().getValueAt(fila, 1).toString(),
                        dlgCli.getTblCliente().getValueAt(fila, 2).toString(),
                        dlgCli.getTblCliente().getValueAt(fila, 3).toString());
                this.selecciono = true;
                dlgCli.dispose();

            } else {
                JOptionPane.showMessageDialog(dlgCli, "Seleccione un cliente");
            }

        } else if (e.getSource() == dlgCli.getBtnCancelar()) {

            System.out.println("cancela");
            dlgCli.dispose();
        }

        // Aquí solamente comparamos el evento con los componentes del dialog y NO DEL PRINCIPAL
        if (e.getSource() == dlgCli.getBtnInsertarCli()) {
            this.clear();
            this.dlgCli.getPanCliente().setEnabledAt(1, true);
            this.dlgCli.getPanCliente().setEnabledAt(0, false);
            this.dlgCli.getTxtCedulaCli().setEditable(true);
            this.opc = 1;
            this.dlgCli.getTxtCedulaCli().setEnabled(true);
            this.dlgCli.getPanCliente().setSelectedIndex(1);

        } else if (e.getSource() == dlgCli.getBtnLimpiarCli()) {

            this.clear();
        } else if (e.getSource() == dlgCli.getBtnGuardarCli()) {

            if (validacionCli()) {
                cliente = new ClassCliente();
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
            }
        } else if (e.getSource() == dlgCli.getBtnEliminarCli()) {

            // Obtenemos la cédula del registro a eliminar
            int fila = dlgCli.getTblCliente().getSelectedRow();

            if (fila > -1) {

                int cedula = Integer.parseInt(dlgCli.getTblCliente().getValueAt(fila, 0) + "");
                int opcion = JOptionPane.showConfirmDialog(dlgCli, "Desea eliminar al cliente con cédula "
                        + cedula + "?", "Eliminar", JOptionPane.YES_NO_OPTION);

                // Si la opción de eliminar fue SI, eliminamos
                if (opcion == JOptionPane.YES_OPTION) {

                    String respuesta = cliModelo.eliminarCliente(4, cedula);

                    if (respuesta.equals("ELIMINADO")) {

                        JOptionPane.showMessageDialog(dlgCli, "Cliente eliminada exitosamente.");
                        this.mostrartabla(this.cliModelo.mostrarClientes());

                    } else if (respuesta.equals("CON REGISTROS")) {

                        JOptionPane.showMessageDialog(dlgCli, "No se puede eliminar este cliente. \n"
                                + "Hay facturas enlazadas a este cliente");

                    } else {
                        JOptionPane.showMessageDialog(dlgCli, "Ha habido un error al intentar eliminarse.");
                    }
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

        } else if (e.getSource() == dlgCli.getBtnBuscar()) {
            buscar();
            dlgCli.getTxtBuscarCli().setText("");
        }
    }

    /**
     * Valida que los campos no estén vacíos
     *
     * @return
     */
    private boolean validacionCli() {
        if (dlgCli.getTxtNombreCli().getText().isEmpty()) {

            dlgCli.getTxtNombreCli().setBackground(Color.red);

            return false;
        } else if (dlgCli.getTxtCedulaCli().getText().isEmpty()) {

            dlgCli.getTxtCedulaCli().setBackground(Color.red);

            return false;
        } else if (dlgCli.getTxtTelefonoCli().getText().isEmpty()) {
            dlgCli.getTxtTelefonoCli().setBackground(Color.red);

            return false;

        } else if (dlgCli.getTxtEmailCli().getText().isEmpty()) {
            dlgCli.getTxtEmailCli().setBackground(Color.red);

            return false;
        }
        return true;
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

    private void buscar() {

        try {

            DefaultTableModel modeloTabla = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndez) {
                    return false;
                }
            };

            String titulos[] = {"Cedula", "Nombre", "Telefono", "Email"};
            modeloTabla.setColumnIdentifiers(titulos);

            ResultSet rs = cliModelo.BuscarCliente(dlgCli.getTxtBuscarCli().getText());

            while (rs.next()) {

                Object nextElement[] = {rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4)};

                modeloTabla.addRow(nextElement);
            }

            rs.close();
            System.out.println("RS cerrado");
            dlgCli.getTblCliente().setModel(modeloTabla);
            dlgCli.getLblRegistrosCli().setText("Total de clientes: " + modeloTabla.getRowCount());

        } catch (SQLException ex) {
            System.out.println("Error al intentar obtener los datos del RS: " + ex.getMessage());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (e.getSource() == dlgCli.getTxtNombreCli()) {

            char letra = e.getKeyChar();

            if (Character.isDigit(letra)) {
                e.consume();
            }
        } else if (e.getSource() == dlgCli.getTxtCedulaCli()) {
            char letra = e.getKeyChar();

            if (!Character.isDigit(letra)) {
                e.consume();
            }

            if (dlgCli.getTxtCedulaCli().getText().length() >= 9) {
                e.consume();
            }
        } else if (e.getSource() == dlgCli.getTxtTelefonoCli()) {

            if (dlgCli.getTxtTelefonoCli().getText().length() >= 8) {
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

    public ClassCliente getCliente() {
        return cliente;
    }

    public boolean isSelecciono() {
        return selecciono;
    }

}
