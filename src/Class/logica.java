package Class;

import Gui.Frame;
import Gui.dlgCalender;
import Gui.dlgPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author vidal
 */
public class logica implements WindowListener, ActionListener, KeyListener {

    private Frame frmPrinci;
    private dlgCalender calender;
    private dlgPrincipal frame;
    
    
    //setter and Getter 
      public Frame getFrmPrinci() {
        return frmPrinci;
    }

    public void setFrmPrinci(Frame frmPrinci) {
        this.frmPrinci = frmPrinci;
    }

    public dlgCalender getCalender() {
        return calender;
    }

    public void setCalender(dlgCalender calender) {
        this.calender = calender;
    }

    public dlgPrincipal getFrame() {
        return frame;
    }

    public void setFrame(dlgPrincipal frame) {
        this.frame = frame;
    }

    //contructor 

    public logica(Frame frmPrinci, dlgCalender calender, dlgPrincipal frame) {
        this.frmPrinci = frmPrinci;
        this.calender = calender;
        this.frame = frame;
        this.frame.btnCalender.addActionListener(this);
        this.frame.btnCalender.addActionListener(this);
    }
    
    
    
    public logica(dlgCalender calender, dlgPrincipal frame) {
        this.calender = calender;
        this.frame = frame;
    }

    //metodos 
    public void ventas(){
        frmPrinci.setTitle("ventas");
        frmPrinci.setLocationRelativeTo(null);
    }
    
    public void Calender() {
        calender.setTitle("Calendario");
        calender.setLocationRelativeTo(null);
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
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frame.btnCalender){
           this.Calender();
           calender.setVisible(true);
        }
        if(e.getSource()== frame.btnVender){
            this.ventas();
            frmPrinci.setVisible(true);
        }

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
