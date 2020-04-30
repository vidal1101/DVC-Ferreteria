package controlador;

import Vista.DlgFacturas;
import Vista.FrmPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.FacturaModelo;

/**
 *
 * @author Dixiana Gómez
 * @author Rodrigo Vidal
 * @author Carlos Mairena
 */
public class FacturasControlador implements ActionListener {

    private FrmPrincipal principal;
    private DlgFacturas ventanaFacturas;
    private FacturaModelo modeloFactura;

    public FacturasControlador(FrmPrincipal principal, DlgFacturas dlfact) {

        this.ventanaFacturas = dlfact;
        this.principal = principal;
        this.ventanaFacturas = new DlgFacturas(this.principal, true);
        this.modeloFactura = new FacturaModelo();

        this.ventanaFacturas.getBtnAnulaFac().addActionListener(this);
        this.ventanaFacturas.getBtnBuscar().addActionListener(this);
        this.ventanaFacturas.getBtnMasDetalles().addActionListener(this);
        this.ventanaFacturas.getBtnRegresar().addActionListener(this);
        ventanaFacturas.getTbpnPestanas().setEnabledAt(1, false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int fila = ventanaFacturas.getTblFacturas().getSelectedRow();

        if (e.getSource() == ventanaFacturas.getBtnAnulaFac()) {

            if (fila != -1) {

                this.anular(fila);

            } else {
                JOptionPane.showMessageDialog(ventanaFacturas, "Seleccione una factura para anular");
            }

        } else if (e.getSource() == ventanaFacturas.getBtnMasDetalles()) {

            if (fila != -1) {

                // Vemos los detalles completos de la factura
                this.verDetalles(fila);

            } else {
                JOptionPane.showMessageDialog(ventanaFacturas, "Seleccione una factura para ver sus detalles");
            }

        } else if (e.getSource() == ventanaFacturas.getBtnRegresar()) {
            System.out.println("Regresa al menú de todas las facturas");

            this.rellenarTablaFacturas();
            ventanaFacturas.getTbpnPestanas().setSelectedIndex(0);
            ventanaFacturas.getTbpnPestanas().setEnabledAt(0, true);
            ventanaFacturas.getTbpnPestanas().setEnabledAt(1, false);

        }
    }

    /**
     * Vemos los detalles de la factura seleccionada
     *
     * @param fila
     */
    private void verDetalles(int fila) {
        
        this.muestraDetalles(fila);
        this.ventanaFacturas.getTbpnPestanas().setSelectedIndex(1);
        ventanaFacturas.getTbpnPestanas().setEnabledAt(1, true);
        ventanaFacturas.getTbpnPestanas().setEnabledAt(0, false);

    }

    /**
     * Permite anular la factura seleccionada
     *
     * @param fila
     */
    private void anular(int fila) {

        System.out.println("");

        if (!ventanaFacturas.getTblFacturas().getValueAt(fila, 4).toString().equals("Facturada")) {
            JOptionPane.showMessageDialog(ventanaFacturas, "Esta facturada ya ha sido anulada");

        } else {

            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(ventanaFacturas,
                    "¿Desea Anular esta factura?", "Anular Factura", JOptionPane.YES_NO_OPTION)) {
                if (modeloFactura.anularFactura(
                        Integer.parseInt(String.valueOf(ventanaFacturas.getTblFacturas().getValueAt(fila, 0)))
                )) {

                    JOptionPane.showMessageDialog(ventanaFacturas, "Factura anulada exitosamente");
                    this.rellenarTablaFacturas();

                } else {
                    JOptionPane.showMessageDialog(ventanaFacturas, "Ha ocurrido un inconveniente al intentar anular la factura");
                }
            }
        }
    }

    /**
     * Método que permite iniciar la vista de la GUI Facturas
     *
     * @param titulo
     */
    public void iniciarVista(String titulo) {

        ventanaFacturas.setTitle(titulo);
        this.rellenarTablaFacturas();
        ventanaFacturas.setVisible(true);

    }

    /**
     * Permite mostrar las facturas en la tabla
     *
     */
    private void rellenarTablaFacturas() {

        // Tal vez podríamos mostrar el subtotal
        String titulos[] = {"ID", "Cliente", "Total pagado", "Fecha", "Estado", "Trabajador", "Dirección"};
        DefaultTableModel modeloTabla = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        modeloTabla.setColumnIdentifiers(titulos);

        try {
            ResultSet rs = null;

            rs = modeloFactura.mostrarFacturas();

            while (rs.next()) {

                Object elementos[] = {rs.getInt(1), rs.getInt(2), rs.getFloat(6),
                    rs.getDate(5), rs.getString(8), rs.getInt(3), rs.getString(4)};

                modeloTabla.addRow(elementos);
            }

            ventanaFacturas.getTblFacturas().setModel(modeloTabla);
        } catch (SQLException ex) {

        }

    }

    /**
     * Muestra los detalles de la factura seleccionada
     */
    private void muestraDetalles(int id) {

        // Tal vez podríamos mostrar el subtotal
        String titulos[] = {"ID Detalle", "ID Producto", "Cant. Prod", "Descuento", "Subtotal"};
        DefaultTableModel modeloTabla = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        modeloTabla.setColumnIdentifiers(titulos);

        try {
            ResultSet rs = null;

            rs = modeloFactura.mostrarDetallesxFacturas(id);

            while (rs.next()) {

                this.ventanaFacturas.getTxtIdFactura().setText(rs.getInt(1) + "");
                this.ventanaFacturas.getTxtCliente().setText(rs.getInt(2) + "");
                this.ventanaFacturas.getTxtTrabajador().setText(rs.getInt(3) + "");
                this.ventanaFacturas.getTxtDirecion().setText(rs.getString(4));

                Object elementos[] = {rs.getInt(5), rs.getInt(6), rs.getInt(7),
                    rs.getInt(8), rs.getFloat(9)};

                modeloTabla.addRow(elementos);
            }

            ventanaFacturas.getTblDetallesCompra().setModel(modeloTabla);
        } catch (SQLException ex) {
            ventanaFacturas.getTblDetallesCompra().setModel(modeloTabla);
            System.out.println("Esta factura parece que no tenía productos: " + ex.getMessage());
        }
    }
}
