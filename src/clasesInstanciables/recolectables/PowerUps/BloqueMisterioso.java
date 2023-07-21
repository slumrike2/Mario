package clasesInstanciables.recolectables.PowerUps;

import java.awt.Graphics;

import clasesInstanciables.Entidad;
import clasesInstanciables.Jugador.Personaje;
import constantes.Constantes;
import constantes.Constantes.PANTALLA;
import constantes.Constantes.ENTITY_TYPE.ITEMS;

public class BloqueMisterioso extends Entidad {

    public ITEMS ItemSpawn = null;
    public int tileX, tileY;

    public BloqueMisterioso(int Posx, int Posy) {
        super(Posx, Posy);
        tileX = Posx / PANTALLA.TILES_ACTUAL_SIZE;
        tileY = Posy / PANTALLA.TILES_ACTUAL_SIZE;
        importarImagen(PANTALLA.MISTERY_BLOCK_DIR);
        anchura_Tiles = 1;
        altura_Tiles = 1;
        velocidadAnimacion = 40;
        InicializarHitbox();
        animaciones = animacion(1, 0, 4, 1, 1);
        vivo = true;

    }

    public void update() {
        ActualizarFrame();

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
        if (vivo == false) {
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
                (int) (altura_Tiles * PANTALLA.TILES_ACTUAL_SIZE) + 5); // *coco */
    }

    public void recibirHit(Entidad ob) {
        if (ob instanceof Personaje) {
            Personaje personaje = (Personaje) ob;

            if (personaje.Hitbox.intersects(this.Hitbox) && personaje.vivo
                    && personaje.getHitbox().getMaxY() >= this.Hitbox.getMaxY() - this.getHitbox().height / 2) {
                vivo = false;
                DeterminarItem();
            }
        }
    }

    private void DeterminarItem() {
        int random = (int) (Math.random() * 100);
        if (random < 40)
            ItemSpawn = ITEMS.MONEDA;
        else if (random < 70)
            ItemSpawn = ITEMS.HONGO;
        else if (random < 90)
            ItemSpawn = ITEMS.FLOR;
        else if (random < 99)
            ItemSpawn = ITEMS.ESTRELLA;
        else if (random == 99)
            ItemSpawn = ITEMS.HONGO_VENENOSO;
    }

}
