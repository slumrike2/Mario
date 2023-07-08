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

    public void keyPressed(java.awt.event.KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                panel.mario.direccion = dirMov.Izquierda;
                panel.mario.accion = AccionPlayer.Correr;
                break;
            case KeyEvent.VK_W:
                panel.mario.direccion = dirMov.Arriba;
                break;
            case KeyEvent.VK_D:
                panel.mario.direccion = dirMov.Derecha;
                panel.mario.accion = AccionPlayer.Correr;
                break;
            case KeyEvent.VK_S:
                panel.mario.direccion = dirMov.Abajo;
                panel.mario.accion = AccionPlayer.Agacharse;
                break;
            case KeyEvent.VK_SPACE:
                panel.mario.accion = AccionPlayer.Saltar;
                break;
            default:
                break;

        }

    }

    public void keyReleased(java.awt.event.KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:

                break;
            case KeyEvent.VK_W:

                break;
            case KeyEvent.VK_D:

                break;
            case KeyEvent.VK_S:

                break;
            case KeyEvent.VK_SPACE:

                break;
            default:
                break;
        }
        panel.mario.accion = AccionPlayer.Quieto;
    }

}
