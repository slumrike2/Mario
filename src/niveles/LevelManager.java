package niveles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import clasesInstanciables.EntityManager;
import graficos.GamePanel;

import constantes.Constantes.NIVEL;
import constantes.Constantes.PANTALLA;

public class LevelManager {

    GamePanel gamePanel;
    private BufferedImage[] levelSprite;
    private int lvlIndex;

    // todo change to an arraylist of levels
    private Level actualLevel;

    public LevelManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        lvlIndex = 1;
        actualLevel = new Level(NIVEL.NIVEL_1);

        levelSprite = actualLevel.getLevelSprite();
    }

    public void startLevelEntities(EntityManager entityManager) {
        entityManager.startLevelEntities(actualLevel);
    }

    public Level getLevel() {
        return actualLevel;
    }

    public void draw(Graphics g, int offset) {
        for (int i = 0; i < actualLevel.getLevelData().length; i++) {
            for (int j = 0; j < PANTALLA.TILES_IN_HEIGHT; j++) {
                int tile = actualLevel.getTile(i, j);
                g.drawImage(levelSprite[tile], i * PANTALLA.TILES_ACTUAL_SIZE - offset, j * PANTALLA.TILES_ACTUAL_SIZE,
                        PANTALLA.TILES_ACTUAL_SIZE, PANTALLA.TILES_ACTUAL_SIZE,
                        null);
            }
        }
    }
}
