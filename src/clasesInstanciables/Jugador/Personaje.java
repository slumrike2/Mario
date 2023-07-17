package clasesInstanciables.Jugador;

import java.awt.image.BufferedImage;

import clasesInstanciables.Entidad;

import constantes.Constantes.*;
import constantes.Constantes.Jugador;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import static utils.HelpMethods.canMoveHere;
import static utils.HelpMethods.isInFloor;

public class Personaje extends Entidad {

    // #region Constantes
    private int gravedad = Globales.GRAVEDAD;
    private int[][] currentLevelData;
    private int velocidad = Jugador.MARIO_VELC;

    // #endregion

    // #region Variables

    // *Cada cuantos frames se actualiza la animacion
    private int contInvensibilityFrames = 0, AccionAnimation = 0, frameAniamcion = 0,
            contFrames = 0, contFramesMuerte = 0;
    public int FuerzaSalto = 0;
    public BufferedImage[][] animaciones; // * todas las animaciones del personaje
    public Boolean enMovimiento = false, saltando = false, EnSuelo = false; // * Boleanos que determinaran acciones
    public Boolean MovDerecha = false, MovIzquierda = false, MovAbajo = false, MovArriba = false;
    private Boolean pequeño = true;
    public int direccion;

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
    public Personaje(int Posx, int Posy) {
        super(Posx, Posy);
        importarImagen(PANTALLA.MarioDir);
        GetAnimations();
        altura_Tiles = 2;
        anchura_Tiles = 1;
        vivo = true;

    }

    // *se encarga de cargar las animaciones del personaje
    public void GetAnimations() {
        animaciones = animacion(21, 0, 4, 1, 2);
    }

    // *Se encarga de cargar la informacion del nivel */
    public void loadLevelData(int[][] currentLevelData) {
        this.currentLevelData = currentLevelData;
    }

    // *se encarga de la actualizacion del personaje en acciones
    public void update() {

        movimiento();
        ActHitbox();
        ActualizarAccion();
        // *funcion que determina que animacion sigue y que frame de la animacion
        ActualizarFrame();
        recibirHit();
        contInvensibilityFrames++;
    }

    // *se encarga de dibujar los frames del personaje

    public void updateFrames(java.awt.Graphics g) {

    }

    public void updateFrames(Graphics g, int offset) {
        g.drawImage(animaciones[AccionAnimation][frameAniamcion], posX - offset, posY,
                (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles), null);
        DibujarHitboxWithOffset(g, offset);
    }

    private void DibujarHitboxWithOffset(Graphics g, int offset) {
        g.setColor(Color.RED);
        g.drawRect(Hitbox.x - offset, Hitbox.y, (int) Hitbox.getWidth(), (int) Hitbox.getHeight());
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
            aux += 5;
        if (direccion == -1)
            aux += 10;
        // ! se suman 10 para que se pueda acceder a las animaciones de la izquierda
        accion = AccionPlayer.Quieto;

        // *confimacioines para la animacion y vista de la direccion

        if (MovDerecha != MovIzquierda && enMovimiento) {

            accion = AccionPlayer.Correr;
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
        float xSpeed = 0;
        float ySpeed = 0;

        if (MovAbajo != true && vivo == true) {

            if (MovDerecha == true && MovIzquierda == false) {
                xSpeed += velocidad;
            }
            if (MovIzquierda == true && MovDerecha == false) {
                xSpeed -= velocidad;
            }
        }
        if (MovArriba == true && MovAbajo == false) {
            ySpeed -= velocidad;
        }
        if (MovAbajo == true && MovArriba == false) {
            ySpeed += velocidad;
        }

        // Todo Actualizar el sistema de graveddad con mas presision y crear clase
        // vector
        // *Gravedad
        if (FuerzaSalto != 0) {
            ySpeed -= gravedad;
            FuerzaSalto--;
            EnSuelo = false;
        }
        // Todo Agregar colisiones para poner el en suelo activo y falso para el
        // funcionamiento del salto
        if (FuerzaSalto == 0) {
            ySpeed += gravedad;
        }

        if (canMoveHere(Hitbox.x, Hitbox.y + ySpeed, Hitbox.width, Hitbox.height, currentLevelData)) {
            posY += ySpeed;
        } else {
            FuerzaSalto = 0;
            ActualizarSuelo();
        }

        if (canMoveHere(Hitbox.x + xSpeed, Hitbox.y, Hitbox.width, Hitbox.height, currentLevelData)) {

            posX += xSpeed;
            enMovimiento = true;
        } else
            enMovimiento = false;

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
        Hitbox = new java.awt.Rectangle(posX, posY, (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles));
    }

    // *hitBox para enemigos
    public void HitEnemigo(Rectangle HitboxEnemigo) {
        if (Hitbox.intersects(HitboxEnemigo) && vivo == true) {
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
    public void recibirHit() {
        if (vivo == false) {
            if (contFramesMuerte == 0) {
                FuerzaSalto = 30;
            }

            contFramesMuerte++;
            if (contFramesMuerte >= Jugador.CANT_FRAMES_MUERTE) {
                posX = 300;
                posY = 350;
                vivo = true;
                pequeño = false;
                EnSuelo = true;
                contFramesMuerte = 0;
                saltando = false;
            }

        }
    }

    public void ActualizarSuelo() {
        if (isInFloor(posX, posY + 1, (int) (anchura_Tiles * PANTALLA.TILES_ACTUAL_SIZE),
                (int) (altura_Tiles * PANTALLA.TILES_ACTUAL_SIZE), currentLevelData)) {
            EnSuelo = true;
            saltando = false;

            return;
        }
        EnSuelo = false;

    }

    // #region Getters and Setters
    public int getGravedad() {
        return gravedad;
    }

    public void setGravedad(int gravedad) {
        this.gravedad = gravedad;
    }

    public int[][] getCurrentLevelData() {
        return currentLevelData;
    }

    public void setCurrentLevelData(int[][] currentLevelData) {
        this.currentLevelData = currentLevelData;
    }

    public int getContInvensibilityFrames() {
        return contInvensibilityFrames;
    }

    public void setContInvensibilityFrames(int contInvensibilityFrames) {
        this.contInvensibilityFrames = contInvensibilityFrames;
    }

    public int getAccionAnimation() {
        return AccionAnimation;
    }

    public void setAccionAnimation(int accionAnimation) {
        AccionAnimation = accionAnimation;
    }

    public int getFrameAniamcion() {
        return frameAniamcion;
    }

    public void setFrameAniamcion(int frameAniamcion) {
        this.frameAniamcion = frameAniamcion;
    }

    public int getContFrames() {
        return contFrames;
    }

    public void setContFrames(int contFrames) {
        this.contFrames = contFrames;
    }

    public int getContFramesMuerte() {
        return contFramesMuerte;
    }

    public void setContFramesMuerte(int contFramesMuerte) {
        this.contFramesMuerte = contFramesMuerte;
    }

    public int getFuerzaSalto() {
        return FuerzaSalto;
    }

    public void setFuerzaSalto(int fuerzaSalto) {
        FuerzaSalto = fuerzaSalto;
    }

    public BufferedImage[][] getAnimaciones() {
        return animaciones;
    }

    public void setAnimaciones(BufferedImage[][] animaciones) {
        this.animaciones = animaciones;
    }

    public Boolean getEnMovimiento() {
        return enMovimiento;
    }

    public void setEnMovimiento(Boolean enMovimiento) {
        this.enMovimiento = enMovimiento;
    }

    public Boolean getSaltando() {
        return saltando;
    }

    public void setSaltando(Boolean saltando) {
        this.saltando = saltando;
    }

    public Boolean getEnSuelo() {
        return EnSuelo;
    }

    public void setEnSuelo(Boolean enSuelo) {
        EnSuelo = enSuelo;
    }

    public Boolean getMovDerecha() {
        return MovDerecha;
    }

    public void setMovDerecha(Boolean movDerecha) {
        MovDerecha = movDerecha;
    }

    public Boolean getMovIzquierda() {
        return MovIzquierda;
    }

    public void setMovIzquierda(Boolean movIzquierda) {
        MovIzquierda = movIzquierda;
    }

    public Boolean getMovAbajo() {
        return MovAbajo;
    }

    public void setMovAbajo(Boolean movAbajo) {
        MovAbajo = movAbajo;
    }

    public Boolean getMovArriba() {
        return MovArriba;
    }

    public void setMovArriba(Boolean movArriba) {
        MovArriba = movArriba;
    }

    public Boolean getPequeño() {
        return pequeño;
    }

    public void setPequeño(Boolean pequeño) {
        this.pequeño = pequeño;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public AccionPlayer getAccion() {
        return accion;
    }

    public void setAccion(AccionPlayer accion) {
        this.accion = accion;
    }

    public AccionPlayer getAccionprevia() {
        return Accionprevia;
    }

    public void setAccionprevia(AccionPlayer accionprevia) {
        Accionprevia = accionprevia;
    }

    // #endregion

}