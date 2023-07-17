package clasesInstanciables.PowerUps;

import java.awt.Graphics;

import constantes.Constantes.PANTALLA;

public class Star extends Recolectable {

    public Star(String Dir) {
        super(Dir);
    }

    public Star(String Dir, int Posx, int Posy, float altura_Tiles, float anchura_Tiles) {
        super(Dir, Posx, Posy, altura_Tiles, anchura_Tiles);
        animaciones = animacion(1, 0, 3, 16, 16);
    }

    public void update() {

    }

    public void updateFrames(Graphics g, int offset) {
        g.drawImage(animaciones[0][AccionAnimation], posX - offset, posY, null);
    }

    @Override
    public void ActualizarFrame() {

    }

    @Override
    protected void InicializarHitbox() {
        Hitbox = new java.awt.Rectangle(posX, posY, (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles));

    }
}
