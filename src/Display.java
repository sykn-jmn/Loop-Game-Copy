package src;

import javax.swing.*;
import java.awt.*;

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


    static class DrawPane extends JPanel{
        public static final int OFFSET = 20;
        Scene scene;


        public void changeScene(Scene scene){
            this.scene = scene;
        }


        public void paintComponent(Graphics g) {
            if(scene!=null) scene.drawScene(g);
        }


    }
}

