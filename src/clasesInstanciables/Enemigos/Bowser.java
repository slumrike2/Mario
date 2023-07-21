package clasesInstanciables.Enemigos;

import clasesInstanciables.Entidad;
import clasesInstanciables.Jugador.Personaje;

import java.awt.Graphics;
import java.awt.Rectangle;
import static utils.HelpMethods.canMoveHere;
import static utils.HelpMethods.isInFloor;

import constantes.Constantes.Enemigos;
//siva
import constantes.Constantes.PANTALLA;

public class Bowser extends Enemigo {
    private int VelocidadSeleccionAccion = 600, contSeleccionAccion = 0;
    private int velocidadAtaque = 80, contVelcAtaque = 0;
    private int direccion = -1;
    private Boolean Ensuelo, Saltando, atacando = false;

    private int gravedad = 1;
    private int FuerzaSalto = 0;

    public Bowser(int Posx, int Posy) {
        super(Posx, Posy);
        importarImagen(PANTALLA.BowserDir);
        puntajeDado = Enemigos.BOWSER_POINTS;
        altura_Tiles = 2;
        anchura_Tiles = 2;
        velocidad = 1f;
        Saltando = false;
        FuerzaSalto = 0;
        vidas = 20;
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
        SeleccionarAccion();

    }

    // Todo añadir las fisicas en caso de estar en el aire y las hitboxes y ganar el
    // juego
    public void movimiento() {
        // Saltos con el tiempo
        ActualizarSuelo();

        ActualizarDireccion();

        if (canMoveHere(Hitbox.x + velocidad * direccion, Hitbox.y, Hitbox.width, Hitbox.height, currentLevelData))
            posX += velocidad;
        else
            posX -= velocidad;

        if (canMoveHere(Hitbox.x, Hitbox.y + gravedad, Hitbox.width, Hitbox.height, currentLevelData) && vidas >= 1
                && FuerzaSalto == 0) {
            posY += gravedad;
        }

        if (FuerzaSalto != 0) {
            posY -= gravedad;
            FuerzaSalto--;
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
        ActualizarSuelo();
        if (Ensuelo == true) {
            FuerzaSalto = 80;
        }
    }

    public void setCurrentLevelData(int[][] currentLevelData) {
        this.currentLevelData = currentLevelData;
    }

    public void VerificarDistancia(Entidad ob) {
        if (ob instanceof Personaje) {
            Personaje personaje = (Personaje) ob;
            if (personaje.getHitbox().getMinX() - Hitbox.getMinX() <= 32 * PANTALLA.TILES_ACTUAL_SIZE
                    && personaje.getHitbox().getMinX() - Hitbox.getMinX() >= 0) {
                direccion = 1;
            } else if (personaje.getHitbox().getMinX() - Hitbox.getMinX() >= -32 * PANTALLA.TILES_ACTUAL_SIZE
                    && personaje.getHitbox().getMinX() - Hitbox.getMinX() <= 0) {
                direccion = -1;

            }

        }
    }

    private void SeleccionarAccion() {
        contSeleccionAccion++;

        if (contSeleccionAccion >= VelocidadSeleccionAccion) {
            Saltar();
            contSeleccionAccion = 0;
            System.out.println("Bowser ha saltado");
        }
    }

    private void ActualizarDireccion() {
        if (Ensuelo) {
            velocidad = direccion;
        }
    }
}
