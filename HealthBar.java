package spaceace;

//this class facilitates on screen display of health bars
public class HealthBar {
    //HealthBar variables
    private int totalHealth = 0;    //total health (sum of red and green health)
    private int greenHealth = 0;    //health remaining (less than total)
    private int redHealth = 0;      //health lost (less than total)
    
    //HealthBar methods
    public HealthBar(int totalHP){
        greenHealth = totalHP;
        totalHealth = totalHP;
    } //constructor
    
    public void updateHealthBar(int currentHP){
        greenHealth = currentHP;
        redHealth = totalHealth - greenHealth;
    }
    
    public int getGreenHealth(){
        return greenHealth;
    }
    
    public int getRedHealth(){
        return redHealth;
    }
}