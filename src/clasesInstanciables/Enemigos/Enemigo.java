package clasesInstanciables.Enemigos;

import clasesInstanciables.Entidad;
import clasesInstanciables.Jugador.FuegoProyectil;
import clasesInstanciables.Jugador.Personaje;
import constantes.Constantes.PANTALLA;

import static utils.HelpMethods.isInFloor;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Enemigo extends Entidad {

    protected int FramesInvensible = 10;
    protected int contFramesInvensible = 0;
    protected int gravedad = 1;
    protected int[][] currentLevelData;
    protected boolean enSuelo;

    protected int puntajeDado;

    public Enemigo(int Posx, int Posy) {
        super(Posx, Posy);

    }

    public void update() {
        super.update();
    }

    public void recibirHit(Entidad ob) {
        if (ob instanceof Personaje) {
            if (contFramesInvensible < FramesInvensible)
                return;
            Personaje personaje = (Personaje) ob;
            if (personaje.Hitbox.intersects(this.Hitbox) && personaje.getstarActive())
                vidas = -1;

            if (personaje.Hitbox.intersects(this.Hitbox)
                    && personaje.posY + personaje.Hitbox.height < this.posY + this.Hitbox.height
                    && personaje.vivo && vidas > 0 && personaje.getVidas() > 0) {
                vidas--;
                contFramesInvensible = 0;

                return;
            }
            if (personaje.Hitbox.intersects(this.Hitbox) && personaje.vivo && vidas == 0) {
                vidas = 1;
                contFramesInvensible = 0;
            }
        }

        if (ob instanceof FuegoProyectil) {
            FuegoProyectil proyectil = (FuegoProyectil) ob;
            if (proyectil.Hitbox.intersects(this.Hitbox) && proyectil.getActive()) {

                vidas--;

                proyectil.setActive(false);
                System.out.println(vidas);
            }
        }
        if (ob instanceof Koopa || ob instanceof KoopaVolador) {
            Koopa koopa = (Koopa) ob;
            if (koopa.Hitbox.intersects(this.Hitbox) && koopa.vidas == 1) {
                this.vidas = 0;
            }
        }

    }

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

    //region getters and setters
    public int getFramesInvensible() {
        return FramesInvensible;
    }

    public void setFramesInvensible(int framesInvensible) {
        FramesInvensible = framesInvensible;
    }

    public int getContFramesInvensible() {
        return contFramesInvensible;
    }

    public void setContFramesInvensible(int contFramesInvensible) {
        this.contFramesInvensible = contFramesInvensible;
    }

    public int getGravedad() {
        return gravedad;
    }

    public void setGravedad(int gravedad) {
        this.gravedad = gravedad;
    }

    public int[][] getCurrentLevelData() {
        return currentLevelData;
    }

    public void setCurrentLevelData(int[][] currentLevelData) {
        this.currentLevelData = currentLevelData;
    }

    public boolean isEnSuelo() {
        return enSuelo;
    }

    public void setEnSuelo(boolean enSuelo) {
        this.enSuelo = enSuelo;
    }

    public int getPuntajeDado() {
        return puntajeDado;
    }

    public void setPuntajeDado(int puntajeDado) {
        this.puntajeDado = puntajeDado;
    }


    //endregion 

    
}
