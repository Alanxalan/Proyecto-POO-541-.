package graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;

/*  Esta clase se utiliza para contener todas las imagenes, fuentes y sonidos que seran utilizados en el proyecto.
Le damos un nombre a cada uno de los elementos en lo general similar al nombre de la imagen, fuente, sonido. Esto para hacerlo mas facil
ya que en unos casos podemos usar arreglos y esto nos facilita la asignacion de nombres.
Hice uso de la libreria importada FontFormatException, javax.sound.sample.Clip y java.util.logging.Level.
*/

//Los recursos donde se encuentran todas las imagenes, fuentes y sonidos, se encuentran en las carpetas.

public class Assets {
        public static boolean loaded = false;   
	public static float count = 0;
	public static float MAX_COUNT = 57;
        //Todos estos objetos tienen que ser estaticos.
	public static BufferedImage player;
        public static BufferedImage doubleGunPlayer;
        //effects
        public static BufferedImage speed;
        //lasers
        public static BufferedImage blueLaser, greenLaser, redLaser;
        //meteoros
        public static BufferedImage[] bigs = new BufferedImage[4];
        public static BufferedImage[] meds = new BufferedImage[2];
        public static BufferedImage[] smalls = new BufferedImage[2];
        public static BufferedImage[] tinies = new BufferedImage[2];
        //explosion
        public static BufferedImage[] exp = new BufferedImage[9];
        //enemies
        public static BufferedImage ufo;
        //numbers
        public static BufferedImage life;
        public static BufferedImage[] numbers = new BufferedImage[11];
	//fonts
        public static Font fontBig;
        public static Font fontMed;
        //music
        public static Clip backgroundMusic, explosion, playerLoose, playerShoot, ufoShoot, powerUp;
        //ui
        public static BufferedImage blueBtn;
	public static BufferedImage greyBtn;
        //power ups
        public static BufferedImage orb, doubleScore, doubleGun, fastFire, shield, star;
        //escudo
        public static BufferedImage[] shieldEffect = new BufferedImage[3];
        
	public static void init(){
		player = Loader.ImageLoader("/player.png"); //Esta es la manera correcta para pasar la ruta de la imagen.
                speed = Loader.ImageLoader("/fire08.png");
                blueLaser = Loader.ImageLoader("/laserBlue01.png");
                greenLaser = Loader.ImageLoader("/laserGreen11.png");
                redLaser = Loader.ImageLoader("/laserRed01.png");
                ufo = Loader.ImageLoader("/ufo.png");
                life = Loader.ImageLoader("/life.png");
            try {
                fontBig = Loader.loadFont("/futureFont.ttf", 42);   //Esta es la manera correcta para pasar la ruta de la fuente.
            } catch (FontFormatException ex) {
                Logger.getLogger(Assets.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                fontMed = Loader.loadFont("/futureFont.ttf", 20);
            } catch (FontFormatException ex) {
                Logger.getLogger(Assets.class.getName()).log(Level.SEVERE, null, ex);
            }
                //Tamano de letra
                for(int i=0;i<bigs.length;i++)
                    bigs[i] = loadImage("/big"+(i+1)+".png");
                for(int i=0;i<meds.length;i++)
                    meds[i] = loadImage("/med"+(i+1)+".png");
                for(int i=0;i<smalls.length;i++)
                    smalls[i] = loadImage("/small"+(i+1)+".png");
                for(int i=0;i<tinies.length;i++)
                    tinies[i] = loadImage("/tiny"+(i+1)+".png");
                //Numeros
                for(int i=0;i<exp.length;i++)
                    exp[i] = loadImage("/e"+i+".png");
                //Numeros
                for(int i=0;i<numbers.length;i++)
                    numbers[i] = loadImage("/numeral"+i+".png");
                //Sonidos
                backgroundMusic = loadSound("/backgroundMusic.wav");    //Esta es la manera correcta para pasar la ruta de la musica.
                explosion = loadSound("/explosion.wav");
                playerLoose = loadSound("/playerLoose.wav");
                playerShoot = loadSound("/playerShoot.wav");
                ufoShoot = loadSound("/ufoShoot.wav");
                powerUp = loadSound("/powerUp.wav");
                //Botones
                greyBtn = loadImage("/greyBtn.png");
		blueBtn = loadImage("/blueBtn.png");
                //Nuevos implementos
                doubleGunPlayer = loadImage("/doubleGunPlayer.png");
                for(int i=0;i < 3;i++)
                    shieldEffect[i] = loadImage("/shield"+(i+1)+".png");
                //Power ups
                orb = loadImage("/orb.png");
		doubleScore = loadImage("/doubleScore.png");
		doubleGun = loadImage("/doubleGun.png");
		fastFire = loadImage("/fastFire.png");
		star = loadImage("/star.png");
		shield = loadImage("/shield.png");
                //============================
                loaded = true;
	}
        public static BufferedImage loadImage(String path) {
		count ++;
		return Loader.ImageLoader(path);
	}
	public static Font loadFont(String path, int size) throws FontFormatException {
		count ++;
		return Loader.loadFont(path, size);
	}
	public static Clip loadSound(String path) {
		count ++;
		return Loader.loadSound(path);
	}
        
}