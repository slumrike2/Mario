package niveles;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import clasesInstanciables.EntityManager;
import graficos.GamePanel;
import constantes.Constantes.NIVEL;
import constantes.Constantes.PANTALLA;

public class LevelManager {

    GamePanel gamePanel;
    private BufferedImage[] levelSprite;
    private Level[] levels;
    private int lvlIndex;

    // todo change to an arraylist of levels
    private Level actualLevel;

    public LevelManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        lvlIndex = 2;
        levels = new Level[4];

        levels[0] = new Level(NIVEL.NIVEL_1);
        levels[1] = new Level(NIVEL.NIVEL_2);
        levels[2] = new Level(NIVEL.NIVEL_3);
        levels[3] = new Level(NIVEL.NIVEL_4);

        actualLevel = levels[lvlIndex];

        levelSprite = actualLevel.getLevelSprite();
    }

    public void nextLevel() {
        lvlIndex++;
        actualLevel = levels[lvlIndex];
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
