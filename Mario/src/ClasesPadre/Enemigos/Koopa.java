package ClasesPadre.Enemigos;

import ClasesPadre.Entidad;

public class Koopa extends Entidad {

    public Koopa(String Dir) {
        super(Dir);
        velocidad = 1;
        velocidadAnimacion = 12;
    }

    public Koopa(String Dir, int Posx, int Posy, int velocidad) {
        super(Dir, Posx, Posy, velocidad);
        animaciones = animacion(3, 0, 2, 16, 32);
        velocidadAnimacion = 12;
    }

    public void update() {

        movimiento();
        ActualizarAccion();
        ActualizarFrame();
    }

    public void movimiento() {
        if (vivo == true)
            posX += velocidad;
    }

    public void cambiarDireccion() {
        velocidad *= -1;
    }

    public void ActualizarAccion() {
        if (vivo == true) {
            if (velocidad == -1) {
                AccionAnimation = 0;
            } else {
                AccionAnimation = 1;
            }
        } else
            AccionAnimation = 3;
    }

    public void ActualizarFrame() {
        contFrames++;

        if (contFrames >= velocidadAnimacion) {
            contFrames = 0;
            frameAniamcion++;
            if (vivo == false) {
                frameAniamcion = 0;
                return;
            } else {
                if (frameAniamcion >= 2)
                    frameAniamcion = 0;
            }
        }
    }

    protected void InicializarHitbox() {
        Hitbox = new java.awt.Rectangle(posX, posY, 16, 32);
    }

}
