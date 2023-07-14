package Graficos2;

import javax.swing.JPanel;
import ClasesPadre.Enemigos.Goomba;
import ClasesPadre.Enemigos.Koopa;
import ClasesPadre.Enemigos.KoopaVolador;
import ClasesPadre.Enemigos.Bowser;
import ClasesPadre.Jugador.Personaje;
import Constantes.Constantes.*;
import Inputs.InptutMouse;
import Inputs.InputTeclado;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.util.ArrayList;
import ClasesPadre.Entidad;

public class GamePanel extends JPanel {

    private InptutMouse mouseimput = new InptutMouse(this);
    // !warning, cambiar en un futuro lo de las direcciones
    public Personaje mario = new Personaje(PANTALLA.MarioDir, 300, 300, 2);
    public Goomba goomba = new Goomba(PANTALLA.GoombaDir, 500, 500, 1);
    public Koopa koopa = new Koopa(PANTALLA.KoopaDir, 400, 400, -1);
    public KoopaVolador koopaVolador = new KoopaVolador(PANTALLA.KoopaVoladorDir, 50, 50, 1);
    public Bowser bowser = new Bowser(PANTALLA.BowserDir, 50, 50, 1);
    public ArrayList<Entidad> entidades = new ArrayList<Entidad>();

    static int contador = 0;
    public BufferedImage aux;

    public GamePanel() {
        setPreferredSize(new Dimension(PANTALLA.SCREEN_WIDTH, PANTALLA.SCREEN_HEIGHT));
        // ? se encargan de agregar los inputs
        addKeyListener(new InputTeclado(this));
        addMouseListener(mouseimput);
        addMouseMotionListener(mouseimput);
        setFocusable(true);
        InicializarEntiendades();
    }

    public void InicializarEntiendades() {
        entidades.add(mario);
        entidades.add(goomba);
        entidades.add(koopa);
        entidades.add(koopaVolador);
        entidades.add(bowser);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // *se encarga de dibujar los frames del peronsaje
        for (Entidad entidad : entidades) {
            entidad.updateFrames(g);
        }
    }

    public void FrameUpdate() {
        for (Entidad entidad : entidades) {
            entidad.update();
        }

    }

}
