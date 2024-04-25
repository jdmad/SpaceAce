package spaceace;

//this class is used for stars, which move right to left in a straight line pattern

import javafx.scene.shape.Circle;

public class Star extends GameObject{
    Circle s = new Circle(0, 0, 1);  //circle for star
    
    public Star(int timeCreated, int size, int velX, int velY, int startX, int startY) {
        super(timeCreated, size, 1, 1, velX, velY);
        s.setCenterX(startX);
        s.setCenterY(startY);
        s.setRadius(size);
    }
    
    public void updatePosition(){
        if(exists){
            s.setCenterX(s.getCenterX() + velX);
            
            if(s.getCenterX() <= 0){
                s.setCenterX(2048);
                s.setCenterY((Boundary.topBoundary + (Boundary.bottomBoundary - Boundary.topBoundary)*Math.random()));
                velX = -1 - (int) (3*Math.random());
                s.setRadius(1 + (int) (4*Math.random()));
            }
        }
    } //move star
}