package clasesInstanciables.recolectables.PowerUps;

import java.awt.Graphics;

import clasesInstanciables.Entidad;
import clasesInstanciables.Jugador.Personaje;
import clasesInstanciables.recolectables.Recolectable;
import constantes.Constantes.Items;
import constantes.Constantes.PANTALLA;

public class Hongo extends Recolectable {

    public Hongo(int Posx, int Posy) {
        super(Posx, Posy);
        anchura_Tiles = 1;
        altura_Tiles = 1;
        velocidadAnimacion = 20;
        puntajeDado = Items.HONGO_POINTS;
        Active = true;
        importarImagen(PANTALLA.HONGO_DIR);
        animaciones = animacion(1, 0, 1, Items.HONGO_WIDTH_TILES, Items.HONGO_HIGH_TILES);
        InicializarHitbox();
    }

    public void Colliision(Entidad ob) {
        if (ob instanceof Personaje) {
            Personaje personaje = (Personaje) ob;

            if (Hitbox.intersects(personaje.Hitbox) && personaje.vivo == true) {
                personaje.setVidas(2);
                Active = false;
            }
        }
    }

    public void update() {

    }

    public void updateFrames(Graphics g, int offset) {
        g.drawImage(animaciones[0][AccionAnimation], posX - offset, posY, null);
    }

    public void ActualizarFrame() {
        AccionAnimation = 0;
    }

    protected void InicializarHitbox() {
        Hitbox = new java.awt.Rectangle(posX, posY, (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles));
    }
}
