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
        public static final String MONEDA_DIR = "res/Coin.png";
        public static final String FLOR_DIR = "res/FlorFuego.png";
        public static final String ESTRELLA_DIR = "res/Star.png";
        public static final String HONGO_DIR = "res/Hongo.png";
        public static final String HONGO_VENENOSO_DIR = "res/HongoPoison.png";
        public static final String PROYECTIL_FUEGO_DIR = "res/DisparoFuego.png";
        public static final String MISTERY_BLOCK_DIR = "res/MisteryBlock.png";
        public static final String FIREBALL_BOWSER_DIR = "Sprites/BowserFireball.png";

    }

    public static class Enemigos {
        public static final int GOOMBA_VELC = 1;
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

        public static final int GOOMBA_POINTS = 100;
        public static final int KOOPA_POINTS = 200;
        public static final int KOOPA_VOLADOR_POINTS = 300;
        public static final int BOWSER_POINTS = 500;

    }

    public static class ENTITY_TYPE {

        public enum Proyectiles {
            BOLA_FUEGO, FIREBALL_BOWSER
        }

        public enum CHARACTERS {
            MAIN, MARIO, LUIGI, WARIO, WALUIGI
        }

        public enum ENEMIES {
            BOWSER, KOOPA, KOOPA_VOLADOR, GOOMBA // ! el orden importa
        }

        public enum ITEMS {
            MONEDA, FLOR, ESTRELLA, HONGO, HONGO_VENENOSO, BLOQUE_MISTERIOSO
        }

    }

    public static class Jugador {
        private static final int FramesPerSecond = 120;
        public static final float MARIO_VELC = 1.3f;
        public static final int MARIO_VELCIDAD_ANIMACION = 10;
        public static final int INVENSIBILITY_FRAMES = 1 * FramesPerSecond;
        public static final int MARIO_JUMP_FORCE = 40;
        public static final int BIG_SPRITE_HEIGTH = PANTALLA.TILES_ACTUAL_SIZE;
        public static final int SPRITE_WIDTH = PANTALLA.TILES_ACTUAL_SIZE;
        public static final int SMALL_SPRITE_HEIGTH = PANTALLA.TILES_ACTUAL_SIZE;
        public static final int MARIO_BIG_DOWN_HEIGHT = PANTALLA.TILES_ACTUAL_SIZE + PANTALLA.TILES_ACTUAL_SIZE / 2;
        public static final int CANT_FRAMES_MUERTE = 1 * FramesPerSecond - 20;
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

        public static final int MONEDA_POINTS = 100;
        public static final int FLOR_POINTS = 200;
        public static final int HONGO_POINTS = 300;
        public static final int ESTRELLA_POINTS = 500;
        public static final int HONGO_VENENOSO_POINTS = -500;

    }

    public static class Globales {
        public static final int GRAVEDAD = 3;
    }

    public enum Pantallas_Menues {
        MENU_PRINCIPAL, REGISTRO, INICIO_SESION, MENU_GESTIONAR_COLECCION
    }

    public enum NIVEL {
        NIVEL_1("res/level1/"),
        NIVEL_2("res/level2/"),
        NIVEL_3("res/level3/"),
        NIVEL_4("res/level4/"),
        NIVEL_FINAL("res/finalLevel");

        private final String path;

        NIVEL(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

    }

}