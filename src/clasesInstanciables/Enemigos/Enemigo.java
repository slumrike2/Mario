package clasesInstanciables.Enemigos;

import clasesInstanciables.Entidad;

public abstract class Enemigo extends Entidad {

    protected int FramesInvensible = 10;
    protected int contFramesInvensible = 0;

    public Enemigo(String Dir) {
        super(Dir);
    }

    public Enemigo(String Dir, int Posx, int Posy) {
        super(Dir, Posx, Posy);
    }

    public Enemigo(String Dir, int Posx, int Posy, int velocidad, int altura_Tiles, int anchura_Tiles) {
        super(Dir, Posx, Posy, velocidad, altura_Tiles, anchura_Tiles);
    }

    public abstract void recibirHit(Entidad ob);

}
