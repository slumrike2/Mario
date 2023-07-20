package clasesInstanciables.Enemigos;

import clasesInstanciables.Entidad;
import clasesInstanciables.Jugador.Personaje;

import java.awt.Graphics;
import java.awt.Rectangle;
import static utils.HelpMethods.canMoveHere;
import static utils.HelpMethods.isInFloor;

//siva
import constantes.Constantes.PANTALLA;

public class Bowser extends Enemigo {

    private int velocidadAtaque = 80, contVelcAtaque = 0;

    private Boolean Ensuelo, Saltando, atacando = false;
    private int tiempoSalto = 120 * 5;
    private float velcodiadHorizontal;
    private int gravedad = 1;
    private int FuerzaSalto = 0;

    public Bowser(int Posx, int Posy) {
        super(Posx, Posy);
        importarImagen(PANTALLA.BowserDir);
        altura_Tiles = 2;
        anchura_Tiles = 2;
        velocidad = 1f;

        animaciones = animacion(2, 0, 2, 2, 2);
        velocidadAnimacion = 30;
        InicializarHitbox();
    }

    public void update() {
        super.update();
        movimiento();
        ActualizarAccion();
        ActualizarFrame();
        ActualizarHitbox();
    }

    // Todo añadir las fisicas en caso de estar en el aire y las hitboxes y ganar el
    // juego
    public void movimiento() {
        // Saltos con el tiempo

        if (canMoveHere(Hitbox.x + velocidad, Hitbox.y, Hitbox.width, Hitbox.height, currentLevelData))
            posX += velocidad;
        else
            velocidad *= -1;
        if (canMoveHere(Hitbox.x, Hitbox.y + gravedad, Hitbox.width, Hitbox.height, currentLevelData) && vidas >= 1) {
            posY += gravedad;
        }

    }

    public void ActualizarAccion() {
        contVelcAtaque++;
        // *Determina cuando ataca
        if (contVelcAtaque >= velocidadAtaque) {
            contVelcAtaque = 0;
            atacando = true;
        }
        // *Determina cuando deja de atacar

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
        Hitbox = new Rectangle(posX, posY, (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles));
    }

    public void DibujarHitbox(Graphics g) {
        g.drawRect(Hitbox.x, Hitbox.y, Hitbox.width, Hitbox.height);
    }

    public void updateFrames(Graphics g, int offset) {
        g.drawImage(animaciones[AccionAnimation][frameAniamcion], posX - offset, posY,
                (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles), null);
    }

    public void recibirHit(Entidad ob) {
        if (ob instanceof Personaje) {
            Personaje personaje = (Personaje) ob;
            if (personaje.Hitbox.intersects(this.Hitbox)) {
                System.out.println("Bowser ha sido golpeado");
            }
        }
    }

    public void ActualizarSuelo() {
        if (isInFloor(posX, posY + 1, (int) (anchura_Tiles * PANTALLA.TILES_ACTUAL_SIZE),
                (int) (altura_Tiles * PANTALLA.TILES_ACTUAL_SIZE), currentLevelData)) {

            Ensuelo = true;
            Saltando = false;

            return;
        }

        Ensuelo = false;

    }

    public void Saltar() {
        if (Ensuelo == true) {
            Saltando = true;
            FuerzaSalto = 40;
        }
    }

    public void setCurrentLevelData(int[][] currentLevelData) {
        this.currentLevelData = currentLevelData;
    }

    public void VerificarDistancia(Entidad ob) {
        if (ob instanceof Personaje) {
            System.out.println("Bowser ha detectado al personaje");
        }
    }
}
