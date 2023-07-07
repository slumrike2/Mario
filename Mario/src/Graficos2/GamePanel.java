package Graficos2;

import javax.swing.JPanel;

import Inputs.InptutMouse;
import Inputs.InputTeclado;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Dimension;

public class GamePanel extends JPanel {

    private InptutMouse mouseimput = new InptutMouse(this);
    private int x = 0, y = 0;

    public GamePanel() {
        setPreferredSize(new Dimension(1280, 800));
        addKeyListener(new InputTeclado(this));
        addMouseListener(mouseimput);
        addMouseMotionListener(mouseimput);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // bueno pero mamamel guebo

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x += x;

    }

    public void setY(int y) {
        this.y += y;

    }

    public int getY() {
        return y;
    }
}
