package clasesPadre;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;

import Constantes.Constantes.PANTALLA;

public abstract class Entidad {

    public int altura_Tiles;
    public int anchura_Tiles;
    public int velocidad; // velocidad en pixeles de forma horizontal
    public int posX, posY;
    public boolean vivo = true;
    protected BufferedImage imagen;
    protected BufferedImage[][] animaciones;
    protected int AccionAnimation = 0, frameAniamcion = 0, contFrames = 0, velocidadAnimacion;

    // * Colliision
    protected Rectangle Hitbox;

    public Entidad(String Dir, int Posx, int Posy, int velocidad, int altura_Tiles, int anchura_Tiles) {
        importarImagen(Dir);
        this.altura_Tiles = altura_Tiles;
        this.anchura_Tiles = anchura_Tiles;
        this.posX = Posx;
        this.posY = Posy;
        this.velocidad = velocidad;
        InicializarHitbox();
    }

    public Entidad(String Dir) {
        importarImagen(Dir);
        InicializarHitbox();
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

    public abstract void update();

    public abstract void updateFrames(Graphics g);

    public abstract void ActualizarFrame();

    protected void ActualizarPosHitbox() {
        Hitbox.setLocation(posX, posY);
    }

    protected abstract void InicializarHitbox();

    public void DibujarHitbox(Graphics g) {
        g.drawRect(Hitbox.x, Hitbox.y, Hitbox.width, (Hitbox.height));
    }

    public Rectangle getHitbox() {
        return Hitbox;
    }
}
