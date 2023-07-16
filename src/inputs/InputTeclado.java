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
                panel.mario.MovIzquierda = true;
                panel.mario.direccion = -1;

                break;
            case KeyEvent.VK_W:
                panel.mario.MovArriba = true;
                break;
            case KeyEvent.VK_D:

                panel.mario.MovDerecha = true;
                panel.mario.direccion = 1;

                break;
            case KeyEvent.VK_S:
                if (panel.mario.EnSuelo) {
                    panel.mario.MovAbajo = true;
                    panel.mario.accion = AccionPlayer.Agacharse;
                }
                break;
            case KeyEvent.VK_SPACE:
                panel.mario.ActualizarSuelo();
                if (panel.mario.FuerzaSalto == 0 && panel.mario.EnSuelo && panel.mario.saltando == false) {
                    panel.mario.accion = AccionPlayer.Saltar;
                    panel.mario.saltando = true;
                    panel.mario.FuerzaSalto = Jugador.MARIO_JUMP_FORCE;
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

                panel.mario.MovIzquierda = false;
                if (panel.mario.MovDerecha && panel.mario.direccion == -1) {
                    panel.mario.direccion = 1;
                }

                break;
            case KeyEvent.VK_W:
                panel.mario.MovArriba = false;
                break;
            case KeyEvent.VK_D:
                panel.mario.MovDerecha = false;
                if (panel.mario.MovIzquierda && panel.mario.direccion == 1) {
                    panel.mario.direccion = -1;

                }
                break;
            case KeyEvent.VK_S:
                panel.mario.MovAbajo = false;
                break;
            case KeyEvent.VK_SPACE:

                break;
            default:

                break;
        }

    }

}
