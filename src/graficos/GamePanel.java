package graficos;

import javax.swing.JPanel;

import clasesInstanciables.*;
import constantes.Constantes;
import constantes.Constantes.PANTALLA;
import inputs.InptutMouse;
import inputs.InputTeclado;
import clasesInstanciables.Enemigos.Bowser;
import clasesInstanciables.Enemigos.Koopa;
import clasesInstanciables.Enemigos.Goomba;
import clasesInstanciables.Enemigos.KoopaVolador;
import clasesInstanciables.Jugador.Personaje;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.util.ArrayList;
import niveles.*;

public class GamePanel extends JPanel {

    private InptutMouse mouseimput = new InptutMouse(this);
    // !warning, cambiar en un futuro lo de las direcciones
    public Personaje mario;
    public Goomba goomba;
    public Koopa koopa;
    public KoopaVolador koopaVolador;
    public Bowser bowser;
    public ArrayList<Entidad> entidades = new ArrayList<Entidad>();

    static int contador = 0;
    public BufferedImage aux;

    public LevelManager levelManager;

    public GamePanel() {
        // Se carga el nivel
        levelManager = new LevelManager(this);

        // Se cargan las entidades
        mario = new Personaje(PANTALLA.MarioDir, 50, 50, 2);
        mario.loadLevelData(levelManager.getLevel().getLevelData());

        goomba = new Goomba(PANTALLA.GoombaDir, 500, 300, Constantes.Enemigos.GOOMBA_VELC);
        koopa = new Koopa(PANTALLA.KoopaDir, 400, 400, -1);
        koopaVolador = new KoopaVolador(PANTALLA.KoopaVoladorDir, 50, 100, 1);
        bowser = new Bowser(PANTALLA.BowserDir, 300, 300, 1);

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
        // entidades.add(goomba);
        // entidades.add(koopa);
        // entidades.add(koopaVolador);
        // entidades.add(bowser);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        levelManager.draw(g);
        // *se encarga de dibujar los frames del peronsaje
        for (Entidad entidad : entidades) {

            entidad.updateFrames(g);
        }
    }

    public void FrameUpdate() {
        for (Entidad entidad : entidades) {
            entidad.update();
            if (!(entidad instanceof Personaje)) {

                mario.HitEnemigo(entidad.getHitbox());
            }
        }

    }

}
