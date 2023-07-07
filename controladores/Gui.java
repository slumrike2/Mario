package gui.controladores;

import java.awt.CardLayout;

import javax.swing.JFrame;

import gui.Pantallas.ManejoPeliculas.*;

import gui.Pantallas.Principales.*;

//por definicion todos los elementos de un enum son public static final
public enum Gui {
    INSTANCE;

    public enum PantallasPosibles {
        MENU_PRINCIPAL, REGISTRO, INICIO_SESION, MENU_GESTIONAR_COLECCION

    }

    private JFrame frame = new JFrame("GESTION_PELICULAS");
    CardLayout cl = new CardLayout(0, 0);

    public JFrame getFrame() {
        return frame;
    }

    public void inicializar() {
        frame.setLayout(cl);
        frame.add(new MenuPrincipal().getPanel(), "MenuPrincipal");
        frame.add(new Registro().getPanel(), "Registro");
        frame.add(new InicioSesion().getPanel(), "InicioSesion");
        frame.add(new MenuGestionarColeccion().getPanel(), "MenuGestionarColeccion");

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

    // WARNING PARA PRUEBAS
    public void inicializar(Pantalla pantalla) {
        frame.setLayout(cl);
        frame.add(pantalla.getPanel(), "MenuPrincipal");
        frame.add(new Registro().getPanel(), "Registro");

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void finalizar() {
        frame.dispose();
    }

    public void goTo(PantallasPosibles pantalla) {

        switch (pantalla) {
            case MENU_PRINCIPAL:
                cl.show(frame.getContentPane(), "MenuPrincipal");
                break;
            case REGISTRO:
                cl.show(frame.getContentPane(), "Registro");
                break;
            case INICIO_SESION:
                cl.show(frame.getContentPane(), "InicioSesion");
                break;
            case MENU_GESTIONAR_COLECCION: {
                MenuGestionarColeccion.actualizarTabla();
                cl.show(frame.getContentPane(), "MenuGestionarColeccion");
            }
                break;
            default:
                break;
        }
    }

}
