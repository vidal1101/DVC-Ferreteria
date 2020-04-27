
package Vista;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class DlgModificarDatos extends javax.swing.JDialog {

    /**
     * Creates new form dlgMoficarDatos
     */
    public DlgModificarDatos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDesc = new javax.swing.JLabel();
        txtCantiStock = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDescAplicar = new javax.swing.JTextField();
        txtCantidadAgregar = new javax.swing.JTextField();
        btnConfirmarCambios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Descuento ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 100, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel2.setText("Cantidad Stock");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 30));

        txtDesc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDesc.setText("0");
        jPanel1.add(txtDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 40, 30));

        txtCantiStock.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCantiStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCantiStock.setText("0");
        jPanel1.add(txtCantiStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 40, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel3.setText("Desc.  Aplicar");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 120, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel4.setText("Cant Agregar");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 110, 30));
        jPanel1.add(txtDescAplicar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 60, 30));
        jPanel1.add(txtCantidadAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 60, 30));

        btnConfirmarCambios.setText("Confirmar");
        jPanel1.add(btnConfirmarCambios, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 100, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 190));

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
            java.util.logging.Logger.getLogger(DlgModificarDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgModificarDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgModificarDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgModificarDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgModificarDatos dialog = new DlgModificarDatos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnConfirmarCambios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtCantiStock;
    private javax.swing.JTextField txtCantidadAgregar;
    private javax.swing.JLabel txtDesc;
    private javax.swing.JTextField txtDescAplicar;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JLabel getTxtCantiStock() {
        return txtCantiStock;
    }

    public javax.swing.JTextField getTxtCantidadAgregar() {
        return txtCantidadAgregar;
    }

    public javax.swing.JLabel getTxtDesc() {
        return txtDesc;
    }

    public javax.swing.JTextField getTxtDescAplicar() {
        return txtDescAplicar;
    }

    public javax.swing.JButton getBtnConfirmarCambios() {
        return btnConfirmarCambios;
    }
}
