package Constantes;

public class Constantes {
    public static class PANTALLA {
        public static final int SCREEN_WIDTH = 1080;
        public static final int SCREEN_HEIGHT = 720;
        public static final String MarioDir = "res/marioAnimations.png";
        public static final String GoombaDir = "Sprites/cusGoombaSprite.png";
        public static final String KoopaDir = "Sprites/KoopaSprite.png";
        public static final String KoopaVoladorDir = "Sprites/KoopaVoladorSprite.png";
        public static final String BowserDir = "Sprites/BowserSprite.png";
    }

    public enum PANTALLAS_MENUES {
        MENU_PRINCIPAL, REGISTRO, INICIO_SESION, MENU_GESTIONAR_COLECCION
    }

    public enum PANTALLAS_MUNDOS {
        MUNDO_1, PANTALLA_2, PANTALLA_3, PANTALLA_4, PANTALLA_5
    }
}
