package ClasesPadre;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

public class Personaje extends Entidad {
    // #region Variables

    // *Cada cuantos frames se actualiza la animacion
    private int velocidadAnimacion = 20, AccionAnimation = 0, frameAniamcion = 0, contFrames = 0;
    public BufferedImage[][] animaciones; // ! todas las animaciones del personaje
    public Boolean enMovimiento = false, saltando = false; // ? Boleanos que determinaran acciones del personaje

    // *se encarga de determinar la direccion del personaje
    public enum dirMov {
        Izquierda, Derecha, Arriba, Abajo
    }

    // *se encarga de determinar la accion del personaje
    public enum AccionPlayer {
        Saltar, Agacharse, Correr, Escalar, Quieto
    }

    public dirMov direccion = dirMov.Derecha;
    public AccionPlayer accion = AccionPlayer.Quieto;
    // #endregion

    // #region Metodos
    // *Dir reseprenta la direccion de la hoja de sprite correspondiente */
    public Personaje(String Dir, int Posx, int Posy) {
        super(Dir, Posx, Posy);
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

    }

    // *se encarga de dibujar los frames del personaje
    public void updateFrames(Graphics g) {
        ActualizarFrame(); // *funcion que determina que animacion sigue y que frame de la animacion
        // *se dibuja el frame correspondiente
        g.drawImage(animaciones[AccionAnimation][frameAniamcion], posX, posY, null);
    }

    // ?Determina la animacion que sigue
    public void ActualizarFrame() {
        contFrames++;
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
        if (direccion == dirMov.Izquierda) {
            aux = 10;
        }
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

        if (enMovimiento) {
            switch (direccion) {
                case Derecha:

                    posX += 1;
                    break;
                case Izquierda:
                    posX -= 1;
                    break;
                case Arriba:
                    posY -= 1;
                    break;
                case Abajo:
                    posY += 1;
                    break;
                default:
                    break;
            }
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
