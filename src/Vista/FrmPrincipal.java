
package Vista;

/**
 *
 * @author Dixiana, Carlos y Vidal
 */
public class FrmPrincipal extends javax.swing.JFrame {
    
    public FrmPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAyuda = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnCaja = new javax.swing.JButton();
        btnCategorias = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnInventario = new javax.swing.JButton();
        btnTrabajadores = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnFacturas = new javax.swing.JButton();
        mnbBarraPrincipal = new javax.swing.JMenuBar();
        mnTrabajadores = new javax.swing.JMenu();
        mnCategorias = new javax.swing.JMenu();
        mnInventario = new javax.swing.JMenu();
        mnHistorial = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal");

        jPanel1.setBackground(new java.awt.Color(131, 182, 231));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setToolTipText("");

        btnAyuda.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        btnAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/informacion (2).png"))); // NOI18N
        btnAyuda.setText("Ayuda");
        btnAyuda.setBorderPainted(false);
        btnAyuda.setContentAreaFilled(false);
        btnAyuda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAyuda.setDefaultCapable(false);
        btnAyuda.setFocusPainted(false);
        btnAyuda.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/informacion (3).png"))); // NOI18N

        btnConfiguracion.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        btnConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/engranaje.png"))); // NOI18N
        btnConfiguracion.setText("Configuracion");
        btnConfiguracion.setBorderPainted(false);
        btnConfiguracion.setContentAreaFilled(false);
        btnConfiguracion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfiguracion.setDefaultCapable(false);
        btnConfiguracion.setFocusPainted(false);
        btnConfiguracion.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/engranaje (1).png"))); // NOI18N

        btnCalendario.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        btnCalendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calendario.png"))); // NOI18N
        btnCalendario.setText("Calendario");
        btnCalendario.setBorderPainted(false);
        btnCalendario.setContentAreaFilled(false);
        btnCalendario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCalendario.setDefaultCapable(false);
        btnCalendario.setFocusPainted(false);
        btnCalendario.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calendario (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 268, Short.MAX_VALUE)
                .addComponent(btnAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBackground(new java.awt.Color(135, 185, 222));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCaja.setBackground(new java.awt.Color(255, 255, 255));
        btnCaja.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/efectivo.png"))); // NOI18N
        btnCaja.setText("Caja ");
        btnCaja.setBorderPainted(false);
        btnCaja.setContentAreaFilled(false);
        btnCaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCaja.setDefaultCapable(false);
        btnCaja.setFocusPainted(false);
        btnCaja.setOpaque(true);
        jPanel2.add(btnCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 310, 80));

        btnCategorias.setBackground(new java.awt.Color(255, 255, 255));
        btnCategorias.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCategorias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/categoria.png"))); // NOI18N
        btnCategorias.setText("Categorias");
        btnCategorias.setBorderPainted(false);
        btnCategorias.setContentAreaFilled(false);
        btnCategorias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCategorias.setDefaultCapable(false);
        btnCategorias.setFocusPainted(false);
        btnCategorias.setOpaque(true);
        jPanel2.add(btnCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 310, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoDvcdieño1.jpeg"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 80));

        btnInventario.setBackground(new java.awt.Color(255, 255, 255));
        btnInventario.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/proveedor.png"))); // NOI18N
        btnInventario.setText("Inventario ");
        btnInventario.setBorderPainted(false);
        btnInventario.setContentAreaFilled(false);
        btnInventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInventario.setDefaultCapable(false);
        btnInventario.setFocusPainted(false);
        btnInventario.setOpaque(true);
        jPanel2.add(btnInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, 320, 80));

        btnTrabajadores.setBackground(new java.awt.Color(255, 255, 255));
        btnTrabajadores.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTrabajadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cuenta.png"))); // NOI18N
        btnTrabajadores.setText("Trabajadores");
        btnTrabajadores.setBorderPainted(false);
        btnTrabajadores.setContentAreaFilled(false);
        btnTrabajadores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTrabajadores.setDefaultCapable(false);
        btnTrabajadores.setFocusPainted(false);
        btnTrabajadores.setOpaque(true);
        jPanel2.add(btnTrabajadores, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 320, -1));

        btnProveedores.setBackground(new java.awt.Color(255, 255, 255));
        btnProveedores.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnProveedores.setText("Proveedores");
        btnProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(btnProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 310, 70));

        btnClientes.setBackground(new java.awt.Color(255, 255, 255));
        btnClientes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, 320, 70));

        btnFacturas.setBackground(new java.awt.Color(255, 255, 255));
        btnFacturas.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnFacturas.setText("Facturas");
        btnFacturas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(btnFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 390, 300, 70));

        mnbBarraPrincipal.setBackground(new java.awt.Color(131, 182, 231));

        mnTrabajadores.setText("Trabajadores");
        mnbBarraPrincipal.add(mnTrabajadores);

        mnCategorias.setText("Categorias");
        mnbBarraPrincipal.add(mnCategorias);

        mnInventario.setText("Inventario");
        mnbBarraPrincipal.add(mnInventario);

        mnHistorial.setText("Historial");
        mnbBarraPrincipal.add(mnHistorial);

        jMenuItem4.setText("jMenuItem4");
        jMenu6.add(jMenuItem4);

        mnbBarraPrincipal.add(jMenu6);

        setJMenuBar(mnbBarraPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
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
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FrmPrincipal dialog = new FrmPrincipal();
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
    private javax.swing.JButton btnAyuda;
    public javax.swing.JButton btnCaja;
    public javax.swing.JButton btnCalendario;
    public javax.swing.JButton btnCategorias;
    private javax.swing.JButton btnClientes;
    public javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnFacturas;
    public javax.swing.JButton btnInventario;
    private javax.swing.JButton btnProveedores;
    public javax.swing.JButton btnTrabajadores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JMenu mnCategorias;
    private javax.swing.JMenu mnHistorial;
    private javax.swing.JMenu mnInventario;
    private javax.swing.JMenu mnTrabajadores;
    private javax.swing.JMenuBar mnbBarraPrincipal;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JButton getBtnTrabajadores() {
        return btnTrabajadores;
    }
    public javax.swing.JButton getBtnAyuda() {
        return btnAyuda;
    }

    public javax.swing.JButton getBtnCaja() {
        return btnCaja;
    }

    public javax.swing.JButton getBtnCalendario() {
        return btnCalendario;
    }

    public javax.swing.JButton getBtnCategorias() {
        return btnCategorias;
    }

    public javax.swing.JButton getBtnClientes() {
        return btnClientes;
    }

    public javax.swing.JButton getBtnConfiguracion() {
        return btnConfiguracion;
    }

    public javax.swing.JButton getBtnFacturas() {
        return btnFacturas;
    }

    public javax.swing.JButton getBtnInventario() {
        return btnInventario;
    }

    public javax.swing.JButton getBtnProveedores() {
        return btnProveedores;
    }

    public javax.swing.JMenu getMnCategorias() {
        return mnCategorias;
    }

    public javax.swing.JMenu getMnHistorial() {
        return mnHistorial;
    }

    public javax.swing.JMenu getMnInventario() {
        return mnInventario;
    }

    public javax.swing.JMenu getMnTrabajadores() {
        return mnTrabajadores;
    }    
}

