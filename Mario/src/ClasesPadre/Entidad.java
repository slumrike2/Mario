
package ClasesPadre;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

import javax.imageio.ImageIO;

public class Entidad {

    public BufferedImage imagen;

    public Entidad(String Dir) {
        importarImagen(Dir);
    }

    private void importarImagen(String Dir) {
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream(Dir));
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen");
        }
    }

}
