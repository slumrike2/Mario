package clasesInstanciables.PowerUps;

import java.awt.Graphics;

import constantes.Constantes.PANTALLA;

import constantes.Constantes.Items;

public class Star extends Recolectable {

    public Star(int Posx, int Posy) {
        super(Posx, Posy);
        animaciones = animacion(1, 0, 4, Items.ESTRELLA_WIDTH_TILES, Items.ESTRELLA_HIGH_TILES);

    }

    public void update() {

    }

    public void updateFrames(Graphics g, int offset) {
        g.drawImage(animaciones[0][AccionAnimation], posX - offset, posY, null);
    }

    public void ActualizarFrame() {

    }

    protected void InicializarHitbox() {
        Hitbox = new java.awt.Rectangle(posX, posY, (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles));

    }
}
