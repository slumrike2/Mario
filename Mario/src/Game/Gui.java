package Game;

import java.awt.BorderLayout;
import java.awt.Canvas;

import javax.swing.JFrame;

import Graficos2.GamePanel;

public class Gui extends Canvas implements Runnable {
    // #region Variables
    // *se genera un serial para la clase
    static final long serialVersionUID = 1L;

    // *se genera un objeto de tipo JFrame
    static JFrame ventana;
    public GamePanel panel;

    public enum PantallasPosiblesMenu {
        MENU_PRINCIPAL, REGISTRO, INICIO_SESION, MENU_GESTIONAR_COLECCION
    }

    public enum PantallasPosiblesJuego {
        MUNDO_1, PANTALLA_2, PANTALLA_3, PANTALLA_4, PANTALLA_5
    }

    Thread hilo;

    private float FramesPorSegundo = 120.0f, TiempoPorActualizacion = 1000000000 / FramesPorSegundo;
    long referenciaActualizacion = System.nanoTime();
    private double tiempoTranscurrido, delta = 0;

    final int WIDTH = 1280; // *altura y anchura de la ventana
    final int HEIGHT = 800;
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
        this.panel = panel;

        // *para que la ventana se muestre
        ventana.setVisible(true);
        // *para que la ventana se muestre en el centro de la pantalla
        ventana.add(this, BorderLayout.CENTER);
    }

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
        panel.FrameUpdate();

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
                mostrar();
                actualizar();

                delta--;
            }

        }
    }

    // #endregion
}
