package game;

import databases.Sesion;
import databases.Usuario;
import graficos.GamePanel;
import graficos.Gui;

public class Game {
    GamePanel panel;
    Gui ventana;

    public Game() {

        ventana = new Gui();


        // panel.requestFocus();
        ventana.iniciar();
    }

    public static void main(String[] args) throws Exception {
        // ! - THIS IS A TEST
        Sesion.INSTANCE.crearSession(new Usuario("1", "1", "1"));
        // ! - THIS IS A TEST
        new Game();

    }
}
