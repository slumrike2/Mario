package clasesInstanciables.Enemigos;

import clasesInstanciables.Entidad;
import constantes.Constantes.PANTALLA;
import static utils.HelpMethods.canMoveHere;
import static utils.HelpMethods.isInFloor;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Enemigo extends Entidad {

    protected int FramesInvensible = 10;
    protected int contFramesInvensible = 0;
    protected int gravedad = 2;
    protected int[][] currentLevelData;
    protected boolean enSuelo;
    

    public Enemigo(int Posx, int Posy) {
        super(Posx, Posy);

    }

    public abstract void recibirHit(Entidad ob);

    public void loadLevelData(int[][] currentLevelData) {
        this.currentLevelData = currentLevelData;
    }

    public void ActualizarHitbox() {
        InicializarHitbox();
        Hitbox.setLocation(posX, posY);
    }

    protected void DibujarHitboxWithOffset(Graphics g, int offset) {

        g.drawRect(Hitbox.x - offset, Hitbox.y, (int) Hitbox.getWidth(), (int) Hitbox.getHeight());
    }

    protected void VerificarSuelo(int[][] currentLevelData) {

        enSuelo = isInFloor(posX, posY + 1, (int) (anchura_Tiles * PANTALLA.TILES_ACTUAL_SIZE),
                (int) (altura_Tiles * PANTALLA.TILES_ACTUAL_SIZE), currentLevelData);
    }

}
