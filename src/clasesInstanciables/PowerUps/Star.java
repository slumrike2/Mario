package clasesInstanciables.PowerUps;

import java.awt.Graphics;

import clasesInstanciables.Entidad;
import clasesInstanciables.Jugador.Personaje;
import constantes.Constantes.PANTALLA;

import constantes.Constantes.Items;

public class Star extends Recolectable {

    public Star(int Posx, int Posy) {
        super(Posx, Posy);
        anchura_Tiles = 1;
        altura_Tiles = 1;
        velocidadAnimacion = 20;
        Active = true;
        importarImagen(PANTALLA.ESTRELLA_DIR);
        animaciones = animacion(1, 0, 4, Items.ESTRELLA_WIDTH_TILES, Items.ESTRELLA_HIGH_TILES);
        InicializarHitbox();
    }

    public void update() {
        if (Active == true) {
            ActualizarFrame();
        }
    }

    public void updateFrames(Graphics g, int offset) {
        g.drawImage(animaciones[0][AccionAnimation], posX - offset, posY, null);
    }

    public void ActualizarFrame() {
        contFrames++;
        if (contFrames >= velocidadAnimacion) {
            contFrames = 0;
            AccionAnimation++;
            if (AccionAnimation > 3) {
                AccionAnimation = 0;
            }
        }
    }

    protected void InicializarHitbox() {
        Hitbox = new java.awt.Rectangle(posX, posY, (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles));

    }

    public void Colliision(Entidad ob) {
        if (ob instanceof Personaje) {
            if (ob instanceof Personaje) {
                Personaje personaje = (Personaje) ob;

                if (Hitbox.intersects(personaje.Hitbox) && personaje.vivo == true) {

                    personaje.setstarActive(Active);
                    Active = false;
                    System.out.println("velocidad aumentada");
                }
            }
        }
    }

}
