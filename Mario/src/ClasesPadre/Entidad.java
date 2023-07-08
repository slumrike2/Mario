package ClasesPadre;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

import javax.imageio.ImageIO;

public abstract class Entidad {

    public int posX, posY;
    public BufferedImage imagen;

    public Entidad(String Dir, int Posx, int Posy) {
        importarImagen(Dir);
        this.posX = Posx;
        this.posY = Posy;
    }

    private void importarImagen(String Dir) {
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream(Dir));
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen");
        }
    }

    public BufferedImage[][] animacion(int animaciones, int xInicial, int xfinal, int ancho, int alto) {
        BufferedImage[][] animacion = new BufferedImage[animaciones][xfinal - xInicial];
        for (int j = 0; j < animaciones; j++) {
            for (int i = xInicial; i < xfinal; i++) {
                animacion[j][i - xInicial] = imagen.getSubimage(i * ancho, j * alto, ancho, alto);
            }
        }
        return animacion;
    }
}
