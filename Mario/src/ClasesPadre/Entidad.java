package ClasesPadre;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

import javax.imageio.ImageIO;

public abstract class Entidad {

    public int velocidad; // velocidad en pixeles de forma horizontal
    public int posX, posY;
    public boolean vivo = true;
    protected BufferedImage imagen;
    protected BufferedImage[][] animaciones;
    protected int AccionAnimation = 0, frameAniamcion = 0, contFrames = 0, velocidadAnimacion;

    // * Colliision
    protected Rectangle Hitbox;

    public Entidad(String Dir, int Posx, int Posy, int velocidad) {
        importarImagen(Dir);
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

    public void updateFrames(java.awt.Graphics g) {
        g.drawImage(animaciones[AccionAnimation][frameAniamcion], posX, posY, null);
    }

    public abstract void ActualizarFrame();

    protected void ActualizarPosHitbox() {
        Hitbox.setLocation(posX, posY);
    }

    protected abstract void InicializarHitbox();

    public void DibujarHitbox(Graphics g) {
        g.drawRect(Hitbox.x, Hitbox.y, Hitbox.width, Hitbox.height);
    }

    public Rectangle getHitbox() {
        return Hitbox;
    }
}
