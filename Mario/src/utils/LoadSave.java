package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import constantes.Constantes.PANTALLA;

public class LoadSave {

    public static String LEVEL_ONE_IMAGE = "res/level.png";
    public static String LEVEL_ONE_DATA = "res/levelData.png";

    public static BufferedImage GetLevelAtlas(String path) {
        BufferedImage imagen = null;
        InputStream is = LoadSave.class.getResourceAsStream(path);
        try {
            imagen = ImageIO.read(is);
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen");
        }
        return imagen;
    }

    public static int[][] getLevelData() {
        int[][] levelData = new int[PANTALLA.TILES_IN_HEIGHT][PANTALLA.TILES_IN_WIDTH];
        BufferedImage img = LoadSave.GetLevelAtlas(LoadSave.LEVEL_ONE_DATA);
        for (int i = 0; i < img.getWidth(); i++)
            for (int j = 0; j < img.getHeight(); j++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value > 12)
                    value = 0;
                levelData[j][i] = value;
            }
        return levelData;
    }

}
