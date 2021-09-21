package src;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {
    DrawPane panel;
    Listener listener;
    public Display(){
        super("Loop Game Copy");

        this.setSize(630,710);
        this.setLocation(330,10);

        panel = new DrawPane();
        this.setContentPane(panel);

        listener = new Listener();
        addMouseListener(listener);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void changeScene(Scene scene){
        panel.changeScene(scene);
        listener.changeScene(scene);
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

