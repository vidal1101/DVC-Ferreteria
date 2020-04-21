package controlador;

import Vista.FrmVentas;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.table.DefaultTableModel;
import modelo.CajaModelo;

/**
 *
 * @author Carlos Mairena
 */
public class CajaControladorMontos implements PropertyChangeListener {

    private FrmVentas ventanaVentas;
    private CajaModelo modeloCajas;

    public CajaControladorMontos(FrmVentas ventanaVentas) {

        this.ventanaVentas = ventanaVentas;
        this.ventanaVentas.getTblProductosAgr().addPropertyChangeListener(this);
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
        float precio = 0.0f;
        float rebaja = 0.0f;
        int cantidad = 0;
        float total = 0.0f;

        System.out.println("Empezando a calcular montos");
        for (int fila = 0; fila < ventanaVentas.getTblProductosAgr().getModel().getRowCount(); fila++) {

            try {
                // Se calculan el valor de cada producto de acuerdo a su descuento
                precio = Float.parseFloat(ventanaVentas.getTblProductosAgr().getValueAt(fila, 3).toString());
                descuento = (Float.parseFloat(ventanaVentas.getTblProductosAgr().getValueAt(fila, 4).toString()));
                cantidad = Integer.parseInt(ventanaVentas.getTblProductosAgr().getValueAt(fila, 2).toString());
                rebaja = ((precio * descuento) / 100);
                /*------------------------------------------------------------------
                                      IMPORTANTE
        La manera en que calcula el descuento es por individual, osea, si el producto vale 1000 y el
       descuento es del 50%, entonces calcula el valor con el descuento, luego ese valor lo multiplica para
       la cantidad que hay:
                             3 productos a 1000 colones
                             50 % de descuento equivale a 500 colones
                             en total se cobran 1500 porque cada producto con descuento son 500
    -----------------------------------------------------------------------
                 */
                total += (precio - rebaja) * cantidad;

                ventanaVentas.getTxtSubTotal().setText(String.format("%.2f",
                        (precio * cantidad) + Float.parseFloat(ventanaVentas.getTxtSubTotal().getText())));

                // El String.format("%.2f",valor) lo que hace es dar formato de 2 decimales al valor de tipo float
                ventanaVentas.getTxtDescuento().setText(String.format("%.2f", (rebaja + Float.parseFloat(ventanaVentas.getTxtDescuento().getText()))));
                ventanaVentas.getTxtTotalPagar().setText(String.format("%.2f", total));

            } catch (NumberFormatException es) {

                System.out.println("Borraron todo de la celda descuento, por lo tanto se le pone 0 de defecto");

                // Verificamos cuál es el campo vacío para settearle un 0
                // DESCUENTO
                if (ventanaVentas.getTblProductosAgr().getValueAt(fila, 4).toString().isEmpty()) {
                    ventanaVentas.getTblProductosAgr().setValueAt(0, fila, 4);
                    
                } else {
                    // CANTIDAD
                    ventanaVentas.getTblProductosAgr().setValueAt(0, fila, 2);

                    // Actualizamos al subtotal
                    DefaultTableModel modelo = (DefaultTableModel) ventanaVentas.getTblProductosAgr().getModel();
                    modelo.removeRow(fila);
                    ventanaVentas.getTblProductosAgr().setModel(modelo);
                            
//                    ventanaVentas.getTxtSubTotal().setText(String.format("%.2f",
//                            (Float.parseFloat(ventanaVentas.getTxtSubTotal().getText())
//                            - Float.parseFloat(ventanaVentas.getTblProductosAgr().getValueAt(fila, 3).toString()))));
                }
                //fila--;
                break;
            }
        }

        System.out.println("Montos calculados");
    }

    // Método de la interfaz PropertyChangeListener
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        System.out.println("Celda modificada");
        this.calcularMontos();
    }

}
