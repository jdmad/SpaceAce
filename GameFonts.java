package spaceace;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class GameFonts {
    //variables
    static Font gameFont = new Font("Rockwell", Font.PLAIN, 23);
    static Font endGameFont = new Font("OCR A Extended", Font.BOLD, 100);
    static Font pauseFont = new Font("Agency FB", Font.BOLD, 50);      //pause font
    static Font powerUpFont = new Font("Arial", Font.PLAIN, 25);
    static Font scoreFont = new Font("Copperplate Gothic Light", Font.PLAIN, 27);
    static Graphics g;
    
    static double getStringWidth(Graphics g, Font f, String s){
        FontMetrics currentFontMetrics = g.getFontMetrics(f);
        Rectangle2D stringBounds = currentFontMetrics.getStringBounds(s, g);
        return stringBounds.getWidth();
    }
    
    static double getStringHeight(Graphics g, Font f, String s){
        FontMetrics currentFontMetrics = g.getFontMetrics(f);
        Rectangle2D stringBounds = currentFontMetrics.getStringBounds(s, g);
        return stringBounds.getHeight();
    }

    static void drawCenteredText(Graphics g, Font f, Color c, String s){
        g.setFont(f);
        g.setColor(c);
        int screenWidth = ScreenDimensions.width;
        int screenHeight = ScreenDimensions.height;

        double textWidth = getStringWidth(g, f, s);
        double textHeight = getStringHeight(g, f, s);

        g.drawString(s, (int)(screenWidth - textWidth)/2, (int)((screenHeight + textHeight)/2));
    }
}
