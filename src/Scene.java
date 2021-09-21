package src;

import java.awt.*;

public interface Scene {
    int OFFSET = Display.DrawPane.OFFSET;

    void drawScene(Graphics g);

    void loadAssets();
}
