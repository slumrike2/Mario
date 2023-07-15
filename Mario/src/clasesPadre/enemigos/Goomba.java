package clasesPadre.enemigos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import clasesPadre.Entidad;
import constantes.constantes.PANTALLA;

public class Goomba extends Entidad {

    // Todo Faltan la muerte y las hitboxes
    BufferedImage[][] animaciones;

    private int AccionAnimation = 0, frameAniamcion = 0, contFrames = 0;
    public boolean Vivo = true, enMovimiento = true;

    public Goomba(String Dir, int Posx, int Posy, int velocidad) {
        super(Dir, Posx, Posy, velocidad, 1, 1);
        animaciones = animacion(3, 0, 2, 16, 16);
        velocidadAnimacion = 12;
        InicializarHitbox();
    }

    public void update() {

        movimiento();
        ActualizarAccion();
        ActualizarFrame();

    }

    public void updateFrames(Graphics g) {
        g.drawImage(animaciones[AccionAnimation][frameAniamcion], posX, posY,
                PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles,
                PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles, null);
        DibujarHitbox(g);
    }

    public void movimiento() {
        if (enMovimiento == true)
            posX -= velocidad;
    }

    public void cambiarDireccion() {
        velocidad *= -1;
    }

    public void ActualizarAccion() {
        AccionAnimation = 1;
        if (Vivo == false) {
            AccionAnimation = 2;
            return;
        }
        if (enMovimiento == false) {
            AccionAnimation = 0;
            return;
        }

    }

    public void ActualizarFrame() {
        contFrames++;

        if (contFrames >= velocidadAnimacion) {
            contFrames = 0;
            frameAniamcion++;
            if (Vivo == false) {
                frameAniamcion = 0;
                return;
            }
            if (enMovimiento == false) {
                frameAniamcion = 0;
                return;
            }
            if (frameAniamcion > 1) {
                frameAniamcion = 0;

            }

        }
    }

    protected void InicializarHitbox() {
        Hitbox = new java.awt.Rectangle(posX, posY, PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles,
                PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles);
    }
}
