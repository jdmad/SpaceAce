package spaceace;

import java.util.Random;

//this class is used for powerups and upgrades, which increase the player's current or total health or energy
public class PowerUp {
    //powerup variables
    private int amount = 0;             //amount of health or energy to add
    private int x = 0;                  //position in X
    private int y = 0;                  //position in Y
    private int velX = 0;               //velocity in X
    private int velY = 0;               //velocity in Y
    private int width = 0;              //width of powerup icon
    private int height = 0;             //height of powerup icon
    private int startAngle = 0;         //start angle for circle in powerup icon
    private int angleWidth = 0;         //width of angle for circle in powerup icon
    private boolean exists = false;     //true when a certain enemy is defeated or conditions are met
    private int type = 0;               //0 for health, 1 for energy
    private Random r;                   //random amount to vary powerup amount
    
    //powerup methods
    public PowerUp(int startType, int startAmount, int startx, int starty, int startxDir, int startyDir, int pwidth, int pheight, int pStartAngle, int pAngleWidth){
        type = startType;
        r = new Random(System.currentTimeMillis());
        if(type == 0){
            amount = startAmount + r.nextInt(5);
        }else{
            amount = startAmount + 100*r.nextInt(10);
        }
        x = startx;
        y = starty;
        velX = startxDir;
        velY = startyDir;
        width = pwidth;
        height = pheight;
        startAngle = pStartAngle;
        angleWidth = pAngleWidth;
    } //constructor
    
    public void updatePosition(){
        if(exists){
            x += velX;
            y += velY;
            if((x + width) <= Boundary.leftBoundary){
                destroy();
            }
        }
    } //move powerup, based on X and Y velocities
    
    public void create(int startingy){
        exists = true;
        y = startingy;
    } //causes powerup to appear
    
    public void destroy(){
        exists = false;
    } //causes powerup to disappear
    
    public int getAmount(){
        return amount;
    }
    
    public int getx(){
        return x;
    }
    
    public int gety(){
        return y;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getStartAngle(){
        return startAngle;
    }
    
    public int getAngleWidth(){
        return angleWidth;
    }
    
    public boolean exists(){
        return exists;
    }
    
    public int getType(){
        return type;
    }
}
