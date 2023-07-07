package Inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Graficos2.GamePanel;

public class InptutMouse implements MouseListener, MouseMotionListener {

    GamePanel panel;

    public InptutMouse(GamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse dragged");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("Mouse moved");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
