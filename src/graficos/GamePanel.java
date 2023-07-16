package graficos;

import javax.swing.JPanel;

import clasesInstanciables.*;
import constantes.Constantes;
import constantes.Constantes.PANTALLA;
import inputs.InptutMouse;
import inputs.InputTeclado;
import clasesInstanciables.Enemigos.Bowser;
import clasesInstanciables.Enemigos.Enemigo;
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
    public Personaje jugador;
    public Goomba goomba;
    public Koopa koopa;
    public KoopaVolador koopaVolador;
    public Bowser bowser;
    public ArrayList<Enemigo> enemigos = new ArrayList<>();
    public ArrayList<Personaje> personajes = new ArrayList<>();
    public ArrayList<Entidad> entidades = new ArrayList<>();

    static int contador = 0;
    public BufferedImage aux;

    public LevelManager levelManager;

    public GamePanel() {
        // Se carga el nivel
        levelManager = new LevelManager(this);

        // Se cargan las entidades
        jugador = new Personaje(PANTALLA.MarioDir, 50, 50, 2);
        jugador.loadLevelData(levelManager.getLevel().getLevelData());

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
        personajes.add(jugador);
        enemigos.add(goomba);
        // entidades.add(koopa);
        // entidades.add(koopaVolador);
        // entidades.add(bowser);
        entidades.addAll(enemigos);
        entidades.addAll(personajes);
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
        }
        for (Personaje personaje : personajes) {
            for (Enemigo enemigo : enemigos) {
                personaje.HitEnemigo(enemigo.Hitbox);
                enemigo.recibirHit(personaje);
            }
        }

    }

}
