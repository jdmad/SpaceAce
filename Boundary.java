package spaceace;

//this class is used for the game's boundaries: left, right, top, and bottom
public class Boundary {
    //variables
    protected static int leftBoundary = 0;
    protected static int rightBoundary = 0;
    protected static int topBoundary = 0;
    protected static int bottomBoundary = 0;
    
    //methods
    Boundary(int leftBoundary, int rightBoundary, int topBoundary, int bottomBoundary){
        Boundary.leftBoundary = leftBoundary;
        Boundary.rightBoundary = rightBoundary;
        Boundary.topBoundary = topBoundary;
        Boundary.bottomBoundary = bottomBoundary;
    }
    
    public void setLeftBoundary(int leftBoundary){
        Boundary.leftBoundary = leftBoundary;
    }
    
    public void setRightBoundary(int rightBoundary){
        Boundary.rightBoundary = rightBoundary;
    }
    
    public void setTopBoundary(int topBoundary){
        Boundary.topBoundary = topBoundary;
    }
    
    public void setBottomBoundary(int bottomBoundary){
        Boundary.bottomBoundary = bottomBoundary;
    }
}