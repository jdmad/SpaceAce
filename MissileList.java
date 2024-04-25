package spaceace;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class MissileList {
    //variables
    ArrayList<Missile> missiles = new ArrayList<Missile>();
    int missileStock = 20;
    int missileSize = 10;
    
    //methods
    public MissileList(){
        for(int i = 0; i < missileStock; i++){
            missiles.add(new Missile(missileSize, 0, 0, 10, 0));
        }
    }
    
    public void drawMissiles(Graphics g){
        g.setColor(Color.YELLOW);
        for(Missile m: missiles){
            if(m.exists()){
                g.fillRect(m.m.x,
                        m.m.y,
                        m.m.width,
                        m.m.height);
            }
        }
    }
    
    public Missile access(int i){
        return missiles.get(i);
    }
}