package niveles;

public class Level {
    private int levelData[][];

    public Level(int[][] levelData) {
        this.levelData = levelData;
    }

    public int getTile(int x, int y) {
        return levelData[x][y];
    }

    public int[][] getLevelData() {
        return levelData;
    }

}
