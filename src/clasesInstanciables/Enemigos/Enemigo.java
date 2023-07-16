package clasesInstanciables.Enemigos;

import clasesInstanciables.Entidad;
import clasesInstanciables.Jugador.Personaje;

public abstract class Enemigo extends Entidad {

    public Enemigo(String Dir) {
        super(Dir);
    }

    public Enemigo(String Dir, int Posx, int Posy, float altura_Tiles, float anchura_Tiles) {
        super(Dir, Posx, Posy, altura_Tiles, anchura_Tiles);
    }

    public Enemigo(String Dir, int Posx, int Posy, int velocidad, int altura_Tiles, int anchura_Tiles) {
        super(Dir, Posx, Posy, velocidad, altura_Tiles, anchura_Tiles);
    }

    public void recibirHit(Personaje jugador) {
        System.out.println(2);
    }
}
