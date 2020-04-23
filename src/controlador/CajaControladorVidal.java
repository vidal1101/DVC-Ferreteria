package controlador;

import Vista.DlgCliente;
import Vista.DlgMostrador;
import Vista.FrmPrincipal;
import Vista.FrmVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import logicaClass.ClassCliente;
import logicaClass.ClassDetallesFactura;
import modelo.CajaModeloVidal;
import modelo.ClienteModelo;
import modelo.InventarioModelo;

/**
 *
 * @author Dixiana, Carlos y Vidal
 */
public class CajaControladorVidal implements ActionListener {

    private ArrayList<ClassDetallesFactura> arraysDetalles = new ArrayList<>();
    private FrmPrincipal principal;
    private FrmVentas ventas;
    private ClassDetallesFactura detaFactura;
    private DlgMostrador mostrador;
    DefaultTableModel modeloDetalles;

    private CajaModeloVidal cajaModelo;
    private ClassCliente cliente;
    private ClienteModelo cliModelo;
    private DlgCliente dlgCli;
    private int ced;
    private String name;
    private int cantidad;

    public CajaControladorVidal(FrmPrincipal principal, FrmVentas ventas, ClassDetallesFactura detaFactura) {
        this.detaFactura = detaFactura;
        this.modeloDetalles = new DefaultTableModel();
        this.mostrador = new DlgMostrador(null, true);
        this.principal = principal;
        this.ventas = ventas;
        this.cajaModelo = new CajaModeloVidal();
        //clientes 
        this.cliModelo = new ClienteModelo();
        this.cliente = new ClassCliente();
        this.dlgCli = new DlgCliente(null, true);
        this.ced = 0;
        this.name = "";
        this.cantidad = 0;
        ventas.getBtnAnadirProd().addActionListener(this);
        ventas.getBtnCliente().addActionListener(this);
        ventas.getBtnQuitarPro().addActionListener(this);
        ventas.getBtnQuitarTodo().addActionListener(this);
        ventas.getBtnSalir().addActionListener(this);
        this.mostrador.getBtnSeleccionar().addActionListener(this);
        this.mostrador.getBtnCancelar().addActionListener(this);

    }

    /**
     * *
     * inicia la vista de ventas
     *
     * @param title titulo del frm
     */
    public void iniciarVista(String title) {
        ventas.setTitle(title);
        ventas.setLocationRelativeTo(null);
        //FECHA DEL SISTEMA
        Date sistFecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd MMMMM YYYY");
        ventas.getTxtFecha().setText(formato.format(sistFecha));

        //HORA DEL SISTEMA
        Timer tiempo = new Timer(100, CajaControladorVidal.this);
        tiempo.start();

        this.mostrarTableDetalles();
        ventas.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //this.hora();

        if (e.getSource() == ventas.getBtnCliente()) {
            ClienteControlador cliControl = new ClienteControlador(null, dlgCli, cliente, cliModelo);
            cliControl.inciarVista("Clientes");
            int fila = dlgCli.getTblCliente().getSelectedRow();
            if (fila != -1) {
                ced = Integer.parseInt(dlgCli.getTblCliente().getValueAt(fila, 0).toString());
                name = dlgCli.getTblCliente().getValueAt(fila, 1).toString();
                ventas.getTxtNombreCliente().setText(name);
                ventas.getTxtIdCliente().setText(String.valueOf(ced));
            }
        } else if (e.getSource() == ventas.getBtnAnadirProd()) {
            
            mostrador.setTitle("Selecionar Productos");
            this.mostrarProductos();
            mostrador.setVisible(true);
            
        } else if (e.getSource() == mostrador.getBtnSeleccionar()) {
            //a traer ese daot
            int file = mostrador.getTblMostrar().getSelectedRow();
            if (file != -1) {

                detaFactura = new ClassDetallesFactura();

                detaFactura.setIdProd(Integer.parseInt(mostrador.getTblMostrar().getValueAt(file, 0).toString()));
                detaFactura.setNombProducto(mostrador.getTblMostrar().getValueAt(file, 3).toString());
                detaFactura.setSubtotal(Double.valueOf(mostrador.getTblMostrar().getValueAt(file, 4).toString()));
                detaFactura.setDescuento(Double.valueOf(mostrador.getTblMostrar().getValueAt(file, 5).toString()));

                //aqui pedimos la cantidad que desea y el ID  seleccionado
                int id = Integer.parseInt(mostrador.getTblMostrar().getValueAt(file, 0).toString());
                int stock = Integer.parseInt(mostrador.getTblMostrar().getValueAt(file, 7).toString());
                this.cantidad = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "¿Cuanto cantidad desea agregar?",
                        "Cantidad", JOptionPane.PLAIN_MESSAGE));

                if (cantidad <= stock) {
                    detaFactura.setCantidadProd(cantidad);

                    if (revisarid(detaFactura.getIdProd())) {
                        System.out.println("inserccion  ..");
                        this.arraysDetalles.add(detaFactura);
                        arraysDetalles.trimToSize();

                    }
                    this.mostrarTableDetalles();
                    cajaModelo.modificarStock(id, 2, cantidad);
                    mostrador.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Cantidad no disponible ", "ERROR", JOptionPane.WARNING_MESSAGE);

                }

            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un producto ", "FALTAN DATOS", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == mostrador.getBtnCancelar()) {
            mostrador.dispose();
        } else if (e.getSource() == ventas.getBtnQuitarPro()) {

            int fila = ventas.getTblProductosAgr().getSelectedRow();

            if (fila != -1) {
                int opcion = JOptionPane.showConfirmDialog(ventas, "¿Desea quitar este producto de la compra?", "Quitar", JOptionPane.YES_NO_OPTION);

                if (opcion == JOptionPane.YES_OPTION) {
                    int id = Integer.parseInt(ventas.getTblProductosAgr().getValueAt(fila, 0).toString());
                    System.out.println("id a eliminar " + id);

                    cajaModelo.modificarStock(id, 1, cantidad);

                    arraysDetalles.remove(fila);
                    arraysDetalles.trimToSize();

                    JOptionPane.showMessageDialog(null, "Eliminado correctamente");
                    this.mostrarTableDetalles();
                }
            } else {
                JOptionPane.showMessageDialog(ventas, "Seleccione un producto");
            }
        } else if (e.getSource() == ventas.getBtnSalir()) {
            this.sumarAlStocks();
            ventas.dispose();
        } else if (e.getSource() == ventas.getBtnQuitarTodo()) {
            this.limpiartodo();
        }

    }

    public void limpiartodo() {
        this.sumarAlStocks();
        this.modeloDetalles = new DefaultTableModel();
        this.arraysDetalles = new ArrayList<>();
        this.cantidad = 0;
        this.mostrarTableDetalles();
        this.ventas.getTxtNombreCliente().setText("");
        this.ventas.getTxtIdCliente().setText("");
        this.ventas.getTxtDireccionEnt().setText("");
        this.ventas.getTxtSubTotal().setText("");
        this.ventas.getTxtTotalPagar().setText("");
        this.ventas.getTxtDescuento().setText("");

    }

    /**
     *
     * regresa todos las cantidades a sus stock de la base de datos con el
     * swcith de suma o restar
     */
    public void sumarAlStocks() {
        if (!arraysDetalles.isEmpty()) {
            for (int i = 0; i < arraysDetalles.size(); i++) {
                System.out.println("date  " + arraysDetalles.get(i).getIdProd());

                if (arraysDetalles.get(i).getIdProd() != 0) {

                    int id = arraysDetalles.get(i).getIdProd();
                    int canti = arraysDetalles.get(i).getCantidadProd();
                    System.out.println("id " + id + " cantidad " + canti);
                    cajaModelo.modificarStock(id, 1, canti);
                    this.mostrarTableDetalles();
                }
            }
        }
    }

    /**
     * *
     * metodo para visualizar la hora., trabajada con un hilo de timer y a
     * traves de un metodo del event para correr mienttas la ventana este en uso
     */
    public void hora() {
        Date sistHora = new Date();
        String pmAm = "hh:mm:ss a";
        SimpleDateFormat format = new SimpleDateFormat(pmAm);
        Calendar hoy = Calendar.getInstance();
        ventas.getTxtHora().setText(String.format(format.format(sistHora), hoy));
    }

    /**
     * revisa si el Id de los productos insertados ya exite de ser asi solo suma
     * los dato al stock
     *
     * @param id el id del producto de la tbal de frmVentas
     * @return false is existe y si es true es que no existe
     */
    public boolean revisarid(int id) {
        System.out.println("id review " + id);
        try {
            if (!this.arraysDetalles.isEmpty()) {
                for (int i = 0; i < arraysDetalles.size(); i++) {
                    if (arraysDetalles.get(i).getIdProd() == id) {
                        int aux = arraysDetalles.get(i).getCantidadProd();
                        arraysDetalles.get(i).setCantidadProd(cantidad + aux);

                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * *
     * muestro la tabla de los productos en proforma
     */
    public void mostrarTableDetalles() {
        String[] titulos = {"Id Prod ", "Nombre ", "Cantidad", "Precio", "Descuento",};
        modeloDetalles = new DefaultTableModel(null, titulos);
        
        for (int i = 0; i < arraysDetalles.size(); i++) {
            System.out.println("mostrando");

            Object[] objeto = {arraysDetalles.get(i).getIdProd(), arraysDetalles.get(i).getNombProducto(),
                arraysDetalles.get(i).getCantidadProd(),
                arraysDetalles.get(i).getSubtotal(), arraysDetalles.get(i).getDescuento()};

            modeloDetalles.addRow(objeto);
        }
        ventas.getTblProductosAgr().setModel(modeloDetalles);
        ventas.getLblRegistrosDetalles().setText("Registros  de Pre-Factura : " + String.valueOf(ventas.getTblProductosAgr().getRowCount()));
    }

    /**
     *
     * metodo para mostrar los prodcutos
     */
    private void mostrarProductos() {
        String[] title = {"ID", "Proveedor", "Categoria", "Nombre Prod ", "Precio", "Descuento", "Venta por",
            "Stock", "Frágil", "Descripción"};
        DefaultTableModel tablaModelo = new DefaultTableModel(null, title) {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        ResultSet rs = null;
        InventarioModelo model = new InventarioModelo();

        try {
            rs = model.mostrarProductos();

            while (rs.next()) {

                Object[] objeto = {rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
                    rs.getDouble(5), rs.getInt(6), rs.getString(7),
                    rs.getInt(8), rs.getBoolean(9), rs.getString(10)};

                tablaModelo.addRow(objeto);
            }

            System.out.println("Cerrando ResultSet");
            rs.close();
            mostrador.getTblMostrar().setModel(tablaModelo);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
