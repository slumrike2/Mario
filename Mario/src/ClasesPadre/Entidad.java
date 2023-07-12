package ClasesPadre;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public abstract class Entidad {

    public int velocidadHorizontal; //velocidad en pixeles de forma horizontal
    public int posX, posY;
    public BufferedImage imagen;

    public Entidad(String Dir, int Posx, int Posy) {
    public Entidad(String Dir, int Posx, int Posy, int velocidad) {
        importarImagen(Dir);
        this.posX = Posx;
        this.posY = Posy;
        this.velocidadHorizontal = velocidad;
    }

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
