package utils;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

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

}
