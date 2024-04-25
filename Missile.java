package spaceace;

//this class is used for the player's missiles, which damage and destroy asteroids, enemies, and bosses

import java.awt.Rectangle;

public class Missile {
    //missile variables
    private int size = 10;                                  //length and width of missile
    private int x = 0;                                      //position in X
    private int y = 0;                                      //position in Y
    private int velX = 10;                                  //velocity in X
    private int velY = 0;                                   //velocity in Y
    private boolean missileFired = false;                   //true when player presses space and fires a missile
    Rectangle m = new Rectangle(x, y, size, size);          //polygon for missile
    
    //missile methods
    public Missile(int size, int x, int y, int xDir, int yDir){
        this.size = size;
        this.x = x;
        this.y = y;
        velX = xDir;
        velY = yDir;
        m.setBounds(x, y, size, size);
    } //constructor
    
    public void initialize(int size, int x, int y, int xDir, int yDir){
        this.size = size;
        this.x = x;
        this.y = y;
        velX = xDir;
        velY = yDir;
        m.setBounds(x, y, size, size);
    }
    
    public void updatePosition(){
        if(missileFired){
            m.translate(velX, velY);

            if(m.x >= Boundary.rightBoundary){
                destroy();
            }
        }
    } //move missile based on X and Y velocities
    
    public void fire(int cannonx, int cannony, int shipVelY){
        missileFired = true;
        if(shipVelY > 0){
            sety(cannony + 55);
        }else if(shipVelY < 0){
            sety(cannony + 35);
        }else{
            sety(cannony + 45);
        }
        setx(cannonx + 40);
    } //create missile, and set its starting position based on player's ship position and velocity
    
    public void destroy(){
        missileFired = false;
    } //causes missile to disappear, such as when it goes off screen or hits an object
    
    public boolean exists(){
        return missileFired;
    }
    
    public int getx(){
        return m.x;
    }
    
    public int gety(){
        return m.y;
    }
    
    public void setx(int x){
        m.x = x;
    }
    
    public void sety(int y){
        m.y = y;
    }
    
    public int getSize(){
        return size;
    }  
}