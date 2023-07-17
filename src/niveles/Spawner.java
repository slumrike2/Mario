package niveles;

import clasesInstanciables.PowerUps.*;
import clasesInstanciables.Enemigos.*;
import clasesInstanciables.Jugador.*;
import constantes.Constantes.ENEMY_TYPE;
import constantes.Constantes.PANTALLA;
import constantes.Constantes.Enemigos;
import graficos.GamePanel;

public class Spawner {

    private GamePanel gamePanel;

    public Spawner(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void spawn(ENEMY_TYPE enemyType, int tileX, int tileY) {

        tileX *= PANTALLA.TILES_ACTUAL_SIZE;
        tileY *= PANTALLA.TILES_ACTUAL_SIZE;

        switch (enemyType) {
            case GOOMBA:
                Goomba goomba = new Goomba(PANTALLA.GoombaDir, tileX, tileY, Enemigos.GOOMBA_VELC);
                gamePanel.getEnemigos().add(goomba);
                gamePanel.getEntidades().add(goomba);
                break;
            case KOOPA:
                Koopa koopa = new Koopa(PANTALLA.KoopaDir, tileX, tileY, Enemigos.KOOPA_VELC);
                gamePanel.getEnemigos().add(koopa);
                gamePanel.getEntidades().add(koopa);

                break;
            case KOOPA_VOLADOR:
                KoopaVolador koopaVolador = new KoopaVolador(PANTALLA.KoopaVoladorDir, tileX, tileY,
                        Enemigos.KOOPA_VOLADOR_VELC);
                gamePanel.getEnemigos().add(koopaVolador);
                gamePanel.getEntidades().add(koopaVolador);

                break;
            case BOWSER:
                Bowser bowser = new Bowser(PANTALLA.BowserDir, tileX, tileY, Enemigos.BOWSER_VELC);
                gamePanel.getEnemigos().add(bowser);
                gamePanel.getEntidades().add(bowser);

                break;
            default:
                break;
        }
    }

}
