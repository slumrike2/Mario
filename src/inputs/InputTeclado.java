package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import clasesInstanciables.Jugador.Personaje.AccionPlayer;
import graficos.GamePanel;
import graficos.Gui;
import constantes.Constantes.Jugador;

public class InputTeclado implements KeyListener {

    public GamePanel panel;

    public InputTeclado(GamePanel panel) {
        this.panel = panel;

    }

    public void keyTyped(java.awt.event.KeyEvent e) {

    }

    // *se encarga de detectar las teclas presionadas
    public void keyPressed(java.awt.event.KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                panel.jugador.MovIzquierda = true;
                panel.jugador.direccion = -1;

                break;
            case KeyEvent.VK_W:
                panel.jugador.MovArriba = true;
                break;
            case KeyEvent.VK_D:

                panel.jugador.MovDerecha = true;
                panel.jugador.direccion = 1;

                break;
            case KeyEvent.VK_S:
                if (panel.jugador.EnSuelo) {
                    panel.jugador.MovAbajo = true;
                    panel.jugador.accion = AccionPlayer.Agacharse;
                }
                break;
            case KeyEvent.VK_SPACE:
                panel.jugador.ActualizarSuelo();
                if (panel.jugador.FuerzaSalto == 0 && panel.jugador.EnSuelo && panel.jugador.saltando == false) {
                    panel.jugador.accion = AccionPlayer.Saltar;
                    panel.jugador.saltando = true;
                    panel.jugador.FuerzaSalto = Jugador.MARIO_JUMP_FORCE;
                }
                break;
            // !Para pruebas. Borrar
            case KeyEvent.VK_ESCAPE:
                Gui.switchState();
            case KeyEvent.VK_ENTER:
                panel.goomba.cambiarDireccion();
                break;

            default:
                break;
        }

    }

    // *se encarga de detectar las teclas soltadas
    public void keyReleased(java.awt.event.KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:

                panel.jugador.MovIzquierda = false;
                if (panel.jugador.MovDerecha && panel.jugador.direccion == -1) {
                    panel.jugador.direccion = 1;
                }

                break;
            case KeyEvent.VK_W:
                panel.jugador.MovArriba = false;
                break;
            case KeyEvent.VK_D:
                panel.jugador.MovDerecha = false;
                if (panel.jugador.MovIzquierda && panel.jugador.direccion == 1) {
                    panel.jugador.direccion = -1;

                }
                break;
            case KeyEvent.VK_S:
                panel.jugador.MovAbajo = false;
                break;
            case KeyEvent.VK_SPACE:

                break;
            default:

                break;
        }

    }

}
