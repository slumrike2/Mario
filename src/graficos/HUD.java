package graficos;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import utils.LoadSave;

public class HUD {
    GamePanel gamePanel;
    private int score;
    private int coins;
    private int level;
    private int time;

    private BufferedImage[] numbers;
    private BufferedImage scoreText;
    private BufferedImage coinsText;
    private BufferedImage timeText;
    private BufferedImage levelText;

    public HUD(GamePanel gamePanel) {
        score = 0;
        coins = 0;
        level = 1;
        time = 60;
        loadTextImages();
        this.gamePanel = gamePanel;

    }

    private void drawNumber(Graphics g, int number, int x, int y, int maxDigits) {
        int digit = 0;
        int digits = (int) Math.floor(Math.log10(number)) + 1;
        int width = 16;
        int height = 16;
        int offset = 0;

        if (number == 0) {
            for (int i = 0; i < maxDigits; i++) {
                g.drawImage(numbers[0], x + offset, y, width, height, null);
                offset += width;
            }
            return;
        }

        for (int i = 0; i < maxDigits - digits; i++) {
            g.drawImage(numbers[0], x + offset, y, width, height, null);
            offset += width;
        }

        int[] digitsArray = new int[digits];

        for (int i = 0; i < digits; i++) {
            digitsArray[i] = number % 10;
            number = number / 10;
        }

        for (int i = digitsArray.length - 1; i >= 0; i--) {
            digit = digitsArray[i];
            g.drawImage(numbers[digit], x + offset, y, width, height, null);
            offset += width;
        }

    }

    private void actualizarNumeros() {
        level = gamePanel.getLevelManager().getLvlIndex() + 1;
        time = gamePanel.getLevelManager().getLevel().getMaxTime() - gamePanel.getGameTime();
        score = gamePanel.getEntityManager().getMainCharacter().getPuntaje();
        coins = gamePanel.getEntityManager().getMainCharacter().getMonedas();
    }

    public void draw(Graphics g) {

        actualizarNumeros();

        g.drawImage(scoreText, 64, 16, null);
        drawNumber(g, score, 50, 48, 8);

        g.drawImage(coinsText, scoreText.getWidth() + 100 + 64, 16, null);
        drawNumber(g, coins, scoreText.getWidth() + 100 + 64, 48, 2);

        g.drawImage(timeText, gamePanel.getWidth() - timeText.getWidth() - 64, 16, null);
        drawNumber(g, time, gamePanel.getWidth() - timeText.getWidth() - 64, 48, 3);

        g.drawImage(levelText, gamePanel.getWidth() - levelText.getWidth() - timeText.getWidth() - 200, 16, null);
        drawNumber(g, level, gamePanel.getWidth() - levelText.getWidth() - timeText.getWidth() - 200, 48, 2);

    }

    private void loadTextImages() {

        try {
            InputStream is = HUD.class.getResourceAsStream("res/numbers.png");
            BufferedImage imagen = ImageIO.read(is);

            numbers = new BufferedImage[10];
            for (int i = 0; i < 10; i++) {
                numbers[i] = imagen.getSubimage(i * 32, 0, 32, 32);
            }

            is = HUD.class.getResourceAsStream("res/PUNTOS.png");
            scoreText = ImageIO.read(is);

            is = HUD.class.getResourceAsStream("res/COINS.png");
            coinsText = ImageIO.read(is);

            is = HUD.class.getResourceAsStream("res/TIEMPO.png");
            timeText = ImageIO.read(is);

            is = HUD.class.getResourceAsStream("res/MUNDO.png");
            levelText = ImageIO.read(is);

        } catch (Exception e) {
            System.out.println("Error al cargar la imagen xd");

        }
    }
}
