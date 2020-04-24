/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author User
 */
public class FrmVentas extends javax.swing.JDialog {

    /**
     * Creates new form Frame
     */
    public FrmVentas(javax.swing.JFrame frame, boolean modal) {

        super(frame, modal);
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductosAgr = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTotalPagar = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDireccionEnt = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnCliente = new javax.swing.JButton();
        btnQuitarPro = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        btnQuitarTodo = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnAnadirProd = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        btnCalender = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        lblRegistrosDetalles = new javax.swing.JLabel();
        lblNomTrab = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        menTrabajadores = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menVentas = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        menHistorial = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Caja");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblProductosAgr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tblProductosAgr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID de producto", "Producto", "Cantidad", "Precio", "Descuento"
            }
        ));
        jScrollPane2.setViewportView(tblProductosAgr);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 810, 190));

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Subtotal:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 120, 20));

        txtSubTotal.setEditable(false);
        jPanel3.add(txtSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 160, 30));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Total descuento:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 30));

        txtDescuento.setEditable(false);
        jPanel3.add(txtDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 160, 30));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Total a pagar:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 120, 30));

        txtTotalPagar.setEditable(false);
        jPanel3.add(txtTotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 160, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 290, 350, 170));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles de compra"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Cedula Cliente :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 100, 30));

        txtIdCliente.setEditable(false);
        txtIdCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel2.add(txtIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 110, 30));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Dirección de entrega:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 160, 20));

        txtDireccionEnt.setColumns(20);
        txtDireccionEnt.setRows(5);
        jScrollPane1.setViewportView(txtDireccionEnt);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 350, 90));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Cliente:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 60, 30));

        txtNombreCliente.setEditable(false);
        txtNombreCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel2.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 130, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 440, 180));

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCliente.setText("Selec. Cliente");
        btnCliente.setToolTipText("");
        jPanel4.add(btnCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 140, 40));

        btnQuitarPro.setText("Quitar Prod");
        jPanel4.add(btnQuitarPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 140, 40));

        btnFacturar.setText("Facturar");
        jPanel4.add(btnFacturar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 140, 40));

        btnQuitarTodo.setText("Quitar Todo");
        jPanel4.add(btnQuitarTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 140, 40));

        btnSalir.setText("Salir");
        jPanel4.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 140, 40));

        btnAnadirProd.setText("Añadir  Prod");
        btnAnadirProd.setToolTipText("");
        jPanel4.add(btnAnadirProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 140, 40));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 130, 160, 330));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Fecha:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 45, 50, 20));

        txtFecha.setEditable(false);
        txtFecha.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        getContentPane().add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 40, 130, 30));

        jPanel1.setBackground(new java.awt.Color(131, 182, 231));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setToolTipText("");
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton7.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/informacion (2).png"))); // NOI18N
        jButton7.setText("Ayuda");
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setDefaultCapable(false);
        jButton7.setFocusPainted(false);
        jButton7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/informacion (3).png"))); // NOI18N
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 150, 40));

        jButton8.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/engranaje.png"))); // NOI18N
        jButton8.setText("Configuracion");
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setDefaultCapable(false);
        jButton8.setFocusPainted(false);
        jButton8.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/engranaje (1).png"))); // NOI18N
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 40));

        btnCalender.setBackground(new java.awt.Color(131, 182, 231));
        btnCalender.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        btnCalender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calendario.png"))); // NOI18N
        btnCalender.setText("Calendario");
        btnCalender.setBorderPainted(false);
        btnCalender.setContentAreaFilled(false);
        btnCalender.setDefaultCapable(false);
        btnCalender.setFocusPainted(false);
        btnCalender.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calendario (1).png"))); // NOI18N
        jPanel1.add(btnCalender, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 190, 40));

        btnImprimir.setText("Imprimir");
        btnImprimir.setFocusable(false);
        jPanel1.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 140, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 1050, 60));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Hora:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 85, 40, 20));

        txtHora.setEditable(false);
        txtHora.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        getContentPane().add(txtHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 80, 130, 30));

        lblRegistrosDetalles.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblRegistrosDetalles.setText("Registros");
        getContentPane().add(lblRegistrosDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, 320, 30));

        lblNomTrab.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNomTrab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNomTrab.setText("Nombre del cajero");
        getContentPane().add(lblNomTrab, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 540, 40));

        jMenu1.setText("Categorias");

        jMenuItem1.setText("herramientas ");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Hogar ");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Jardin");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        menTrabajadores.setText("Cajeros");

        jMenuItem5.setText("Mi Perfil");
        menTrabajadores.add(jMenuItem5);

        jMenuItem6.setText("OtrosPerfiles");
        menTrabajadores.add(jMenuItem6);

        jMenuBar1.add(menTrabajadores);

        jMenu3.setText("Inventario");
        jMenuBar1.add(jMenu3);

        menVentas.setText("Ventas");

        jMenuItem7.setText("Ventas Diaria");
        menVentas.add(jMenuItem7);

        jMenuItem8.setText("Ventas por Semana");
        menVentas.add(jMenuItem8);

        jMenuBar1.add(menVentas);

        menHistorial.setText("Historial");

        jMenuItem9.setText("Facturas Anuladas");
        menHistorial.add(jMenuItem9);

        jMenuBar1.add(menHistorial);

        jMenuItem4.setText("jMenuItem4");
        jMenu6.add(jMenuItem4);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Ayuda");
        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
               FrmVentas dialog = new FrmVentas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAnadirProd;
    public javax.swing.JButton btnCalender;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnFacturar;
    public javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnQuitarPro;
    private javax.swing.JButton btnQuitarTodo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton7;
    public javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNomTrab;
    private javax.swing.JLabel lblRegistrosDetalles;
    private javax.swing.JMenu menHistorial;
    private javax.swing.JMenu menTrabajadores;
    private javax.swing.JMenu menVentas;
    private javax.swing.JTable tblProductosAgr;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextArea txtDireccionEnt;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotalPagar;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JButton getBtnAnadirProd() {
        return btnAnadirProd;
    }

    public javax.swing.JButton getBtnCalender() {
        return btnCalender;
    }

    public javax.swing.JButton getBtnCliente() {
        return btnCliente;
    }

    public javax.swing.JButton getBtnFacturar() {
        return btnFacturar;
    }

    public javax.swing.JButton getBtnImprimir() {
        return btnImprimir;
    }

    public javax.swing.JButton getBtnQuitarPro() {
        return btnQuitarPro;
    }

    public javax.swing.JButton getBtnQuitarTodo() {
        return btnQuitarTodo;
    }

    public javax.swing.JButton getBtnSalir() {
        return btnSalir;
    }

    public javax.swing.JTextField getTxtDescuento() {
        return txtDescuento;
    }

    public javax.swing.JTextArea getTxtDireccionEnt() {
        return txtDireccionEnt;
    }

    public javax.swing.JTextField getTxtFecha() {
        return txtFecha;
    }

    public javax.swing.JTextField getTxtHora() {
        return txtHora;
    }

    public javax.swing.JTextField getTxtIdCliente() {
        return txtIdCliente;
    }

    public javax.swing.JTextField getTxtNombreCliente() {
        return txtNombreCliente;
    }

    public javax.swing.JTextField getTxtSubTotal() {
        return txtSubTotal;
    }

    public javax.swing.JTextField getTxtTotalPagar() {
        return txtTotalPagar;
    }

    public javax.swing.JTable getTblProductosAgr() {
        return tblProductosAgr;
    }

    public javax.swing.JLabel getLblRegistrosDetalles() {
        return lblRegistrosDetalles;
    }

    public javax.swing.JLabel getLblNomTrab() {
        return lblNomTrab;
    }
    
    
}
