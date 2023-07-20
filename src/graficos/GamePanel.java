package graficos;

import javax.swing.JPanel;

import audio.AudioManager;

import clasesInstanciables.*;
import constantes.Constantes.PANTALLA;
import constantes.Constantes.ENTITY_TYPE.*;
import inputs.InptutMouse;
import inputs.InputTeclado;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import niveles.*;

public class GamePanel extends JPanel {

    private InptutMouse mouseimput = new InptutMouse(this);

    public EntityManager entityManager;
    public LevelManager levelManager;
    public AudioManager audioManager;

    static int contador = 0;
    public BufferedImage aux;

    private int xlvlOffset;
    private int leftBorder = (int) (PANTALLA.SCREEN_WIDTH * 0.2);
    private int rightBorder = (int) (PANTALLA.SCREEN_WIDTH * 0.6);
    private int levelWide;
    private int maxLvlOffsetX;

    public GamePanel() {
        // Se carga el nivel
        levelManager = new LevelManager(this);
        levelWide = levelManager.getLevel().getLevelData().length;
        maxLvlOffsetX = (levelWide - PANTALLA.TILES_IN_WIDTH) * PANTALLA.TILES_ACTUAL_SIZE;
        entityManager = new EntityManager(this);
        levelManager.startLevelEntities(entityManager);

        // Se cargan los audios
        audioManager = new AudioManager();

        entityManager.spawn(ITEMS.BLOQUE_MISTERIOSO, 4, 15, levelManager.getLevel());

        setPreferredSize(new Dimension(PANTALLA.SCREEN_WIDTH, PANTALLA.SCREEN_HEIGHT));
        // ? se encargan de agregar los inputs
        addKeyListener(new InputTeclado(this));
        addMouseListener(mouseimput);
        addMouseMotionListener(mouseimput);
        setFocusable(true);

    }

    public void FrameUpdate() {
        verifyLevelEnded();
        verifyCloseToBorder();
        playEffects();
        entityManager.update();
    }

    public void playEffects() {
        if (entityManager.getMainCharacter().saltando && entityManager.getMainCharacter().EnSuelo)
            audioManager.playEffect(AudioManager.JUMP);
        if (entityManager.getMainCharacter().getVidas() <= 0 && !audioManager.isSongMute()) {
            audioManager.stopSong();
            audioManager.playEffect(AudioManager.DIE);
        }
    }

    public void verifyCloseToBorder() {

        int PlayerX = (int) entityManager.getMainCharacter().getHitbox().x;

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
        entityManager.render(g, xlvlOffset);
    }

    public void verifyLevelEnded() {
        if (entityManager.getMainCharacter().getHitbox().x > levelWide * PANTALLA.TILES_ACTUAL_SIZE - 50)
            passNextLevel();
    }

    public void passNextLevel() {
        xlvlOffset = 0;
        levelManager.nextLevel();
        levelWide = levelManager.getLevel().getLevelData().length;
        maxLvlOffsetX = (levelWide - PANTALLA.TILES_IN_WIDTH) * PANTALLA.TILES_ACTUAL_SIZE;
        entityManager.restart();
        entityManager.startLevelEntities(levelManager.getLevel());
    }

    // #region Getters and Setters

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public InptutMouse getMouseimput() {
        return mouseimput;
    }

    public void setMouseimput(InptutMouse mouseimput) {
        this.mouseimput = mouseimput;
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
