package spaceace;

public class Explosion {
    //variables
    int cycleDestroyed = 0;     //the cycle when the object was destroyed
    int xDestroyed = 0;         //object's x position when destroyed
    int yDestroyed = 0;         //object's y position when destroyed
    int width = 0;              //width of destroyed object (and thus, explosion)
    int height = 0;             //height of destroyed object (and thus, explosion)
    int cyclesToAnimate = 0;    //number of gameCycles to continue drawing each explosion image
    
    //methods
    public Explosion(int cycleDestroyed, int xDestroyed, int yDestroyed,
            int width, int height, int cyclesToAnimate){
        this.cycleDestroyed = cycleDestroyed;
        this.xDestroyed = xDestroyed;
        this.yDestroyed = yDestroyed;
        this.width = width;
        this.height = height;
        this.cyclesToAnimate = cyclesToAnimate;
    }
}