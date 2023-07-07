package Graficos2;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ClasesPadre.Personaje;
import Inputs.InptutMouse;
import Inputs.InputTeclado;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.awt.Dimension;

public class GamePanel extends JPanel {

    private InptutMouse mouseimput = new InptutMouse(this);
    private int x = 0, y = 0;
    BufferedImage imagen;

    public GamePanel() {
        setPreferredSize(new Dimension(1280, 800));

        addKeyListener(new InputTeclado(this));
        addMouseListener(mouseimput);
        addMouseMotionListener(mouseimput);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagen, x, y, null);

    }

}
