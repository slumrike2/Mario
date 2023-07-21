package graficos.pantallas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import constantes.*;
import constantes.Constantes.PANTALLA;
import databases.Sesion;
import graficos.Gui;

public class MenuPrincipal extends JPanel {

    JButton bttSinglePlayer = new JButton();
    JButton bttMultiPlayer = new JButton();
    JButton bttIniciarSesion = new JButton();
    JButton bttCerrarSesion = new JButton();
    JButton bttRegistrarse = new JButton();
    JButton bttSalir = new JButton();

    BufferedImage imagenFondo;
    BufferedImage imagenSinglePlayer;
    BufferedImage imagenIniciarSesion;
    BufferedImage imagenCerrarSesion;
    BufferedImage imagenRegistrarse;
    BufferedImage imagenSalir;

    public MenuPrincipal() {
        setPreferredSize(new Dimension(1080, 720));
        setLayout(null);

        loadImages();

        inicializarBotones();
        inicializarPosiciones();
        add(bttSinglePlayer);
        add(bttMultiPlayer);
        add(bttIniciarSesion);
        add(bttCerrarSesion);
        add(bttRegistrarse);
        add(bttSalir);

        SetBotonSesion();

        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(imagenFondo, 0, 0, null);

        g.drawImage(imagenSinglePlayer, PANTALLA.SCREEN_WIDTH / 2 - imagenSinglePlayer.getWidth() / 2, 300, null);

        if (Sesion.INSTANCE.isSesionIniciada())
            g.drawImage(imagenCerrarSesion, PANTALLA.SCREEN_WIDTH / 2 - imagenCerrarSesion.getWidth() / 2, 360, null);
        else
            g.drawImage(imagenIniciarSesion, PANTALLA.SCREEN_WIDTH / 2 - imagenIniciarSesion.getWidth() / 2, 360, null);

        g.drawImage(imagenRegistrarse, PANTALLA.SCREEN_WIDTH / 2 - imagenRegistrarse.getWidth() / 2, 420, null);

        g.drawImage(imagenSalir, PANTALLA.SCREEN_WIDTH / 2 - imagenSalir.getWidth() / 2, 480, null);

    }

    private void loadImages() {
        InputStream is = MenuPrincipal.class.getResourceAsStream("res/titleScreen.png");

        try {
            imagenFondo = ImageIO.read(is);

            is = MenuPrincipal.class.getResourceAsStream("res/jugar.png");
            imagenSinglePlayer = ImageIO.read(is);

            is = MenuPrincipal.class.getResourceAsStream("res/iniciarSesion.png");
            imagenIniciarSesion = ImageIO.read(is);

            is = MenuPrincipal.class.getResourceAsStream("res/cerrarSesion.png");
            imagenCerrarSesion = ImageIO.read(is);

            is = MenuPrincipal.class.getResourceAsStream("res/registrarse.png");
            imagenRegistrarse = ImageIO.read(is);

            is = MenuPrincipal.class.getResourceAsStream("res/cerrarJuego.png");
            imagenSalir = ImageIO.read(is);

        } catch (Exception e) {
            System.out.println("Error al cargar la imagen");
        }
    }

    private void inicializarPosiciones() {

        // Se coloca el boton a la mitad de la pantalla
        bttSinglePlayer.setBounds(PANTALLA.SCREEN_WIDTH / 2 - imagenSinglePlayer.getWidth() / 2, 300,
                imagenSinglePlayer.getWidth(), imagenSinglePlayer.getHeight());

        bttIniciarSesion.setBounds(PANTALLA.SCREEN_WIDTH / 2 - imagenIniciarSesion.getWidth() / 2, 360,
                imagenIniciarSesion.getWidth(), imagenIniciarSesion.getHeight());

        bttCerrarSesion.setBounds(PANTALLA.SCREEN_WIDTH / 2 - imagenCerrarSesion.getWidth() / 2, 360,
                imagenCerrarSesion.getWidth(), imagenCerrarSesion.getHeight());

        bttRegistrarse.setBounds(PANTALLA.SCREEN_WIDTH / 2 - imagenRegistrarse.getWidth() / 2, 420,
                imagenRegistrarse.getWidth(), imagenRegistrarse.getHeight());

        bttSalir.setBounds(PANTALLA.SCREEN_WIDTH / 2 - imagenSalir.getWidth() / 2, 480, imagenSalir.getWidth(),
                imagenSalir.getHeight());

        bttCerrarSesion.setVisible(false);

        // colocar los botones transparentes
        bttSinglePlayer.setBorderPainted(false);
        bttSinglePlayer.setFocusPainted(false);
        bttSinglePlayer.setContentAreaFilled(false);
        bttSinglePlayer.setOpaque(false);

        bttIniciarSesion.setBorderPainted(false);
        bttIniciarSesion.setFocusPainted(false);
        bttIniciarSesion.setContentAreaFilled(false);
        bttIniciarSesion.setOpaque(false);

        bttCerrarSesion.setBorderPainted(false);
        bttCerrarSesion.setFocusPainted(false);
        bttCerrarSesion.setContentAreaFilled(false);
        bttCerrarSesion.setOpaque(false);

        bttRegistrarse.setBorderPainted(false);
        bttRegistrarse.setFocusPainted(false);
        bttRegistrarse.setContentAreaFilled(false);
        bttRegistrarse.setOpaque(false);

        bttSalir.setBorderPainted(false);
        bttSalir.setFocusPainted(false);
        bttSalir.setContentAreaFilled(false);
        bttSalir.setOpaque(false);

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
        if (Sesion.INSTANCE.isSesionIniciada())
            Gui.switchState();
        else
            JOptionPane.showMessageDialog(null, "Inicie sesion para jugar", "Error", JOptionPane.ERROR_MESSAGE);

        // TODO crear menu en el que se seleccion la forma de juego
    }

    private void botonMultiPlayerActionPerformed() {
        // TODO crear sistema de multijugador, pipipipipi
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
        bttSalir.addActionListener(e -> botonSalirActionPerformed());

    }

}
