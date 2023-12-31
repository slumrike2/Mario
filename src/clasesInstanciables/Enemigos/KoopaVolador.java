package clasesInstanciables.Enemigos;

import constantes.Constantes.Enemigos;
import constantes.Constantes.PANTALLA;

public class KoopaVolador extends Koopa {

    public Boolean Alas = true;

    public KoopaVolador(int Posx, int Posy) {
        super(Posx, Posy);
        importarImagen(PANTALLA.KoopaVoladorDir);
        puntajeDado = Enemigos.KOOPA_VOLADOR_POINTS;
        velocidad = 1;
        altura_Tiles = 2;
        anchura_Tiles = 1;
        animaciones = animacion(3, 0, 2, (int) anchura_Tiles, (int) altura_Tiles);
        velocidadAnimacion = 40;
        vidas = 3;
        InicializarHitbox();

    }

    public void update() {
        super.update();

    }

    // Todo añadir las fisicas en caso de estar en el aire y las hitboxes

}
