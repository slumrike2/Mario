package clasesInstanciables.Enemigos;

import java.awt.Graphics;
import java.awt.Rectangle;

import clasesInstanciables.Entidad;
import clasesInstanciables.Jugador.FuegoProyectil;
import constantes.Constantes.PANTALLA;
import static utils.HelpMethods.canMoveHere;

public class BowserFireball extends Entidad {

    public int direccion = 0, direccionX = 0;
    private int[][] currentLevelData;

    private Boolean Active = true;

    public BowserFireball(int x, int y) {
        super(x, y);

        velocidad = 1.2f;
        velocidadAnimacion = 60;
        altura_Tiles = 1;
        anchura_Tiles = 2;
        contFrames = 0;
        importarImagen(PANTALLA.FIREBALL_BOWSER_DIR);
        animaciones = animacion(2, 0, 2, 2, 1);
        InicializarHitbox();
    }

    public void ActualizarFrame() {
        contFrames++;
        if (contFrames >= velocidadAnimacion) {
            contFrames = 0;
            if (frameAniamcion == 1)
                frameAniamcion = 0;
            else
                frameAniamcion = 1;

        }
    }

    @Override
    public void updateFrames(Graphics g, int offset) {
        if (direccion == 1) {
            g.drawImage(animaciones[0][frameAniamcion], Hitbox.x - offset, Hitbox.y, Hitbox.width,
                    Hitbox.height,
                    null);
        } else
            g.drawImage(animaciones[1][frameAniamcion], Hitbox.x - offset, Hitbox.y, Hitbox.width,
                    Hitbox.height,
                    null);
    }

    @Override
    protected void InicializarHitbox() {
        Hitbox = new Rectangle(posX, posY, (int) (anchura_Tiles * PANTALLA.TILES_ACTUAL_SIZE),
                (int) (altura_Tiles * PANTALLA.TILES_ACTUAL_SIZE));
    }

    private void ActualizarHitbox() {
        Hitbox.setLocation(posX, posY);
    }

    public void movimiento() {

        if (canMoveHere(Hitbox.x + velocidad * direccion, Hitbox.y, Hitbox.width, Hitbox.height, currentLevelData))
            posX += (velocidad + 1) * direccion;
        else
            Active = false;
    }

    public void loadLevelData(int[][] currentLevelData) {
        this.currentLevelData = currentLevelData;
    }

    public boolean getActive() {
        return Active;
    }

    public void update() {
        ActualizarFrame();
        movimiento();
        ActualizarHitbox();
    }
}
