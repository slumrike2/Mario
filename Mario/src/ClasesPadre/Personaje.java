package ClasesPadre;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

public class Personaje extends Entidad {
    // #region Variables

    // *Cada cuantos frames se actualiza la animacion
    private int velocidadAnimacion = 20, AccionAnimation = 0, frameAniamcion = 0, contFrames = 0;
    public BufferedImage[][] animaciones;

    public enum dirMov {
        Izquierda, Derecha, Arriba, Abajo
    }

    public enum AccionPlayer {
        Saltar, Agacharse, Correr, Escalar, Quieto
    }

    public dirMov direccion = dirMov.Derecha;
    public AccionPlayer accion = AccionPlayer.Quieto;

    // #region Metodos
    // *Dir reseprenta la direccion de la hoja de sprite correspondiente */
    public Personaje(String Dir, int Posx, int Posy) {
        super(Dir, Posx, Posy);
        GetAnimations();

    }

    public void GetAnimations() {
        animaciones = animacion(20, 0, 3, 16, 32);
    }

    public void update() {
        movimiento();
        ActualizarAccion();
    }

    public void updateFrames(Graphics g) {
        ActualizarFrame();
        g.drawImage(animaciones[AccionAnimation][frameAniamcion], posX, posY, null);
    }

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

    public void ActualizarAccion() {
        int aux = 0;
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

    public void movimiento() {
        if (accion == AccionPlayer.Quieto) {
            return;
        }
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
