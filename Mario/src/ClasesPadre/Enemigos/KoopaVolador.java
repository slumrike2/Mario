package ClasesPadre.Enemigos;

public class KoopaVolador extends Koopa {

    public Boolean Alas = true;

    public KoopaVolador(String Dir, int Posx, int Posy, int velocidad) {
        super(Dir, Posx, Posy, velocidad);
        velocidadAnimacion = 12;

    }

    public void update() {
        movimiento();
        ActualizarAccion();
        ActualizarFrame();
    }

}
