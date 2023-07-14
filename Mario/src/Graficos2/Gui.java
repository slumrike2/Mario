package Graficos2;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Menu;
import Constantes.Constantes;
import Graficos2.pantallas.menues.MenuPrincipal;
import databases.Sesion;

import javax.swing.JFrame;

public class Gui extends Canvas implements Runnable {
    // #region Variables
    // *se genera un serial para la clase
    static final long serialVersionUID = 1L;

    // *se genera un objeto de tipo JFrame
    static JFrame ventana;
    public static GamePanel panel;
    public static MenuPanel menu;
    public static GameState estadoJuego = GameState.MENU;
    public static CardLayout cardLayout = new CardLayout();

    public enum GameState {
        MENU, JUEGO
    }

    Thread hilo;

    private float FramesPorSegundo = 120, TiempoPorActualizacion = 1000000000 / FramesPorSegundo;
    long referenciaActualizacion = System.nanoTime();
    private double tiempoTranscurrido, delta = 0;

    public final static int WIDTH = 1080; // *altura y anchura de la ventana
    public final static int HEIGHT = 720;
    private static volatile boolean Corriendo = false;
    // #endregion

    public Gui(GamePanel panel) {
        // *se crea la ventana
        ventana = new JFrame("Mario");
        // *se le da un tamaño a la ventana
        ventana.setSize(WIDTH, HEIGHT);
        // *se le da un comportamiento al cerrar la ventana
        // !sin esto no se cierra y queda en ejecucion
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // *para que no se pueda cambiar el tamaño de la ventana
        ventana.setResizable(false);
        // *para que la ventana se centre en la pantalla
        ventana.setLayout(new BorderLayout());
        // *para que la ventana se muestre en el centro de la pantalla
        ventana.setLocationRelativeTo(null);
        ventana.add(panel);
        Gui.panel = panel;

        // *para que la ventana se muestre
        ventana.setVisible(true);
        // *para que la ventana se muestre en el centro de la pantalla
        ventana.add(this, BorderLayout.CENTER);
    }

    public Gui() {
        // *se crea la ventana
        ventana = new JFrame("Mario");
        // *se le da un tamaño a la ventana
        ventana.setSize(WIDTH, HEIGHT);
        // *se le da un comportamiento al cerrar la ventana
        // !sin esto no se cierra y queda en ejecucion
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // *para que no se pueda cambiar el tamaño de la ventana
        ventana.setResizable(false);
        // *para que la ventana se centre en la pantalla
        ventana.setLayout(cardLayout);
        // *para que la ventana se muestre en el centro de la pantalla
        ventana.setLocationRelativeTo(null);

        estadoJuego = GameState.MENU;
        menu = new MenuPanel();
        panel = new GamePanel();
        // ventana.add(panel, "Juego");
        ventana.add(menu, "Menu");
        menu.requestFocusInWindow();
        cardLayout.show(ventana.getContentPane(), "Menu");

        // *para que la ventana se muestre
        ventana.setVisible(true);
        // *para que la ventana se muestre en el centro de la pantalla
        ventana.add(this, BorderLayout.CENTER);

    }

    // #region funciones de cambio de pantalla y estado
    public static void switchState() {
        if (estadoJuego == GameState.JUEGO) {
            estadoJuego = GameState.MENU;
            ventana.remove(panel);
            panel = null;
            menu = new MenuPanel();
            ventana.add(menu, "Menu");
            cardLayout.show(ventana.getContentPane(), "Menu");
        } else {
            ventana.remove(menu);
            menu = null;
            panel = new GamePanel();
            ventana.add(panel, "Juego");
            cardLayout.show(ventana.getContentPane(), "Juego");
            panel.requestFocusInWindow();
            estadoJuego = GameState.JUEGO;
        }

    }

    public static void switchPantallaMenu(Constantes.Pantallas_Menues pantalla) {
        switch (pantalla) {
            case MENU_PRINCIPAL:
                MenuPanel.menuPrincipal.SetBotonSesion();
                MenuPanel.cardLayout.show(menu, "MenuPrincipal");
                break;
            case REGISTRO:
                MenuPanel.cardLayout.show(menu, "Registro");
                break;
            case INICIO_SESION:
                MenuPanel.cardLayout.show(menu, "InicioSesion");
                break;
            default:
                break;
        }
    }

    public static void salir() {
        System.exit(0);
    }

    // #endregion

    // #region ThreadManagers
    // todo Funcion que inicia el hilo y el juego
    public void iniciar() {
        Corriendo = true;
        // *se crea un hilo
        hilo = new Thread(this, "Graficos");
        // *se inicia el hilo
        hilo.start();
    }

    // todo Funcion que detiene el hilo y el juego
    public void detener() {
        Corriendo = false;
        try {
            hilo.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // #endregion

    // #region Actualizacion de pantalla
    // TODO movimientos y funcionamientos del juego
    public void actualizar() {
        // aps
        // *Llama a la actualizacion del panel en funcionalidad */
        if (estadoJuego == GameState.JUEGO) {
            panel.FrameUpdate();
        }

    }

    // TODO Funcion que dibuja en el juego
    public void mostrar() {
        // fps
        // * Se encarga de pintar los graficos
        ventana.repaint();

    }

    // todo Funcion que actualiza el juego
    public void run() {
        // ? Esto representara el sistema update*/
        while (Corriendo) {
            long inicioBucle = System.nanoTime();
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            delta += tiempoTranscurrido / TiempoPorActualizacion;

            while (delta >= 1) {

                actualizar();
                mostrar();
                delta--;
            }

        }
    }

    // #endregion
}
