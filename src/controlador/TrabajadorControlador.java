package controlador;

import Vista.DlgTrabajadores;
import Vista.FrmInicioSesion;
import Vista.FrmPrincipal;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaClass.ClassTrabajador;
import org.apache.commons.codec.digest.DigestUtils;
import modelo.TrabajadorModelo;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class TrabajadorControlador implements ActionListener, KeyListener {

    private FrmPrincipal principal;
    private FrmInicioSesion entradaLogin;
    private DlgTrabajadores dlgtrab;
    private ClassTrabajador trabajador;
    private TrabajadorModelo trabModelo;
    private DefaultTableModel modeloTrab;
    private int opc;

    public TrabajadorControlador(FrmPrincipal principal, ClassTrabajador trabajadores) {

        this.modeloTrab = new DefaultTableModel();
        this.principal = principal;
        this.dlgtrab = new DlgTrabajadores(principal, true);
        this.trabModelo = new TrabajadorModelo();
        this.trabajador = trabajadores;
        this.opc = 0;

        this.principal.getBtnTrabajadores().addActionListener(this);
        this.dlgtrab.getBtnGuardarT().addActionListener(this);
        this.dlgtrab.getBtnInsertarT().addActionListener(this);
        this.dlgtrab.getBtnModificarT().addActionListener(this);
        this.dlgtrab.getBtnEliminarT().addActionListener(this);
        this.dlgtrab.getBtnLimpiarT().addActionListener(this);
        this.dlgtrab.getBtnCancelarT().addActionListener(this);
        this.dlgtrab.getBtnBuscar().addActionListener(this);

        this.dlgtrab.getTxtNombreT().addKeyListener(this);
        this.dlgtrab.getTxtCedulaT().addKeyListener(this);
        this.dlgtrab.getTxtTelefono().addKeyListener(this);
    }

    public TrabajadorControlador(FrmInicioSesion sesionVentana) {

        this.entradaLogin = sesionVentana;
        this.trabModelo = new TrabajadorModelo();
        this.trabajador = new ClassTrabajador();
    }

    /**
     * Iniciar sesión
     *
     * @return
     */
    public boolean iniciarSesion() {

        try {

            int cedula = Integer.parseInt(entradaLogin.getTxtUsuario().getText());
            String cocinada = encriptarContrasenia(String.valueOf(entradaLogin.getTxtContrasenia().getPassword()));

            // Traemos el usuario que se ubica en la BD
            ResultSet rs = trabModelo.iniciarSesion(cedula, cocinada);

            if (rs.getInt(1) != 0) {

                this.trabajador = new ClassTrabajador(rs.getInt(1),
                        rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getBoolean(6));

                System.out.println("Sesion exitosa");
                rs.close();

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

        } catch (NumberFormatException es) {
            JOptionPane.showMessageDialog(entradaLogin, "Formato de cédula incorrecto\n"
                    + "No coloque guiones o espacios", "Cédula incorrecta",
                    JOptionPane.ERROR_MESSAGE);

            return false;
        } catch (SQLException ex) {
            System.out.println("Error al intentar extraer los datos del trabajador: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Limpia los datos del registro
     *
     */
    public void clear() {
        dlgtrab.getTxtCedulaT().setText("");
        dlgtrab.getTxtEmail().setText("");
        dlgtrab.getTxtNombreT().setText("");
        dlgtrab.getTxtTelefono().setText("");
        dlgtrab.getCmbPuestoT().setSelectedIndex(0);
        dlgtrab.getTxtContrsenia().setText("");
        dlgtrab.getTxtContrsenia2().setText("");
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
            this.dlgtrab.getTxtCedulaT().setEditable(true);
            this.dlgtrab.getPantrabajador().setSelectedIndex(1);

        } else if (e.getSource() == dlgtrab.getBtnLimpiarT()) {

            this.clear();
        } else if (e.getSource() == dlgtrab.getBtnGuardarT()) {

            // GUARDAR TRABAJADOR
            if (camposVacios()) {

                this.trabajador = new ClassTrabajador();
                trabajador.setCedulaTrab(Integer.parseInt(dlgtrab.getTxtCedulaT().getText()));
                trabajador.setNombreTrab(dlgtrab.getTxtNombreT().getText());
                trabajador.setPuesto(String.valueOf(dlgtrab.getCmbPuestoT().getSelectedItem()));
                trabajador.setTelefonoTrab(dlgtrab.getTxtTelefono().getText());
                trabajador.setEmailTrab(dlgtrab.getTxtEmail().getText());
                trabajador.setAbministrador(abministrador(dlgtrab.getCmbPuestoT().getSelectedIndex()));

                // Encriptamos la contraseña si ambas coinciden
                if (String.valueOf(dlgtrab.getTxtContrsenia().getPassword()).equals(
                        String.valueOf(dlgtrab.getTxtContrsenia2().getPassword())
                )) {

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

                            JOptionPane.showMessageDialog(dlgtrab, "Se Modificó el trabajador");
                            this.mostrartabla(this.trabModelo.mostrarTrabajadores());
                            this.dlgtrab.getPantrabajador().setSelectedIndex(0);
                            this.dlgtrab.getPantrabajador().setEnabled(true);

                        } else {
                            JOptionPane.showMessageDialog(dlgtrab, "Error al Modificar ");
                        }
                    }

                } else {

                    dlgtrab.getTxtContrsenia().setText("");
                    dlgtrab.getTxtContrsenia2().setText("");
                    JOptionPane.showMessageDialog(dlgtrab, "Las contraseñas no coinciden", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(dlgtrab, "Rellene todos los campos para registrar");
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
                    String respuesta = trabModelo.eliminarTrabajador(1, cedula);

                    if (respuesta.equals("ELIMINADO")) {
                        // No se va a caer, porque todo está medido, hasta el mínimo detalle.
                        JOptionPane.showMessageDialog(dlgtrab, "Eliminado");
                        this.mostrartabla(this.trabModelo.mostrarTrabajadores());

                    } else if (respuesta.equals("CON REGISTROS")) {

                        JOptionPane.showMessageDialog(dlgtrab, "No se puede eliminar este trabajador. \n"
                                + "Hay facturas enlazadas a este trabajador");

                    } else {
                        JOptionPane.showMessageDialog(dlgtrab, "Ha habido un error al intentar eliminarse.");
                    }
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
                this.dlgtrab.getTxtEmail().setText(dlgtrab.getTblTrabajadores().getValueAt(file, 3).toString());
                this.dlgtrab.getTxtTelefono().setText(dlgtrab.getTblTrabajadores().getValueAt(file, 4).toString());

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

        } else if (e.getSource() == dlgtrab.getBtnBuscar()) {
            buscar();
            dlgtrab.getTxtBuscarT().setText("");
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
     * Recibe los datos desde la tabla y los imprime en la tabla de la GUI
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

                Object[] objeto = {rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getBoolean(6)};

                modeloTrab.addRow(objeto);
            }

            System.out.println("Cerrando ResultSet");
            rs.close();
            dlgtrab.getTblTrabajadores().setModel(modeloTrab);

        } catch (SQLException e) {
            System.out.println("No existen datos");
            dlgtrab.getTblTrabajadores().setModel(modeloTrab);
            System.out.println("--------------------------------------");
        }
    }

    /**
     * Verifica si hay campos vacíos
     *
     */
    private boolean camposVacios() {

        if (dlgtrab.getTxtCedulaT().getText().isEmpty()) {
            return false;
        } else if (dlgtrab.getTxtNombreT().getText().isEmpty()) {
            return false;
        } else if (dlgtrab.getTxtContrsenia().getPassword().length < 4) {
            return false;
        } else if (dlgtrab.getTxtContrsenia2().getPassword().length < 4 ) {
            return false;
        } else if (dlgtrab.getCmbPuestoT().getSelectedIndex() == 0) {
            return false;
        } else if (dlgtrab.getTxtEmail().getText().isEmpty()) {
            return false;
        } else if (dlgtrab.getTxtTelefono().getText().isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Método que permite buscar al trabajador <br>
     * por medio de su cédula y nombre
     *
     */
    private void buscar() {

        try {

            DefaultTableModel modeloTabla = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndez) {
                    return false;
                }
            };

            String titulos[] = {"Cedula", "Nombre", "Puesto", "Email", "Telefono"};
            modeloTabla.setColumnIdentifiers(titulos);

            ResultSet rs = trabModelo.BuscarTrabajador(dlgtrab.getTxtBuscarT().getText());

            while (rs.next()) {

                Object nextElement[] = {rs.getInt(1),
                    rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getBoolean(6)};

                modeloTabla.addRow(nextElement);
            }

            rs.close();
            System.out.println("RS cerrado");
            dlgtrab.getTblTrabajadores().setModel(modeloTabla);
            dlgtrab.getLblRegistrosT().setText("Total de trabajadores: " + modeloTabla.getRowCount());

        } catch (SQLException ex) {
            System.out.println("Error al intentar obtener los datos del RS: " + ex.getMessage());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (e.getSource() == dlgtrab.getTxtNombreT()) {

            char letra = e.getKeyChar();

            if (Character.isDigit(letra)) {
                e.consume();
            }
        } else if (e.getSource() == dlgtrab.getTxtCedulaT()) {
            char letra = e.getKeyChar();

            if (!Character.isDigit(letra)) {
                e.consume();
            }
            if (dlgtrab.getTxtCedulaT().getText().length() >= 9) {
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

    public ClassTrabajador getTrabajador() {
        return trabajador;
    }

}
