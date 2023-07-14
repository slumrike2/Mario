package Graficos2.pantallas.menues;

import Game.*;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Registro extends JPanel {
    

    JTextField nombre = new JTextField("Nombre");
    JTextField nombreUsuario = new JTextField("Nombre de usuario");
    JTextField contraseña = new JTextField("Contraseña");
    JTextField confirmarContraseña = new JTextField("Confirmar contraseña");

    JButton botonRegistrarse = new JButton("Registrarse");
    JButton botonVolver = new JButton("Volver");

    public Registro() {
        setPreferredSize(new Dimension(1080, 720));
        setLayout(null);

        inicializarBotones();
        inicializarPosiciones();
        add(botonRegistrarse);
        add(botonVolver);

        setVisible(true);
    }

    private void botonRegistrarseActionPerformed() {
        // TODO add your handling code here:
    }

    private void botonVolverActionPerformed() {
        Gui.switchPantallaMenu(Gui.PantallasPosiblesMenu.MENU_PRINCIPAL);
    }

    private void inicializarBotones() {
        // !Para utilizar cuando se estilice el boton/botones. Probablemente se haga una
       
    }

    private void inicializarPosiciones() {
        botonRegistrarse.setBounds(400, 600, 100, 50);
        botonVolver.setBounds(600, 600, 100, 50);
    
    }
}
