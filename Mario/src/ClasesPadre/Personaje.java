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

    // *Cada cuantos frames se actualiza la animacion
    public int velocidadAnimacion = 20;
    public BufferedImage[][] animaciones;
    
    public boolean moverDerecha = false, quieto = true;

    // #region Metodos
    // *Dir reseprenta la direccion de la hoja de sprite correspondiente */
    public Personaje(String Dir, int Posx, int Posy) {
        super(Dir, Posx, Posy);
        GetAnimations();
    }

    public void GetAnimations() {
        animaciones = animacion(20, 0, 3, 16, 32);
    }

}
