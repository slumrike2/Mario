package clasesInstanciables;

import clasesInstanciables.PowerUps.*;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import audio.AudioManager;
import clasesInstanciables.Enemigos.*;
import clasesInstanciables.Jugador.*;
import constantes.Constantes.ENTITY_TYPE.*;
import constantes.Constantes.PANTALLA;
import constantes.Constantes;
import graficos.GamePanel;
import niveles.Level;

public class EntityManager {

    private GamePanel gamePanel;

    public Personaje mainCharacter;
    public ArrayList<Personaje> personajes = new ArrayList<>();
    public ArrayList<Enemigo> enemigos = new ArrayList<>();
    public ArrayList<Entidad> entidades = new ArrayList<>();
    public ArrayList<Recolectable> recolectables = new ArrayList<>();
    public ArrayList<FuegoProyectil> fuegoProyectils = new ArrayList<>();
    public ArrayList<BloqueMisterioso> bloquesMisteriosos = new ArrayList<>();

    public EntityManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
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

        if (mainCharacter.vivo == false) {
            restart();
            startLevelEntities(gamePanel.levelManager.getLevel());
            mainCharacter.vivo = true;
        }

        verifyEntityCollision();
    }

    public void verifyEntityCollision() {
        ArrayList<Entidad> Eliminar = new ArrayList<>();

        for (Enemigo entidad : enemigos) {
            if (entidad.vivo == true) {
                entidad.recibirHit(mainCharacter);
                mainCharacter.HitEnemigo(entidad.getHitbox());
                for (Enemigo enemigo : enemigos) {
                    if (enemigo != entidad) {
                        enemigo.recibirHit(entidad);
                    }
                }
            }
            if (entidad.vivo == false) {
                Eliminar.add(entidad);
            }
        }

        for (Recolectable recolectable : recolectables) {

            if (recolectable.Active == true) {
                recolectable.Colliision(mainCharacter);
                if (recolectable.Active == false) {
                    Eliminar.add(recolectable);
                }
            }
        }

        for (Personaje personaje : personajes) {
            if (personaje.disparar == true) {
                personaje.disparar = false;
                spawn(Constantes.ENTITY_TYPE.Proyectiles.BOLA_FUEGO, personaje.posX, personaje.posY,
                        personaje.direccion, gamePanel.levelManager.getLevel());

            }
        }

        for (FuegoProyectil fuegoProyectil : fuegoProyectils) {
            for (Enemigo enemigo : enemigos) {
                enemigo.recibirHit(fuegoProyectil);
            }
            if (fuegoProyectil.getActive() == false) {
                Eliminar.add(fuegoProyectil);
            }
        }

        for (BloqueMisterioso bloqueMisterioso : bloquesMisteriosos) {

            if (bloqueMisterioso.vivo == true) {
                bloqueMisterioso.recibirHit(mainCharacter);
                if (bloqueMisterioso.ItemSpawn != null) {
                    spawn(bloqueMisterioso.ItemSpawn, bloqueMisterioso.tileX, bloqueMisterioso.tileY - 1,
                            gamePanel.levelManager.getLevel());
                    bloqueMisterioso.ItemSpawn = null;
                    System.out.println("Spanear item");

                }
            }
        }



        for (Entidad entidad : Eliminar) {
            entidades.remove(entidad);
            // *coco */
            entidad.setPosY(10000);
            entidad.Hitbox.y = 10000;

            if (entidad instanceof Enemigo) {
                enemigos.remove(entidad);
            }

            if (entidad instanceof Recolectable) {
                recolectables.remove(entidad);
            }

            if (entidad instanceof FuegoProyectil) {
                fuegoProyectils.remove(entidad);
            }

            if (entidad instanceof BloqueMisterioso) {
                bloquesMisteriosos.remove(entidad);
            }



        }

    }

    // TODO hacer que reciba un int[][] que mande a spawnear todas las entidades de

    public void startLevelEntities(Level level) {

        Spawner<CHARACTERS> characterSpawn = level.getCharacterSpawn();
        ArrayList<Spawner<ITEMS>> objectSpawners = level.getObjectSpawners();
        ArrayList<Spawner<ENEMIES>> enemySpawners = level.getEnemySpawners();

        spawnMainCharacter(characterSpawn.getX(), characterSpawn.getY(), level);

        for (Spawner<ENEMIES> spawner : enemySpawners) {
            spawn(spawner.getEntity(), spawner.getX(), spawner.getY(), level);
        }

        for (Spawner<ITEMS> spawner : objectSpawners) {
            spawn(spawner.getEntity(), spawner.getX(), spawner.getY(), level);
        }

        gamePanel.audioManager.playSong(gamePanel.levelManager.getLvlIndex());

    }

    public void restart() {
        personajes.clear();
        enemigos.clear();
        entidades.clear();
        recolectables.clear();
        fuegoProyectils.clear();
    }

    public void spawnMainCharacter(int tileX, int tileY, Level level) {
        if (mainCharacter == null) {
            mainCharacter = new Personaje(PANTALLA.TILES_ACTUAL_SIZE * tileX, PANTALLA.TILES_ACTUAL_SIZE * tileY);
        } else {
            mainCharacter.restartStates();
            mainCharacter.setPosX(tileX * PANTALLA.TILES_ACTUAL_SIZE);
            mainCharacter.setPosY(tileY * PANTALLA.TILES_ACTUAL_SIZE);
        }
        mainCharacter.loadLevelData(gamePanel.levelManager.getLevel().getLevelData());
        entidades.add(mainCharacter);
        personajes.add(mainCharacter);
    }

    public void spawn(ENEMIES entity, int tileX, int tileY, Level level) {

        tileX *= PANTALLA.TILES_ACTUAL_SIZE;
        tileY *= PANTALLA.TILES_ACTUAL_SIZE;

        int[][] levelData = level.getLevelData();
        int[][] enemyLevelData = new int[levelData.length][levelData[0].length];

        for (int i = 0; i < levelData.length; i++) {
            for (int j = 0; j < levelData[0].length; j++) {
                enemyLevelData[i][j] = levelData[i][j];
            }
        }

        for (Point point : level.getInvisibleWalls()) {
            enemyLevelData[point.x][point.y] = 100;
        }

        switch (entity) {
            case GOOMBA:
                Goomba goomba = new Goomba(tileX, tileY);
                goomba.loadLevelData(enemyLevelData);
                enemigos.add(goomba);
                entidades.add(goomba);
                break;
            case KOOPA:
                Koopa koopa = new Koopa(tileX, tileY);
                enemigos.add(koopa);
                koopa.loadLevelData(enemyLevelData);
                entidades.add(koopa);

                break;
            case KOOPA_VOLADOR:
                KoopaVolador koopaVolador = new KoopaVolador(tileX, tileY);
                koopaVolador.loadLevelData(enemyLevelData);
                enemigos.add(koopaVolador);
                entidades.add(koopaVolador);

                break;
            case BOWSER:
                Bowser bowser = new Bowser(tileX, tileY);
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
                Personaje jugador = new Personaje(tileX, tileY);
                jugador.loadLevelData(level.getLevelData());
                personajes.add(jugador);
                entidades.add(jugador);
                break;

            default:
                break;
        }
    }

    public void spawn(ITEMS item, int tileX, int tileY, Level level) {
        tileX *= PANTALLA.TILES_ACTUAL_SIZE;
        tileY *= PANTALLA.TILES_ACTUAL_SIZE;

        switch (item) {
            case MONEDA:

                break;
            case FLOR:
                FlorFuego flor = new FlorFuego(tileX, tileY);
                recolectables.add(flor);
                entidades.add(flor);
                break;
            case ESTRELLA:
                Star star = new Star(tileX, tileY);
                recolectables.add(star);
                entidades.add(star);

                break;
            case HONGO:
                Hongo hongo = new Hongo(tileX, tileY);
                recolectables.add(hongo);
                entidades.add(hongo);
                break;
            case HONGO_VENENOSO:
                HongoEnvenenado hongoEnvenenado = new HongoEnvenenado(tileX, tileY);

                recolectables.add(hongoEnvenenado);
                entidades.add(hongoEnvenenado);
                break;
            case BLOQUE_MISTERIOSO:
                BloqueMisterioso bloqueMisterioso = new BloqueMisterioso(tileX, tileY);
                System.out.println("bloque misterioso creado + " + bloqueMisterioso.vivo);
                bloquesMisteriosos.add(bloqueMisterioso);
                entidades.add(bloqueMisterioso);
                break;
            default:
                break;
        }
    }

    public void spawn(Proyectiles proyectil, int tileX, int tileY, int direccion, Level level) {

        switch (proyectil) {
            case BOLA_FUEGO:
                if (fuegoProyectils.size() > 2)
                    return;

                gamePanel.audioManager.playEffect(AudioManager.FIREBALL);
                FuegoProyectil bolaFuego = new FuegoProyectil(tileX, tileY);
                bolaFuego.loadLevelData(gamePanel.levelManager.getLevel().getLevelData());
                bolaFuego.direccion = direccion;
                entidades.add(bolaFuego);
                fuegoProyectils.add(bolaFuego);
                break;

            default:
                break;
        }
    }

    public Personaje getMainCharacter() {
        return mainCharacter;
    }
}
