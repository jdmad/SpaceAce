package spaceace;

import java.awt.Polygon;

public class Rock extends GameObject{
    //Rock variables
    private final int[] initArray = {0,0,0,0,0,0,0,0,0};    //array to initialize rock vertices
    Polygon p = new Polygon(initArray, initArray, 9);       //polygon for rock
    
    //Rock methods
    public Rock(int timeCreated, int velX, int velY, int startX, int startY){
        super(timeCreated, 1, 1, 1, velX, velY);
    } //constructor
    
    public void setCoordinates(int xCoordinates[], int yCoordinates[]){
        for(int i = 0; i < p.npoints; i++){
            p.xpoints[i] = xCoordinates[i] + 1920;
            p.ypoints[i] = yCoordinates[i] + 108;
        }
    } //set x and y coordinates of rock, after rock object is created
    
    public void updatePosition(){
        if(exists){
            p.translate(velX, velY);
        }
        
        if(p.xpoints[p.npoints - 1] < 0){
                destroy();
            }
    } //move rock based on x and y velocities
}
