package src;

import java.awt.*;

public interface Scene {
    int OFFSET = Display.DrawPane.OFFSET;
    int WIDTH = Display.DrawPane.WIDTH;

    void DrawScene(Graphics g);
}
