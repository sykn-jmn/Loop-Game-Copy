package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Display extends JFrame {
    DrawPane panel;

    public Display(Loop[][] loops){
        super("Loop Game Copy");

        this.setSize(630,650);
        this.setLocation(330,30);

        setContentPane(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setPanel(Scene scene){
        panel = new DrawPane(scene);
    }

    public int getXIndex(int x){
        return panel.getXIndex(x);
    }
    public int getYIndex(int y){
        return panel.getYIndex(y);
    }







    static class DrawPane extends JPanel{
        private BufferedImage[][] assets = new BufferedImage[5][4];
        public static final int OFFSET = 20;
        public static final int WIDTH = 64;
        Scene scene;

        public DrawPane(Scene scene){
            this.scene = scene;
        }

        public void changeScene(Scene scene){
            this.scene = scene;
        }

        public void setAssets(BufferedImage[][] assets){
            this.assets = assets;
        }

        public int getXIndex(int x){
            x = x-OFFSET-10;
            return x/WIDTH;
        }
        public int getYIndex(int y){
            y = y-OFFSET-OFFSET-10;
            return y/WIDTH;
        }

        public void paintComponent(Graphics g) {
            scene.DrawScene(g);
        }


    }
}

