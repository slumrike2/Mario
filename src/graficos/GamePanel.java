package graficos;

import javax.swing.JPanel;

import audio.AudioManager;
import clasesInstanciables.*;
import constantes.Constantes.PANTALLA;
import constantes.Constantes.ENTITY_TYPE.*;
import databases.ArchivoSerializable;
import databases.Sesion;
import databases.Usuario;
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
    public HUD userInterface;

    static int contador = 0;
    public BufferedImage aux;

    private int gameTime;
    private int framesElapsed;

    private int xlvlOffset;
    private int leftBorder = (int) (PANTALLA.SCREEN_WIDTH * 0.2);
    private int rightBorder = (int) (PANTALLA.SCREEN_WIDTH * 0.6);
    private int levelWide;
    private int maxLvlOffsetX;

    public GamePanel() {
        // Se cargan los audios
        audioManager = new AudioManager();
        // Se carga el nivel
        levelManager = new LevelManager(this);
        levelWide = levelManager.getLevel().getLevelData().length;
        maxLvlOffsetX = (levelWide - PANTALLA.TILES_IN_WIDTH) * PANTALLA.TILES_ACTUAL_SIZE;
        entityManager = new EntityManager(this);
        levelManager.startLevelEntities(entityManager);

        userInterface = new HUD(this);

       

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

        elapseTime();
    }

    // #region gameFunctions

    public void elapseTime() {
        framesElapsed++;
        if (framesElapsed == 120) {
            gameTime++;
            framesElapsed = 0;
        }

        if (gameTime == levelManager.getLevel().getMaxTime()) {
            audioManager.stopSong();
            entityManager.getMainCharacter().kill();
        }
    }

    public void restartGame() {
        audioManager.playSong(levelManager.getLvlIndex());
        gameTime = 0;
    }

    public void playEffects() {

        if (entityManager.getMainCharacter().saltando && entityManager.getMainCharacter().EnSuelo)
            audioManager.playEffect(AudioManager.JUMP);

        if (entityManager.getMainCharacter().getVidas() <= 0) {
            audioManager.playSong(AudioManager.DIE);
        } else {
            if (entityManager.getMainCharacter().getstarActive()) {
                audioManager.playSong(AudioManager.STAR);
            } else {
                audioManager.playSong(levelManager.getLvlIndex());
            }
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

    public void verifyLevelEnded() {
        if (entityManager.getMainCharacter().getHitbox().x > levelWide * PANTALLA.TILES_ACTUAL_SIZE - 50) {
            audioManager.stopSong();
            audioManager.playSong(AudioManager.STAGE_CLEAR);
            passNextLevel();
        }
    }

    public void passNextLevel() {
        if (levelManager.getLvlIndex() == levelManager.getLevels().length - 1) {
            writeScore();
            audioManager.stopAll();
            Gui.switchState();
            return;
        }
        xlvlOffset = 0;
        levelManager.nextLevel();
        levelWide = levelManager.getLevel().getLevelData().length;
        maxLvlOffsetX = (levelWide - PANTALLA.TILES_IN_WIDTH) * PANTALLA.TILES_ACTUAL_SIZE;
        entityManager.restart();
        entityManager.startLevelEntities(levelManager.getLevel());
    }

    public void writeScore() {
        Usuario usuario = Sesion.INSTANCE.getUsuario();
        ArchivoSerializable<Usuario> archivoSerializable = new ArchivoSerializable(usuario.getEstadisticas_fileName());
        usuario.setCantidadPartidasJugadas(usuario.getCantidadPartidasJugadas() + 1);
        if (entityManager.getMainCharacter().getVidas() > 0) {
            usuario.setCantidadPartidasGanadas(usuario.getCantidadPartidasGanadas() + 1);
        } else {
            usuario.setCantidadPartidasPerdidas(usuario.getCantidadPartidasPerdidas() + 1);
        }
        archivoSerializable.sobrescribirArchivo(usuario);

    }

    // #endregion

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        levelManager.draw(g, xlvlOffset);
        entityManager.render(g, xlvlOffset);
        userInterface.draw(g);
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

    public AudioManager getAudioManager() {
        return audioManager;
    }

    public void setAudioManager(AudioManager audioManager) {
        this.audioManager = audioManager;
    }

    public HUD getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(HUD userInterface) {
        this.userInterface = userInterface;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public int getFramesElapsed() {
        return framesElapsed;
    }

    public void setFramesElapsed(int framesElapsed) {
        this.framesElapsed = framesElapsed;
    }

    // #endregion

}
