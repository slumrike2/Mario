package ClasesPadre;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Personaje extends Entidad {
    // #region Variables

    // *Porsicion del personaje en la pantalla */
    public int posicionX = 0, posicionY = 0;
    // Cada cuantos frames se actualiza la animacion
    public int velocidadAnimacion = 20;
    public BufferedImage[] animacionCaminarGrande;
    public BufferedImage quietoder = imagen.getSubimage(0 * 16, 2 * 32, 16, 32);
    public BufferedImage quietoizq = imagen.getSubimage(20 * 16, 0 * 32, 16, 32);
    public boolean moverDerecha = false, quieto = true;

    // #region Metodos
    // *Dir reseprenta la direccion de la hoja de sprite correspondiente */
    public Personaje(String Dir) {
        super(Dir);
        animacionCaminarGrande();
    }

    public void animacionCaminarGrande() {
        animacionCaminarGrande = animacion(3, 1, 3, 16, 32, 2);
    }

}
