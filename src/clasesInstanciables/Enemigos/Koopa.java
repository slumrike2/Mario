package clasesInstanciables.Enemigos;

import clasesInstanciables.Entidad;
import clasesInstanciables.Jugador.Personaje;
import static utils.HelpMethods.canMoveHere;

import constantes.Constantes.PANTALLA;

public class Koopa extends Enemigo {

    public Koopa(int Posx, int Posy) {
        super(Posx, Posy);
        importarImagen(PANTALLA.KoopaDir);
        velocidad = 1;
        altura_Tiles = 2;
        anchura_Tiles = 1;
        animaciones = animacion(3, 0, 2, (int) anchura_Tiles, (int) altura_Tiles);
        velocidadAnimacion = 40;
        vidas = 2;
        vivo = true;
        InicializarHitbox();
    }

    public void update() {

        movimiento();
        ActualizarAccion();
        ActualizarFrame();
        ActualizarPosHitbox();
        contFramesInvensible++;
    }

    public void updateFrames(java.awt.Graphics g, int offset) {
        g.drawImage(animaciones[AccionAnimation][frameAniamcion], posX - offset, posY,
                (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles), null);
    }

    public void movimiento() {
        VerificarSuelo(currentLevelData);
        if (vidas >= 1) {
            if (canMoveHere(Hitbox.x + velocidad, Hitbox.y, Hitbox.width, Hitbox.height, currentLevelData))
                posX += velocidad;
            else
                cambiarDireccion();
        }
        if (!enSuelo && canMoveHere(Hitbox.x, Hitbox.y + gravedad, Hitbox.width, Hitbox.height, currentLevelData)
                && vidas != 3)
            posY += gravedad;
    }

    public void cambiarDireccion() {
        velocidad *= -1;
    }

    public void ActualizarAccion() {
        if (vidas >= 2) {
            if (velocidad == -1) {
                AccionAnimation = 0;
            } else {
                AccionAnimation = 1;
            }
        } else
            AccionAnimation = 2;
    }

    public void ActualizarFrame() {
        contFrames++;

        if (contFrames >= velocidadAnimacion) {
            contFrames = 0;
            frameAniamcion++;
            if (vidas >= 2 && frameAniamcion >= 2) {
                frameAniamcion = 0;
                return;
            }
            if (vidas <= 1) {
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
            if (contFramesInvensible < FramesInvensible)
                return;
            Personaje personaje = (Personaje) ob;
            if (personaje.Hitbox.intersects(this.Hitbox)
                    && personaje.posY + personaje.Hitbox.height <= this.posY + this.Hitbox.height / 2
                    && personaje.vivo && vidas > 0) {
                vidas--;
                contFramesInvensible = 0;

                return;
            }
            if (personaje.Hitbox.intersects(this.Hitbox) && personaje.vivo && vidas == 0) {
                vidas = 1;
                contFramesInvensible = 0;
            }
        }
    }
}
