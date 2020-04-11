/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vista.dlgInventario;
import Vista.dlgPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaClass.ClassProducto;
import modelo.inventarioModelo;

/**
 *
 * @author User
 */
public class inventarioControlador implements ActionListener, WindowListener, KeyListener {
    
    private dlgPrincipal principal;
    private dlgInventario dlgivent;
    private ClassProducto producto;
    private inventarioModelo prodModelo;
    DefaultTableModel modeloInvent;
    private int opc;
    
    public inventarioControlador(dlgPrincipal principal, dlgInventario dlginvent, ClassProducto producto,
            inventarioModelo prodModelo) {
        this.modeloInvent = new DefaultTableModel();
        this.principal = principal;
        this.dlgivent = dlginvent;
        this.prodModelo = prodModelo;
        this.opc = opc;
        this.producto = producto;
        this.principal.getBtnInventario().addActionListener(this);
        this.dlgivent.getBtnNuevo().addActionListener(this);
        this.dlgivent.getBtnCalender().addActionListener(this);
        this.dlgivent.getBtnCancelar().addActionListener(this);
        this.dlgivent.getBtnEditar().addActionListener(this);
        this.dlgivent.getBtnEliminar().addActionListener(this);
        this.dlgivent.getBtnGuardarProd().addActionListener(this);
        this.dlgivent.getBtnLimpiar().addActionListener(this);
        this.dlgivent.getRbdNOfragil().addActionListener(this);
        this.dlgivent.getRbdSIfragil().addActionListener(this);
        
    }

    /**
     * Limpia los datos del registro
     */
    public void clear() {
        dlgivent.getTxtCantidadStockP().setText("");
        dlgivent.getTxtDescripProductoP().setText("");
        dlgivent.getTxtDescuentoProductoP().setText("");
        dlgivent.getTxtNombProductoP().setText("");
        dlgivent.getTxtPrecProductoP().setText("");
        dlgivent.getCmbIdCategoria().setSelectedIndex(0);
        dlgivent.getCmbIdProveedor().setSelectedIndex(0);
        dlgivent.getCmbUnidadVenta().setSelectedIndex(0);
        dlgivent.getRbdNOfragil().setSelected(true);
    }
    
    public void inciarVista(String title) {
        dlgivent.setTitle(title);
        dlgivent.setLocationRelativeTo(null);
        dlgivent.getPanInventario().setSelectedIndex(0);
        System.out.println("Abriendo registros de Proveedores");
        this.mostrartabla(this.prodModelo.mostrarProductos());
        this.dlgivent.getPanInventario().setEnabledAt(1, false);
        this.dlgivent.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == dlgivent.getBtnNuevo()) {
            this.clear();
            this.dlgivent.getPanInventario().setEnabledAt(1, true);
            this.dlgivent.getPanInventario().setEnabledAt(0, false);
            this.opc = 1;
            this.dlgivent.getPanInventario().setSelectedIndex(1);
        } else if (e.getSource() == dlgivent.getBtnLimpiar()) {
            this.clear();
            
        } else if (e.getSource() == dlgivent.getBtnGuardarProd()) {
            
            producto.setCantidadProd(Integer.parseInt(dlgivent.getTxtCantidadStockP().getText()));
            producto.setCategoria(dlgivent.getCmbIdCategoria().getSelectedIndex());
            producto.setDescriProd(dlgivent.getTxtDescripProductoP().getText());
            producto.setDescuentProd(Double.valueOf(dlgivent.getTxtDescuentoProductoP().getText()));
            producto.setNombreProd(dlgivent.getTxtNombProductoP().getText());
            producto.setPrecioProd(Double.valueOf(dlgivent.getTxtPrecProductoP().getText()));
            producto.setProdfragil(dlgivent.getRbdNOfragil().isSelected()
                    || dlgivent.getRbdSIfragil().isSelected());
            producto.setProvedor(dlgivent.getCmbIdProveedor().getSelectedIndex());
            producto.setUnidadVenta(String.valueOf(dlgivent.getCmbUnidadVenta().getSelectedItem()));
            
            if (opc == 1) {
                if (this.prodModelo.insertarProducto(producto)){
                    
                    JOptionPane.showMessageDialog(dlgivent, "Se inserto con Exito");
                    this.clear();
                    // No se va a caer, porque todo está medido, hasta el mínimo detalle.
                    this.mostrartabla(this.prodModelo.mostrarProductos());
                    this.dlgivent.getPanInventario().setSelectedIndex(0);
                    this.dlgivent.getPanInventario().setEnabledAt(1, false);
                    
                } else {
                    JOptionPane.showMessageDialog(dlgivent, "Usuario ya existente");
                    this.clear();
                }
                
            } else {//modificar

            }
            
        } else if (e.getSource() == dlgivent.getBtnCancelar()) {
            this.clear();
            this.dlgivent.getPanInventario().setEnabledAt(1, false);
            this.dlgivent.getPanInventario().setEnabledAt(0, true);
            this.dlgivent.getPanInventario().setSelectedIndex(0);
            
        }
        
    }
    
    public void mostrartabla(ResultSet rs) {
        // Títulos
        String[] title = {"Id Producto", "IdProveedor", "Id Categoria", "Nombre Prod ", "Precio ",
            "Descuento", "Unidad Venta", "Cantidad ", "Fragil", "Descrip"};
        modeloInvent = new DefaultTableModel(null, title);
        
        try {
            
            while (rs.next()) {
                this.producto = new ClassProducto(rs.getInt(1),
                        rs.getString(4), rs.getString(10), rs.getInt(8), rs.getDouble(5), rs.getBoolean(9),
                        rs.getInt(6), rs.getInt(3), rs.getInt(2), rs.getString(7));
                
                Object[] objeto = {producto.getIdProducto(), producto.getProvedor(), producto.getCategoria(),
                    producto.getNombreProd(), producto.getPrecioProd(), producto.getDescuentProd(),
                    producto.getUnidadVenta(), producto.getCantidadProd(), producto.getProdfragil(),
                    producto.getDescriProd()};
                
                modeloInvent.addRow(objeto);
            }
            
            System.out.println("Cerrando ResultSet");
            rs.close();
            dlgivent.getTblInventario().setModel(modeloInvent);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------");
        }
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
