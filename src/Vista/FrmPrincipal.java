/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import controlador.CajaControladorVidal;
import controlador.CategoriaControlador;
import controlador.ClienteControlador;
import controlador.ProveedoresControlador;
import controlador.TrabajadorControlador;
import controlador.InventarioControlador;
import logicaClass.ClassCliente;
import logicaClass.ClassDetallesFactura;
import logicaClass.ClassProducto;
import logicaClass.ClassProveedor;
import logicaClass.ClassTrabajador;
import modelo.ClienteModelo;
import modelo.TrabajadorModelo;
import modelo.InventarioModelo;
import modelo.ProveedorModelo;

/**
 *
 * @author User
 */
public class FrmPrincipal extends javax.swing.JFrame {

    //Instancias
    private ClassTrabajador breteador;
    private TrabajadorModelo trabModelo;
    private DlgTrabajadores dlgTrab;
    private DlgCategorias dlgCateg;

    private ClassCliente cliente;
    private ClienteModelo cliModelo;
    private DlgCliente dlgCli;

    private ClassProveedor proveedor;
    private ProveedorModelo provModelo;
    private DlgProveedores dlgprov;

    private ClassProducto producto;
    private InventarioModelo invenModelo;
    private DlgInventario dlginve;
    
    private FrmVentas ventas;
    private ClassDetallesFactura detalleFact;
    
    public FrmPrincipal() {
        //super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);

        this.trabModelo = new TrabajadorModelo();
        this.breteador = new ClassTrabajador();

        this.dlgTrab = new DlgTrabajadores(null, true);
        this.dlgCateg = new DlgCategorias(null, true);

        this.cliModelo = new ClienteModelo();
        this.cliente = new ClassCliente();
        this.dlgCli = new DlgCliente(null, true);

        this.provModelo = new ProveedorModelo();
        this.proveedor = new ClassProveedor();
        this.dlgprov = new DlgProveedores(null, true);

        this.invenModelo = new InventarioModelo();
        this.producto = new ClassProducto();
        this.dlginve = new DlgInventario(null, true);
        
        this.ventas = new FrmVentas(null, true);
        this.detalleFact= new ClassDetallesFactura();

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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
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
        btnCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCajaActionPerformed(evt);
            }
        });
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
        btnCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriasActionPerformed(evt);
            }
        });
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
        btnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventarioActionPerformed(evt);
            }
        });
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
        btnTrabajadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrabajadoresActionPerformed(evt);
            }
        });
        jPanel2.add(btnTrabajadores, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 320, -1));

        btnProveedores.setBackground(new java.awt.Color(255, 255, 255));
        btnProveedores.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnProveedores.setText("Proveedores");
        btnProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });
        jPanel2.add(btnProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 310, 70));

        btnClientes.setBackground(new java.awt.Color(255, 255, 255));
        btnClientes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        jPanel2.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, 320, 70));

        btnFacturas.setBackground(new java.awt.Color(255, 255, 255));
        btnFacturas.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnFacturas.setText("Facturas");
        btnFacturas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturasActionPerformed(evt);
            }
        });
        jPanel2.add(btnFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 390, 300, 70));

        jMenuBar1.setBackground(new java.awt.Color(131, 182, 231));

        jMenu1.setText("Categorias");

        jMenuItem1.setText("herramientas ");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Hogar ");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Jardin");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("cajeros");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Inventario");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Ventas");

        jMenuItem5.setText("Ventas Diarias");
        jMenu4.add(jMenuItem5);

        jMenuItem6.setText("Ventas por Semana");
        jMenu4.add(jMenuItem6);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Historial");
        jMenuBar1.add(jMenu5);

        jMenuItem4.setText("jMenuItem4");
        jMenu6.add(jMenuItem4);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

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
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnTrabajadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrabajadoresActionPerformed

        TrabajadorControlador trabControl = new TrabajadorControlador(this, dlgTrab,
                breteador, trabModelo);
        trabControl.inciarVista("Trabajadores");

    }//GEN-LAST:event_btnTrabajadoresActionPerformed

    private void btnCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriasActionPerformed

        CategoriaControlador categControl = new CategoriaControlador(dlgCateg);
        categControl.inciarVista("Categorias");
    }//GEN-LAST:event_btnCategoriasActionPerformed

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed
        // TODO add your handling code here:
        ProveedoresControlador provCont = new ProveedoresControlador(this, dlgprov,
                proveedor, provModelo);
        provCont.inciarVista("Proveedores");
    }//GEN-LAST:event_btnProveedoresActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        ClienteControlador cliControl = new ClienteControlador(this, dlgCli, cliente, cliModelo);
        cliControl.inciarVista("Clientes");
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed

        InventarioControlador inventControl = new InventarioControlador(this, dlginve,
                producto, invenModelo);

        inventControl.inciarVista("Productos");
    }//GEN-LAST:event_btnInventarioActionPerformed

    private void btnCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCajaActionPerformed

     CajaControladorVidal controlCaja = new CajaControladorVidal(this, ventas, detalleFact);
     controlCaja.iniciarVista("Caja");

    }//GEN-LAST:event_btnCajaActionPerformed

    private void btnFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturasActionPerformed
        
        
        
    }//GEN-LAST:event_btnFacturasActionPerformed

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
    public javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenu2;
    public javax.swing.JMenu jMenu3;
    public javax.swing.JMenu jMenu4;
    public javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JButton getBtnTrabajadores() {
        return btnTrabajadores;
    }

}
