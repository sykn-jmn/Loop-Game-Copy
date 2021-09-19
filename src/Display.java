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

        this.setSize(700,720);
        this.setLocation(170,0);

        panel = new DrawPane(loops);
        loadAssets();
        setContentPane(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void loadAssets(){
        try {
            BufferedImage[][] assets = new BufferedImage[5][4];
            BufferedImage pipes = ImageIO.read(new File("src/assets/pipes.png"));
            //get the third line of asset style
            pipes = pipes.getSubimage(0,(pipes.getHeight()/4)*2,pipes.getWidth(),pipes.getHeight()/4);
            int width = pipes.getWidth()/15;
            int height = pipes.getHeight();
            int x = -width;
            for(int i = 0; i <2; i++)
                assets[1][0] = pipes.getSubimage(x+=width,0,width,height);
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
        private static final int OFFSET = 20;
        private int width;

        public DrawPane(Loop[][] loops){
            this.loops = loops;
        }

        public void setAssets(BufferedImage[][] assets){
            this.assets = assets;
            width = assets[0][0].getWidth();
        }

        public void paintComponent(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillRect(0,0,1400,1000);
            for(int y = 0; y <loops[0].length; y++){
                for(int x = 0; x<loops.length; x++){
                    if(loops[x][y]!=null){
                        int type = loops[x][y].getType();
                        int orientation = loops[x][y].getOrientation();
                        g.drawImage(assets[type][orientation],(x*width)+OFFSET,(y*width)+OFFSET,null);
                        System.out.println((x*width+OFFSET));
                    }
                }
            }
        }


    }
}

