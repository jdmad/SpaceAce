package spaceace;
////////////////////////////////////////////////////////////////////////////////////////////////////////////
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.sound.sampled.Clip;
//import packages for audio (music and sound fx)!!!
//can use windows sound recorder to create files (wma)
////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class SpaceAce extends JFrame implements Runnable {
    double spinTheta = Math.PI/4;
    //game variables
    static Boundary bounds = new Boundary(0, ScreenDimensions.width, (int)(0.1*ScreenDimensions.height), (int)(0.9*ScreenDimensions.height));
    int score = 0;                                          //player score based on objects destroyed
    Time clock = new Time();
    public static GameStates gs = GameStates.MENU;          //tracks game state (menu, options, etc.)
    
    //player variables
    int pShieldWidth = ScreenDimensions.height/5;                                 //width of shield arc
    int pShieldHeight = ScreenDimensions.height/5;                                //height of shield arc
    int pTotalHealth = 20;                                  //player's starting health
    int pTotalEnergy = 40000;                               //player's starting energy (for shields)
    int pStartAngle = -90;                                  //shield arc start angle in degrees
    int pAngleWidth = 180;                                  //shield arc angle width in degrees
    Player player1 = new Player(pShieldWidth, pShieldHeight,
            pTotalHealth, pTotalEnergy, pStartAngle, pAngleWidth);
    
    //powerup variables
    int powerUpWidth = 1080/36;
    int powerUpHeight = 1080/36;
    
    PowerUp healthPowerUp = new PowerUp(0, 5, Boundary.rightBoundary, 0, -8, 0, 30, 30, 0, 360);
    
    //asteroid, enemy, rock, star, missile, and boss variables
    AsteroidList ast = new AsteroidList();
    EnemyList ene = new EnemyList();
    RockList roc = new RockList();
    StarList sta = new StarList();
    MissileList mis = new MissileList();
    ExplosionList exp = new ExplosionList();
    Boss boss1 = new Boss(100, 1850, 242, 0, 10, 20);
    HealthBar hb1 = new HealthBar(20);
    long startTime = 0; //test cycle length
    long elapsed = 0; //test cycle length
    long maxElapsed = 0; //test cycle length
    long cycleOfMaxElapsed = 0; //index of the longest computing cycle (expected to be the first)
    static String path = System.getProperty("user.dir");
    
    //graphics variables
    private Image dbImage;
    private Graphics dbg;
////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    //methods
    @Override
    public void run(){
        try{
            GameImages.filterImages();
            Time.tempTime = System.nanoTime();
            while(Time.running){
                startTime = System.nanoTime(); //test cycle length
                if(!Time.paused){
                    clock.calculateGameTime();
                    move();
                }else{
                    Time.tempTime = System.nanoTime();
                }
                elapsed = System.nanoTime() - startTime; //test cycle length
                if(elapsed > maxElapsed){ //test cycle length
                    maxElapsed = elapsed; //test cycle length
                    cycleOfMaxElapsed = Time.currentCycle;
                }                         //test cycle length
                //Thread.sleep((20000000 - elapsed)/1000000, (int) ((20000000 - elapsed)%1000000));
                Thread.sleep(10);
                if(!Time.paused){
                    Time.currentCycle++;
                }
                repaint(); //moved this here, from in "PaintComponent" to match up with GameCycles
            }
        }catch(Exception e){
            System.out.println("Error");
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    public void move(){
        long t1 = 0;
        
        t1 = System.nanoTime();
        //create asteroids, enemies, rocks, stars, bosses
        ast.createAsteroids(Time.gameTime);
        ene.createEnemies(Time.gameTime);
        roc.createRocks(Time.gameTime);
        sta.createStars(Time.gameTime);
        createBosses();
        System.out.println("CreateObjects: " + (System.nanoTime() - t1));
        
        t1 = System.nanoTime();
        destroyAsteroids(player1.ps);
        destroyEnemies(player1.ps);
        destroyMissiles();
         System.out.println("DestroyObjects: " + (System.nanoTime() - t1));
        
        t1 = System.nanoTime();
        player1.updatePosition();
        System.out.println((System.nanoTime() - t1) );
        
        t1 = System.nanoTime();
        healthPowerUp.updatePosition();
        System.out.println((System.nanoTime() - t1) );
        
        t1 = System.nanoTime();
        ast.removeDestroyedAsteroids();
        for(Asteroid a: ast.asteroids){
            a.updatePosition();
        }
        System.out.println((System.nanoTime() - t1) );
        
        t1 = System.nanoTime();
        ene.removeDestroyedEnemies();
        for(Enemy e: ene.enemies){
            e.updatePosition();
        }
        System.out.println((System.nanoTime() - t1) );
        
        t1 = System.nanoTime();
        for(Rock r: roc.rocks){
            r.updatePosition();
        }
        System.out.println((System.nanoTime() - t1) );   
        
        t1 = System.nanoTime();
        for(Star s: sta.stars){
            s.updatePosition();
        }
        System.out.println((System.nanoTime() - t1) );    
        
        t1 = System.nanoTime();
        for(Missile m: mis.missiles){
            m.updatePosition();
        }
        System.out.println((System.nanoTime() - t1) );
        
        t1 = System.nanoTime();
        boss1.updatePosition();
        System.out.println((System.nanoTime() - t1) );
        
        t1 = System.nanoTime();
        calculateIntersections(player1, boss1, healthPowerUp);
        System.out.println((System.nanoTime() - t1) );
        System.out.println("That's It For UpdatePosition.");
    } //update player, powerup, missile, asteroid, enemy, and boss positions on screen
////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    public class AL extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            player1.setPressVelocities(e);
            
            int keyCode = e.getKeyCode();
            if(keyCode == KeyEvent.VK_SPACE){
                player1.fire(mis);
            }
            if(keyCode == KeyEvent.VK_J){
                player1.activateShield();
            }
            if(keyCode == KeyEvent.VK_I){
                player1.toggleSpeedBoost();
            }
            if(keyCode == KeyEvent.VK_P){
                Time.paused = !Time.paused;
            }
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////        
        @Override
        public void keyReleased(KeyEvent e){
            player1.setReleaseVelocities(e);
            
            int keyCode = e.getKeyCode();
            if(keyCode == KeyEvent.VK_SPACE){
                //
            }
            if(keyCode == KeyEvent.VK_J){
                player1.deactivateShield();
            }
        }
    } //listen for key press/release events - move up, down, shield
////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    public SpaceAce() {
        //Game Properties
        AL AL1 = new AL();
        addKeyListener(AL1);
        setTitle("Space Ace");
        setSize(ScreenDimensions.width, ScreenDimensions.height);
        setResizable(true);
        setVisible(true);
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } // SpaceAce constructor: change game background color, size, etc.
////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    @Override
    public void paint(Graphics g){
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponentLevel1((Graphics2D) dbg);
        //dbImage = dbImage.getScaledInstance(this.getWidth(), this.getHeight(), 1);
        //g.drawImage(dbImage, 0, 0, this);
        //g.drawImage(dbImage, 15, 15, ScreenDimensions.width, ScreenDimensions.height, this);
        g.drawImage(dbImage, 10, 10, getWidth(), getHeight() - 20, this);

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void paintComponentLevel2(Graphics g){
        g.setFont(GameFonts.gameFont);
        g.setColor(Color.RED);
        g.drawString("This is Level 2!!!", 100, 100);
        repaint();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    public void paintComponentLevel1(Graphics2D g){
        g.setFont(GameFonts.gameFont);
        g.drawImage(GameImages.spaceBackground11.im, 10, 108, 1910, 864, null);
        
        Graphics g2;
        Image myImage;
        myImage = createImage(300, 300);
        g2 = myImage.getGraphics();
        //paintComponentLevel2(g2);
        //g.drawImage(myImage, 100, 200, this);

        //health and energy powerups
        Rectangle hpu = new Rectangle(healthPowerUp.getx(), healthPowerUp.gety(), healthPowerUp.getWidth(), healthPowerUp.getHeight());
                
        //bosses and their health bars
        Rectangle b1 = new Rectangle(boss1.getx(), boss1.gety(), boss1.getSize(), boss1.getSize());
        Rectangle h1Green = new Rectangle((990 - 5*boss1.getTotalHealth()), 67, 5*hb1.getGreenHealth(), 20);
        Rectangle h1Red = new Rectangle(((990 - 5*boss1.getTotalHealth())) + 5*hb1.getGreenHealth(), 67, 5*hb1.getRedHealth() , 20);
               
        //lines
        Rectangle line1 = new Rectangle(Boundary.leftBoundary, Boundary.topBoundary - 1, Boundary.rightBoundary, 1);
        Rectangle line2 = new Rectangle(0, Boundary.bottomBoundary, Boundary.rightBoundary, 1);
        Rectangle[] lines = {line1, line2};

        long temp = System.nanoTime();
        //draw asteroids, enemies, rocks, bosses, stars, missiles, player's ship, health/energy bars, game info, powerups
        sta.drawStars(g);
        System.out.println("DrawStars: " + (System.nanoTime() - temp));
        temp = System.nanoTime();
        ast.drawAsteroids(g);
        System.out.println("DrawAsteroids: " + (System.nanoTime() - temp));
        temp = System.nanoTime();
        ene.drawEnemies(g);
        System.out.println("DrawEnemies: " + (System.nanoTime() - temp));
        temp = System.nanoTime();
        roc.drawRocks(g);
        System.out.println("DrawRocks: " + (System.nanoTime() - temp));
        temp = System.nanoTime();
        mis.drawMissiles(g);
        System.out.println("DrawMissiles: " + (System.nanoTime() - temp));
        temp = System.nanoTime();
        exp.drawExplosions(g);
        System.out.println("DrawExplosions: " + (System.nanoTime() - temp));
        temp = System.nanoTime();
        drawBosses(g, b1, h1Green, h1Red);
        System.out.println("DrawBosses: " + (System.nanoTime() - temp));

        temp = System.nanoTime();
        /*
        player ship 3D stuff below
        Point V2 = new Point(ScreenDimensions.width/2, 0);
        int[] playerShipEdgeArrayFirst = {2, 3, 5, 7, 8, 10};
        int[] playerShipEdgeArraySecond = {3, 4, 6, 8, 9, 0};
        int[][] pSEA = {playerShipEdgeArrayFirst, playerShipEdgeArraySecond};
        
        g.setColor(Color.RED);
        for(int i = 0; i < pSEA[0].length; i++){
            Draw3D.draw3dEdges(g, new Line(player1.s.xpoints[pSEA[0][i]], player1.s.ypoints[pSEA[0][i]], player1.s.xpoints[pSEA[1][i]], player1.s.ypoints[pSEA[1][i]]), V2, 50);
        }
        player ship 3D stuff above
                */

        player1.drawPlayerShip(g);
        player1.drawPlayerShield(g);
        player1.drawPlayerHealthBar(g);
        player1.drawPlayerEnergyBar(g);
        drawGameInfo(g, lines);
        drawPauseText(g);
        drawPowerUps(g, hpu);
        System.out.println("PlayerInfo: " + (System.nanoTime() - temp));
        
        player1.checkPlayerHealth(g, exp); //determine if player's ship is destroyed
                
        if((Time.gameTime >= 40000) && (Time.gameTime <= 45000)){
            GameFonts.drawCenteredText(g, GameFonts.pauseFont, Color.ORANGE, "Warning: Approaching enemy station!!!");
        }
        
        /*
        g.drawString("Max Time Elapsed (in nanoseconds): " + maxElapsed, 500, 500);
        g.drawString("Cycle during which Max Time Elapsed: " + cycleOfMaxElapsed, 500, 550);
        g.drawString("Current cycle: " + Time.currentCycle, 500, 600);
        System.out.print("Current cycle = " + Time.currentCycle);
        */
        
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void destroyAsteroids(Rectangle ps){
        for(Asteroid a: ast.asteroids){
            if(a.exists){
                if((player1.shieldEngaged()) && (player1.getCurrentEnergy() > 0) && (a.r.intersects(ps))){
                    a.destroy();
                    //ast.asteroids.remove(a);
                    score += 100;
                    exp.explosions.add(new Explosion(Time.currentCycle,
                            a.r.x, a.r.y, a.r.width, a.r.height, 20));
                }else{ // destroy with shield
                    for(Missile m: mis.missiles){
                        if((m.exists()) && (a.r.intersects(m.m))){
                            a.destroy();
                            //ast.asteroids.remove(a);
                            m.destroy();
                            score += 100;
                            exp.explosions.add(new Explosion(Time.currentCycle,
                                a.r.x, a.r.y, a.r.width, a.r.height, 20));
                        }
                    } //destroy with missile
                }
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void destroyEnemies(Rectangle ps){
        for(Enemy e: ene.enemies){
            if(e.exists){
                if(player1.shieldEngaged() && (player1.getCurrentEnergy() > 0) && (e.r.intersects(ps))){
                    e.destroy();
                    score += 100;
                    exp.explosions.add(new Explosion(Time.currentCycle,
                            e.r.x, e.r.y, e.r.width, e.r.height, 20));
                }else{ //destroy with shield
                    for(Missile m: mis.missiles){
                        if(m.exists() && (e.r.intersects(m.m))){
                            e.destroy();
                            m.destroy();
                            score += 200;
                            exp.explosions.add(new Explosion(Time.currentCycle,
                                e.r.x, e.r.y, e.r.width, e.r.height, 20));
                        }
                    } //destroy with missile
                }
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    public void destroyMissiles(){
        for(Rock r: roc.rocks){
            if(r.exists()){
                for(Missile m: mis.missiles){
                    if(m.exists()){
                        if(r.p.intersects(m.m)){
                            m.destroy(); //destroy a missile if it hits a rock
                        }
                    }
                }
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    public void damageBosses(Rectangle b1, Rectangle ps){
        for(Missile m: mis.missiles){
            if((boss1.exists()) && (m.exists()) && (b1.intersects(m.m))){
                boss1.damage();
                m.destroy();
            } //damage or destroy with missile
        }
        
        if((boss1.exists() && (player1.shieldEngaged()) && (player1.getCurrentEnergy() != 0) && (b1.intersects(ps)))){
            boss1.damage();
        } //damage or destroy with shield
        
        hb1.updateHealthBar(boss1.getCurrentHealth()); //update boss health display
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void createBosses(){
        if((Time.gameTime >= 60000) && (!boss1.destroyed())){
            boss1.create();
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void drawGameInfo(Graphics g, Rectangle[] lines){
        g.setColor(Color.CYAN);
        for(Rectangle r: lines){
            g.fillRect(r.x, r.y, r.width, r.height);
        }
        
        g.setFont(GameFonts.scoreFont);
        int gameTextHeight = (int)(0.03*ScreenDimensions.height);
        g.drawString("Score: " + score, 15, Boundary.bottomBoundary + gameTextHeight);
        g.drawString("Time: " + Time.gameTime/1000, 15, Boundary.bottomBoundary + 2*gameTextHeight);
        g.drawString("W to move UP | S to move DOWN | A to move LEFT | D to move RIGHT", (int)(0.3*ScreenDimensions.width), Boundary.bottomBoundary + gameTextHeight);
        g.drawString("SPACEBAR to FIRE | J to SHIELD | I to SPEED BOOST | P to PAUSE", (int)(0.3*ScreenDimensions.width), Boundary.bottomBoundary + 2*gameTextHeight);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void drawPauseText(Graphics g){
        if(Time.paused){
            GameFonts.drawCenteredText(g, GameFonts.pauseFont, Color.WHITE, "PAUSED (Press P to resume game)");
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void drawPowerUps(Graphics g, Rectangle hpu){
        g.setColor(Color.GREEN);
        if(healthPowerUp.exists()){
            g.drawRect(hpu.x, hpu.y, hpu.width, hpu.height);
            g.drawArc(healthPowerUp.getx(), healthPowerUp.gety(), powerUpWidth, powerUpHeight, 0, 360);
            g.setFont(GameFonts.powerUpFont);
            g.drawString("H", hpu.x + 6, (hpu.y + hpu.height - 6));
        }
    }
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void drawBosses(Graphics g, Rectangle b1, Rectangle h1Green, Rectangle h1Red){
        if(!boss1.destroyed() && boss1.exists()){
            g.setColor(GameColors.bossGreen);
            g.drawString("BOSS HEALTH:", 870, 52);
            g.fillRect(h1Green.x, h1Green.y, h1Green.width, h1Green.height);
            g.setColor(Color.RED);
            g.fillRect(h1Red.x, h1Red.y, h1Red.width, h1Red.height);
            if(boss1.checkHP()){
                score += 2000;
                healthPowerUp.create(boss1.gety() + ((boss1.getSize())/2));
            } // if boss is destroyed, increase player score and create a bonus powerup
        } // draw boss items
        
        if(!boss1.destroyed() && boss1.exists()){
            g.setColor(GameColors.bossGreen);
            g.drawRect(b1.x, b1.y, b1.width, b1.height);
            g.fillRect(b1.x, b1.y, b1.width, b1.height);
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void calculateIntersections(Player player1, Boss b1, PowerUp hpu){
        for(Asteroid a: ast.asteroids){
            if((a.exists()) && (player1.exists) && (player1.r.intersects(a.r))){
                player1.drainHealth(2);
                a.destroy();
                exp.explosions.add(new Explosion(Time.currentCycle,
                        a.r.x, a.r.y, a.r.width, a.r.height, 20));
            }
        }//asteroids may damage ship
        
        for(Enemy e: ene.enemies){
            if((e.exists()) && (player1.exists) && (player1.r.intersects(e.r))){  
                player1.drainHealth(2);
                e.destroy();
                exp.explosions.add(new Explosion(Time.currentCycle,
                        e.r.x, e.r.y, e.r.width, e.r.height, 20));
            }
        } //enemies may damage ship
        
        for(Rock r: roc.rocks){
            if((r.exists()) && (player1.exists) && (r.p.intersects(player1.r))){
                player1.drainHealth(2);
            }
        } //rocks may damage ship
        
        if((boss1.exists()) && (player1.exists) && (testIntersection(player1.r, new Rectangle(b1.getx(), b1.gety(), b1.getx() + b1.getSize(), b1.gety() + b1.getSize())))){
            player1.drainHealth(2);
        } //boss may damage ship
        
        if((healthPowerUp.exists()) && (player1.exists) && (testIntersection(player1.r, new Rectangle(hpu.getx(), hpu.gety(), hpu.getx() + hpu.getWidth(), hpu.gety() + hpu.getHeight())))){
            player1.restoreHealth(healthPowerUp.getAmount());
            healthPowerUp.destroy();
        } //health powerup may restore health
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean testIntersection(Shape shapeA, Shape shapeB) {
        if(shapeA.getBounds().intersects(shapeB.getBounds())){
            Area areaA = new Area(shapeA);
            areaA.intersect(new Area(shapeB));
            return !areaA.isEmpty();
        }else{
            return false;
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SpaceAce.class.getName()).log(Level.SEVERE, null, ex);
        }//sleep for 10 seconds so that user can see the nifty splash screen!
        switch(gs){
            case MENU:
                Menu menu = new Menu(gs);
                menu.setVisible(true);
                break;
            case TUTORIAL:
                //run game tutorial, with on-screen instructions on how to play; sandbox
                break;
            case START:
                //start game, by declaring SpaceAce and thread, and starting thread w/ SpaceAce arg
                break;
            case LOAD:
                //load saved game: similar to START, but must load in times, score, etc.
                break;
            case OPTIONS:
                //display options screen: decide on sounds, music, difficulty, etc.
                break;
            case CREDITS:
                //show credits for programming, art, music, sounds design, etc.
                break;
            case EXIT:
        }
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////