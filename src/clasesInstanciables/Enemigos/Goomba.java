package clasesInstanciables.Enemigos;

import java.awt.image.BufferedImage;

import clasesInstanciables.Entidad;
import clasesInstanciables.Jugador.Personaje;
import constantes.Constantes.PANTALLA;

public class Goomba extends Enemigo {

    // Todo Faltan la muerte y las hitboxes
    BufferedImage[][] animaciones;

    private int AccionAnimation = 0, frameAniamcion = 0, contFrames = 0;
    public boolean enMovimiento = true;

    public Goomba(String Dir, int Posx, int Posy, int velocidad) {
        super(Dir, Posx, Posy, velocidad, 1, 1);
        animaciones = animacion(3, 0, 2, 1, 1);
        velocidadAnimacion = 12;
        InicializarHitbox();
    }

    public void update() {

        movimiento();
        ActualizarAccion();
        ActualizarFrame();
        contFramesInvensible++;

    }

    public void updateFrames(java.awt.Graphics g, int offset) {
        g.drawImage(animaciones[AccionAnimation][frameAniamcion], posX - offset, posY,
                (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles), null);
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
        if (vivo == false) {
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
            if (vivo == false) {
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
        Hitbox = new java.awt.Rectangle(posX, posY, (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles));
    }

    public void recibirHit(Entidad ob) {
        if (contFramesInvensible < FramesInvensible) {
            return;
        }
        if (ob instanceof Personaje) {
            Personaje personaje = (Personaje) ob;
            if (personaje.Hitbox.intersects(this.Hitbox) && personaje.vivo == true && vivo == true) {
                if ((personaje.Hitbox.y * personaje.Hitbox.height) <= ((this.Hitbox.getY() * Hitbox.height))) {
                    System.out.println("Muerto");
                    vivo = false;
                }

            }
        }
        contFramesInvensible = 0;
    }

}
