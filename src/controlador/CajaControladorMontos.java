package controlador;

import Vista.FrmVentas;
import javax.swing.table.DefaultTableModel;
import modelo.CajaModelo;

/**
 *
 * @author Carlos Mairena
 */
public class CajaControladorMontos {

    FrmVentas ventanaVentas;
    CajaModelo modeloCajas;

    public CajaControladorMontos(FrmVentas ventanaVentas) {

        this.ventanaVentas = ventanaVentas;
        this.modeloCajas = new CajaModelo();
    }

    /**
     * Permite iniciar la vista de la ventana
     *
     */
    public void iniciarVista() {

        this.rellenarTabla();
        ventanaVentas.setVisible(true);
    }

    /**
     * Prepara la tabla para poder utilizarla con el formato
     *
     */
    private void rellenarTabla() {

        String titles[] = {"ID de producto", "Producto", "Cantidad", "Precio", "Descuento"};

        // Creamos el arreglo con la misma cantidad de columnas,
        // indicando el lugar que será editable con true
        boolean[] editables = new boolean[]{false, false, true, false, true};

        // Preparamos al modelo, ciertas columnas editables y otras no
        DefaultTableModel model = new javax.swing.table.DefaultTableModel() {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return editables[columnIndex];
            }
        };

        model.setColumnIdentifiers(titles);

        // ArrayList o extraer las mismas que están en la tabla
        for (int i = 0; i < 5; i++) {

            Object elements[] = {(i + 10), "Tomates", 2, (810 + (i * 4)), 15};
            model.addRow(elements);
        }

        this.ventanaVentas.getTblProductosAgr().setModel(model);
        this.calcularMontos();
    }

    /**
     * Permite calcular el total a pagar, el total descuento y el subtotal
     *
     */
    private void calcularMontos() {

        ventanaVentas.getTxtDescuento().setText("0");
        ventanaVentas.getTxtSubTotal().setText("0");
        ventanaVentas.getTxtTotalPagar().setText("0");
        
        float descuento = 0.0f;
        float rebaja = 0.0f;
        float total = 0.0f;
        
        System.out.println("Empezando a calcular montos");
        for (int fila = 0; fila < ventanaVentas.getTblProductosAgr().getModel().getRowCount(); fila++) {

            // Se calculan el valor de cada producto de acuerdo a su descuento
            descuento = (Float.parseFloat(ventanaVentas.getTblProductosAgr().getValueAt(fila, 4).toString()));
            rebaja = (((Float.parseFloat(ventanaVentas.getTblProductosAgr().getValueAt(fila, 3).toString())) * descuento) / 100);
            total += (Float.parseFloat(ventanaVentas.getTblProductosAgr().getValueAt(fila, 3).toString())) - rebaja;

            ventanaVentas.getTxtSubTotal().setText((Float.parseFloat(ventanaVentas.getTblProductosAgr().getValueAt(fila, 3).toString())
                    + Float.parseFloat(ventanaVentas.getTxtSubTotal().getText())) + "");

            // El String.format("%.2f",valor) lo que hace es dar formato de 2 decimales al valor de tipo float
            ventanaVentas.getTxtDescuento().setText(String.format("%.2f", (rebaja + Float.parseFloat(ventanaVentas.getTxtDescuento().getText()))));
            ventanaVentas.getTxtTotalPagar().setText(String.format("%.2f", total));

        }
        
        System.out.println("Montos calculados");
    }

}
