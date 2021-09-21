package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameScene implements Scene{
    final static int OFFSET = Display.DrawPane.OFFSET;
    final static int Y_OFFSET = 50;
    Scene levelSelector;
    BufferedImage[][] assets;
    BufferedImage backButton;
    Loop[][] loops;
    Display display;
    final static int WIDTH = 64;
    Game game;

    public GameScene(Loop[][] level, Display display, Scene scene){
        this.loops = level;
        game = new Game(level);
        this.levelSelector = scene;

        loadAssets();
        this.display = display;
        display.changeScene(this);
        display.repaint();
    }


    public static int getXIndex(int x){
        x = x-OFFSET-10;
        return x/WIDTH;
    }
    public static int getYIndex(int y){
        y = y-OFFSET-OFFSET-10-Y_OFFSET;
        return y/WIDTH;
    }

    @Override
    public void drawScene(Graphics g) {
        g.setColor(Color.decode("#333738"));
        g.fillRect(0,0,1400,1000);
        for(int y = 0; y <loops[0].length; y++){
            for(int x = 0; x<loops.length; x++){
                if(loops[x][y]!=null){
                    int type = loops[x][y].getType();
                    int orientation = loops[x][y].getOrientation();
                    g.drawImage(assets[type][orientation],(x*WIDTH)+OFFSET,(y*WIDTH)+OFFSET+Y_OFFSET,WIDTH,WIDTH,null);
                }
            }
        }
        g.drawImage(backButton,520,-5,85,70,null);
    }

    public void loadAssets(){
        try {
            assets = new BufferedImage[5][4];
            BufferedImage pipes = ImageIO.read(new File("src/assets/pipes.png"));
            //get the third line of asset style
            backButton = ImageIO.read(new File("src/assets/backButton.png"));
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

    @Override
    public void processClick(int x, int y) {
        if(y<=Y_OFFSET+OFFSET && x>= 520) {
            endScene();
            return;
        }
        game.updateLoops(x,y);
        if(game.isComplete()) endScene();
        display.repaint();
    }

    @Override
    public void endScene() {
        display.changeScene(levelSelector);
        display.repaint();
    }
}
