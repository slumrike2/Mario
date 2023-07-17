package constantes;

public class Constantes {
    public static class PANTALLA {
        // !public static final int SCREEN_WIDTH = 1080;
        // !public static final int SCREEN_HEIGHT = 720;
        public static final int TILES_DEFAULT_SIZE = 16;
        public static final float PIXEL_SCALE = 1.5f;
        public static final int TILES_IN_WIDTH = 40;
        public static final int TILES_IN_HEIGHT = 24;
        public static final int TILES_ACTUAL_SIZE = (int) (PIXEL_SCALE * TILES_DEFAULT_SIZE);

        public static final int SCREEN_WIDTH = TILES_IN_WIDTH * TILES_ACTUAL_SIZE;
        public static final int SCREEN_HEIGHT = TILES_IN_HEIGHT * TILES_ACTUAL_SIZE;

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
        public static final int GOOMBA_HIGH_TILES = 1;
        public static final int GOOMBA_WIDTH_TILES = 1;
        public static final int KOOPA_HIGH_TILES = 1;
        public static final int KOOPA_WIDTH_TILES = 1;
        public static final int KOOPA_VOLADOR_HIGH_TILES = 2;
        public static final int KOOPA_VOLADOR_WIDTH_TILES = 1;
        public static final int BOWSER_HIGH_TILES = 2;
        public static final int BOWSER_WIDTH_TILES = 2;

    }

    public static class ENTITY_TYPE {

        public enum CHARACTERS {
            MARIO, LUIGI, WARIO, WALUIGI
        }

        public enum ENEMIES {
            GOOMBA, KOOPA, KOOPA_VOLADOR, BOWSER
        }

        public enum ITEMS {
            MONEDA, FLOR, ESTRELLA, HONGO
        }

        public enum ATAQUES {
            BOLA_FUEGO, BOLA_HIELO, CAPARAZON_AZUL
        }
    }

    public static class Jugador {
        private static final int FramesPerSecond = 120;
        public static final int MARIO_VELC = 2;
        public static final int MARIO_VELCIDAD_ANIMACION = 10;
        public static final int INVENSIBILITY_FRAMES = 1 * FramesPerSecond;
        public static final int MARIO_JUMP_FORCE = 40;
        public static final int BIG_SPRITE_HEIGTH = PANTALLA.TILES_ACTUAL_SIZE;
        public static final int SPRITE_WIDTH = PANTALLA.TILES_ACTUAL_SIZE;
        public static final int SMALL_SPRITE_HEIGTH = PANTALLA.TILES_ACTUAL_SIZE;
        public static final int MARIO_BIG_DOWN_HEIGHT = PANTALLA.TILES_ACTUAL_SIZE + PANTALLA.TILES_ACTUAL_SIZE / 2;
        public static final int CANT_FRAMES_MUERTE = 1 * FramesPerSecond;
    }

    public static class Items {
        public static final int MONEDA_HIGH_TILES = 1;
        public static final int MONEDA_WIDTH_TILES = 1;
        public static final int FLOR_HIGH_TILES = 1;
        public static final int FLOR_WIDTH_TILES = 1;
        public static final int ESTRELLA_HIGH_TILES = 1;
        public static final int ESTRELLA_WIDTH_TILES = 1;
        public static final int HONGO_HIGH_TILES = 1;
        public static final int HONGO_WIDTH_TILES = 1;

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