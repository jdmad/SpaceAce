package spaceace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

public class RockList {
    //variables
    ArrayList<Rock> rocks = new ArrayList<Rock>();
    int rockStock = 2;
    
    Polygon[] r = new Polygon[rockStock];
    int A = 20;
    
    int[] rock1x = {0*A, 4*A, 12*A, 25*A, 29*A, 36*A, 42*A, 47*A, 50*A};
    int[] rock1y = {0*A, 10*A, 12*A, 17*A, 16*A, 14*A, 7*A, 22*A, 0*A};

    int[] rock2x = {0*A, 3*A, 10*A, 12*A, 20*A, 30*A, 33*A, 39*A, 50*A};
    int[] rock2y = {(int)(0.9*ScreenDimensions.height), 27*A, 30*A, 40*A, 30*A, 42*A, 28*A, 29*A, (int)(0.9*ScreenDimensions.height)};
    //methods
    public RockList(){
        for(int i = 0; i < 2; i++){
            rocks.add(new Rock(7500, -1, 0, Boundary.rightBoundary, Boundary.bottomBoundary));
            rocks.add(new Rock(7500, -1, 0, Boundary.rightBoundary, Boundary.bottomBoundary));               
        }
        
        for(int i = 0; i < rock2y.length; i++){
            rock2y[i] -= 0.1*ScreenDimensions.height;
        }
        
        rocks.get(0).setCoordinates(rock1x, rock1y);
        rocks.get(1).setCoordinates(rock2x, rock2y);   
    }
    
    public void createRocks(long gameTime){
        for(Rock ro: rocks){
            if((gameTime >= ro.timeCreated) && (!ro.destroyed())){
                ro.create();
            }
        }
    }
    
    public void drawRocks(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        for(Rock ro: rocks){
            if(ro.exists()){
                g.fillPolygon(ro.p);
            }
        }
    }
}