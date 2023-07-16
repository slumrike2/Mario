package clasesInstanciables;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

import javax.imageio.ImageIO;

public abstract class Entidad {

    public float altura_Tiles;
    public float anchura_Tiles;
    public int velocidad; // velocidad en pixeles de forma horizontal
    public int posX, posY;
    public boolean vivo = true;
    protected BufferedImage imagen;
    protected BufferedImage[][] animaciones;
    protected int AccionAnimation = 0, frameAniamcion = 0, contFrames = 0, velocidadAnimacion;

    // * Colliision
    public Rectangle Hitbox;

    public Entidad(String Dir, int Posx, int Posy, int velocidad, int altura_Tiles, int anchura_Tiles) {
        importarImagen(Dir);
        this.altura_Tiles = altura_Tiles;
        this.anchura_Tiles = anchura_Tiles;
        this.posX = Posx;
        this.posY = Posy;
        this.velocidad = velocidad;
        InicializarHitbox();
    }

    public Entidad(String Dir, int posX, int posY, float altura_Tiles, float anchura_Tiles) {
        importarImagen(Dir);
        this.posX = posX;
        this.posY = posY;
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

    // region Getters y Setters
    public float getAltura_Tiles() {
        return altura_Tiles;
    }

    public void setAltura_Tiles(int altura_Tiles) {
        this.altura_Tiles = altura_Tiles;
    }

    public float getAnchura_Tiles() {
        return anchura_Tiles;
    }

    public void setAnchura_Tiles(int anchura_Tiles) {
        this.anchura_Tiles = anchura_Tiles;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public BufferedImage[][] getAnimaciones() {
        return animaciones;
    }

    public void setAnimaciones(BufferedImage[][] animaciones) {
        this.animaciones = animaciones;
    }

    public int getAccionAnimation() {
        return AccionAnimation;
    }

    public void setAccionAnimation(int accionAnimation) {
        AccionAnimation = accionAnimation;
    }

    public int getFrameAniamcion() {
        return frameAniamcion;
    }

    public void setFrameAniamcion(int frameAniamcion) {
        this.frameAniamcion = frameAniamcion;
    }

    public int getContFrames() {
        return contFrames;
    }

    public void setContFrames(int contFrames) {
        this.contFrames = contFrames;
    }

    public int getVelocidadAnimacion() {
        return velocidadAnimacion;
    }

    public void setVelocidadAnimacion(int velocidadAnimacion) {
        this.velocidadAnimacion = velocidadAnimacion;
    }

    public void setHitbox(Rectangle hitbox) {
        Hitbox = hitbox;
    }

    // endregion

}