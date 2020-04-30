package controlador;

import DVC_Ferreteria.Ayuda;
import Vista.DlgCategorias;
import Vista.DlgCliente;
import Vista.DlgFacturas;
import Vista.DlgInventario;
import Vista.DlgProveedores;
import Vista.FrmInicioSesion;
import Vista.FrmPrincipal;
import Vista.FrmVentas;
import Vista.dlgAyuda;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import logicaClass.ClassTrabajador;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class ControladorPrincipal implements ActionListener, KeyListener {

    AudioClip sonido = Applet.newAudioClip(ClassLoader.getSystemResource("DVC_Ferreteria/prueba.wav"));
    private FrmInicioSesion frameSesion;
    private FrmPrincipal framePrincipal;

    // Este objeto se utiliza en todo el programa, ya que identifica al trabajador que hizo login
    private ClassTrabajador trabajador;

    private DlgCategorias dlgCateg;
    private DlgProveedores dlgprov;
    private DlgInventario dlginve;
    private DlgCliente dlgcli;
    private DlgFacturas dlgFact;
    private FrmVentas dlgVentas;
    private Ayuda ayuda;
    private dlgAyuda dlgAyuda;

    public ControladorPrincipal() {

        // GUI principales del programa
        this.framePrincipal = new FrmPrincipal();
        this.frameSesion = new FrmInicioSesion();

        // Este objeto se utiliza en todo el programa, ya que se obtiene después del login
        this.trabajador = new ClassTrabajador();

        this.dlgCateg = new DlgCategorias(framePrincipal, true);
        this.dlgprov = new DlgProveedores(framePrincipal, true);
        this.dlginve = new DlgInventario(framePrincipal, true);
        this.dlgcli = new DlgCliente(framePrincipal, true);
        this.dlgVentas = new FrmVentas(framePrincipal, true);
        this.ayuda = new Ayuda();
        this.dlgAyuda = new dlgAyuda(framePrincipal, true);
        this.dlgFact = new DlgFacturas(framePrincipal, true);

        this.framePrincipal.getBtnAyuda().addActionListener(this);
        this.framePrincipal.getBtnCaja().addActionListener(this);
        this.framePrincipal.getBtnCategorias().addActionListener(this);
        this.framePrincipal.getBtnClientes().addActionListener(this);
        this.framePrincipal.getBtnFacturas().addActionListener(this);
        this.framePrincipal.getBtnInventario().addActionListener(this);
        this.framePrincipal.getBtnProveedores().addActionListener(this);
        this.framePrincipal.getBtnTrabajadores().addActionListener(this);
        this.framePrincipal.getMnTrabajadores().addActionListener(this);
        this.framePrincipal.getMnHistorial().addActionListener(this);
        this.framePrincipal.getMnInventario().addActionListener(this);
        this.framePrincipal.getMnCategorias().addActionListener(this);

        this.framePrincipal.getMnIrTraba().addActionListener(this);
        this.framePrincipal.getMnIrCate().addActionListener(this);
        this.framePrincipal.getMnIrHisto().addActionListener(this);
        this.framePrincipal.getMnIrInven().addActionListener(this);
        this.framePrincipal.getMniSalir().addActionListener(this);

        this.frameSesion.getBtnIngresar().addActionListener(this);
        this.frameSesion.getTxtUsuario().addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == framePrincipal.getBtnAyuda()) {
            
            ayuda.ventana();
        }
        
        if (e.getSource() == frameSesion.getBtnIngresar()) { //el inicio de sesion 
            TrabajadorControlador principalCon = new TrabajadorControlador(frameSesion);

            if (principalCon.iniciarSesion()) {

                this.trabajador = principalCon.getTrabajador();
                this.frameSesion.dispose();
                this.framePrincipal.setVisible(true);
                //this.sonidoInicio();
            }
        }

        if (e.getSource() == framePrincipal.getBtnCaja()) { // La cajaControlador 

            CajaControlador cajaControl = new CajaControlador(this.framePrincipal, trabajador);
            System.out.println("Trabajador actual: " + trabajador.getNombreTrab());
            cajaControl.setTrabajador(trabajador);
            cajaControl.iniciarVista();

        } else if (e.getSource() == framePrincipal.getBtnCategorias()) { //las categorias

            CategoriaControlador categControl = new CategoriaControlador(this.framePrincipal);
            categControl.inciarVista("Categorias");

        } else if (e.getSource() == framePrincipal.getBtnClientes()) {//los clientes 

            ClienteControlador cliControl = new ClienteControlador(this.framePrincipal);
            cliControl.inciarVista("Clientes");

        } else if (e.getSource() == framePrincipal.getBtnProveedores()) { //los proveedores

            ProveedoresControlador provCont = new ProveedoresControlador(this.framePrincipal);
            provCont.inciarVista("Proveedores");

        } else if (e.getSource() == framePrincipal.getBtnTrabajadores()) {//los trabajadores

            TrabajadorControlador trabControl = new TrabajadorControlador(this.framePrincipal, trabajador);
            trabControl.inciarVista("Trabajadores");

        } else if (e.getSource() == framePrincipal.getBtnInventario()) {

            InventarioControlador inventControl = new InventarioControlador(this.framePrincipal);
            inventControl.inciarVista("Productos");

        } else if (e.getSource() == framePrincipal.getBtnFacturas()) {
            /**
             * aun falta aqui las facturas
             */
            FacturasControlador facturaControl = new FacturasControlador(this.framePrincipal, dlgFact);
            facturaControl.iniciarVista("Facturas");
            
        } else if (e.getSource() == framePrincipal.getMnTrabajadores()) { //eventos de los menur bar

            TrabajadorControlador trabControl = new TrabajadorControlador(this.framePrincipal, trabajador);
            trabControl.inciarVista("Trabajadores");
        } /**
         * Escuchan las opciones de la barra principal
         */
        else if (e.getSource() == framePrincipal.getMnIrTraba()) {

            TrabajadorControlador trabControl = new TrabajadorControlador(this.framePrincipal, trabajador);
            trabControl.inciarVista("Trabajadores");

        } else if (e.getSource() == framePrincipal.getMnIrCate()) {
            CategoriaControlador cateCon = new CategoriaControlador(this.framePrincipal);
            cateCon.inciarVista("Categorias");

        } else if (e.getSource() == framePrincipal.getMnIrInven()) {
            InventarioControlador inveCon = new InventarioControlador(this.framePrincipal);
            inveCon.inciarVista("Productos");

        } else if (e.getSource() == framePrincipal.getMnIrHisto()) {
            FacturasControlador factuCon = new FacturasControlador(this.framePrincipal, dlgFact);
            factuCon.iniciarVista("Facturas");

        } else if (e.getSource() == framePrincipal.getMniSalir()) {
            System.exit(0);
        } else {
            System.out.println("Ningún botón parecido");
        }

    }

    /**
     * Llamado desde el arranque para iniciar la vista
     */
    public void muestraVentanaPrincipal() {

        frameSesion.setVisible(true);
    }

    /**
     * metodo que da el sonido al abrir la app principal la partida usando una clase que espero aun funcione con formato
     * de audio.wav
     */
    public void sonidoInicio() {
        sonido.play();
    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (e.getSource() == frameSesion.getTxtUsuario()) {
            char letra = e.getKeyChar();

            if (!Character.isDigit(letra)) {
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

}
