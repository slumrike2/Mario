package Graficos2.pantallas.menues;

import Game.*;
import Constantes.*;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPrincipal extends JPanel {

    JButton botonSinglePlayer = new JButton("Un solo jugador");
    JButton botonMultiPlayer = new JButton("Multijugador");
    JButton botonIniciarSesion = new JButton("Iniciar sesion");
    JButton botonnRegistrarse = new JButton("Registrarse");
    JButton botonOpciones = new JButton("Opciones");
    JButton botonSalir = new JButton("Salir");

    public MenuPrincipal() {
        setPreferredSize(new Dimension(1080, 720));
        setLayout(null);

        inicializarBotones();
        inicializarPosiciones();
        add(botonSinglePlayer);
        add(botonMultiPlayer);
        add(botonIniciarSesion);
        add(botonnRegistrarse);
        add(botonOpciones);
        add(botonSalir);

        setVisible(true);
    }

    private void botonSinglePlayerActionPerformed() {
        Gui.switchState();
    }

    private void botonMultiPlayerActionPerformed() {
        // TODO add your handling code here:
    }

    private void botonIniciarSesionActionPerformed() {
        Gui.switchPantallaMenu(Constantes.PANTALLAS_MENUES.INICIO_SESION);
    }

    private void botonnRegistrarseActionPerformed() {
        Gui.switchPantallaMenu(Constantes.PANTALLAS_MENUES.REGISTRO);
    }

    private void botonOpcionesActionPerformed() {
        // TODO add your handling code here:
    }

    private void botonSalirActionPerformed() {
        Gui.salir();
    }

    private void inicializarBotones() {
        // !Para utilizar cuando se estilice el boton/botones. Probablemente se haga una
        // clase abstractButton
        // boton1.setBorderPainted(false);
        // boton1.setFocusPainted(false);
        // boton1.setContentAreaFilled(false);
        // boton1.setOpaque(false);
        // botonSinglePlayer.setVisible(true);

        botonSinglePlayer.addActionListener(e -> botonSinglePlayerActionPerformed());
        botonMultiPlayer.addActionListener(e -> botonMultiPlayerActionPerformed());
        botonIniciarSesion.addActionListener(e -> botonIniciarSesionActionPerformed());
        botonnRegistrarse.addActionListener(e -> botonnRegistrarseActionPerformed());
        botonOpciones.addActionListener(e -> botonOpcionesActionPerformed());
        botonSalir.addActionListener(e -> botonSalirActionPerformed());
        
    }

    private void inicializarPosiciones() {

        // Se coloca el boton a la mitad de la pantalla
        botonSinglePlayer.setBounds(530 - 100, 360 - 50, 200, 50);
        botonMultiPlayer.setBounds(530 - 100, 360, 200, 50);
        botonIniciarSesion.setBounds(530 - 100, 360 + 50, 200, 50);
        botonnRegistrarse.setBounds(530 - 100, 360 + 100, 200, 50);
        botonOpciones.setBounds(530 - 100, 360 + 150, 200, 50);
        botonSalir.setBounds(530 - 100, 360 + 200, 200, 50);

    }

}
