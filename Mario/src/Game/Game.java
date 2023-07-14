package game;

import databases.Sesion;
import databases.Usuario;
import graficos.GamePanel;
import graficos.Gui;

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
        Sesion.INSTANCE.crearSession(new Usuario("1", "1", "1"));
        new Game();

    }
}
