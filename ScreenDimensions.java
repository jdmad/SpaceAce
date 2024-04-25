package spaceace;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ScreenDimensions {
    static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static final int width = (int) screenSize.getWidth();
    static final int height = (int) screenSize.getHeight();
}
