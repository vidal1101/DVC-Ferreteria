/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vista.DlgCategorias;
import Vista.DlgInventario;
import Vista.DlgProveedores;
import Vista.DlgTrabajadores;
import Vista.FrmInicioSesion;
import Vista.FrmPrincipal;
import Vista.FrmVentas;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaClass.ClassCliente;
import logicaClass.ClassProducto;
import logicaClass.ClassProveedor;
import logicaClass.ClassTrabajador;
import modelo.ClienteModelo;
import modelo.InventarioModelo;
import modelo.ProveedorModelo;
import modelo.TrabajadorModelo;

/**
 *
 * @author Dixi, Vidal y Canales
 */
public class ControladorPrincipal implements ActionListener {

    //Instancias
    AudioClip sonido = Applet.newAudioClip(ClassLoader.getSystemResource("DVC_Ferreteria/prueba.wav"));
    private FrmInicioSesion frameSesion;
    private FrmPrincipal framePrincipal;

    private ClassTrabajador trabajador;
    private TrabajadorModelo trabModelo;
    private DlgTrabajadores dlgTrab;
    
    private DlgCategorias dlgCateg;

    private ClassCliente cliente;
    private ClienteModelo cliModelo;

    private ClassProveedor proveedor;
    private ProveedorModelo provModelo;
    private DlgProveedores dlgprov;

    private ClassProducto producto;
    private InventarioModelo invenModelo;
    private DlgInventario dlginve;

    private FrmVentas ventas;
    private CajaControlador caja;

    /**
     * estos botones son los menu bar, aqui les dejo los nombres de variables solo copian y pegan y crean los getter
     * especificos de cada uno menTrajadores menHistorial menInventario menCategorias
     */
    public ControladorPrincipal() {

        this.framePrincipal = new FrmPrincipal();

        this.frameSesion = new FrmInicioSesion();

        this.trabModelo = new TrabajadorModelo();
        this.trabajador = new ClassTrabajador();

        this.dlgTrab = new DlgTrabajadores(null, true);
        this.dlgCateg = new DlgCategorias(null, true);

        this.cliModelo = new ClienteModelo();
        this.cliente = new ClassCliente();

        this.provModelo = new ProveedorModelo();
        this.proveedor = new ClassProveedor();
        this.dlgprov = new DlgProveedores(null, true);

        this.invenModelo = new InventarioModelo();
        this.producto = new ClassProducto();
        this.dlginve = new DlgInventario(null, true);

        this.ventas = new FrmVentas(null, true);
        this.caja = new CajaControlador(ventas, trabajador);

        this.framePrincipal.getBtnAyuda().addActionListener(this);
        this.framePrincipal.getBtnCaja().addActionListener(this);
        this.framePrincipal.getBtnCalendario().addActionListener(this);
        this.framePrincipal.getBtnCategorias().addActionListener(this);
        this.framePrincipal.getBtnClientes().addActionListener(this);
        this.framePrincipal.getBtnConfiguracion().addActionListener(this);
        this.framePrincipal.getBtnFacturas().addActionListener(this);
        this.framePrincipal.getBtnInventario().addActionListener(this);
        this.framePrincipal.getBtnProveedores().addActionListener(this);
        this.framePrincipal.getBtnTrabajadores().addActionListener(this);
        this.frameSesion.getBtnIngresar().addActionListener(this);
        this.framePrincipal.getMnTrabajadores().addActionListener(this);
        this.framePrincipal.getMnHistorial().addActionListener(this);
        this.framePrincipal.getMnInventario().addActionListener(this);
        this.framePrincipal.getMnCategorias().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) { // eventosa de todos los botones 

        if (e.getSource() == frameSesion.getBtnIngresar()) { //el inicio de sesion 
            TrabajadorControlador principalCon = new TrabajadorControlador(frameSesion);

            if (principalCon.iniciarSesion()) {
                framePrincipal = new FrmPrincipal();
                this.frameSesion.dispose();
                this.framePrincipal.setVisible(true);
                this.sonidoInicio();

            }
        } 
        
        if (e.getSource() == framePrincipal.getBtnCaja()) { //breteo la caja 
            System.out.println("Trabajador: " + trabajador.getNombreTrab());
            caja.iniciarVista();
        } else if (e.getSource() == framePrincipal.getBtnCategorias()) { //las categorias

            CategoriaControlador categControl = new CategoriaControlador(dlgCateg);
            categControl.inciarVista("Categorias");

        } else if (e.getSource() == framePrincipal.getBtnClientes()) {//los clientes 
            ClienteControlador cliControl = new ClienteControlador(this.framePrincipal);
            cliControl.inciarVista("Clientes");

        } else if (e.getSource() == framePrincipal.getBtnProveedores()) { //los proveedores
            ProveedoresControlador provCont = new ProveedoresControlador(this.framePrincipal, dlgprov,
                    proveedor, provModelo);
            provCont.inciarVista("Proveedores");
        } else if (e.getSource() == framePrincipal.getBtnTrabajadores()) {//los trabajadores
            TrabajadorControlador trabControl = new TrabajadorControlador(framePrincipal);
            trabControl.inciarVista("Trabajadores");

        } else if (e.getSource() == framePrincipal.getBtnInventario()) {

            InventarioControlador inventControl = new InventarioControlador(this.framePrincipal, dlginve,
                    producto, invenModelo);
            inventControl.inciarVista("Productos");

        } else if (e.getSource() == framePrincipal.getBtnFacturas()) {
            /**
             * aun falta aqui las facturas
             */
        } else if (e.getSource() == framePrincipal.getBtnCalendario()) {

        } else if (e.getSource() == framePrincipal.getBtnConfiguracion()) {

        } else if (e.getSource() == framePrincipal.getMnTrabajadores()) {//eventos de los menur bar 
            TrabajadorControlador trabControl = new TrabajadorControlador(framePrincipal);
            trabControl.inciarVista("Trabajadores");
        }

    }

    /**
     * metodo llamado desde el arranque para iniciar la vista
     */
    public void muestraVentanaPrincipal() {

        frameSesion.setVisible(true);

//            this.frameSesion.dispose();
//            this.framePrincipal.setVisible(true);
//            this.sonidoInicio();

    }

    /**
     * metodo que da el sonido al abrir la app principal la partida usando una clase que espero aun funcione con formato
     * de audio.wav
     */
    public void sonidoInicio() {
        sonido.play();
    }

}
