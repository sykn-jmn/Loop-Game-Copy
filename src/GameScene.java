package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameScene implements Scene{
    BufferedImage[][] assets;
    Loop[][] loops;
    public GameScene(Loop[][] level){
        this.loops = level;
        loadAssets();
    }

    @Override
    public void DrawScene(Graphics g) {
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

    public void loadAssets(){
        try {
            assets = new BufferedImage[5][4];
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
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
