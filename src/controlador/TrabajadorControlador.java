package controlador;

import Vista.DlgTrabajadores;
import Vista.FrmInicioSesion;
import Vista.FrmPrincipal;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaClass.ClassTrabajador;
import org.apache.commons.codec.digest.DigestUtils;
import modelo.TrabajadorModelo;

/**
 *
 * @author Maria, Carlos y Vidal
 */
public class TrabajadorControlador implements ActionListener, WindowListener, KeyListener {

    private FrmPrincipal principal;
    private FrmInicioSesion entradaLogin;
    private DlgTrabajadores dlgtrab;
    private ClassTrabajador trabajador;
    private TrabajadorModelo trabModelo;
    private DefaultTableModel modeloTrab;
    private int opc;

    public TrabajadorControlador(FrmPrincipal principal, DlgTrabajadores dlgtrab, ClassTrabajador trabajador,
            TrabajadorModelo trabModelo) {

        this.modeloTrab = new DefaultTableModel();
        this.principal = principal;
        this.dlgtrab = dlgtrab;
        this.trabajador = trabajador;
        this.trabModelo = trabModelo;
        this.opc = 0;

        this.principal.getBtnTrabajadores().addActionListener(this);
        this.dlgtrab.getBtnGuardarT().addActionListener(this);
        this.dlgtrab.getBtnInsertarT().addActionListener(this);
        this.dlgtrab.getBtnModificarT().addActionListener(this);
        this.dlgtrab.getBtnEliminarT().addActionListener(this);
        this.dlgtrab.getBtnLimpiarT().addActionListener(this);
        this.dlgtrab.getBtnCancelarT().addActionListener(this);
    }

    public TrabajadorControlador(FrmInicioSesion entradaLogin, FrmPrincipal principal) {

        this.trabModelo = new TrabajadorModelo();
        this.entradaLogin = entradaLogin;
        this.principal = principal;
    }

    /**
     * Inicia sesión
     *
     * @param cedula
     * @param contrasenia
     * @return
     */
    public boolean iniciarSesion() {

        try {
            
            int cedula = Integer.parseInt(entradaLogin.getTxtUsuario().getText());
            String cocinada = encriptarContrasenia(String.valueOf(entradaLogin.getTxtContrasenia().getPassword()));

            System.out.println("cocinada "+cocinada);
            if (trabModelo.iniciarSesion(cedula, cocinada)) {

                principal.setVisible(true);
                return true;

            } else {
                JOptionPane.showMessageDialog(entradaLogin, "Cédula y Contraseña incorrectas.\n"
                        + "\tVuelva a ingresarlos.", "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(entradaLogin, "Ingrese su contraseña y cédula", "Campos vacíos.",
                    JOptionPane.ERROR_MESSAGE);

            System.out.println(e.getCause());
            return false;

        } catch (NumberFormatException es){
            JOptionPane.showMessageDialog(entradaLogin, "Formato de cédula incorrecto", "Cédula incorrecta",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

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

    /**
     * INICIA el dialog
     *
     * @param title
     */
    public void inciarVista(String title) {
        dlgtrab.setTitle(title);
        dlgtrab.setLocationRelativeTo(null);
        dlgtrab.getPantrabajador().setSelectedIndex(0);
        System.out.println("Abriendo registros de trabajadores");
        this.mostrartabla(this.trabModelo.mostrarTrabajadores());
        this.dlgtrab.getPantrabajador().setEnabledAt(1, false);

        this.dlgtrab.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Aquí solamente comparamos el evento con los componentes del dialog y NO DEL PRINCIPAL
        if (e.getSource() == dlgtrab.getBtnInsertarT()) {
            this.clear();
            this.dlgtrab.getPantrabajador().setEnabledAt(1, true);
            this.dlgtrab.getPantrabajador().setEnabledAt(0, false);
            this.opc = 1;
            this.dlgtrab.getTxtCedulaT().setEnabled(true);
            this.dlgtrab.getPantrabajador().setSelectedIndex(1);

        } else if (e.getSource() == dlgtrab.getBtnLimpiarT()) {

            this.clear();
        } else if (e.getSource() == dlgtrab.getBtnGuardarT()) {
            // GUARDAR TRABAJADOR
            
            trabajador.setCedulaTrab(Integer.parseInt(dlgtrab.getTxtCedulaT().getText()));
            trabajador.setNombreTrab(dlgtrab.getTxtNombreT().getText());
            trabajador.setPuesto(String.valueOf(dlgtrab.getCmbPuestoT().getSelectedItem()));
            trabajador.setTelefonoTrab(dlgtrab.getTxtTelefonoT().getText());
            trabajador.setEmailTrab(dlgtrab.getTxtEmailT().getText());
            trabajador.setAbministrador(abministrador(dlgtrab.getCmbPuestoT().getSelectedIndex()));

            // Encriptamos la contraseña
            trabajador.setContrasenia(encriptarContrasenia(String.valueOf(dlgtrab.getTxtContrsenia())));

            // Revisa si va a editar o guardar
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
                if (this.trabModelo.modificarTrabajador(trabajador)) {
                    JOptionPane.showMessageDialog(dlgtrab, "Se Modifico el trabajador");
                    this.mostrartabla(this.trabModelo.mostrarTrabajadores());
                    this.dlgtrab.getPantrabajador().setSelectedIndex(0);
                    this.dlgtrab.getPantrabajador().setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(dlgtrab, "Error al Modificar ");
                }

            }
        } //Si ha pulsado eliminar
        else if (e.getSource() == dlgtrab.getBtnEliminarT()) {

            // Obtenemos la cédula del registro a eliminar
            int fila = dlgtrab.getTblTrabajadores().getSelectedRow();

            if (fila > -1) {

                int cedula = Integer.parseInt(dlgtrab.getTblTrabajadores().getValueAt(fila, 0) + "");
                int opcion = JOptionPane.showConfirmDialog(dlgtrab, "Desea eliminar al trabajador con cédula "
                        + cedula + "?", "Eliminar", JOptionPane.YES_NO_OPTION);

                // Si la opción de eliminar fue SI, eliminamos
                if (opcion == JOptionPane.YES_OPTION) {
                    trabModelo.eliminarTrabajadores(cedula);
                    // No se va a caer, porque todo está medido, hasta el mínimo detalle.
                    JOptionPane.showMessageDialog(dlgtrab, "Eliminado");
                    this.mostrartabla(this.trabModelo.mostrarTrabajadores());
                }

            } else {
                JOptionPane.showMessageDialog(dlgtrab, "Selecciones un trabajador para eliminar");
            }

        } else if (e.getSource() == dlgtrab.getBtnModificarT()) {
            // Editar

            if (dlgtrab.getTblTrabajadores().getSelectedRow() != -1) {

                this.dlgtrab.getPantrabajador().setSelectedIndex(1);
                this.dlgtrab.getPantrabajador().setEnabledAt(0, false);
                int file = dlgtrab.getTblTrabajadores().getSelectedRow();

                this.dlgtrab.getTxtCedulaT().setText(dlgtrab.getTblTrabajadores().getValueAt(file, 0).toString());
                this.dlgtrab.getTxtCedulaT().setEditable(false);
                this.dlgtrab.getTxtNombreT().setText(dlgtrab.getTblTrabajadores().getValueAt(file, 1).toString());
                this.dlgtrab.getCmbPuestoT().setSelectedItem(dlgtrab.getTblTrabajadores().getValueAt(file, 2).toString());
                this.dlgtrab.getTxtEmailT().setText(dlgtrab.getTblTrabajadores().getValueAt(file, 3).toString());
                this.dlgtrab.getTxtTelefonoT().setText(dlgtrab.getTblTrabajadores().getValueAt(file, 4).toString());

                this.opc = 2;
                this.dlgtrab.getPantrabajador().setSelectedIndex(1);
                this.dlgtrab.getPantrabajador().setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(dlgtrab, "Debe Seleccionar Fila");
            }

        } else if (e.getSource() == dlgtrab.getBtnCancelarT()) {
            this.clear();
            this.dlgtrab.getPantrabajador().setEnabledAt(1, false);
            this.dlgtrab.getPantrabajador().setEnabledAt(0, true);
            this.dlgtrab.getPantrabajador().setSelectedIndex(0);
            this.dlgtrab.getPantrabajador().setEnabled(true);
        }

    }

    /**
     * Se llama para encriptar la contraseña
     *
     * @param contrasenia
     */
    private String encriptarContrasenia(String contrasenia) {

        System.out.println("Encriptano contraseña");
        String encript = DigestUtils.md5Hex(contrasenia);
        System.out.println("Encriptada");
        return encript;
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
        modeloTrab = new DefaultTableModel(null, title) {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

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

            System.out.println("Cerrando ResultSet");
            rs.close();
            dlgtrab.getTblTrabajadores().setModel(modeloTrab);

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
