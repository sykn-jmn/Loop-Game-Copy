package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Display extends JFrame {
    DrawPane panel;

    public Display(){
        super("Loop Game Copy");

        this.setSize(1010,800);
        this.setLocation(170,0);

        panel = new DrawPane();
        setContentPane(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void loadAssets(){
        try {
            Image image = ImageIO.read(new File("src/assets/pipes.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static class DrawPane extends JPanel{
        public void paintComponent(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(0,0,1400,1000);

        }
    }
}

