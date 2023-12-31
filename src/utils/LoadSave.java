package utils;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import clasesInstanciables.Spawner;
import constantes.Constantes.ENTITY_TYPE;
import constantes.Constantes.ENTITY_TYPE.*;
import graficos.GamePanel;
import graficos.Gui;
import niveles.Level;

public class LoadSave {

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

    public static void loadLevelData(Level level, String path) {

        BufferedImage img = LoadSave.GetLevelAtlas(path + "levelData.png");
        Spawner<CHARACTERS> characterSpawn = null;
        ArrayList<Spawner<ITEMS>> objectSpawners = new ArrayList<Spawner<ITEMS>>();
        ArrayList<Spawner<ENEMIES>> enemySpawners = new ArrayList<Spawner<ENEMIES>>();
        ArrayList<Point> inviBlocks = new ArrayList<Point>();
        ArrayList<Point> misteryBlocks = new ArrayList<Point>();
        ArrayList<Point> coins = new ArrayList<Point>();

        int[][] levelData = new int[img.getWidth()][img.getHeight()];

        for (int i = 0; i < img.getWidth(); i++)
            for (int j = 0; j < img.getHeight(); j++) {

                Color color = new Color(img.getRGB(i, j));

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                if (red + blue + green == 0) {
                    levelData[i][j] = 11;
                    characterSpawn = new Spawner<>(i, j, 2021, ENTITY_TYPE.CHARACTERS.MAIN);
                } else {
                    if (red <= 23) {
                        levelData[i][j] = red;
                        if (red == 2)
                            misteryBlocks.add(new Point(i, j));
                        if (red == 8) {
                            coins.add(new Point(i, j));
                            levelData[i][j] = 11;
                        }
                    } else {
                        levelData[i][j] = 11;

                        if (blue + red + green == 255 * 3) // white
                            inviBlocks.add(new Point(i, j));
                        if (green <= 7)
                            objectSpawners.add(new Spawner<>(i, j, green, ENTITY_TYPE.ITEMS.values()[green]));
                        if (blue <= 7)
                            enemySpawners.add(new Spawner<>(i, j, blue, ENTITY_TYPE.ENEMIES.values()[blue]));
                    }
                }
            }

        level.setLevelData(levelData);
        level.setCharacterSpawn(characterSpawn);
        level.setObjectSpawners(objectSpawners);
        level.setEnemySpawners(enemySpawners);
        level.setInvisibleWalls(inviBlocks);
        level.setMisteryBlocks(misteryBlocks);
        level.setCoins(coins);

    }

}
