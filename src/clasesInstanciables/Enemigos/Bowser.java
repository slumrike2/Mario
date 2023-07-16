package clasesInstanciables.Enemigos;

import clasesInstanciables.Entidad;
import clasesInstanciables.Jugador.Personaje;
//siva
import constantes.Constantes.PANTALLA;

public class Bowser extends Enemigo {

    private int velocidadAtaque = 80, contVelcAtaque = 0;

    private Boolean Ensuelo, Saltando, atacando = false;
    private int tiempoSalto = 120 * 5;

    public Bowser(String Dir, int Posx, int Posy, int velocidad) {
        super(Dir, Posx, Posy, velocidad, 2, 2);
        animaciones = animacion(2, 0, 2, 32, 32);
        velocidadAnimacion = 30;
    }

    public void update() {
        movimiento();
        ActualizarAccion();
        ActualizarFrame();
    }

    // Todo añadir las fisicas en caso de estar en el aire y las hitboxes y ganar el
    // juego
    public void movimiento() {
        // Saltos con el tiempo

    }

    public void ActualizarAccion() {
        contVelcAtaque++;
        // *Determina cuando ataca
        if (contVelcAtaque >= velocidadAtaque) {
            contVelcAtaque = 0;
            atacando = true;
        }
        // *Determina cuando deja de atacar
        if (atacando == true && contVelcAtaque >= 40) {
            atacando = false;
            contVelcAtaque = 0;
        }

        if (atacando == true) {
            AccionAnimation = 1;
        } else {
            AccionAnimation = 0;
        }
    }

    public void ActualizarFrame() {
        contFrames++;
        if (contFrames >= velocidadAnimacion) {
            contFrames = 0;
            frameAniamcion++;
            if (frameAniamcion >= animaciones[AccionAnimation].length) {
                frameAniamcion = 0;
            }
        }
    }

    protected void InicializarHitbox() {
        Hitbox = new java.awt.Rectangle(posX, posY, (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles));
    }

    public void DibujarHitbox(java.awt.Graphics g) {
        g.drawRect(Hitbox.x, Hitbox.y, Hitbox.width, Hitbox.height);
    }

    public void updateFrames(java.awt.Graphics g) {
        g.drawImage(animaciones[AccionAnimation][frameAniamcion], posX, posY,
                (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles), null);
    }

    public void recibirHit(Entidad ob) {
        if (ob instanceof Personaje) {
            Personaje personaje = (Personaje) ob;
            if (personaje.Hitbox.intersects(this.Hitbox)) {
                
            }
        }
    }
}
