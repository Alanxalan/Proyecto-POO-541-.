package graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*  Esta clase se encargara de cargar imagenes,sonidos y recursos externos que agreguemos.  */

public class Loader {
    //BefferedImage es como java guarda imagenes en memoria.
    public static BufferedImage ImageLoader(String path) {  //String path es la ruta de la imagen.
	try {
            return ImageIO.read(Loader.class.getResource(path));    //Se pasa la ruta.
	} catch (IOException e) {
            e.printStackTrace();    //Nos imprime si hay un error en la carga de la imagen.
	}
	return null;
    }
        
    public static Font loadFont(String path, int size) throws FontFormatException{
        try {
            return Font.createFont(Font.TRUETYPE_FONT, Loader.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Clip loadSound(String path){
        
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Loader.class.getResource(path)));
            return clip;
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        return null;
    }
}