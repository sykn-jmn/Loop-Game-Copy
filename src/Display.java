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

        panel = new DrawPane(loops);
        loadAssets();
        setContentPane(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public int getXIndex(int x){
        return panel.getXIndex(x);
    }
    public int getYIndex(int y){
        return panel.getYIndex(y);
    }

    public void loadAssets(){
        try {
            BufferedImage[][] assets = new BufferedImage[5][4];
            BufferedImage pipes = ImageIO.read(new File("src/assets/pipes.png"));
            //get the third line of asset style
            int width = pipes.getWidth()/15;
            int height = pipes.getHeight();
            int x = -width;
            for(int i = 0; i <2; i++)
                assets[1][i] = pipes.getSubimage(x+=width,0,width,height);
            assets[1][2] = assets[1][0];
            assets[1][3] = assets[1][1];
            for(int i = 0; i <4; i++)
                assets[3][i] = pipes.getSubimage(x+=width,0,width,height);
            for(int i = 0; i <4; i++)
                assets[0][i] = pipes.getSubimage(x+=width,0,width,height);
            x+=width;
            for(int i = 0; i <4; i++)
                assets[4][i] = pipes.getSubimage(x,0,width,height);
            for(int i = 0; i <4; i++)
                assets[2][i] = pipes.getSubimage(x+=width,0,width,height);
            panel.setAssets(assets);
        }catch(IOException e){
            e.printStackTrace();
        }
    }





    static class DrawPane extends JPanel{
        private BufferedImage[][] assets = new BufferedImage[5][4];
        private Loop[][] loops;
        public static final int OFFSET = 20;
        private static final int WIDTH = 64;

        public DrawPane(Loop[][] loops){
            this.loops = loops;
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
            g.setColor(Color.decode("#333738"));
            g.fillRect(0,0,1400,1000);
            for(int y = 0; y <loops[0].length; y++){
                for(int x = 0; x<loops.length; x++){
                    if(loops[x][y]!=null){
                        int type = loops[x][y].getType();
                        int orientation = loops[x][y].getOrientation();
                        g.drawImage(assets[type][orientation],(x*WIDTH)+OFFSET,(y*WIDTH)+OFFSET,WIDTH,WIDTH,null);
                    }
                }
            }
        }


    }
}

