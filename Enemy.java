package spaceace;

//this class is used for enemy ships, which move right to left in a sine wave pattern

import java.awt.Rectangle;

public class Enemy extends GameObject{
    //enemy variables
    private int amplitude = 0;                                          //height parameter for sine wave movement pattern
    Rectangle r;                 
    
    //enemy methods
    public Enemy(int timeCreated, int size, int velX, int velY, int startX, int startY, int amplitude){
        super(timeCreated, size, 1, 1, velX, velY);
        topLeftX = startX;
        topLeftY = startY;
        this.r = new Rectangle(topLeftX, topLeftY, 3*size, 4*size);
        this.amplitude = amplitude;
    } //constructor
    
    public void updatePosition(){
        if(exists){
            double rad = Math.PI/180;
            r.translate(velX, (int) (velX*amplitude*rad*Math.cos(Boundary.rightBoundary - rad*r.x)));
            
            if(r.x + size <= Boundary.leftBoundary){
                destroy();
            }
        }  
    } //move enemy in a sine pattern, based on X velocity
}