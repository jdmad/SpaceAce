package spaceace;

//a generic game class, which other classes (asteroid, boss, enemy, player, missile) can inherit from

public class GameObject {
    //asteroid variables
    protected int topLeftX = 0;
    protected int topLeftY = 0;
    protected int timeCreated = 0;                        //time at which this GameObject appears
    protected int size = 0;                               //size of object on screen
    protected int currentHealth = 0;                      //this GameObject's current (remaining) health
    protected int totalHealth = 0;                        //this GameObject's total (maximum) health
    protected int velX = 0;                               //velocity in X
    protected int velY = 0;                               //velocity in Y
    protected boolean exists = false;                     //true at timeCreated
    protected boolean destroyed = false;                  //true when this GameObject is destroyed
    
    //asteroid methods
    public GameObject(int timeCreated, int size, int currentHealth, int totalHealth, int velX, int velY){
        this.timeCreated = timeCreated;
        this.size = size;
        this.currentHealth = currentHealth;
        this.totalHealth = totalHealth;
        this.velX = velX;
        this.velY = velY;
    } //constructor
    
    public boolean exists(){
        return exists;
    }
    
    public boolean destroyed(){
        return destroyed;
    }
    
    public void create(){
        exists = true;
    } //causes object to appear
    
    public void destroy(){
        exists = false;
        destroyed = true;
    } //causes object to disappear
}