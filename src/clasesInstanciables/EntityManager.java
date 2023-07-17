package clasesInstanciables;

import clasesInstanciables.PowerUps.*;

import niveles.Level;

import java.awt.Graphics;
import java.util.ArrayList;

import clasesInstanciables.Enemigos.*;
import clasesInstanciables.Jugador.*;
import constantes.Constantes.ENTITY_TYPE.*;
import constantes.Constantes.PANTALLA;
import constantes.Constantes.ENTITY_TYPE;
import constantes.Constantes.Enemigos;
import constantes.Constantes.Jugador;
import graficos.GamePanel;

public class EntityManager {

    private GamePanel gamePanel;

    public Personaje mainCharacter;
    public ArrayList<Personaje> personajes = new ArrayList<>();
    public ArrayList<Enemigo> enemigos = new ArrayList<>();
    public ArrayList<Entidad> entidades = new ArrayList<>();

    public EntityManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        initialize();
    }

    public void render(Graphics g, int offset) {
        for (int i = 0; i < entidades.size(); i++) {
            entidades.get(i).updateFrames(g, offset);
        }
    }

    public void update() {
        // *se actualiza la logica de cada entidad */
        for (int i = 0; i < entidades.size(); i++) {
            entidades.get(i).update();
        }

        verifyEntityCollision();
    }

    public void verifyEntityCollision() {
        for (Enemigo entidad : enemigos) {
            entidad.update();
            entidad.recibirHit(mainCharacter);
            mainCharacter.HitEnemigo(entidad.getHitbox());
        }
    }

    // TODO hacer que reciba un int[][] que mande a spawnear todas las entidades de
    // un nivel dado
    public void initialize() {
        initializeMainCharacter();
    }

    public void initializeMainCharacter() {
        mainCharacter = new Personaje(PANTALLA.MarioDir, 100, 100, Jugador.MARIO_VELC);
        mainCharacter.loadLevelData(gamePanel.levelManager.getLevel().getLevelData());
        personajes.add(mainCharacter);
        entidades.add(mainCharacter);
    }

    public void spawn(ENEMIES entity, int tileX, int tileY, Level level) {

        tileX *= PANTALLA.TILES_ACTUAL_SIZE;
        tileY *= PANTALLA.TILES_ACTUAL_SIZE;

        switch (entity) {
            case GOOMBA:
                Goomba goomba = new Goomba(PANTALLA.GoombaDir, tileX, tileY, Enemigos.GOOMBA_VELC);
                enemigos.add(goomba);
                entidades.add(goomba);
                break;
            case KOOPA:
                Koopa koopa = new Koopa(PANTALLA.KoopaDir, tileX, tileY, Enemigos.KOOPA_VELC);
                enemigos.add(koopa);
                entidades.add(koopa);

                break;
            case KOOPA_VOLADOR:
                KoopaVolador koopaVolador = new KoopaVolador(PANTALLA.KoopaVoladorDir, tileX, tileY,
                        Enemigos.KOOPA_VOLADOR_VELC);
                enemigos.add(koopaVolador);
                entidades.add(koopaVolador);

                break;
            case BOWSER:
                Bowser bowser = new Bowser(PANTALLA.BowserDir, tileX, tileY, Enemigos.BOWSER_VELC);
                enemigos.add(bowser);
                entidades.add(bowser);

                break;

            default:
                break;
        }
    }

    public void spawn(CHARACTERS entity, int tileX, int tileY, Level level) {

        tileX *= PANTALLA.TILES_ACTUAL_SIZE;
        tileY *= PANTALLA.TILES_ACTUAL_SIZE;

        switch (entity) {
            case MARIO:
                Personaje jugador = new Personaje(PANTALLA.MarioDir, tileX, tileY, 2);
                jugador.loadLevelData(level.getLevelData());
                personajes.add(jugador);
                entidades.add(jugador);
                break;

            default:
                break;
        }
    }

    public Personaje getMainCharacter() {
        return mainCharacter;
    }
}
