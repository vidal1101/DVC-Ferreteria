package controlador;

import Vista.DlgFacturas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.FacturaModelo;

/**
 *
 * @author Dixiana, Carlos y Vidal
 */
public class FacturasControlador implements ActionListener {
    
    private DlgFacturas ventanaFacturas;
    private FacturaModelo modeloFactura;
    
    public FacturasControlador() {
        this.ventanaFacturas = new DlgFacturas(null, true);
        this.modeloFactura = new FacturaModelo();
        
        this.ventanaFacturas.getBtnAnulaFac().addActionListener(this);
        this.ventanaFacturas.getBtnBuscar().addActionListener(this);
        this.ventanaFacturas.getBtnMasDetalles().addActionListener(this);
        this.ventanaFacturas.getBtnRegresar().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
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
    
}
