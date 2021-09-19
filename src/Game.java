package src;

import java.util.Random;

public class Game {
    private Loop[][] level;
    private Display display;

    public Game(Loop[][] level){
        this.level = level;

    }

    public void run(){
        while(!isComplete()){
            //wait for mouseInput
            display.repaint();
        }
    }

    private boolean isComplete(){
        for(int y = 0; y<level[0].length; y++){
            for(int x = 0; x<level.length; x++){
                Loop current = level[x][y];
                if(current.pointsAt(Loop.DOWN) && !checkLoop(x,y+1,Loop.UP)) return false;
                if(current.pointsAt(Loop.UP) && !checkLoop(x,y-1,Loop.DOWN)) return false;
                if(current.pointsAt(Loop.RIGHT) && !checkLoop(x,y+1,Loop.LEFT)) return false;
                if(current.pointsAt(Loop.LEFT) && !checkLoop(x,y+1,Loop.RIGHT)) return false;
            }
        }
        return true;
    }

    private boolean checkLoop(int x, int y, int direction){
        return x >= 0 &&
                x < level.length &&
                y >= 0 &&
                y < level[0].length &&
                level[x][y] != null &&
                level[x][y].pointsAt(direction);
    }
}
