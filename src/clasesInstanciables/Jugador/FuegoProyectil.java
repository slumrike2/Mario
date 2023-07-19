package clasesInstanciables.Jugador;

import java.awt.Graphics;
import java.awt.Rectangle;

import clasesInstanciables.Entidad;
import constantes.Constantes.PANTALLA;
import static utils.HelpMethods.isInFloor;
import static utils.HelpMethods.canMoveHere;

public class FuegoProyectil extends Entidad {

    public int direccion;
    private int[][] currentLevelData;
    private int gravedad = 2;
    private Boolean enSuelo = true;
    private boolean Active;

    public FuegoProyectil(int x, int y) {
        super(x, y);

        velocidad = 1;
        velocidadAnimacion = 120;
        altura_Tiles = 1;
        anchura_Tiles = 1;
        Active = true;
        importarImagen(PANTALLA.PROYECTIL_FUEGO_DIR);
        animaciones = animacion(1, 0, 4, (int) (altura_Tiles), (int) (anchura_Tiles));
        InicializarHitbox();
    }

    public void update() {
        ActualizarFrame();
        movimiento();
        ActualizarHitbox();

    }

    public void updateFrames(Graphics g, int offset) {
        g.drawImage(animaciones[0][frameAniamcion], Hitbox.x - offset, Hitbox.y, null);
    }

    public void ActualizarFrame() {
        if (frameAniamcion >= animaciones[0].length - 1) {
            frameAniamcion = 0;
        } else {
            frameAniamcion++;
        }
    }

    protected void InicializarHitbox() {
        Hitbox = new Rectangle(posX, posY, (int) (anchura_Tiles * PANTALLA.TILES_ACTUAL_SIZE),
                (int) (altura_Tiles * PANTALLA.TILES_ACTUAL_SIZE));
    }

    private void ActualizarHitbox() {
        InicializarHitbox();
        Hitbox.setLocation(posX, posY);
    }

    public void movimiento() {
        VerificarSuelo(currentLevelData);

        posX += velocidad * direccion;
        Hitbox.x = posX;

        if (canMoveHere(Hitbox.x + velocidad, Hitbox.y, Hitbox.width, Hitbox.height, currentLevelData))
            posX += velocidad * direccion;
        else
            Active = false;
        if (!enSuelo && canMoveHere(Hitbox.x, Hitbox.y + gravedad, Hitbox.width, Hitbox.height, currentLevelData))
            posY += gravedad;

    }

    public void loadLevelData(int[][] currentLevelData) {
        this.currentLevelData = currentLevelData;
    }

    protected void VerificarSuelo(int[][] currentLevelData) {

        enSuelo = isInFloor(posX, posY + 1, (int) (anchura_Tiles * PANTALLA.TILES_ACTUAL_SIZE),
                (int) (altura_Tiles * PANTALLA.TILES_ACTUAL_SIZE), currentLevelData);
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public boolean getActive() {
        return Active;
    }

    public void setActive(boolean Active) {
        this.Active = Active;
    }
}
