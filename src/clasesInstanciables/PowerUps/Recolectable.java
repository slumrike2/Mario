package clasesInstanciables.PowerUps;

import clasesInstanciables.Entidad;

public abstract class Recolectable extends Entidad {

    public Boolean Active;

    public Recolectable(int Posx, int Posy) {
        super(Posx, Posy);
    }

    public abstract void Colliision(Entidad ob);

}
