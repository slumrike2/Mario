package clasesInstanciables.Enemigos;

import clasesInstanciables.Entidad;
import clasesInstanciables.Jugador.Personaje;

import constantes.Constantes.PANTALLA;

public class Koopa extends Enemigo {

    public Koopa(String Dir) {
        super(Dir);
        velocidad = 1;
        velocidadAnimacion = 12;
    }

    public Koopa(String Dir, int Posx, int Posy, int velocidad) {
        super(Dir, Posx, Posy, velocidad, 2, 1);
        animaciones = animacion(3, 0, 2, 16, 32);
        velocidadAnimacion = 12;
    }

    public void update() {

        movimiento();
        ActualizarAccion();
        ActualizarFrame();
        ActualizarPosHitbox();
    }

    public void updateFrames(java.awt.Graphics g, int offset) {
        g.drawImage(animaciones[AccionAnimation][frameAniamcion], posX - offset, posY,
                (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles), null);
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
        Hitbox = new java.awt.Rectangle(posX, posY, (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles));
    }

    public void recibirHit(Entidad ob) {
        if (ob instanceof Personaje) {
            Personaje personaje = (Personaje) ob;
            if (personaje.Hitbox.intersects(this.Hitbox)) {

            }
        }
    }

}
