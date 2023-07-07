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
                
                
                break;
            case KeyEvent.VK_W:
                
                break;
            case KeyEvent.VK_D:

                
                break;
            case KeyEvent.VK_S:
                
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
                System.out.println("izquierda");
                
                break;
            case KeyEvent.VK_W:
            
                break;
            case KeyEvent.VK_D:

              
                break;
            case KeyEvent.VK_S:
               
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("salto");
                break;
            default:
                break;
        }
    }

}
