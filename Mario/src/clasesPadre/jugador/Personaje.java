package clasesInstanciables.Jugador;

import java.awt.image.BufferedImage;

import clasesInstanciables.Entidad;
import constantes.constantes;
import constantes.constantes.*;

import java.awt.Rectangle;

public class Personaje extends Entidad {

    // #region constantes
    private int gravedad = Globales.GRAVEDAD;

    // #endregion

    // #region Variables

    // *Cada cuantos frames se actualiza la animacion
    private int contInvensibilityFrames = 0, AccionAnimation = 0, frameAniamcion = 0,
            contFrames = 0, contFramesMuerte = 0;
    public int FuerzaSalto = 0;
    public BufferedImage[][] animaciones; // * todas las animaciones del personaje
    public Boolean enMovimiento = false, saltando = false, EnSuelo = true; // * Boleanos que determinaran acciones
    public Boolean MovDerecha = false, MovIzquierda = false, MovAbajo = false, MovArriba = false;
    private Boolean pequeño = false;

    // *se encarga de determinar la direccion del personaje

    // *se encarga de determinar la accion del personaje
    public enum AccionPlayer {
        Saltar, Agacharse, Correr, Escalar, Quieto
    }

    public AccionPlayer accion = AccionPlayer.Quieto;
    AccionPlayer Accionprevia = accion;
    // #endregion

    // #region Metodos
    // *Dir reseprenta la direccion de la hoja de sprite correspondiente */
    public Personaje(String Dir, int Posx, int Posy, int velocidad) {
        super(Dir, Posx, Posy, velocidad, 2, 1);
        GetAnimations();

    }

    // *se encarga de cargar las animaciones del personaje
    public void GetAnimations() {
        animaciones = animacion(21, 0, 3, 16, 32);
    }

    // *se encarga de la actualizacion del personaje en acciones
    public void update() {

        movimiento();
        ActHitbox();
        ActualizarAccion();
        // *funcion que determina que animacion sigue y que frame de la animacion
        ActualizarFrame();
        VerificarMuerte();

        contInvensibilityFrames++;
    }

    // *se encarga de dibujar los frames del personaje

    public void updateFrames(java.awt.Graphics g) {
        g.drawImage(animaciones[AccionAnimation][frameAniamcion], posX, posY,
                PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles,
                PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles, null);
        DibujarHitbox(g);
    }

    // *Determina la animacion que sigue
    public void ActualizarFrame() {
        contFrames++;
        if (accion == AccionPlayer.Quieto || accion == AccionPlayer.Agacharse) {
            frameAniamcion = 0;
            return;
        }

        if (accion == AccionPlayer.Saltar) {
            frameAniamcion = 1;
            return;
        }
        if (contFrames >= Jugador.MARIO_VELCIDAD_ANIMACION) {
            contFrames = 0;
            frameAniamcion++;
            if (frameAniamcion >= CantidadSprites(accion)) {

                frameAniamcion = 0;

            }

        }

    }

    // *Determina la accion que sigue y su direccion
    public void ActualizarAccion() {
        if (vivo == false) {
            AccionAnimation = 20;
            accion = AccionPlayer.Quieto;
            return;
        }

        int aux = 0;
        if (pequeño)
            aux = 5;
        // ! se suman 10 para que se pueda acceder a las animaciones de la izquierda
        accion = AccionPlayer.Quieto;
        // *confimacioines para la animacion y vista de la direccion

        if (MovDerecha == true && MovIzquierda == false) {
            accion = AccionPlayer.Correr;

        } else if (MovIzquierda == true && MovDerecha == false) {
            accion = AccionPlayer.Correr;
            aux += 10;
        }
        if (MovAbajo == true) {
            if (!pequeño) {
                accion = AccionPlayer.Agacharse;
            } else {
                accion = AccionPlayer.Quieto;
            }
        }

        if (saltando == true) {
            accion = AccionPlayer.Saltar;
            System.out.println("Saltando");

        }

        // * determina la accion a realizar y su direccion gracias al aux */
        switch (accion) {
            case Quieto:
                AccionAnimation = 0 + aux;
                break;
            case Correr:
                AccionAnimation = 1 + aux;
                break;
            case Saltar:
                AccionAnimation = 2 + aux;
                break;
            case Escalar:
                AccionAnimation = 4 + aux;
                break;
            case Agacharse:
                AccionAnimation = 3 + aux;
                break;
            default:
                break;
        }

    }

    // *se encarga de mover al personaje
    public void movimiento() {

        if (MovAbajo != true && vivo == true) {

            if (MovDerecha == true && MovIzquierda == false) {
                posX += velocidad;

            }
            if (MovIzquierda == true && MovDerecha == false) {
                posX -= velocidad;

            }
        }
        if (MovArriba == true && MovAbajo == false) {
            posY -= velocidad;
        }
        if (MovAbajo == true && MovArriba == false) {
            posY += velocidad;
        }

        // Todo Actualizar el sistema de graveddad con mas presision y crear clase
        // vector
        // *Gravedad
        if (FuerzaSalto != 0) {
            posY -= gravedad;
            FuerzaSalto--;

        }
        // Todo Agregar colisiones para poner el en suelo activo y falso para el
        // funcionamiento del salto
        if (EnSuelo == false && FuerzaSalto == 0) {
            posY += gravedad;
        }

    }

    // *se encarga de deterinar cuantos frames tienen las animaciones
    // *se cuenta desde el 0
    public static int CantidadSprites(AccionPlayer AccionPlayer) {
        switch (AccionPlayer) {
            case Quieto:
            case Agacharse:
                return 0;
            case Correr:
                return 3;
            case Saltar:
                return 1;
            case Escalar:
                return 1;
            default:
                return 0;
        }

    }

    // *Hitbox es del sistema de collisiones
    // ? se necesita actualizar o mostrarla para verificacion
    protected void InicializarHitbox() {
        Hitbox = new java.awt.Rectangle(posX, posY, PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles,
                PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles);
    }

    // *hitBox para enemigos
    public void HitEnemigo(Rectangle HitboxEnemigo) {
        if (Hitbox.intersects(HitboxEnemigo)) {
            // *Tiempo Entre golpes
            // *Si esta por encima del enemigo salta sobre el
            // Todo Que el enemigo reciba el golpe perse
            if ((Hitbox.y + Hitbox.height) <= (HitboxEnemigo.y + HitboxEnemigo.height)) {
                FuerzaSalto = Jugador.MARIO_JUMP_FORCE;
                saltando = true;
                return;
            }
            // * Sino le hara damage a mario */
            if (contInvensibilityFrames >= Jugador.INVENSIBILITY_FRAMES) {
                // Todo VolverMarioChiquito con el golpe
                if (pequeño == false) {
                    pequeño = true;
                    System.out.println("Recibio golpe");
                    contInvensibilityFrames = 0;
                    return;
                }
                vivo = false;
                System.out.println("Muerto");
                contInvensibilityFrames = 0;
            }
        }
    }

    // * Se sobre escribio por mas comodidad
    // * Cambia la posicion de la hitbox y se adapta a la accion

    public void ActHitbox() {
        InicializarHitbox();
        int newposy = posY;
        if (accion == AccionPlayer.Agacharse && !pequeño) {
            newposy = posY + Jugador.MARIO_BIG_DOWN_HEIGHT - Jugador.BIG_SPRITE_HEIGTH;
            Hitbox = new Rectangle(posX, newposy, Jugador.SPRITE_WIDTH,
                    Jugador.MARIO_BIG_DOWN_HEIGHT);
        }
        if (pequeño) {
            newposy = posY + Jugador.SMALL_SPRITE_HEIGTH;
            Hitbox = new Rectangle(posX, newposy, Jugador.SPRITE_WIDTH, Jugador.SMALL_SPRITE_HEIGTH);
        }
        Hitbox.setLocation(posX, newposy);

    }

    // *Verifica vidas y si se va a morir se pone resetean variables */
    // Todo Poner Que se ponga en el spanw/
    public void VerificarMuerte() {
        if (vivo == false) {
            if (contFramesMuerte == 0) {
                FuerzaSalto = 20;

            }

            contFramesMuerte++;
            if (contFramesMuerte >= Jugador.CANT_FRAMES_MUERTE) {
                posX = 300;
                posY = 300;
                vivo = true;
                pequeño = false;
                EnSuelo = true;
                contFramesMuerte = 0;
            }

        }
    }

}