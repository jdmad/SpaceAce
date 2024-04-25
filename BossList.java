package spaceace;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class BossList {
    //variables
    ArrayList<Boss> bosses = new ArrayList<Boss>();
    int bossStock = 1;
    
    //methods
    public BossList(){
        for(int i = 0; i < bossStock; i++){
            bosses.add(new Boss(100, 1850, 242, 0, 10, 20));
        }
    }
}