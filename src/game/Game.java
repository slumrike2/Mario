package game;

import databases.Sesion;
import databases.Usuario;
import graficos.GamePanel;
import graficos.Gui;
import networking.Client;
import networking.Server;

public class Game {
    GamePanel panel;
    Gui ventana;
    Client client;

    public Game() {

        panel = new GamePanel();
        ventana = new Gui();

        try {
            client = new Client();
        } catch (Exception e) {
        }

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
