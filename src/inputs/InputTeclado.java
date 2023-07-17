package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import clasesInstanciables.Jugador.Personaje;
import clasesInstanciables.Jugador.Personaje.AccionPlayer;
import graficos.GamePanel;
import graficos.Gui;
import constantes.Constantes.Jugador;

public class InputTeclado implements KeyListener {

    public GamePanel gamePanel;
    public Personaje jugador;

    public InputTeclado(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.jugador = gamePanel.getEntityManager().getMainCharacter();

    }

    public void keyTyped(java.awt.event.KeyEvent e) {

    }

    // *se encarga de detectar las teclas presionadas
    public void keyPressed(java.awt.event.KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                jugador.MovIzquierda = true;
                jugador.direccion = -1;

                break;
            case KeyEvent.VK_W:
                jugador.MovArriba = true;
                break;
            case KeyEvent.VK_D:

                jugador.MovDerecha = true;
                jugador.direccion = 1;

                break;
            case KeyEvent.VK_S:
                if (jugador.EnSuelo) {
                    jugador.MovAbajo = true;
                    jugador.accion = AccionPlayer.Agacharse;
                }
                break;
            case KeyEvent.VK_SPACE:
                jugador.ActualizarSuelo();
                if (jugador.FuerzaSalto == 0 && jugador.EnSuelo && jugador.saltando == false) {
                    jugador.accion = AccionPlayer.Saltar;
                    jugador.saltando = true;
                    jugador.FuerzaSalto = Jugador.MARIO_JUMP_FORCE;
                }
                break;
            // !Para pruebas. Borrar
            case KeyEvent.VK_ESCAPE:
                Gui.switchState();

            default:
                break;
        }

    }

    // *se encarga de detectar las teclas soltadas
    public void keyReleased(java.awt.event.KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:

                jugador.MovIzquierda = false;
                if (jugador.MovDerecha && jugador.direccion == -1) {
                    jugador.direccion = 1;
                }

                break;
            case KeyEvent.VK_W:
                jugador.MovArriba = false;
                break;
            case KeyEvent.VK_D:
                jugador.MovDerecha = false;
                if (jugador.MovIzquierda && jugador.direccion == 1) {
                    jugador.direccion = -1;

                }
                break;
            case KeyEvent.VK_S:
                jugador.MovAbajo = false;
                break;
            case KeyEvent.VK_SPACE:

                break;
            default:

                break;
        }

    }

}
