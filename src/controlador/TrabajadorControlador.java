package controlador;

import Vista.DlgTrabajadores;
import Vista.dlgPrincipal;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaClass.ClassTrabajador;
import modelo.trabajadorModelo;

/**
 *
 * @author Maria, Carlos y Vidal
 */
public class TrabajadorControlador implements ActionListener, WindowListener, KeyListener {

    private dlgPrincipal principal;
    private DlgTrabajadores dlgtrab;
    private ClassTrabajador trabajador;
    private trabajadorModelo trabModelo;
    DefaultTableModel modeloTrab;
    private int opc;

    public TrabajadorControlador(dlgPrincipal principal, DlgTrabajadores dlgtrab, ClassTrabajador trabajador, int opc, trabajadorModelo trabModelo) {
        this.modeloTrab = new DefaultTableModel();
        this.principal = principal;
        this.dlgtrab = dlgtrab;
        this.trabajador = trabajador;
        this.trabModelo = trabModelo;
        this.opc = opc;
        this.principal.getBtnTrabajadores().addActionListener(this);
        this.dlgtrab.getBtnGuardarT().addActionListener(this);
        this.dlgtrab.getBtnInsertarT().addActionListener(this);
        this.dlgtrab.getBtnModificarT().addActionListener(this);
        this.dlgtrab.getBtnEliminarT().addActionListener(this);
        this.dlgtrab.getBtnLimpiarT().addActionListener(this);
        this.dlgtrab.getBtnCancelarT().addActionListener(this);
    }

    /**
     * Limpia los datos del registro
     */
    public void clear() {
        dlgtrab.getTxtCedulaT().setText("");
        dlgtrab.getTxtEmailT().setText("");
        dlgtrab.getTxtNombreT().setText("");
        dlgtrab.getTxtTelefonoT().setText("");
        dlgtrab.getCmbPuestoT().setSelectedIndex(0);

    }

    public void inciarVista(String title) {
        dlgtrab.setTitle(title);
        dlgtrab.setLocationRelativeTo(null);
        dlgtrab.getPantrabajador().setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == principal.getBtnTrabajadores()) {
            this.inciarVista("Trabajadores");
            this.dlgtrab.setVisible(true);
        }

        if (e.getSource() == dlgtrab.getBtnInsertarT()) {
            this.opc = 1;
            this.dlgtrab.getPantrabajador().setSelectedIndex(1);
        }

        if (e.getSource() == dlgtrab.getBtnLimpiarT()) {
            this.clear();
        }

        if (e.getSource() == dlgtrab.getBtnGuardarT()) {

            trabajador.setCedulaTrab(Integer.parseInt(dlgtrab.getTxtCedulaT().getText()));
            trabajador.setNombreTrab(dlgtrab.getTxtNombreT().getText());
            trabajador.setPuesto(String.valueOf(dlgtrab.getCmbPuestoT().getSelectedItem()));
            trabajador.setTelefonoTrab(dlgtrab.getTxtTelefonoT().getText());
            trabajador.setEmailTrab(dlgtrab.getTxtEmailT().getText());
            trabajador.setAbministrador(abministrador(dlgtrab.getCmbPuestoT().getSelectedIndex()));

            if (opc == 1) {
                if (trabModelo.insertarTrabajador(trabajador)) {
                    JOptionPane.showMessageDialog(dlgtrab, "Se inserto con Exito");
                    this.clear();
                    // No se va a caer, porque todo está medido, hasta el mínimo detalle.
                    this.mostrartabla(this.trabModelo.mostrarTrabajadores());
                    this.dlgtrab.getPantrabajador().setSelectedIndex(0);

                } else {
                    JOptionPane.showMessageDialog(dlgtrab, "Usuario ya existente");
                    this.clear();
                }
            } else {
                //el metodo de modificar
            }
        }
    }

    /**
     * Verifica si es administrador
     *
     * @param indice
     * @return
     */
    public boolean abministrador(int indice) {
        if (indice == 1) {
            return true;
        }
        return false;
    }

    /**
     * Recibe los datos desde la tabla y los imprime en la tabla
     *
     * @param rs
     */
    public void mostrartabla(ResultSet rs) {
        // Títulos
        String[] title = {"Cedula", "Nombre", "Puesto", "Email", "Telefono"};
        modeloTrab = new DefaultTableModel(null, title);

        try {

            while (rs.next()) {
                this.trabajador = new ClassTrabajador(rs.getInt(1),
                        rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getBoolean(6));

                Object[] objeto = {trabajador.getCedulaTrab(), trabajador.getNombreTrab(),
                    trabajador.getEmailTrab(), trabajador.getPuesto(), trabajador.getTelefonoTrab(),
                    trabajador.getAbministrador()};
                modeloTrab.addRow(objeto);
            }

            dlgtrab.getTblTrabajadores().setModel(modeloTrab);

        } catch (Exception e) {
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
