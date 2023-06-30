import java.awt.BorderLayout;
import java.awt.Canvas;

import javax.swing.JFrame;

public class Ventana extends Canvas implements Runnable {
    // *se genera un serial para la clase
    static final long serialVersionUID = 1L;
    // *se genera un objeto de tipo JFrame
    static JFrame ventana;

    Thread hilo;

    final int WIDTH = 800; // *altura y anchura de la ventana
    final int HEIGHT = 600;
    private static volatile boolean Corriendo = false;

    public Ventana() {
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
        // *para que la ventana se muestre
        ventana.setVisible(true);
        // *para que la ventana se muestre en el centro de la pantalla
        ventana.add(this, BorderLayout.CENTER);
    }

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

    public void actualizar() {

    }

    public void mostrar() {

    }

    // todo Funcion que actualiza el juego
    public void run() {
        // ? Esto representara el sistema update*/
        while (Corriendo) {

        }
    }
}
