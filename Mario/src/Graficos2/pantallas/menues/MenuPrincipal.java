package Graficos2.pantallas.menues;

import Game.*;
import Graficos2.Gui;
import Constantes.*;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import databases.Database;
import databases.Sesion;

public class MenuPrincipal extends JPanel {

    JButton bttSinglePlayer = new JButton("Un solo jugador");
    JButton bttMultiPlayer = new JButton("Multijugador");
    JButton bttIniciarSesion = new JButton("Iniciar sesion");
    JButton bttCerrarSesion = new JButton("Cerrar sesion");
    JButton bttRegistrarse = new JButton("Registrarse");
    JButton bttOpciones = new JButton("Opciones");
    JButton bttSalir = new JButton("Salir");

    public MenuPrincipal() {
        setPreferredSize(new Dimension(1080, 720));
        setLayout(null);

        inicializarBotones();
        inicializarPosiciones();
        add(bttSinglePlayer);
        add(bttMultiPlayer);
        add(bttIniciarSesion);
        add(bttCerrarSesion);
        add(bttRegistrarse);
        add(bttOpciones);
        add(bttSalir);

        setVisible(true);
    }

    public void SetBotonSesion() {
        if (Sesion.INSTANCE.isSesionIniciada()) {
            bttIniciarSesion.setVisible(false);
            bttCerrarSesion.setVisible(true);
        } else {
            bttIniciarSesion.setVisible(true);
            bttCerrarSesion.setVisible(false);
        }

    }

    private void botonSinglePlayerActionPerformed() {
        Gui.switchState();
    }

    private void botonMultiPlayerActionPerformed() {
        // TODO add your handling code here:
    }

    private void botonIniciarSesionActionPerformed() {
        Gui.switchPantallaMenu(Constantes.Pantallas_Menues.INICIO_SESION);
    }

    private void botonCerrarSesionActionPerformed() {
        Sesion.INSTANCE.cerrarSesion();
        SetBotonSesion();
    }

    private void botonnRegistrarseActionPerformed() {
        Gui.switchPantallaMenu(Constantes.Pantallas_Menues.REGISTRO);
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

        bttSinglePlayer.addActionListener(e -> botonSinglePlayerActionPerformed());
        bttMultiPlayer.addActionListener(e -> botonMultiPlayerActionPerformed());
        bttIniciarSesion.addActionListener(e -> botonIniciarSesionActionPerformed());
        bttCerrarSesion.addActionListener(e -> botonCerrarSesionActionPerformed());
        bttRegistrarse.addActionListener(e -> botonnRegistrarseActionPerformed());
        bttOpciones.addActionListener(e -> botonOpcionesActionPerformed());
        bttSalir.addActionListener(e -> botonSalirActionPerformed());

    }

    private void inicializarPosiciones() {

        // Se coloca el boton a la mitad de la pantalla
        bttSinglePlayer.setBounds(530 - 100, 360 - 50, 200, 50);
        bttMultiPlayer.setBounds(530 - 100, 360, 200, 50);
        bttIniciarSesion.setBounds(530 - 100, 360 + 50, 200, 50);
        bttCerrarSesion.setBounds(530 - 100, 360 + 50, 200, 50);
        bttRegistrarse.setBounds(530 - 100, 360 + 100, 200, 50);
        bttOpciones.setBounds(530 - 100, 360 + 150, 200, 50);
        bttSalir.setBounds(530 - 100, 360 + 200, 200, 50);

    }

}
