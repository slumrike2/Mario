<<<<<<<< HEAD:Mario/src/clasesInstanciables/Enemigos/KoopaVolador.java
package clasesInstanciables.Enemigos;
========
package clasesPadre.enemigos;
>>>>>>>> cf8cf87921214ca3eab205bd839ecebb485d4336:Mario/src/clasesPadre/enemigos/KoopaVolador.java

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

    // Todo añadir las fisicas en caso de estar en el aire y las hitboxes

}
