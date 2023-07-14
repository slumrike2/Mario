package Graficos2;

import javax.swing.JPanel;

import Game.Gui;
import Graficos2.pantallas.menues.*;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

//*Panel encargado de contener todos los paneles del menu */
public class MenuPanel extends JPanel {

    // #region pantallas
    public static CardLayout cardLayout = new CardLayout();
    public static MenuPrincipal menuPrincipal = new MenuPrincipal();
    public static Registro registro = new Registro();
    public static InicioSesion inicioSesion = new InicioSesion();

    public enum Pantallas {
        MENU_PRINCIPAL, REGISTRO, INICIO_SESION
    }

    // #endregion

    static int contador = 0;
    public BufferedImage aux;

    public MenuPanel() {
        setPreferredSize(new Dimension(1080, 720));
        // ? se encargan de agregar los inputs

        setBackground(Color.BLACK);

        setLayout(cardLayout);
        add(menuPrincipal, "MenuPrincipal");
        add(registro, "Registro");
        add(inicioSesion, "InicioSesion");

        cardLayout.show(this, "MenuPrincipal");

        setFocusable(true);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // *se encarga de dibujar los frames del peronsaje
    }

    public void FrameUpdate() {

    }

}
