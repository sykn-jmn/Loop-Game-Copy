package src;

import src.Scene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LevelSelectorScene implements Scene {
    BufferedImage levelButton, backButton;
    Display display;
    Level levels;
    private Scene prevScene;
    final int WIDTH = 80;
    final int DISTANCE = 115;
    final int Y_OFFSET = 120;
    final int X_OFFSET = 40;
    final int STRING_X_OFFSET = 8;
    final int STRING_Y_OFFSET = 30;

    public LevelSelectorScene(Display display, Scene scene){
        this.prevScene = scene;
        loadAssets();
        this.display = display;
        display.changeScene(this);
        display.repaint();
        this.levels = new Level();
    }

    public void loadGame(int level){
        new GameScene(levels.getLevel(level-1),display, this);
    }

    @Override
    public void drawScene(Graphics g) {
        g.setColor(Color.decode("#333738"));
        g.fillRect(0,0,1400,1000);
        g.drawImage(backButton,520,-5,85,70,null);
        Font font = new Font("Verdana", Font.BOLD, 27);
        int level = 1;
        outerLoop:
        for(int y = 0; y <4; y++){
            for(int x = 0; x<5; x++){
                g.drawImage(levelButton,(x*DISTANCE)+X_OFFSET,(y*DISTANCE)+Y_OFFSET,WIDTH,WIDTH,null);
                g.setColor(Color.WHITE);
                g.setFont(font);
                g.drawString(""+level,(x*DISTANCE)+X_OFFSET+STRING_X_OFFSET,(y*DISTANCE)+Y_OFFSET+STRING_Y_OFFSET);
                level++;
                if(level>levels.getNumLevels()) break outerLoop;
            }
        }
    }

    @Override
    public void loadAssets() {
        try {
            levelButton = ImageIO.read(new File("src/assets/levelButtons.png"));
            backButton = ImageIO.read(new File("src/assets/backButton.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void processClick(int x, int y) {
        if(clickedBack(x,y)){
            endScene();
        }else {
            x = (x - X_OFFSET) / DISTANCE;
            y = (y - Y_OFFSET) / DISTANCE;
            loadGame(getLevel(x, y));
        }
    }

    private boolean clickedBack(int x, int y){
        return x>=520 && x<=520+85 && y>=-5+28 && y<=-5+70+28;
    }

    @Override
    public void processDrag(int x, int y) {

    }

    public int getLevel(int xIndex, int yIndex){
        return xIndex + (5*yIndex)+1;
    }

    @Override
    public void endScene() {
        display.changeScene(prevScene);
        display.repaint();
    }
}
