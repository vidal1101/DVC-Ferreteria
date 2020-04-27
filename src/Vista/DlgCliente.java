package Vista;

import javax.swing.JFormattedTextField;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class DlgCliente extends javax.swing.JDialog {

   
    public DlgCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panCliente = new javax.swing.JTabbedPane();
        pnlUno = new javax.swing.JPanel();
        lblBuscarCli = new javax.swing.JLabel();
        txtBuscarCli = new javax.swing.JTextField();
        btnInsertarCli = new javax.swing.JButton();
        btnModificarCli = new javax.swing.JButton();
        btnEliminarCli = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        pnlOpciones = new javax.swing.JPanel();
        lblRegistrosCli = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnSleccinar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        pnlDos = new javax.swing.JPanel();
        pnlCuatro = new javax.swing.JPanel();
        lblNombreCli = new javax.swing.JLabel();
        lblCedulaCli = new javax.swing.JLabel();
        lblTelefonoCli = new javax.swing.JLabel();
        lblEmailCli = new javax.swing.JLabel();
        txtNombreCli = new javax.swing.JTextField();
        txtCedulaCli = new javax.swing.JTextField();
        txtEmailCli = new javax.swing.JTextField();
        txtTelefonoCli = new javax.swing.JFormattedTextField();
        pnlCinco = new javax.swing.JPanel();
        btnLimpiarCli = new javax.swing.JButton();
        btnGuardarCli = new javax.swing.JButton();
        btnCancelarCli = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Clientes");

        lblBuscarCli.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBuscarCli.setText("Buscar:");

        btnInsertarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/insertaP.png"))); // NOI18N
        btnInsertarCli.setText("Insertar");

        btnModificarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/modificarP.png"))); // NOI18N
        btnModificarCli.setText("Modificar");

        btnEliminarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/eliminarP.png"))); // NOI18N
        btnEliminarCli.setText("Eliminar");

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cédula", "Nombre", "Puesto", "Teléfono", "Email"
            }
        ));
        jScrollPane1.setViewportView(tblCliente);

        pnlOpciones.setBackground(new java.awt.Color(0, 102, 102));

        btnCancelar.setText("Cancelar");

        btnSleccinar.setText("Seleccionar");

        javax.swing.GroupLayout pnlOpcionesLayout = new javax.swing.GroupLayout(pnlOpciones);
        pnlOpciones.setLayout(pnlOpcionesLayout);
        pnlOpcionesLayout.setHorizontalGroup(
            pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblRegistrosCli, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSleccinar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlOpcionesLayout.setVerticalGroup(
            pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesLayout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(lblRegistrosCli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSleccinar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscarP.png"))); // NOI18N

        javax.swing.GroupLayout pnlUnoLayout = new javax.swing.GroupLayout(pnlUno);
        pnlUno.setLayout(pnlUnoLayout);
        pnlUnoLayout.setHorizontalGroup(
            pnlUnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUnoLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lblBuscarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtBuscarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnInsertarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnModificarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnEliminarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlUnoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addGap(50, 50, 50))
            .addComponent(pnlOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlUnoLayout.setVerticalGroup(
            pnlUnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUnoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlUnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar)
                    .addComponent(btnInsertarCli)
                    .addComponent(btnModificarCli)
                    .addComponent(btnEliminarCli)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblBuscarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnlOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panCliente.addTab("Principal", pnlUno);

        pnlDos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        pnlCuatro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreCli.setText("Nombre:");
        pnlCuatro.add(lblNombreCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblCedulaCli.setText("Cédula:");
        pnlCuatro.add(lblCedulaCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        lblTelefonoCli.setText("Teléfono:");
        pnlCuatro.add(lblTelefonoCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        lblEmailCli.setText("Email:");
        pnlCuatro.add(lblEmailCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));
        pnlCuatro.add(txtNombreCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 340, 30));
        pnlCuatro.add(txtCedulaCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 340, 30));
        pnlCuatro.add(txtEmailCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 340, 30));

        try {
            txtTelefonoCli.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("+506-####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        pnlCuatro.add(txtTelefonoCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 340, 30));

        pnlDos.add(pnlCuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 730, 270));

        pnlCinco.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout pnlCincoLayout = new javax.swing.GroupLayout(pnlCinco);
        pnlCinco.setLayout(pnlCincoLayout);
        pnlCincoLayout.setHorizontalGroup(
            pnlCincoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        pnlCincoLayout.setVerticalGroup(
            pnlCincoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        pnlDos.add(pnlCinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 800, 50));

        btnLimpiarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/limpiar.png"))); // NOI18N
        btnLimpiarCli.setText("Limpiar");
        pnlDos.add(btnLimpiarCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 130, 50));

        btnGuardarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardarPe.png"))); // NOI18N
        btnGuardarCli.setText("Guardar");
        pnlDos.add(btnGuardarCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, 120, 50));

        btnCancelarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cancelarP.png"))); // NOI18N
        btnCancelarCli.setText("Cancelar");
        pnlDos.add(btnCancelarCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 320, 120, 50));

        panCliente.addTab("Registro", pnlDos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panCliente)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panCliente)
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DlgCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DlgCliente dialog = new DlgCliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarCli;
    private javax.swing.JButton btnEliminarCli;
    private javax.swing.JButton btnGuardarCli;
    private javax.swing.JButton btnInsertarCli;
    private javax.swing.JButton btnLimpiarCli;
    private javax.swing.JButton btnModificarCli;
    private javax.swing.JButton btnSleccinar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscarCli;
    private javax.swing.JLabel lblCedulaCli;
    private javax.swing.JLabel lblEmailCli;
    private javax.swing.JLabel lblNombreCli;
    private javax.swing.JLabel lblRegistrosCli;
    private javax.swing.JLabel lblTelefonoCli;
    private javax.swing.JTabbedPane panCliente;
    private javax.swing.JPanel pnlCinco;
    private javax.swing.JPanel pnlCuatro;
    private javax.swing.JPanel pnlDos;
    private javax.swing.JPanel pnlOpciones;
    private javax.swing.JPanel pnlUno;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTextField txtBuscarCli;
    private javax.swing.JTextField txtCedulaCli;
    private javax.swing.JTextField txtEmailCli;
    private javax.swing.JTextField txtNombreCli;
    private javax.swing.JFormattedTextField txtTelefonoCli;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JButton getBtnCancelarCli() {
        return btnCancelarCli;
    }

    public void setBtnCancelarCli(javax.swing.JButton btnCancelarCli) {
        this.btnCancelarCli = btnCancelarCli;
    }

    public javax.swing.JButton getBtnEliminarCli() {
        return btnEliminarCli;
    }

    public void setBtnEliminarCli(javax.swing.JButton btnEliminarCli) {
        this.btnEliminarCli = btnEliminarCli;
    }

    public javax.swing.JButton getBtnGuardarCli() {
        return btnGuardarCli;
    }

    public void setBtnGuardarCli(javax.swing.JButton btnGuardarCli) {
        this.btnGuardarCli = btnGuardarCli;
    }

    public javax.swing.JButton getBtnInsertarCli() {
        return btnInsertarCli;
    }

    public void setBtnInsertarCli(javax.swing.JButton btnInsertarCli) {
        this.btnInsertarCli = btnInsertarCli;
    }

    public javax.swing.JButton getBtnLimpiarCli() {
        return btnLimpiarCli;
    }

    public void setBtnLimpiarCli(javax.swing.JButton btnLimpiarCli) {
        this.btnLimpiarCli = btnLimpiarCli;
    }

    public javax.swing.JButton getBtnModificarCli() {
        return btnModificarCli;
    }

    public void setBtnModificarCli(javax.swing.JButton btnModificarCli) {
        this.btnModificarCli = btnModificarCli;
    }

    public javax.swing.JLabel getLblRegistrosCli() {
        return lblRegistrosCli;
    }

    public void setLblRegistrosCli(javax.swing.JLabel lblRegistrosCli) {
        this.lblRegistrosCli = lblRegistrosCli;
    }

    public javax.swing.JTabbedPane getPanCliente() {
        return panCliente;
    }

    public void setPanCliente(javax.swing.JTabbedPane panCliente) {
        this.panCliente = panCliente;
    }

    public javax.swing.JPanel getPnlUno() {
        return pnlUno;
    }

    public void setPnlUno(javax.swing.JPanel pnlUno) {
        this.pnlUno = pnlUno;
    }

    public javax.swing.JTable getTblCliente() {
        return tblCliente;
    }

    public void setTblCliente(javax.swing.JTable tblCliente) {
        this.tblCliente = tblCliente;
    }

    public javax.swing.JTextField getTxtBuscarCli() {
        return txtBuscarCli;
    }

    public void setTxtBuscarCli(javax.swing.JTextField txtBuscarCli) {
        this.txtBuscarCli = txtBuscarCli;
    }

    public javax.swing.JTextField getTxtCedulaCli() {
        return txtCedulaCli;
    }

    public void setTxtCedulaCli(javax.swing.JTextField txtCedulaCli) {
        this.txtCedulaCli = txtCedulaCli;
    }

    public javax.swing.JTextField getTxtEmailCli() {
        return txtEmailCli;
    }

    public void setTxtEmailCli(javax.swing.JTextField txtEmailCli) {
        this.txtEmailCli = txtEmailCli;
    }

    public javax.swing.JTextField getTxtNombreCli() {
        return txtNombreCli;
    }

    public void setTxtNombreCli(javax.swing.JTextField txtNombreCli) {
        this.txtNombreCli = txtNombreCli;
    }

    public JFormattedTextField getTxtTelefonoCli() {
        return txtTelefonoCli;
    }

    public void setTxtTelefonoCli(JFormattedTextField txtTelefonoCli) {
        this.txtTelefonoCli = txtTelefonoCli;
    }

    public javax.swing.JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(javax.swing.JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public javax.swing.JButton getBtnCancelar() {
        return btnCancelar;
    }

    public javax.swing.JButton getBtnSleccinar() {
        return btnSleccinar;
    }

    public javax.swing.JPanel getPnlOpciones() {
        return pnlOpciones;
    }
}