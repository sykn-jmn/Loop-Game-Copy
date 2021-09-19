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

        this.setSize(1010,800);
        this.setLocation(170,0);

        panel = new DrawPane();
        loadAssets();
        setContentPane(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void loadAssets(){
        try {
            BufferedImage[] sourceLoopAssets = new BufferedImage[4];
            BufferedImage[] iLoopAssets = new BufferedImage[4];
            BufferedImage[] tLoopAssets = new BufferedImage[4];
            BufferedImage[] crossLoopAssets = new BufferedImage[4];
            BufferedImage[] lLoopAssets = new BufferedImage[4];
            BufferedImage pipes = ImageIO.read(new File("src/assets/pipes.png"));
            //get the third line of asset style
            pipes = pipes.getSubimage(0,(pipes.getHeight()/4)*2,pipes.getWidth(),pipes.getHeight()/4);
            int width = pipes.getWidth()/15;
            int height = pipes.getHeight();
            int x = -width;
            for(int i = 0; i <2; i++)
                iLoopAssets[0] = pipes.getSubimage(x+=width,0,width,height);
            iLoopAssets[2] = iLoopAssets[0];
            iLoopAssets[3] = iLoopAssets[1];
            for(int i = 0; i <4; i++)
                lLoopAssets[i] = pipes.getSubimage(x+=width,0,width,height);
            for(int i = 0; i <4; i++)
                sourceLoopAssets[i] = pipes.getSubimage(x+=width,0,width,height);
            x+=width;
            for(int i = 0; i <4; i++)
                crossLoopAssets[i] = pipes.getSubimage(x,0,width,height);
            for(int i = 0; i <4; i++)
                tLoopAssets[i] = pipes.getSubimage(x+=width,0,width,height);
            panel.setCrossLoopAssets(crossLoopAssets);
            panel.setiLoopAssets(iLoopAssets);
            panel.setlLoopAssets(lLoopAssets);
            panel.setSourceLoopAssets(sourceLoopAssets);
            panel.settLoopAssets(tLoopAssets);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static class DrawPane extends JPanel{
        private BufferedImage[] sourceLoopAssets = new BufferedImage[4];
        private BufferedImage[] iLoopAssets = new BufferedImage[4];
        private BufferedImage[] tLoopAssets = new BufferedImage[4];
        private BufferedImage[] crossLoopAssets = new BufferedImage[4];
        private BufferedImage[] lLoopAssets = new BufferedImage[4];



        public void paintComponent(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(0,0,1400,1000);
        }


        public void setSourceLoopAssets(BufferedImage[] sourceLoopAssets) {
            this.sourceLoopAssets = sourceLoopAssets;
        }

        public void setiLoopAssets(BufferedImage[] iLoopAssets) {
            this.iLoopAssets = iLoopAssets;
        }

        public void settLoopAssets(BufferedImage[] tLoopAssets) {
            this.tLoopAssets = tLoopAssets;
        }

        public void setCrossLoopAssets(BufferedImage[] crossLoopAssets) {
            this.crossLoopAssets = crossLoopAssets;
        }

        public void setlLoopAssets(BufferedImage[] lLoopAssets) {
            this.lLoopAssets = lLoopAssets;
        }
    }
}

