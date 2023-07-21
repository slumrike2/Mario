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
    private int VelocidadSeleccionAccion = (int) (Math.random() * 50) + 100, contSeleccionAccion = 0;
    private int SegundosFinal = 120 * 30, contFinPelea = 0;
    private int velocidadAtaque = 80, contVelcAtaque = 0;
    private boolean quieto = false;
    public int direccion = -1;
    private Boolean Ensuelo, atacando = false;
    public Boolean SpawnStrella = false;

    private int gravedad = 1;
    private int FuerzaSalto = 0;
    public Boolean Ataque = false;

    public Bowser(int Posx, int Posy) {
        super(Posx, Posy);
        importarImagen(PANTALLA.BowserDir);
        puntajeDado = Enemigos.BOWSER_POINTS;
        altura_Tiles = 2;
        anchura_Tiles = 2;
        velocidad = 1f;

        FuerzaSalto = 0;
        vidas = 40000000;
        animaciones = animacion(4, 0, 2, 2, 2);
        velocidadAnimacion = 30;
        InicializarHitbox();
    }

    public void update() {
        super.update();
        if (!quieto)
            movimiento();
        ActualizarAccion();
        ActualizarFrame();
        ActualizarHitbox();
        SeleccionarAccion();

        if (contFinPelea >= SegundosFinal) {
            SpawnStrella = true;
            contFinPelea = -300;
        }

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
        int aux = 0;

        if (Ataque == true) {
            contVelcAtaque = 0;
            atacando = true;
        }
        contVelcAtaque++;

        if (contVelcAtaque >= velocidadAtaque && atacando == true) {
            contVelcAtaque = 0;
            atacando = false;
        }
        // *Determina cuando deja de atacar
        if (direccion == 1) {
            aux = 2;
        }

        if (atacando == true) {
            AccionAnimation = 1 + aux;
        } else {
            AccionAnimation = 0 + aux;
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

    public void updateFrames(Graphics g, int offset) {
        g.drawImage(animaciones[AccionAnimation][frameAniamcion], posX - offset, posY,
                (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles), null);
    }

    public void recibirHit(Entidad ob) {
        if (ob instanceof Personaje) {
            Personaje personaje = (Personaje) ob;
            if (personaje.Hitbox.intersects(this.Hitbox) && personaje.getContInvensibility_Star() > 0) {
                
                vidas = 0;
            }
        }
    }

    public void ActualizarSuelo() {
        if (isInFloor(posX, posY + 1, (int) (anchura_Tiles * PANTALLA.TILES_ACTUAL_SIZE),
                (int) (altura_Tiles * PANTALLA.TILES_ACTUAL_SIZE), currentLevelData)) {

            Ensuelo = true;

            return;
        }

        Ensuelo = false;

    }

    public void Saltar() {
        ActualizarSuelo();
        Moverse();
        if (Ensuelo == true) {
            FuerzaSalto = 90;
        }

    }

    public void Atacar() {
        Ataque = true;
    }

    public void Quieto() {
        quieto = true;
    }

    public void Moverse() {
        quieto = false;
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
                contFinPelea++;
                
            } else if (personaje.getHitbox().getMinX() - Hitbox.getMinX() >= -32 * PANTALLA.TILES_ACTUAL_SIZE
                    && personaje.getHitbox().getMinX() - Hitbox.getMinX() <= 0) {
                direccion = -1;
                contFinPelea++;
                

            } else
                direccion = 0;

        }
    }

    private void SeleccionarAccion() {
        contSeleccionAccion++;
        int random = (int) (Math.random() * 100);
        if (contSeleccionAccion >= VelocidadSeleccionAccion && direccion != 0) {
            if (random < 25)
                Saltar();
            else if (random < 60) {
                Atacar();
            } else if (random < 90)
                Moverse();
            else
                Quieto();

            VelocidadSeleccionAccion = (int) (Math.random() * 100) + 100;
            contSeleccionAccion = 0;

        }
    }

    private void ActualizarDireccion() {
        if (Ensuelo) {
            velocidad = direccion;
        }
    }
}
