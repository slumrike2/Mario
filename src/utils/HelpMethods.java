package utils;

import constantes.Constantes.PANTALLA;

public class HelpMethods {

    public static boolean canMoveHere(float x, float y, int width, int height, int[][] lvlData) {

        int x1 = (int) (x / PANTALLA.TILES_ACTUAL_SIZE);
        int y1 = (int) (y / PANTALLA.TILES_ACTUAL_SIZE);
        int x2 = (int) ((x + width) / PANTALLA.TILES_ACTUAL_SIZE);
        int y2 = (int) ((y + height) / PANTALLA.TILES_ACTUAL_SIZE);

        if (isSolid(x1, y1, lvlData))
            return false;

        if (isSolid(x2, y1, lvlData))
            return false;
        else if (isSolid(x2, y1 + 1, lvlData)) // Verificar tile del medio
            return false;

        if (isSolid(x1, y2, lvlData))
            return false;
        else if (isSolid(x1, y2 - 1, lvlData)) // Verificar tile del medio
            return false;

        if (isSolid(x2, y2, lvlData))
            return false;

        return true;
    }

    private static boolean isSolid(float x, float y, int[][] lvlData) {

        if (x <= 0 || x >= lvlData.length) {
            return true;
        }

        if (y <= 0 || y >= lvlData[0].length)
            return false;

        return lvlData[(int) x][(int) y] < 11 || lvlData[(int) x][(int) y] > 17;

    }

    public static boolean isInFloor(float x, float y, int width, int height, int[][] lvlData) {

        int x1 = (int) (x / PANTALLA.TILES_ACTUAL_SIZE);
        int x2 = (int) ((x + width) / PANTALLA.TILES_ACTUAL_SIZE);
        int y2 = (int) ((y + height) / PANTALLA.TILES_ACTUAL_SIZE);

        if (isSolid(x2, y2, lvlData) || isSolid(x1, y2, lvlData))
            return true;
        return false;

    }

}
