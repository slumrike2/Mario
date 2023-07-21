package clasesInstanciables.recolectables.PowerUps;

import clasesInstanciables.Entidad;
import clasesInstanciables.Jugador.Personaje;
import clasesInstanciables.recolectables.Recolectable;
import constantes.Constantes.Items;
import constantes.Constantes.PANTALLA;
import constantes.Constantes.Items;

public class FlorFuego extends Recolectable {

    public FlorFuego(int posX, int posY) {
        super(posX, posY);
        anchura_Tiles = 1;
        altura_Tiles = 1;
        velocidadAnimacion = 20;
        puntajeDado = Items.FLOR_POINTS;
        Active = true;
        importarImagen(PANTALLA.FLOR_DIR);
        animaciones = animacion(1, 0, 1, Items.HONGO_WIDTH_TILES, Items.HONGO_HIGH_TILES);
        InicializarHitbox();
    }

    public void Colliision(Entidad ob) {
        if (ob instanceof Personaje) {
            Personaje personaje = (Personaje) ob;
            if (Hitbox.intersects(personaje.Hitbox) && personaje.vivo == true) {
                personaje.setVidas(3);
                Active = false;
            }
        }
    }

    public void update() {

    }

    public void ActualizarFrame() {
        AccionAnimation = 0;
    }

    protected void InicializarHitbox() {
        Hitbox = new java.awt.Rectangle(posX, posY, (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles));
    }

    public void updateFrames(java.awt.Graphics g, int offset) {
        g.drawImage(animaciones[0][AccionAnimation], posX - offset, posY, null);
    }

}
