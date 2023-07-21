package clasesInstanciables.recolectables;

import clasesInstanciables.Entidad;

public abstract class Recolectable extends Entidad {

    public Boolean Active;
    protected int puntajeDado;

    public Recolectable(int Posx, int Posy) {
        super(Posx, Posy);
    }

    public abstract void Colliision(Entidad ob);

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }

    public int getPuntajeDado() {
        return puntajeDado;
    }

    public void setPuntajeDado(int puntosDados) {
        this.puntajeDado = puntosDados;
    }

}
