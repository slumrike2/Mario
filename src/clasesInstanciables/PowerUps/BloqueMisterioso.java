package clasesInstanciables.PowerUps;

import java.awt.Graphics;

import clasesInstanciables.Entidad;
import clasesInstanciables.Jugador.Personaje;
import constantes.Constantes.PANTALLA;

public class BloqueMisterioso extends Entidad {

    public BloqueMisterioso(int Posx, int Posy) {
        super(Posx, Posy);
        importarImagen(PANTALLA.MISTERY_BLOCK_DIR);
        anchura_Tiles = 1;
        altura_Tiles = 1;
        InicializarHitbox();
        animaciones = animacion(1, 0, 4, 1, 1);
        vivo = true;
    }

    public void updateFrames(Graphics g, int offset) {
        g.drawImage(animaciones[0][frameAniamcion], posX - offset, posY,
                (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles), null);
        DrawHitbox(g, offset);
    }

    public void DrawHitbox(Graphics g, int offset) {
        g.drawRect(posX - offset, posY, (int) (anchura_Tiles * PANTALLA.TILES_ACTUAL_SIZE),
                (int) (altura_Tiles * PANTALLA.TILES_ACTUAL_SIZE));
    }

    public void ActualizarFrame() {
        contFrames++;
        if (vivo = false) {
            frameAniamcion = 3;
            return;
        }
        if (contFrames > velocidadAnimacion) {
            contFrames = 0;
            frameAniamcion++;
            if (frameAniamcion >= 2)
                frameAniamcion = 0;
        }
    }

    protected void InicializarHitbox() {
        Hitbox = new java.awt.Rectangle(posX, posY, (int) (anchura_Tiles * PANTALLA.TILES_ACTUAL_SIZE),
                (int) (altura_Tiles * PANTALLA.TILES_ACTUAL_SIZE));
    }

    public void recibirHit(Entidad ob) {
        if (ob instanceof Personaje) {
            Personaje personaje = (Personaje) ob;
            if (personaje.Hitbox.intersects(this.Hitbox) && personaje.vivo
                    && personaje.getHitbox().getMinY() >= this.Hitbox.getMaxY() - this.getHitbox().height / 2) {

                vivo = false;
                System.out.println("Spanear item");
            }
        }
    }

}
