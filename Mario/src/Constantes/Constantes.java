package constantes;

public class Constantes {
    public static class PANTALLA {
        // !public static final int SCREEN_WIDTH = 1080;
        // !public static final int SCREEN_HEIGHT = 720;
        public static final int TILES_DEFAULT_SIZE = 16;
        public static final float TILES_DEFAULT_SCALE = 1f;
        public static final int TILES_IN_WIDTH = 30;
        public static final int TILES_IN_HEIGHT = 30;
        public static final int TILES_SIZE = (int) (TILES_DEFAULT_SCALE * TILES_DEFAULT_SIZE);

        public static final int SCREEN_WIDTH = TILES_IN_WIDTH * TILES_SIZE;
        public static final int SCREEN_HEIGHT = TILES_IN_HEIGHT * TILES_SIZE;

        public static final String MarioDir = "res/marioAnimations.png";
        public static final String GoombaDir = "Sprites/cusGoombaSprite.png";
        public static final String KoopaDir = "Sprites/KoopaSprite.png";
        public static final String KoopaVoladorDir = "Sprites/KoopaVoladorSprite.png";
        public static final String BowserDir = "Sprites/BowserSprite.png";
    }

    public static class Enemigos {
        public static final int GOOMBA_VELC = 0;
        public static final int KOOPA_VELC = 1;
        public static final int KOOPA_VOLADOR_VELC = 1;
        public static final int BOWSER_VELC = 1;
    }

    public static class Jugador {
        public static final int MARIO_VELC = 2;
        public static final int MARIO_VELCIDAD_ANIMACION = 10;
        public static final int INVENSIBILITY_FRAMES = 500;
        public static final int MARIO_JUMP_FORCE = 30;
        public static final int BIG_SPRITE_HEIGTH = 32;
        public static final int SPRITE_WIDTH = 16;
        public static final int SMALL_SPRITE_HEIGTH = 16;
        public static final int MARIO_BIG_DOWN_HEIGHT = 24;
        public static final int CANT_FRAMES_MUERTE = 120;
    }

    public static class Globales {
        public static final int GRAVEDAD = 3;
    }

    public enum Pantallas_Menues {
        MENU_PRINCIPAL, REGISTRO, INICIO_SESION, MENU_GESTIONAR_COLECCION
    }

    public enum Pantallas_Mundos {
        MUNDO_1, PANTALLA_2, PANTALLA_3, PANTALLA_4, PANTALLA_5
    }
}
