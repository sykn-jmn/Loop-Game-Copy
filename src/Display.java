package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Display extends JFrame {
    DrawPane panel;

    public Display(){
        super("Loop Game Copy");

        this.setSize(630,650);
        this.setLocation(330,30);

        panel = new DrawPane();
        this.setContentPane(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void changeScene(Scene scene){
        panel.changeScene(scene);
    }

    public int getXIndex(int x){
        return panel.getXIndex(x);
    }
    public int getYIndex(int y){
        return panel.getYIndex(y);
    }


    static class DrawPane extends JPanel{
        public static final int OFFSET = 20;
        public static final int WIDTH = 64;
        Scene scene;


        public void changeScene(Scene scene){
            this.scene = scene;
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
            if(scene!=null) scene.DrawScene(g);
        }


    }
}

