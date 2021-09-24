package src;

import src.Display;
import src.Loop;
import src.Scene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LevelMakerScene implements Scene {
    final static int OFFSET = 20;
    final static int Y_OFFSET = 50;
    final static int WIDTH = 64;
    private static final int NUM_PIXELS_SIDE = 8;
    private static final int PIXEL_WIDTH = WIDTH/NUM_PIXELS_SIDE;
    private final Display display;
    private final boolean[][][][] map = new boolean[9][9][NUM_PIXELS_SIDE][NUM_PIXELS_SIDE];
    private BufferedImage play,backButton;
    private Scene prevScene;

    public LevelMakerScene(Display display, Scene prevScene){
        this.prevScene = prevScene;
        display.changeScene(this);
        this.display=display;
        loadAssets();
        display.repaint();
    }

    @Override
    public void drawScene(Graphics g) {
        g.setColor(Color.decode("#333738"));
        g.fillRect(0,0,1400,1000);
        g.setColor(Color.WHITE);
        g.drawImage(play,243,15,120,44,null);
        g.drawImage(backButton,520,-5,85,70,null);
        for(int x = 0; x<10; x++){
            g.drawLine(x*WIDTH+OFFSET,OFFSET+Y_OFFSET,x*WIDTH+OFFSET,OFFSET+Y_OFFSET+(WIDTH*9));
        }
        for(int y = 0; y<10; y++){
            g.drawLine(OFFSET,y*WIDTH+OFFSET+Y_OFFSET,OFFSET+(WIDTH*9),y*WIDTH+OFFSET+Y_OFFSET);
        }
        for(int y = 0; y<9; y++){
            for(int x = 0; x<9; x++){
                drawCell(x,y,g);
            }
        }
    }
    private void drawCell(int x, int y, Graphics g){
        g.setColor(Color.WHITE);
        Graphics2D g2 = (Graphics2D)g;
        for(int iy = 0; iy <NUM_PIXELS_SIDE; iy++){
            for(int ix = 0; ix <NUM_PIXELS_SIDE; ix++){
                if(map[x][y][ix][iy])
                    g2.fillRect(x*WIDTH+OFFSET+(ix*PIXEL_WIDTH),y*WIDTH+OFFSET+Y_OFFSET+(iy*PIXEL_WIDTH),PIXEL_WIDTH,PIXEL_WIDTH);
            }
        }
    }

    public int[][] getLevel(){
        int[][] result = new int[9][9];
        for(int y = 0; y <9; y++){
            for(int x = 0; x<9; x++){
                result[x][y] = checkDirections(map[x][y]);
            }
        }
        return result;
    }

    @Override
    public void loadAssets() {
        try {
            play = ImageIO.read(new File("src/assets/play.png"));
            backButton = ImageIO.read(new File("src/assets/backButton.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void processClick(int x, int y) {
        if(clickedBack(x,y)) {
            endScene();
            return;
        }else if (clickedPlay(x,y)){
            new GameScene(Level.intToLoop(getLevel()),display,this);
        }
        processDrag(x,y);
    }


    @Override
    public void processDrag(int x, int y) {
        System.out.println(x+","+y);
        if(!outside(x,y)){
            x = x-OFFSET-5;
            y = y-OFFSET-Y_OFFSET-28;

            map[x/WIDTH][y/WIDTH][(x%WIDTH)/PIXEL_WIDTH][(y%WIDTH)/PIXEL_WIDTH]=true;
            display.repaint();
        }
    }

    public boolean outside(int x, int y){
        return x<OFFSET+5 ||
                x>WIDTH*9+OFFSET+5||
                y<OFFSET+Y_OFFSET+28||
                y>WIDTH*9+OFFSET+Y_OFFSET+28;

    }

    @Override
    public void endScene() {
        display.changeScene(prevScene);
        display.repaint();
    }


    private boolean clickedPlay(int x, int y){
        return x>=243 && x<=243+120 && y>=15+28 && y<=15+44+28;
    }

    private boolean clickedBack(int x, int y){
        return x>=520 && x<=520+85 && y>=-5+28 && y<=-5+70+28;
    }

    private int checkDirections(boolean[][] drawing){
        boolean north = false;
        boolean south = false;
        boolean east = false;
        boolean west = false;
        for(int i = 1; i < NUM_PIXELS_SIDE-1 ; i++) {
            if (drawing[NUM_PIXELS_SIDE-1][i]) {
                east = true;
                break;
            }
        }
        for(int i = 0; i < NUM_PIXELS_SIDE-1 ; i++) {
            if (drawing[i][0]) {
                north = true;
                break;
            }
        }
        for(int i = 1; i < NUM_PIXELS_SIDE-1 ; i++) {
            if (drawing[i][NUM_PIXELS_SIDE - 1]) {
                south = true;
                break;
            }
        }
        for(int i = 1; i < NUM_PIXELS_SIDE-1 ; i++) {
            if (drawing[0][i]) {
                west = true;
                break;
            }
        }
        return getLoopFromDirection(west,north,east,south);
    }

    private int getLoopFromDirection(boolean ... directions){
        int numTrue = 0;
        for(int i = 0; i <4; i++){
            if(directions[i]){
                numTrue++;
            }
        }
        if(numTrue == 0) return 5;
        if(numTrue == 1) return Loop.SOURCE_LOOP;
        if(numTrue == 2){
            boolean firstTrue = false;
            boolean isConsecutive = false;
            for(int i = 0; i <5; i++){
                if(directions[i%4]){
                    if(firstTrue) isConsecutive = true;
                    else firstTrue=true;
                }else{
                    firstTrue=false;
                }
            }
            if (isConsecutive) return Loop.L_LOOP;
            else return Loop.I_LOOP;
        }
        if(numTrue == 3) return Loop.T_LOOP;
        else return Loop.CROSS_LOOP;
    }

}
