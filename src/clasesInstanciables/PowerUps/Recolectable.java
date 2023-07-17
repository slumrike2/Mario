package clasesInstanciables.PowerUps;

import clasesInstanciables.Entidad;

public abstract class Recolectable extends Entidad {

    public Boolean Active;

    public Recolectable(String Dir) {
        super(Dir);
    }

    public Recolectable(int Posx, int Posy) {
        super(Posx, Posy);
    }

}
