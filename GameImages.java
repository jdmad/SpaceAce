package spaceace;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

public class GameImages{
    //variables
    static GameImage playerShip = new GameImage("images/PlayerShip.png");
    
    static GameImage enemyShip = new GameImage("images/EnemyShip.png");
    
    static GameImage asteroid = new GameImage("images/Asteroid1.png");
    
    static GameImage thruster1 = new GameImage("images/Thruster1.png");
    static GameImage thruster2 = new GameImage("images/Thruster2.png");
    static GameImage thruster3 = new GameImage("images/Thruster3.png");
    static GameImage[] thruster = {thruster1, thruster2, thruster3};
    
    static GameImage explosion1 = new GameImage("images/Explosion1.png");
    static GameImage explosion2 = new GameImage("images/Explosion2.png");
    static GameImage explosion3 = new GameImage("images/Explosion3.png");
    static GameImage explosion4 = new GameImage("images/Explosion4.png");
    static GameImage explosion5 = new GameImage("images/Explosion5.png");
    static GameImage[] explosion = {explosion1, explosion2, explosion3, explosion4, explosion5};
    static int explosionCycles = 20; //duration of explosion on screen
            
    static GameImage spaceBackground11 = new GameImage("images/SpaceBackground11.png");
    
    //methods
    public static void filterImages(){
        for (GameImage exp : explosion) {
            exp.im = getFilteredImage(exp.im);
        } //filter explosions
        
        for(GameImage thr : thruster){
            thr.im = getFilteredImage(thr.im);
        }
        
        playerShip.im = getFilteredImage(playerShip.im);
        
        enemyShip.im = getFilteredImage(enemyShip.im);
        
        asteroid.im = getFilteredImage(asteroid.im);
    }
    
    public static ImageFilter pinkFilter = new RGBImageFilter(){
    @Override
    public final int filterRGB(int x, int y, int rgb){
        if(rgb == GameColors.transparentPink.getRGB()){
            return 0;
        }
        return rgb;
    }
    };//this ImageFilter will take the color "transparentPink" (see GameColors.java) and make it transparent
    
    public static BufferedImage getFilteredImage(Image im){
        ImageProducer ip = new FilteredImageSource(im.getSource(), pinkFilter);
        im = Toolkit.getDefaultToolkit().createImage(ip);
        BufferedImage bImage = new BufferedImage(im.getWidth(null),
                im.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D gTemp = bImage.createGraphics();
        gTemp.drawImage(im, 0, 0, null);
        gTemp.dispose();
        return bImage;
    }
}