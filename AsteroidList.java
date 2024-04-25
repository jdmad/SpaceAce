package spaceace;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class AsteroidList {
    //variables
    ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
    int asteroidStock = 9;
    int t = 0; // a temporary variable, for explosion animations
    
    //methods
    public AsteroidList(){
        for(int j = 0; j < 5; j++){
            for(int i = 0; i < asteroidStock; i++){
               asteroids.add(new Asteroid(4500 + 700*j, 2, -3, 0, Boundary.rightBoundary, 20 + 108 + 25*(i+1) + 34*2*i));
            }
        }
    }
    
    public void createAsteroids(long gameTime){
        for(Asteroid a: asteroids){
            if((gameTime >= a.timeCreated) && (!a.destroyed())){
                a.create();
            }
        }
    }
    
    public void drawAsteroids(Graphics2D g){
        Asteroid a;
        for(int i = 0; i < asteroids.size(); i++){
            a = asteroids.get(i);
            if(a.exists()){
                g.drawImage(GameImages.asteroid.im, a.r.x, a.r.y, a.r.width, a.r.height, null);
            }
        }
    }
    
    public Asteroid access(int i){
        return asteroids.get(i);
    }
    
     public void removeDestroyedAsteroids(){
        for (Iterator<Asteroid> iterator = asteroids.iterator(); iterator.hasNext();){
            Asteroid a = iterator.next();
            if (a.destroyed){
                iterator.remove();
            }
        }
     }
}