package graficos;

import javax.swing.JPanel;

import clasesInstanciables.*;
import constantes.Constantes;
import constantes.Constantes.PANTALLA;
import constantes.Constantes.ENEMY_TYPE;
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
import utils.LoadSave;

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
    public Spawner spawner = new Spawner(this);

    static int contador = 0;
    public BufferedImage aux;

    public LevelManager levelManager;

    private int xlvlOffset;
    private int leftBorder = (int) (PANTALLA.SCREEN_WIDTH * 0.2);
    private int rightBorder = (int) (PANTALLA.SCREEN_WIDTH * 0.8);
    private int levelWide = LoadSave.getLevelData().length;
    private int maxLvlOffsetX = (levelWide - PANTALLA.TILES_IN_WIDTH) * PANTALLA.TILES_ACTUAL_SIZE;

    public GamePanel() {
        // Se carga el nivel
        levelManager = new LevelManager(this);

        // Se cargan las entidades
        jugador = new Personaje(PANTALLA.MarioDir, 50, 50, 2);
        jugador.loadLevelData(levelManager.getLevel().getLevelData());

        goomba = new Goomba(PANTALLA.GoombaDir, 200, 100, Constantes.Enemigos.GOOMBA_VELC);
        koopa = new Koopa(PANTALLA.KoopaDir, 200, 200, -1);
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
        spawner.spawn(ENEMY_TYPE.GOOMBA, 41, 1);
        spawner.spawn(ENEMY_TYPE.BOWSER, 2, 1);
        spawner.spawn(ENEMY_TYPE.KOOPA_VOLADOR, 3, 1);
        spawner.spawn(ENEMY_TYPE.KOOPA, 4, 1);

        // enemigos.add(goomba);
        // // entidades.add(koopa);
        // // entidades.add(koopaVolador);
        // // entidades.add(bowser);
        // entidades.addAll(enemigos);

    }

    public void moveEntitieOffset() {

    }

    public void FrameUpdate() {
        veryfyCloseToBorder();

        for (Enemigo entidad : enemigos) {
            entidad.update();
            entidad.recibirHit(jugador);
            jugador.HitEnemigo(entidad.getHitbox());

        }
        jugador.update();
    }

    public void veryfyCloseToBorder() {

        int PlayerX = (int) jugador.getHitbox().x;
        int difference = PlayerX - xlvlOffset;

        if (difference > rightBorder) {
            xlvlOffset += difference - rightBorder;
        } else if (difference < leftBorder) {
            xlvlOffset += difference - leftBorder;
        }

        if (xlvlOffset < 0) {
            xlvlOffset = 0;
        } else if (xlvlOffset > maxLvlOffsetX) {
            xlvlOffset = maxLvlOffsetX;
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        levelManager.draw(g, xlvlOffset);
        jugador.updateFrames(g, xlvlOffset);
        // *se encarga de dibujar los frames del peronsaje
        for (Enemigo entidad : enemigos) {
            entidad.updateFrames(g, xlvlOffset);
        }
    }

    // #region Getters and Setters

    public InptutMouse getMouseimput() {
        return mouseimput;
    }

    public void setMouseimput(InptutMouse mouseimput) {
        this.mouseimput = mouseimput;
    }

    public Personaje getJugador() {
        return jugador;
    }

    public void setJugador(Personaje jugador) {
        this.jugador = jugador;
    }

    public Goomba getGoomba() {
        return goomba;
    }

    public void setGoomba(Goomba goomba) {
        this.goomba = goomba;
    }

    public Koopa getKoopa() {
        return koopa;
    }

    public void setKoopa(Koopa koopa) {
        this.koopa = koopa;
    }

    public KoopaVolador getKoopaVolador() {
        return koopaVolador;
    }

    public void setKoopaVolador(KoopaVolador koopaVolador) {
        this.koopaVolador = koopaVolador;
    }

    public Bowser getBowser() {
        return bowser;
    }

    public void setBowser(Bowser bowser) {
        this.bowser = bowser;
    }

    public ArrayList<Enemigo> getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(ArrayList<Enemigo> enemigos) {
        this.enemigos = enemigos;
    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }

    public ArrayList<Entidad> getEntidades() {
        return entidades;
    }

    public void setEntidades(ArrayList<Entidad> entidades) {
        this.entidades = entidades;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        GamePanel.contador = contador;
    }

    public BufferedImage getAux() {
        return aux;
    }

    public void setAux(BufferedImage aux) {
        this.aux = aux;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public void setLevelManager(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    public int getXlvlOffset() {
        return xlvlOffset;
    }

    public void setXlvlOffset(int xlvlOffset) {
        this.xlvlOffset = xlvlOffset;
    }

    public int getLeftBorder() {
        return leftBorder;
    }

    public void setLeftBorder(int leftBorder) {
        this.leftBorder = leftBorder;
    }

    public int getRightBorder() {
        return rightBorder;
    }

    public void setRightBorder(int rightBorder) {
        this.rightBorder = rightBorder;
    }

    public int getLevelWide() {
        return levelWide;
    }

    public void setLevelWide(int levelWide) {
        this.levelWide = levelWide;
    }

    public int getMaxLvlOffsetX() {
        return maxLvlOffsetX;
    }

    public void setMaxLvlOffsetX(int maxLvlOffsetX) {
        this.maxLvlOffsetX = maxLvlOffsetX;
    }

    // #endregion

}
