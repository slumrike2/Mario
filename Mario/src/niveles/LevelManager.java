package niveles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import graficos.GamePanel;
import graficos.Gui;
import utils.LoadSave;
import constantes.Constantes.PANTALLA;

public class LevelManager {

    GamePanel gamePanel;
    private static BufferedImage imagen;
    private BufferedImage[] levelSprite;
    private int lvlIndex;

    private Level level1;

    public LevelManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        importarImagen();
        importOutsideSprites();

        lvlIndex = 1;
        level1 = new Level(LoadSave.getLevelData());
    }

    private void importarImagen() {
        try {
            imagen = LoadSave.GetLevelAtlas(LoadSave.LEVEL_ONE_IMAGE);
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen");
        }
    }

    private void importOutsideSprites() {
        BufferedImage loader = imagen;
        levelSprite = new BufferedImage[12];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                int index = i * 6 + j;
                levelSprite[index] = imagen.getSubimage(j * 16, i * 16, 16, 16);
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < PANTALLA.TILES_IN_HEIGHT; i++) {
            for (int j = 0; j < PANTALLA.TILES_IN_WIDTH; j++) {
                int tile = level1.getTile(j, i);
                    g.drawImage(levelSprite[tile], j * PANTALLA.TILES_DEFAULT_SIZE, i * PANTALLA.TILES_DEFAULT_SIZE,
                            PANTALLA.TILES_DEFAULT_SIZE, PANTALLA.TILES_DEFAULT_SIZE,
                            null);
                }
            }
        }
    }


