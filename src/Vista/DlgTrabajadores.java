package Vista;

import javax.swing.JFormattedTextField;
import javax.swing.JTabbedPane;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class DlgTrabajadores extends javax.swing.JDialog {

    /**
     * Creates new form DlgCajeros
     */
    public DlgTrabajadores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panTrabajador = new javax.swing.JTabbedPane();
        pnl1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblTrabajadores = new javax.swing.JTable();
        lblBuscar = new javax.swing.JLabel();
        txtBuscarT = new javax.swing.JTextField();
        btnInsertarT = new javax.swing.JButton();
        btnModificarT = new javax.swing.JButton();
        btnEliminarT = new javax.swing.JButton();
        lblTrabajador = new javax.swing.JLabel();
        pnl2 = new javax.swing.JPanel();
        lblRegistrosT = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        pnl3 = new javax.swing.JPanel();
        lblTrabajador2 = new javax.swing.JLabel();
        pnl4 = new javax.swing.JPanel();
        pnl5 = new javax.swing.JPanel();
        lblNombreT = new javax.swing.JLabel();
        lblCedulaT = new javax.swing.JLabel();
        lblTelefonoT = new javax.swing.JLabel();
        lblEmailT = new javax.swing.JLabel();
        cmbPuestoT = new javax.swing.JComboBox<>();
        txtNombreT = new javax.swing.JTextField();
        txtCedulaT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        lblContrasenia = new javax.swing.JLabel();
        txtContrsenia = new javax.swing.JPasswordField();
        txtContrsenia2 = new javax.swing.JPasswordField();
        lblVeriContra = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JFormattedTextField();
        btnLimpiarT = new javax.swing.JButton();
        btnGuardarT = new javax.swing.JButton();
        btnCancelarT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Cajeros");

        pnl1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TblTrabajadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cédula", "Nombre", "Puesto", "Teléfono", "Email"
            }
        ));
        jScrollPane1.setViewportView(TblTrabajadores);

        pnl1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 780, 250));

        lblBuscar.setText("Buscar:");
        pnl1.add(lblBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        pnl1.add(txtBuscarT, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 320, 30));

        btnInsertarT.setText("Insertar");
        pnl1.add(btnInsertarT, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 100, 40));

        btnModificarT.setText("Modificar");
        pnl1.add(btnModificarT, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 100, 40));

        btnEliminarT.setText("Eliminar");
        pnl1.add(btnEliminarT, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 100, 40));

        lblTrabajador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trabajador.png"))); // NOI18N
        pnl1.add(lblTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 360, 140, 140));

        pnl2.setBackground(new java.awt.Color(0, 153, 0));
        pnl2.setBorder(new javax.swing.border.MatteBorder(null));
        pnl2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnl2.add(lblRegistrosT, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 18, 302, -1));

        pnl1.add(pnl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 820, 50));
        pnl1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 60, 40));

        panTrabajador.addTab("Principal", pnl1);

        pnl3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTrabajador2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trabajador.png"))); // NOI18N
        pnl3.add(lblTrabajador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 370, 140, -1));

        pnl4.setBackground(new java.awt.Color(0, 153, 0));
        pnl4.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout pnl4Layout = new javax.swing.GroupLayout(pnl4);
        pnl4.setLayout(pnl4Layout);
        pnl4Layout.setHorizontalGroup(
            pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 818, Short.MAX_VALUE)
        );
        pnl4Layout.setVerticalGroup(
            pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        pnl3.add(pnl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 820, 50));

        pnl5.setBorder(new javax.swing.border.MatteBorder(null));
        pnl5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombreT.setText("Nombre:");
        pnl5.add(lblNombreT, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 70, 30));

        lblCedulaT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCedulaT.setText("Cédula:");
        pnl5.add(lblCedulaT, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 70, 30));

        lblTelefonoT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTelefonoT.setText("Teléfono:");
        pnl5.add(lblTelefonoT, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 90, 30));

        lblEmailT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmailT.setText("Email:");
        pnl5.add(lblEmailT, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 70, 30));

        cmbPuestoT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Puesto", "Administrador", "Cajero" }));
        pnl5.add(cmbPuestoT, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 180, -1));
        pnl5.add(txtNombreT, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 310, 30));
        pnl5.add(txtCedulaT, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 310, 30));
        pnl5.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 310, 30));

        lblContrasenia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblContrasenia.setText("Contraseña:");
        pnl5.add(lblContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 90, 30));
        pnl5.add(txtContrsenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 310, 30));
        pnl5.add(txtContrsenia2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 310, 30));

        lblVeriContra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVeriContra.setText("Re escriba la contraseña:");
        pnl5.add(lblVeriContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 200, 30));

        try {
            txtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("+506-####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        pnl5.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 109, 310, 30));

        pnl3.add(pnl5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 750, 320));

        btnLimpiarT.setText("Limpiar");
        pnl3.add(btnLimpiarT, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 110, 40));

        btnGuardarT.setText("Guardar");
        pnl3.add(btnGuardarT, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 360, 110, 40));

        btnCancelarT.setText("Cancelar");
        pnl3.add(btnCancelarT, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 360, 110, 40));

        panTrabajador.addTab("Registro", pnl3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panTrabajador)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panTrabajador)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgTrabajadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgTrabajadores dialog = new DlgTrabajadores(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TblTrabajadores;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelarT;
    private javax.swing.JButton btnEliminarT;
    private javax.swing.JButton btnGuardarT;
    private javax.swing.JButton btnInsertarT;
    private javax.swing.JButton btnLimpiarT;
    private javax.swing.JButton btnModificarT;
    private javax.swing.JComboBox<String> cmbPuestoT;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCedulaT;
    private javax.swing.JLabel lblContrasenia;
    private javax.swing.JLabel lblEmailT;
    private javax.swing.JLabel lblNombreT;
    private javax.swing.JLabel lblRegistrosT;
    private javax.swing.JLabel lblTelefonoT;
    private javax.swing.JLabel lblTrabajador;
    private javax.swing.JLabel lblTrabajador2;
    private javax.swing.JLabel lblVeriContra;
    private javax.swing.JTabbedPane panTrabajador;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnl3;
    private javax.swing.JPanel pnl4;
    private javax.swing.JPanel pnl5;
    private javax.swing.JTextField txtBuscarT;
    private javax.swing.JTextField txtCedulaT;
    private javax.swing.JPasswordField txtContrsenia;
    private javax.swing.JPasswordField txtContrsenia2;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombreT;
    private javax.swing.JFormattedTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JButton getBtnCancelarT() {
        return btnCancelarT;
    }

    public void setBtnCancelarT(javax.swing.JButton btnCancelarT) {
        this.btnCancelarT = btnCancelarT;
    }

    public javax.swing.JButton getBtnEliminarT() {
        return btnEliminarT;
    }

    public void setBtnEliminarT(javax.swing.JButton btnEliminarT) {
        this.btnEliminarT = btnEliminarT;
    }

    public javax.swing.JButton getBtnGuardarT() {
        return btnGuardarT;
    }

    public void setBtnGuardarT(javax.swing.JButton btnGuardarT) {
        this.btnGuardarT = btnGuardarT;
    }

    public javax.swing.JButton getBtnInsertarT() {
        return btnInsertarT;
    }

    public void setBtnInsertarT(javax.swing.JButton btnInsertarT) {
        this.btnInsertarT = btnInsertarT;
    }

    public javax.swing.JButton getBtnLimpiarT() {
        return btnLimpiarT;
    }

    public void setBtnLimpiarT(javax.swing.JButton btnLimpiarT) {
        this.btnLimpiarT = btnLimpiarT;
    }

    public javax.swing.JButton getBtnModificarT() {
        return btnModificarT;
    }

    public void setBtnModificarT(javax.swing.JButton btnModificarT) {
        this.btnModificarT = btnModificarT;
    }

    public javax.swing.JComboBox<String> getCmbPuestoT() {
        return cmbPuestoT;
    }

    public void setCmbPuestoT(javax.swing.JComboBox<String> cmbPuestoT) {
        this.cmbPuestoT = cmbPuestoT;
    }

    public javax.swing.JLabel getLblRegistrosT() {
        return lblRegistrosT;
    }

    public void setLblRegistrosT(javax.swing.JLabel lblRegistrosT) {
        this.lblRegistrosT = lblRegistrosT;
    }

    public javax.swing.JTextField getTxtBuscarT() {
        return txtBuscarT;
    }

    public javax.swing.JTextField getTxtCedulaT() {
        return txtCedulaT;
    }

    public javax.swing.JTextField getTxtTelefono() {
        return txtTelefono;
    }


    public javax.swing.JTextField getTxtNombreT() {
        return txtNombreT;
    }

    public void setTxtNombreT(javax.swing.JTextField txtNombreT) {
        this.txtNombreT = txtNombreT;
    }

    public javax.swing.JTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(javax.swing.JTextField txtTelefonoT) {
        this.txtEmail = (JFormattedTextField) txtTelefonoT;
    }

    public javax.swing.JPanel getPnl1() {
        return pnl1;
    }

    public void setPnl1(javax.swing.JPanel pnl1) {
        this.pnl1 = pnl1;
    }

    public JTabbedPane getPantrabajador() {
        return panTrabajador;
    }

    public void setPanTrabajador(JTabbedPane jTabbedPane1) {
        this.panTrabajador = jTabbedPane1;
    }

    public javax.swing.JTable getTblTrabajadores() {
        return TblTrabajadores;
    }

    public void setTblTrabajadores(javax.swing.JTable tblTrabajadores) {
        this.TblTrabajadores = tblTrabajadores;
    }

    public javax.swing.JPasswordField getTxtContrsenia() {
        return txtContrsenia;
    }

    public javax.swing.JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(javax.swing.JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public javax.swing.JPasswordField getTxtContrsenia2() {
        return txtContrsenia2;
    }
}
