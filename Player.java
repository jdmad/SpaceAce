package spaceace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

//this class is used for the player and hero's ship, which moves up and down, fires missiles, and uses shield
public class Player{
    // player variables
    Rectangle r;
    public boolean exists = true;
    private final int healthYposition = (int)(0.55*Boundary.topBoundary);
    private final int energyYposition = (int)(0.85*Boundary.topBoundary);
    private final int baseSpeed = 5; //originally 5                    
    private int currentSpeed = baseSpeed;               
    private final int size = 5;                         //size of ship
    private int velX = 0;                               //velocity in X
    private int velY = 0;                               //velocity in Y
    private int totalHealth = 0;                        //player's total (maximum) health
    private int currentHealth = 0;                      //player's current (remaining) health
    private int totalEnergy = 0;                        //player's total (maximum) energy
    private int currentEnergy = 0;                      //player's current (remaining) energy
    private int startAngle = 0;                         //start angle, for shield
    private int angleWidth = 0;                         //width of angle, for shield
    private int shieldWidth = 0;                        //width of shield
    private int shieldHeight = 0;                       //height of shield
    private boolean shieldEngaged = false;              //true while player holds down J key
    private boolean speedBoostEngaged = false;          //true while player holds down I key
    private boolean movingUp = false;                   //true while player holds down W key
    private boolean movingDown = false;                 //true while player holds down S key
    private boolean movingLeft = false;                 //true while player holds down A key
    private boolean movingRight = false;                //true while player holds down D key
    Rectangle playerHealthGreen = new Rectangle(0, 0, 0, 0);
    Rectangle playerHealthRed = new Rectangle(0, 0, 0, 0);
    Rectangle playerEnergyOrange = new Rectangle(0, 0, 0, 0);
    Rectangle playerEnergyWhite = new Rectangle(0, 0, 0, 0);
    Rectangle ps;
    
    
    //player methods
    public Player(int shieldw, int shieldh, int playerTotalH, int playerTotalE, int starta, int anglew){
        this.
        totalHealth = playerTotalH;
        currentHealth = totalHealth;
        totalEnergy = playerTotalE;
        currentEnergy = totalEnergy;
        startAngle = starta;
        angleWidth = anglew;
        shieldWidth = shieldw;
        shieldHeight = shieldh;
        
        this.r = new Rectangle(20, 490, 14*size, 20*size);
        ps = new Rectangle(getUpperLeftX(), getUpperLeftY(), 200, 200);
        
        playerHealthGreen.setBounds(100,
                (int)(0.70*Boundary.topBoundary),
                20*currentHealth,
                20);
        playerHealthRed.setBounds(100 + 20*currentHealth,
                (int)(0.70*Boundary.topBoundary),
                20*(totalHealth - currentHealth),
                20);
        playerEnergyOrange.setBounds(100,
                (int)(0.85*Boundary.topBoundary),
                (int) currentEnergy/100,
                20);
        playerEnergyWhite.setBounds(100 + (int) currentEnergy/100,
                (int)(0.85*Boundary.topBoundary),
                (int) ((totalEnergy - currentEnergy)/100),
                20);
    } //constructor
    
    public void updatePosition(){
        if(exists){
            currentSpeed = baseSpeed;
            if(speedBoostEngaged && (currentEnergy > 0)){
                currentSpeed = 2*baseSpeed;
            }

            if(movingUp){
                setVelocityY(-1*currentSpeed);
            }else if(movingDown){
                setVelocityY(+1*currentSpeed);
            }else{
                setVelocityY(0);
            }

            if(movingLeft){
                setVelocityX(-1*currentSpeed);
            }else if(movingRight){
                setVelocityX(+1*currentSpeed);
            }else{
                setVelocityX(0);
            }

            if(velY > 0 && r.y >= Boundary.bottomBoundary - r.height){
                velY = 0;
            } //if ship is too low on screen, stop it from going lower
            if(velY < 0 && r.y <= Boundary.topBoundary+1){
                velY = 0;
            } //if ship is too high on screen, stop it from going higher
            if(velX > 0 && r.x >= Boundary.rightBoundary - r.width){
                velX = 0;
            } //if ship is too far right on screen, stop it from going further right
            if(velX < 0 && r.x <= 10){
                velX = 0;
            } //if ship is too far left on screen, stop it from going further left

            r.translate(velX, velY);
        }
    } //move ship, and 3d edges, based on player velocity - done by moving top left vertex, and others by offsets
    
    public int getVelocityX(){
        return velX;
    }
    
    public int getVelocityY(){
        return velY;
    }
    
    public int getUpperLeftX(){
        return r.x;
    }
    
    public int getUpperLeftY(){
        return r.y;
    }
    
    public void setVelocityX(int xDir){
        velX = xDir;
    }
    
    public void setVelocityY(int yDir){
        velY = yDir;
    }
    
    public void setPressVelocities(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_W){
            movingUp = true;
        }
        if(keyCode == KeyEvent.VK_S){
            movingDown = true;
        }
        if(keyCode == KeyEvent.VK_A){
            movingLeft = true;
        }
        if(keyCode == KeyEvent.VK_D){
            movingRight = true;
        }
    }
    
    public void setReleaseVelocities(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_W){
            movingUp = false;
        }
        if(keyCode == KeyEvent.VK_S){
            movingDown = false;
        }
        if(keyCode == KeyEvent.VK_A){
            movingLeft = false;
        }
        if(keyCode == KeyEvent.VK_D){
            movingRight = false;
        }
    }
    
    public void drainHealth(int health){
        currentHealth -= health;
        if(currentHealth <= 0){
            currentHealth = 0;
        }
    } //drain player health, if struck by an object
    
    public void restoreHealth(int health){
        currentHealth += health;
        if(currentHealth > totalHealth){
            currentHealth = totalHealth;
        }
    } //restore player health, if a powerup is grabbed
    
    public void maxHealthUp(int health){
        totalHealth += health;
    } //increase player's total health, if an upgrade is grabbed
    
    public int getCurrentHealth(){
        return currentHealth;
    }
    
    public int getTotalHealth(){
        return totalHealth;
    }
    
    public void drainEnergy(int energy){
        currentEnergy -= energy;
        if(currentEnergy<0){
            currentEnergy = 0;
        }
    } //drain player energy, if shield or special weapon is used
    
    public void restoreEnergy(int energy){
        currentEnergy += energy;
        if(currentEnergy > totalEnergy){
            currentEnergy = totalEnergy;
        }
    } //restore player energy, if a powerup is grabbed
    
    public void maxEnergyUp(int energy){
        totalEnergy += energy;
    } //increase player's total energy, if an upgrade is grabbed
    
    public int getCurrentEnergy(){
        return currentEnergy;
    }
    
    public int getTotalEnergy(){
        return totalEnergy;
    }
    
     public int getStartAngle(){
        return startAngle;
    }
    
    public int getAngleWidth(){
        return angleWidth;
    }
    
    public int getShieldWidth(){
        return shieldWidth;
    }
    
    public int getShieldHeight(){
        return shieldHeight;
    }
    
    public void activateShield(){
        if(exists){
            shieldEngaged = true;
        }
    } //activate shield, which damages enemies and protects player from taking damage
    
    public void deactivateShield(){
        shieldEngaged = false;
    } //deactivate shield
    
    public boolean shieldEngaged(){
        return shieldEngaged;
    }
    
    public void toggleSpeedBoost(){
        speedBoostEngaged = !speedBoostEngaged;
    }
    
    public boolean speedBoostEngaged(){
        return speedBoostEngaged;
    }
    
    public void drawPlayerShip(Graphics g){
        if(exists){
            if(speedBoostEngaged()){
                drainEnergy(50);
            }
            g.drawImage(GameImages.playerShip.im, r.x, r.y, r.width, r.height, null);
            g.drawImage(GameImages.thruster[Time.currentCycle%3].im, r.x - 60, r.y + 10, 60, 20, null);
            g.drawImage(GameImages.thruster[Time.currentCycle%3].im, r.x - 60, r.y + 70, 60, 20, null);
        }
    }
    
    public void drawPlayerShield(Graphics g){
        g.setColor(Color.ORANGE);
        if((shieldEngaged()) && (getCurrentEnergy() > 0)){
            g.drawArc(r.x - 100, r.y - 50, 
                    getShieldWidth(), getShieldHeight(), getStartAngle(), getAngleWidth());
            drainEnergy(50);
        }
    }

    public void drawPlayerHealthBar(Graphics g){
        updateHealthBar(g);
        
        g.setFont(GameFonts.gameFont);
        g.setColor(Color.GREEN);
        g.drawString("HEALTH:", 10, healthYposition);
        g.fillRoundRect(playerHealthGreen.x,
                playerHealthGreen.y,
                playerHealthGreen.width,
                playerHealthGreen.height,
                20,
                20);
        
        g.setColor(Color.RED);
        g.fillRoundRect(playerHealthRed.x,
                playerHealthRed.y,
                playerHealthRed.width,
                playerHealthRed.height,
                20,
                20);
    }

    public void drawPlayerEnergyBar(Graphics g){
        updateEnergyBar(g);
        
        g.setFont(GameFonts.gameFont);
        g.setColor(Color.ORANGE);
        g.drawString("ENERGY:", 10, energyYposition);
        g.fillRoundRect(playerEnergyOrange.x,
                playerEnergyOrange.y, 
                playerEnergyOrange.width,
                playerEnergyOrange.height,
                20,
                20);
        
        g.setColor(Color.WHITE);
        g.fillRoundRect(playerEnergyWhite.x,
                playerEnergyWhite.y,
                playerEnergyWhite.width,
                playerEnergyWhite.height,
                20,
                20);
    }
    
    public void checkPlayerHealth(Graphics g, ExplosionList exp){
        if(getCurrentHealth() <= 0){
            if(exists){
                exp.explosions.add(new Explosion(Time.currentCycle,
                    r.x, r.y, r.width, r.height, 20));
            }
            g.setColor(Color.RED);
            g.setFont(GameFonts.endGameFont);
            GameFonts.drawCenteredText(g, GameFonts.endGameFont, Color.RED, "GAME OVER");
            exists = false;
        }
    }
    
    public void updateHealthBar(Graphics g){
        int healthTextWidth = (int)GameFonts.getStringWidth(g, GameFonts.gameFont, "HEALTH:");
        int healthTextHeight = (int)GameFonts.getStringHeight(g, GameFonts.gameFont, "HEALTH:");
        
        playerHealthGreen.setBounds(10 + healthTextWidth + 10,
                healthYposition - 19,
                20*currentHealth,
                20);
        
        playerHealthRed.setBounds(playerHealthGreen.x + playerHealthGreen.width,
                playerHealthGreen.y,
                20*(totalHealth - currentHealth),
                20);
    }
    
    public void updateEnergyBar(Graphics g){
        int energyTextWidth = (int)GameFonts.getStringWidth(g, GameFonts.gameFont, "ENERGY:");
        int energyTextHeight = (int)GameFonts.getStringHeight(g, GameFonts.gameFont, "ENERGY:");
        
        playerEnergyOrange.setBounds(10 + energyTextWidth + 5,
                energyYposition - 19,
                currentEnergy/100,
                20);
        
        playerEnergyWhite.setBounds(playerEnergyOrange.x + playerEnergyOrange.width,
                playerEnergyOrange.y,
                (totalEnergy- currentEnergy)/100,
                20);
    }
    
    public void fire(MissileList mis){
        if(exists){
            int b = 0;
            boolean fired = false;
            while((!fired) && (b < mis.missileStock)){
                if(!mis.access(b).exists()){
                    mis.access(b).fire(this.getUpperLeftX(), this.getUpperLeftY(), this.getVelocityY());
                    fired = true;
                }
                b++;
            }
        }
    } //fire a missile after player's command (spacebar)
}