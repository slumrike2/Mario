package Game;

import Graficos2.GamePanel;

public class Game {
    GamePanel panel;
    Gui ventana;

    public Game() {

        panel = new GamePanel();
        ventana = new Gui();
        // panel.requestFocus();
        ventana.iniciar();
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
    }
}
