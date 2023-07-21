package graficos;

import javax.swing.JPanel;

import constantes.Constantes.*;
import graficos.pantallas.InicioSesion;
import graficos.pantallas.MenuPrincipal;
import graficos.pantallas.Registro;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

//*Panel encargado de contener todos los paneles del menu */
//TODO Mejorar la vista de todos los paneles de menu
public class MenuPanel extends JPanel {

    // #region pantallas
    public static CardLayout cardLayout = new CardLayout();
    public static MenuPrincipal menuPrincipal = new MenuPrincipal();
    public static Registro registro = new Registro();
    public static InicioSesion inicioSesion = new InicioSesion();

    // #endregion

    static int contador = 0;
    public BufferedImage aux;

    public MenuPanel() {
        setPreferredSize(new Dimension(PANTALLA.SCREEN_WIDTH, PANTALLA.SCREEN_HEIGHT));
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
        menuPrincipal.paintComponent(g);
        registro.paintComponent(g);
        inicioSesion.paintComponent(g);

        // *se encarga de dibujar los frames del peronsaje
    }

    public void FrameUpdate() {

    }

}
