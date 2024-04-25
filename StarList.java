package spaceace;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class StarList {
    //variables
    ArrayList<Star> stars = new ArrayList<Star>();
    int starStock = 12;
    
    //methods
    public StarList(){//make this a for loop
            Star star1 = new Star(0, 1, -1, 0, 2048, 200);
            Star star2 = new Star(0, 4, -2, 0, 12, 800);
            Star star3 = new Star(0, 2, -1, 0, 256, 300);
            Star star4 = new Star(0, 1, -3, 0, 607, 750);
            Star star5 = new Star(0, 3, -8, 0, 1555, 450);
            Star star6 = new Star(0, 1, -1, 0, 401, 850);
            Star star7 = new Star(0, 5, -1, 0, 2048, 250);
            Star star8 = new Star(0, 2, -3, 0, 1405, 700);
            Star star9 = new Star(0, 3, -1, 0, 1569, 350);
            Star star10 = new Star(0, 2, -2, 0, 1678, 450);
            Star star11= new Star(0, 4, -1, 0, 1987, 300);
            Star star12 = new Star(0, 5, -2, 0, 1106, 650);
            
            stars.add(star1);
            stars.add(star2);
            stars.add(star3);
            stars.add(star4);
            stars.add(star5);
            stars.add(star6);
            stars.add(star7);
            stars.add(star8);
            stars.add(star9);
            stars.add(star10);
            stars.add(star11);
            stars.add(star12); 
    }
    
    public void createStars(long gameTime){
        for(Star s: stars){
            if((gameTime >= s.timeCreated)){
                s.create();
            }
        }
    }
    
    public void drawStars(Graphics g){
        g.setColor(Color.WHITE);
        for(Star s: stars){
            g.fillOval((int)s.s.getCenterX(),
                    (int)s.s.getCenterY(),
                    2*(int)(s.s.getRadius()),
                    2*(int)(s.s.getRadius()));
        }
    }
    
    public Star access(int i){
        return stars.get(i);
    }
}
