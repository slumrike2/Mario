package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ClasesPadre.Jugador.Personaje.AccionPlayer;
import Game.Gui;
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
                panel.mario.MovIzquierda = true;

                break;
            case KeyEvent.VK_W:
                panel.mario.MovArriba = true;
                break;
            case KeyEvent.VK_D:

                panel.mario.MovDerecha = true;

                break;
            case KeyEvent.VK_S:
                panel.mario.MovAbajo = true;
                panel.mario.accion = AccionPlayer.Agacharse;
                break;
            case KeyEvent.VK_SPACE:
                panel.mario.accion = AccionPlayer.Saltar;
                panel.mario.saltando = true;
                panel.mario.EnSuelo = false;
                panel.mario.FuerzaSalto = 20;
                break;
            case KeyEvent.VK_ESCAPE:
                Gui.switchState();
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

                break;
            case KeyEvent.VK_W:
                panel.mario.MovArriba = false;
                break;
            case KeyEvent.VK_D:
                panel.mario.MovDerecha = false;

                break;
            case KeyEvent.VK_S:
                panel.mario.MovAbajo = false;
                break;
            case KeyEvent.VK_SPACE:
                panel.mario.saltando = false;

                break;
            default:

                break;
        }

    }

}
