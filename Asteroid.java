package spaceace;

import java.awt.Rectangle;

//this class is used for asteroids, which move right to left in a straight line pattern
public class Asteroid extends GameObject{
    //asteroid variables
    Rectangle r;   
    
    //asteroid methods
    public Asteroid(int timeCreated, int size, int velX, int velY, int startX, int startY){
        super(timeCreated, size, 1, 1, velX, velY);
        topLeftX = startX;
        topLeftY = startY;
        this.r = new Rectangle(topLeftX, topLeftY, 34*size, 34*size);
    } //constructor
    
    public void updatePosition(){
        if(exists){
            r.translate(velX, velY);

            if((r.y + r.height) >= Boundary.bottomBoundary){
                r.y = Boundary.bottomBoundary - (23*size);
            }
            if(r.y <= Boundary.topBoundary){
                r.y = Boundary.topBoundary + (9*size);
            }
            if((r.x + r.width) <= Boundary.leftBoundary){
                destroy();
            }
        }
    } //update position of asteroid based on X and Y velocities
}