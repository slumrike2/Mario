package niveles;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import graficos.GamePanel;

import utils.LoadSave;
import Constantes.Constantes.PANTALLA;

public class LevelManager {

    GamePanel gamePanel;
    private static BufferedImage imagen;
    private BufferedImage[] levelSprite;
    private int lvlIndex;

    public LevelManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        importarImagen();
        importOutsideSprites();
    }

    private void importarImagen() {
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("res/level.png"));
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

    public static int[][] getLevelData() {
        int[][] levelData = new int[PANTALLA.TILES_IN_HEIGHT][PANTALLA.TILES_IN_WIDTH];
        BufferedImage img = LoadSave.GetLevelAtlas(LoadSave.LEVEL_ONE_DATA);
        return levelData;
    }

    public void draw(java.awt.Graphics g) {
        g.drawImage(imagen, 0, 0, null);
    }

}