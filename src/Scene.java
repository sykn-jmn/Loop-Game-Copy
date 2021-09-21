package src;

import java.awt.*;

public interface Scene {

    void drawScene(Graphics g);

    void loadAssets();

    void processClick(int x, int y);

    void endScene();
}
