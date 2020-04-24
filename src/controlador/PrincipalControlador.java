package controlador;

import Vista.FrmInicioSesion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Dixiana, Carlos y Vidal
 */
public class PrincipalControlador implements ActionListener {

    public static void main(String[] s) {
        
        FrmInicioSesion sesion = new FrmInicioSesion();
        sesion.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
