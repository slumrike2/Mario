package clasesInstanciables.PowerUps;

import java.awt.Graphics;

import clasesInstanciables.Entidad;
import clasesInstanciables.Jugador.Personaje;
import constantes.Constantes.PANTALLA;

public class HongoEnvenenado extends Recolectable {

    public HongoEnvenenado(int Posx, int Posy) {
        super(Posx, Posy);
        anchura_Tiles = 1;
        altura_Tiles = 1;
        velocidadAnimacion = 20;
        Active = true;
        importarImagen(PANTALLA.HONGO_VENENOSO_DIR);
        animaciones = animacion(1, 0, 1, 1, 1);
        InicializarHitbox();
    }

    public void Colliision(Entidad ob) {
        if (ob instanceof Personaje) {
            Personaje personaje = (Personaje) ob;

            if (Hitbox.intersects(personaje.Hitbox) && personaje.vivo == true) {
                personaje.setPequeño(true);
                Active = false;

            }
        }
    }

    public void update() {
        // * Utilizar si se quieren mover los hongos con sus hitboxs
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
