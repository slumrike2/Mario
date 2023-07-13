package Graficos2;

import javax.swing.JPanel;

import ClasesPadre.Enemigos.Goomba;
import ClasesPadre.Jugador.Personaje;
import Inputs.InptutMouse;
import Inputs.InputTeclado;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

import java.awt.Dimension;

public class GamePanel extends JPanel {

    private InptutMouse mouseimput = new InptutMouse(this);
    public Personaje mario = new Personaje("res/marioAnimations.png", 300, 300, 2);
    public Goomba goomba = new Goomba("Sprites/cusGoombaSprite.png", 500, 500, 1);
    static int contador = 0;
    public BufferedImage aux;

    public GamePanel() {
        setPreferredSize(new Dimension(1280, 800));
        // ? se encargan de agregar los inputs
        addKeyListener(new InputTeclado(this));
        addMouseListener(mouseimput);
        addMouseMotionListener(mouseimput);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // *se encarga de dibujar los frames del peronsaje
        mario.updateFrames(g);
        goomba.updateFrames(g);
    }

    public void FrameUpdate() {
        mario.update();
        goomba.update();

    }

}
