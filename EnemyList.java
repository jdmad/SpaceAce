package spaceace;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class EnemyList {
    //variables
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    int enemyStock = 6;
    int t = 0; // a temporary variable, for explosion animations
    
    //methods
    public EnemyList(){
        
        int velocityX = -6;
        int amplitude = 50;
        double trueA = 2*amplitude - (10*velocityX + 60);
        int enemyScaleY = 4;
        int enemySize = 20;
        int enemyCount = 5;
        int space = (int)((0.8*ScreenDimensions.height - trueA - enemyCount*enemyScaleY*enemySize)/(enemyCount - 1));
        for(int j = 0; j < 6; j++){
            for(int i = 0; i < enemyCount; i++){
                enemies.add(new Enemy(800*j, enemySize, velocityX, -1, Boundary.rightBoundary, Boundary.topBoundary + (space + enemyScaleY*enemySize)*i, amplitude));
            }
        }
        
        for(int j = 0; j < 10; j++){
            for(int i = 0; i < enemyCount; i++){
                enemies.add(new Enemy(30000 + 500*j, enemySize, velocityX, -1, Boundary.rightBoundary, Boundary.topBoundary + (space + enemyScaleY*enemySize)*i, amplitude));
            }
        }
    }
    
    public void createEnemies(long gameTime){
        for(Enemy e: enemies){
            if((gameTime >= e.timeCreated) && (!e.destroyed())){
                e.create();
            }
        }
    }
    
    public void drawEnemies(Graphics2D g){
        Enemy e;
        for(int i = 0; i < enemies.size();i++){
            e = enemies.get(i);
            if(e.exists()){
                g.drawImage(GameImages.enemyShip.im, e.r.x, e.r.y, e.r.width, e.r.height, null);
            }
        }
    }
    
    public Enemy access(int i){
        return enemies.get(i);
    }
    
    public void removeDestroyedEnemies(){
        for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext();){
            Enemy e = iterator.next();
            if (e.destroyed){
                iterator.remove();
            }
        }
    }
}