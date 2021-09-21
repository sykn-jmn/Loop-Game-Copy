package src;

import src.Scene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LevelSelectorScene implements Scene {
    BufferedImage levelButton;
    final int WIDTH = 100;

    public LevelSelectorScene(){
        Level level = new Level();
        level.loadBlueprints();
    }


    @Override
    public void drawScene(Graphics g) {
        g.setColor(Color.decode("#333738"));
        g.fillRect(0,0,1400,1000);
        int level = 1;
        for(int y = 0; y <5; y++){
            for(int x = 0; x<5; x++){
                g.drawImage(levelButton,(x*WIDTH)+OFFSET,(y*WIDTH)+OFFSET,WIDTH,WIDTH,null);
                g.drawString(""+level,(x*WIDTH)+OFFSET,(y*WIDTH)+OFFSET);
            }
        }

    }

    @Override
    public void loadAssets() {
        try {
            levelButton = ImageIO.read(new File("src/assets/levelButtons.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
