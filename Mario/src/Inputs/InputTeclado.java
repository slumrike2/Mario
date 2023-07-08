package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

                panel.mario.posX -= 10;
                panel.mario.moverDerecha = true;
                panel.mario.quieto = false;
                break;
            case KeyEvent.VK_W:
                panel.mario.posY -= 10;
                break;
            case KeyEvent.VK_D:
                panel.mario.posX += 10;
                panel.mario.moverDerecha = true;
                panel.mario.quieto = false;
                break;
            case KeyEvent.VK_S:
                panel.mario.posY += 10;
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("salto");
                break;
            default:
                break;
        }
    }

    public void keyReleased(java.awt.event.KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:

                panel.mario.posX -= 10;
                panel.mario.moverDerecha = true;
                panel.mario.quieto = false;
                break;
            case KeyEvent.VK_W:
                panel.mario.posY -= 10;
                break;
            case KeyEvent.VK_D:
                panel.mario.posX += 10;
                panel.mario.moverDerecha = true;
                panel.mario.quieto = false;
                break;
            case KeyEvent.VK_S:
                panel.mario.posY += 10;
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("salto");
                break;
            default:
                break;
        }
    }

}
