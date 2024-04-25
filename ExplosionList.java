package spaceace;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class ExplosionList {
    //variables
    ArrayList<Explosion> explosions = new ArrayList<Explosion>();
    
    //methods
    public ExplosionList(){
        //
    }
    
    public void drawExplosions(Graphics2D g){
        int t;
        Explosion e;
        for (int i = explosions.size() - 1; i >= 0; i--){
            e = explosions.get(i);
            t = Time.currentCycle - e.cycleDestroyed;
            if(t < (GameImages.explosion.length*e.cyclesToAnimate)){
                g.drawImage(GameImages.explosion[t/e.cyclesToAnimate].im,
                            e.xDestroyed, e.yDestroyed, e.width, e.height, null);
            }else{
                explosions.remove(i);
            }
        }
    }
}