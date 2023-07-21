package clasesInstanciables.Enemigos;

import java.awt.image.BufferedImage;

import constantes.Constantes.Enemigos;
import constantes.Constantes.PANTALLA;
import static utils.HelpMethods.canMoveHere;

public class Goomba extends Enemigo {

    // Todo Faltan la muerte y las hitboxes
    BufferedImage[][] animaciones;

    private int AccionAnimation = 0, frameAniamcion = 0, contFrames = 0;
    public boolean enMovimiento = true;

    public Goomba(int Posx, int Posy) {
        super(Posx, Posy);
        importarImagen(PANTALLA.GoombaDir);
        velocidad = Enemigos.GOOMBA_VELC;
        altura_Tiles = Enemigos.GOOMBA_HIGH_TILES;
        anchura_Tiles = Enemigos.GOOMBA_WIDTH_TILES;
        animaciones = animacion(3, 0, 2, (int) anchura_Tiles, (int) altura_Tiles);
        puntajeDado = Enemigos.GOOMBA_POINTS;
        velocidadAnimacion = 20;
        enSuelo = false;
        vivo = true;
        vidas = 1;
        InicializarHitbox();
    }

    public void update() {
        super.update();
        movimiento();
        ActualizarAccion();
        ActualizarFrame();
        ActualizarHitbox();
        contFramesInvensible++;
    }

    public void updateFrames(java.awt.Graphics g, int offset) {
        g.drawImage(animaciones[AccionAnimation][frameAniamcion], posX - offset, posY,
                (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles), null);
        DibujarHitboxWithOffset(g, offset);
    }

    public void movimiento() {
        VerificarSuelo(currentLevelData);

        if (canMoveHere(Hitbox.x + velocidad, Hitbox.y, Hitbox.width, Hitbox.height, currentLevelData))
            posX += velocidad;
        else
            cambiarDireccion();

        if (!enSuelo && canMoveHere(Hitbox.x, Hitbox.y + gravedad, Hitbox.width, Hitbox.height, currentLevelData)
                && vidas != 3)
            posY += gravedad;
    }

    public void cambiarDireccion() {
        velocidad *= -1;
    }

    public void ActualizarAccion() {
        AccionAnimation = 1;
        if (vidas <= 0) {
            AccionAnimation = 2;
            vivo = false;
            return;
        }
        if (enMovimiento == false) {
            AccionAnimation = 0;
            return;
        }

    }

    public void ActualizarFrame() {
        contFrames++;

        if (contFrames >= velocidadAnimacion) {
            contFrames = 0;
            frameAniamcion++;
            if (vidas <= 0 || enMovimiento == false) {
                frameAniamcion = 0;
                return;
            }

            if (frameAniamcion > 1) {
                frameAniamcion = 0;
            }

        }
    }

    protected void InicializarHitbox() {
        Hitbox = new java.awt.Rectangle(posX, posY, (int) (PANTALLA.TILES_ACTUAL_SIZE * anchura_Tiles),
                (int) (PANTALLA.TILES_ACTUAL_SIZE * altura_Tiles));
    }

}
