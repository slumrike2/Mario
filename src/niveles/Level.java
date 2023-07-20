package niveles;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import clasesInstanciables.Spawner;
import constantes.Constantes.NIVEL;
import constantes.Constantes.PANTALLA;
import constantes.Constantes.ENTITY_TYPE.*;
import utils.LoadSave;

public class Level {
    private static BufferedImage levelAtlas;
    private BufferedImage[] levelSprite;

    private int levelData[][];
    private ArrayList<Spawner<ENEMIES>> enemySpawners = new ArrayList<>();
    private ArrayList<Spawner<ITEMS>> objectSpawners = new ArrayList<>();
    private ArrayList<Point> invisibleWalls = new ArrayList<>();
    private Spawner<CHARACTERS> characterSpawn;

    public Level(NIVEL nivel) {

        importarImagen(nivel.getPath());
        importOutsideSprites();
        LoadSave.loadLevelData(this, nivel.getPath());

    }

    private void importarImagen(String path) {
        try {
            levelAtlas = LoadSave.GetLevelAtlas(path + "levelAtlas.png");
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen");
        }
    }

    private void importOutsideSprites() {
        levelSprite = new BufferedImage[12];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                int index = i * 6 + j;
                levelSprite[index] = levelAtlas.getSubimage(j * PANTALLA.TILES_DEFAULT_SIZE,
                        i * PANTALLA.TILES_DEFAULT_SIZE, PANTALLA.TILES_DEFAULT_SIZE, PANTALLA.TILES_DEFAULT_SIZE);
            }
        }
    }

    public int getTile(int x, int y) {

        return levelData[x][y];
    }

    // #region Getters and Setters
    public int[][] getLevelData() {
        return levelData;
    }

    public void setLevelData(int[][] levelData) {
        this.levelData = levelData;
    }

    public BufferedImage[] getLevelSprite() {
        return levelSprite;
    }

    public void setLevelSprite(BufferedImage[] levelSprite) {
        this.levelSprite = levelSprite;
    }

    public static BufferedImage getLevelAtlas() {
        return levelAtlas;
    }

    public static void setLevelAtlas(BufferedImage levelAtlas) {
        Level.levelAtlas = levelAtlas;
    }

    public ArrayList<Spawner<ENEMIES>> getEnemySpawners() {
        return enemySpawners;
    }

    public void setEnemySpawners(ArrayList<Spawner<ENEMIES>> enemySpawners) {
        this.enemySpawners = enemySpawners;
    }

    public ArrayList<Spawner<ITEMS>> getObjectSpawners() {
        return objectSpawners;
    }

    public void setObjectSpawners(ArrayList<Spawner<ITEMS>> objectSpawners) {
        this.objectSpawners = objectSpawners;
    }

    public Spawner<CHARACTERS> getCharacterSpawn() {
        return characterSpawn;
    }

    public void setCharacterSpawn(Spawner<CHARACTERS> characterSpawn) {
        this.characterSpawn = characterSpawn;
    }

    public ArrayList<Point> getInvisibleWalls() {
        return invisibleWalls;
    }

    public void setInvisibleWalls(ArrayList<Point> invisibleWalls) {
        this.invisibleWalls = invisibleWalls;
    }

    // #endregion

}
