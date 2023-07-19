package graficos;

import javax.swing.JPanel;

import clasesInstanciables.*;
import constantes.Constantes.PANTALLA;
import constantes.Constantes.ENTITY_TYPE.*;
import inputs.InptutMouse;
import inputs.InputTeclado;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import niveles.*;
import utils.LoadSave;

public class GamePanel extends JPanel {

    private InptutMouse mouseimput = new InptutMouse(this);

    public EntityManager entityManager;

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
        entityManager = new EntityManager(this);

        setPreferredSize(new Dimension(PANTALLA.SCREEN_WIDTH, PANTALLA.SCREEN_HEIGHT));
        // ? se encargan de agregar los inputs
        addKeyListener(new InputTeclado(this));
        addMouseListener(mouseimput);
        addMouseMotionListener(mouseimput);
        setFocusable(true);

        entityManager.spawn(ENEMIES.GOOMBA, 3, 12, levelManager.getLevel());
        entityManager.spawn(ENEMIES.KOOPA, 10, 8, levelManager.getLevel());
        entityManager.spawn(ENEMIES.KOOPA_VOLADOR, 10, 10, levelManager.getLevel());
        entityManager.spawn(ENEMIES.BOWSER, 10, 6, levelManager.getLevel());
        entityManager.spawn(ITEMS.HONGO, 5, 10, levelManager.getLevel());
        entityManager.spawn(ITEMS.HONGO_VENENOSO, 10, 10, levelManager.getLevel());

    }

    public void moveEntitieOffset() {

    }

    public void FrameUpdate() {
        veryfyCloseToBorder();
        entityManager.update();

    }

    public void veryfyCloseToBorder() {

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
