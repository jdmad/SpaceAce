package spaceace;

public class Time {
    //variables
    static long tempTime = 0;                                      //a variable to help keep track of time
    static long timeElapsed = 0;                                   //system time, to help keep gameTime
    static long gameTime = 0;                                      //game time elapsed, to script enemies, etc.
    static boolean running = true;                                 //false when game ends
    static boolean paused = false;                                 //true when game is paused; press P to toggle
    static int currentCycle = 0;
    
    //methods
    public Time(){
        tempTime = 0;                                      //a variable to help keep track of time
        timeElapsed = 0;                                   //system time, to help keep gameTime
        gameTime = 0;                                      //game time elapsed, to script enemies, etc.
        running = true;                                 //false when game ends
        paused = false;                                 //true when game is paused; press P to toggle
        currentCycle = 0;
    }
    
    public void calculateGameTime(){
        timeElapsed += System.nanoTime() - tempTime;
        tempTime = System.nanoTime();
        gameTime = timeElapsed/1000000;
    }
}