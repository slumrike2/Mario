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
    public BufferedImage[] animacionIdle;

    // #region Metodos
    // *Dir reseprenta la direccion de la hoja de sprite correspondiente */
    public Personaje(String Dir) {
        super(Dir);
    }

    public void animacionIdle() {
        animacionIdle = animacion(3, 0, 3, 24, 16, 0);
    }

}
