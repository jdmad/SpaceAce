package spaceace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import javafx.scene.shape.Line;

public class Draw3D {
    //variables
    
    //methods
    public static void draw3dEdges(Graphics2D g, Line L, Point V, int projectionFactor){
        Point A = new Point((int) L.getStartX(), (int) L.getStartY());
        Point B = new Point((int) L.getEndX(), (int) L.getEndY());
        
        Point A2 = projectPoint(A, V, projectionFactor);
        Point B2 = projectPoint(B, V, projectionFactor);

        int[] xArray = {A.x, A2.x, B2.x, B.x};
        int[] yArray = {A.y, A2.y, B2.y, B.y};
        Polygon p = new Polygon(xArray, yArray, 4);
        g.setColor(GameColors.ship3dEdgeBlue);
        g.fillPolygon(p);
        g.setColor(Color.WHITE);
        g.drawPolygon(p);
    }
    
    public static Line createLineFromPoints(Point A, Point B){
        return new Line(A.x, A.y, B.x, B.y);
    }
   
    public static Point projectPoint(Point P, Point V, int projectionFactor){
        //V = vanishing point
        int dx = P.x - V.x;
        int dy = P.y - V.y;
        int shiftY = (int) -1*dy/projectionFactor;
        int shiftX = (int) (shiftY*dx/dy);
        return new Point(P.x + shiftX, P.y + shiftY);
    }

    public static void drawLine(Graphics g, Line L){
        g.drawLine((int) L.getStartX(), (int) L.getStartY(), (int) L.getEndX(), (int) L.getEndY());
    }   
}