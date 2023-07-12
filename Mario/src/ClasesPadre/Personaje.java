package ClasesPadre;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

import Inputs.InputTeclado;

public class Personaje extends Entidad {
    // #region Constantes
    private int velocidad = 2, gravedad = 1;
    public int FuerzaSalto = 0;

    // #region Variables

    // *Cada cuantos frames se actualiza la animacion
    private int velocidadAnimacion = 10, AccionAnimation = 0, frameAniamcion = 0, contFrames = 0;
    public BufferedImage[][] animaciones; // ! todas las animaciones del personaje
    public Boolean enMovimiento = false, saltando = false, EnSuelo = true; // ? Boleanos que determinaran acciones del
                                                                           // personaje
    public Boolean MovDerecha = false, MovIzquierda = false, MovAbajo = false, MovArriba = false;

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
        super(Dir, Posx, Posy, velocidad);
        GetAnimations();

    }

    // *se encarga de cargar las animaciones del personaje
    public void GetAnimations() {
        animaciones = animacion(20, 0, 3, 16, 32);
    }

    // *se encarga de la actualizacion del personaje en acciones
    public void update() {

        movimiento();
        ActualizarAccion();
        // *funcion que determina que animacion sigue y que frame de la animacion
        ActualizarFrame();
    }

    // *se encarga de dibujar los frames del personaje
    public void updateFrames(Graphics g) {

        // *se dibuja el frame correspondiente
        g.drawImage(animaciones[AccionAnimation][frameAniamcion], posX, posY, null);

    }

    // ?Determina la animacion que sigue
    public void ActualizarFrame() {
        contFrames++;
        if (accion == AccionPlayer.Quieto || accion == AccionPlayer.Agacharse) {
            frameAniamcion = 0;
            return;
        }

        if (contFrames >= velocidadAnimacion) {
            contFrames = 0;
            frameAniamcion++;
            if (frameAniamcion >= CantidadSprites(accion)) {

                frameAniamcion = 0;
                if (accion == AccionPlayer.Saltar) {
                    frameAniamcion = 1;
                }
            }

        }

    }

    // ?Determina la accion que sigue y su direccion
    public void ActualizarAccion() {
        int aux = 0;

        // ! se suman 10 para que se pueda acceder a las animaciones de la izquierda
        accion = AccionPlayer.Quieto;
        // *confimacioines para la animacion y vista de la direccion

        if (MovDerecha == true && MovIzquierda == false) {
            accion = AccionPlayer.Correr;

        } else if (MovIzquierda == true && MovDerecha == false) {
            accion = AccionPlayer.Correr;
            aux = 10;
        }
        if (MovAbajo == true) {
            accion = AccionPlayer.Agacharse;
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

        System.out.println("posX: " + posX + " posY: " + posY);
        if (MovAbajo != true) {

            if (MovDerecha == true && MovIzquierda == false) {
                posX += velocidad;
                System.out.println("derecha");
            }
            if (MovIzquierda == true && MovDerecha == false) {
                posX -= velocidad;
                System.out.println("izquierda");
            }
        }
        // ?Gravedad
        if (FuerzaSalto != 0) {
            posY -= gravedad;
            FuerzaSalto--;
            System.out.println(FuerzaSalto);
        }
        if (EnSuelo == false && FuerzaSalto == 0) {
            posY += gravedad;
        }

    }

    // *se encarga de deterinar cuantos frames tienen las animaciones
    // ?se cuenta desde el 0
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
}
