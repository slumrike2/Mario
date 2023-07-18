package networking;

public class PlayerInput {

    public int PlayerId;
    public Boolean saltando;
    public Boolean movDerecha;
    public Boolean movIzquierda;
    public Boolean agachado;

    public PlayerInput(int id) {
        this.saltando = false;
        this.movDerecha = false;
        this.movIzquierda = false;
        this.agachado = false;
        this.PlayerId = id;
    }

}
