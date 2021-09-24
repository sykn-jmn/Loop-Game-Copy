package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenuScene implements Scene{
    BufferedImage playButton, sandBoxButton;
    Display display;
    public MainMenuScene(Display display){
        this.display = display;
        display.changeScene(this);
        loadAssets();
        display.repaint();
    }

    @Override
    public void drawScene(Graphics g) {
        if(playButton!=null) {
            g.setColor(Color.decode("#333738"));
            g.fillRect(0, 0, 1400, 1000);
            g.drawImage(playButton, 230, 200, playButton.getWidth() * 4, playButton.getHeight() * 4, null);
            g.drawImage(sandBoxButton, 230, 400, sandBoxButton.getWidth() * 4, sandBoxButton.getHeight() * 4, null);
        }
    }

    @Override
    public void loadAssets() {
        try {
            BufferedImage menuImages = ImageIO.read(new File("src/assets/menuButtons.png"));
            int width = menuImages.getWidth()/2;
            int height = menuImages.getHeight();
            playButton = menuImages.getSubimage(0,0,width,height);
            sandBoxButton = menuImages.getSubimage(width,0,width,height);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void processClick(int x, int y) {
        if (clickedPlay(x,y)){
            System.out.println("play");
            new LevelSelectorScene(display, this);
        }else if(clickedSandBox(x,y)){
            System.out.println("sandbox");
            new LevelMakerScene(display, this);
        }
    }
    private boolean clickedPlay(int x, int y){
        return x >= 230+5 && x <= 230+5+(playButton.getWidth()*4)
                && y >= 200+28 && y<= 200+28+(playButton.getHeight()*4);
    }
    private boolean clickedSandBox(int x, int y){
        return x >= 230+5 && x <= 230+5+((playButton.getWidth()*4))
                && y >= 400+28 && y <= 400+28+(playButton.getHeight()*4);
    }

    @Override
    public void processDrag(int x, int y) {

    }

    @Override
    public void endScene() {

    }
}
