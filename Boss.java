package spaceace;

//this class is used for enemy bosses, which move up and down in a random pattern
public class Boss {
    //boss variables
    private int size = 0;               //size of boss
    private int x = 0;                  //position in X
    private int y = 0;                  //position in Y
    private int velX = 0;               //velocity in X
    private int velY = 0;               //velocity in Y
    private int currentHP = 0;          //boss's current health
    private int totalHP = 0;            //boss's total  health
    private boolean exists = false;     //true when certain conditions are met
    private boolean destroyed = false;  //true when destroyed by player's missiles
    private int upper = Boundary.topBoundary;
    private int lower = Boundary.bottomBoundary;
    boolean movingUp = true;
    private int length = 0;
    //boss methods
    public Boss(int size, int x, int y, int xDir, int yDir, int totalHP){
        this.size = size;
        this.x = x;
        this.y = y;
        velX = xDir;
        velY = yDir;
        this.totalHP = totalHP;
        currentHP = totalHP;
    } //constructor
    
    public void updatePosition() {
        if(exists){
            if(y <= upper){
                length = (Boundary.bottomBoundary - y)/2;
                lower = y + length + (int) (Math.random()*length);
                velY = +10;
            }else if(y >= lower){
                length = (y - Boundary.topBoundary)/2;
                upper = y - length - (int) (Math.random()*length);
                velY = -10;
            }
            y += velY;
        }
    }
    
    public void create(){
        exists = true;
    } //causes boss to appear
    
    public void destroy(){
        exists = false;
        destroyed = true;
    } //causes boss to disappear
    
    public boolean exists(){
        return exists;
    }
    
    public boolean destroyed(){
        return destroyed;
    }
    
    public int getx(){
        return x;
    }
    
    public int gety(){
        return y;
    }
    
    public int getSize(){
        return size;
    }
    
    public int getTotalHealth(){
        return totalHP;
    }
    
    public int getCurrentHealth(){
        return currentHP;
    }
    
    public void damage(){
        currentHP--;
    } //does damage to boss; update based on weapon used by player???
    
    public boolean checkHP(){
        if(currentHP <= 0){
            destroy();
            return true;
        }else{
            return false;
        }
    } //destroys boss if health drops to zero
}
