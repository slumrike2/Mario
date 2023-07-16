package clasesInstanciables.PowerUps;



import clasesInstanciables.Entidad;

public abstract class Recolectable extends Entidad {

    public Boolean Active;

    public Recolectable(String Dir) {
        super(Dir);
    }

    public Recolectable(String Dir, int Posx, int Posy, float altura_Tiles, float anchura_Tiles) {
        super(Dir, Posx, Posy, altura_Tiles, anchura_Tiles);
    }

    

}
