package clasesInstanciables.Enemigos;

import clasesInstanciables.Entidad;

public abstract class Enemigo extends Entidad {

    protected int FramesInvensible = 10;
    protected int contFramesInvensible = 0;

   

    public Enemigo(int Posx, int Posy) {
        super(Posx, Posy);
    }

    

    public abstract void recibirHit(Entidad ob);

}
