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
    public Personaje mario = new Personaje("res/mario.png");
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
        contador++;
        if (mario.quieto) {
            aux = mario.quietoder;
        }
        if (contador >= mario.velocidadAnimacion && mario.moverDerecha) {
            contador = 0;
            if (xanimacion == 2) {
                direccionanimacion = -1;
            }
            if (xanimacion == 0) {
                direccionanimacion = 1;
            }

            xanimacion += direccionanimacion;
            aux = mario.animacionCaminarGrande[xanimacion];
        }

        g.drawImage(aux, mario.posicionX, mario.posicionY, null);

    }

}
