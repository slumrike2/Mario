package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ClasesPadre.Personaje.AccionPlayer;
import ClasesPadre.Personaje.dirMov;
import Graficos2.GamePanel;

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
                panel.mario.direccion = dirMov.Izquierda;
                panel.mario.accion = AccionPlayer.Correr;
                panel.mario.enMovimiento = true;
                break;
            case KeyEvent.VK_W:
                panel.mario.direccion = dirMov.Arriba;
                break;
            case KeyEvent.VK_D:
                panel.mario.direccion = dirMov.Derecha;
                panel.mario.accion = AccionPlayer.Correr;
                panel.mario.enMovimiento = true;
                break;
            case KeyEvent.VK_S:
                panel.mario.direccion = dirMov.Abajo;
                panel.mario.accion = AccionPlayer.Agacharse;
                break;
            case KeyEvent.VK_SPACE:
                panel.mario.accion = AccionPlayer.Saltar;
                panel.mario.saltando = true;
                break;
            default:
                break;

        }

    }

    // *se encarga de detectar las teclas soltadas
    public void keyReleased(java.awt.event.KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:

                panel.mario.enMovimiento = false;
                panel.mario.accion = AccionPlayer.Quieto;

                break;
            case KeyEvent.VK_W:

                break;
            case KeyEvent.VK_D:

                panel.mario.enMovimiento = false;
                panel.mario.accion = AccionPlayer.Quieto;

                break;
            case KeyEvent.VK_S:

                break;
            case KeyEvent.VK_SPACE:
                panel.mario.saltando = false;

                break;
            default:

                break;
        }

    }

}
