package spaceace;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class GameImage {
    BufferedImage im;
    
    public GameImage(String imageURL){
        InputStream is = getClass().getClassLoader().getResourceAsStream(imageURL);
        try {
            im = ImageIO.read(is);
        } catch (IOException ex) {
            Logger.getLogger(GameImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}