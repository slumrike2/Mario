package Graficos2;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import ClasesPadre.Personaje;
import Inputs.InptutMouse;
import Inputs.InputTeclado;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.awt.Dimension;

public class GamePanel extends JPanel {

    private InptutMouse mouseimput = new InptutMouse(this);
    private int x = 0, y = 0, direccionanimacion = 1;
    public Personaje mario = new Personaje("res/marioAnimations.png", 0, 0);
    static int contador = 0;
    public BufferedImage aux;
    private int xanimacion;

    public GamePanel() {
        setPreferredSize(new Dimension(1280, 800));
        addKeyListener(new InputTeclado(this));
        addMouseListener(mouseimput);
        addMouseMotionListener(mouseimput);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        mario.updateFrames(g);
    }

    public void FrameUpdate() {
        mario.update();

    }

}
