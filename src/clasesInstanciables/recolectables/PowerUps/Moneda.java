package clasesInstanciables.recolectables.PowerUps;

import java.awt.Graphics;
import java.awt.Rectangle;

import clasesInstanciables.Entidad;
import clasesInstanciables.Jugador.Personaje;
import clasesInstanciables.recolectables.Recolectable;
import constantes.Constantes.Items;
import constantes.Constantes.PANTALLA;

public class Moneda extends Recolectable {

    public Moneda(int Posx, int Posy) {
        super(Posx, Posy);
        anchura_Tiles = 1;
        altura_Tiles = 1;
        velocidadAnimacion = 20;
        puntajeDado = Items.MONEDA_POINTS;
        Active = true;
        importarImagen(PANTALLA.MONEDA_DIR);
        animaciones = animacion(1, 0, 4, 1, 1);
        InicializarHitbox();
        vivo = true;

    }

    @Override
    public void Colliision(Entidad ob) {
        if (ob instanceof Personaje) {
            Personaje personaje = (Personaje) ob;

            if (Hitbox.intersects(personaje.Hitbox) && personaje.vivo == true) {
                personaje.addMonedas();
                Active = false;
            }
        }
    }

    @Override
    public void ActualizarFrame() {
        AccionAnimation = 0;

    }

    @Override
    protected void InicializarHitbox() {
        Hitbox = new Rectangle(posX, posY, (int) (anchura_Tiles * PANTALLA.TILES_ACTUAL_SIZE),
                (int) (altura_Tiles * PANTALLA.TILES_ACTUAL_SIZE));
    }

    @Override
    public void updateFrames(Graphics g, int offset) {
        g.drawImage(animaciones[0][AccionAnimation], posX - offset, posY, null);

    }

}
