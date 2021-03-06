package src;

public class Game {
    private Loop[][] level;

    public Game(Loop[][] level){
        this.level = level;
        Level.randomizeAll(level);
    }

    public void updateLoops(int x, int y){
        x = GameScene.getXIndex(x);
        y = GameScene.getYIndex(y);
        if(level[x][y]!=null) level[x][y].rotate();
    }

    public boolean isComplete(){
        for(int y = 0; y<level[0].length; y++){
            for(int x = 0; x<level.length; x++){
                Loop current = level[x][y];
                if(current!=null) {
                    if (current.pointsAt(Loop.DOWN) && notAligned(x, y + 1, Loop.UP)) return false;
                    if (current.pointsAt(Loop.UP) && notAligned(x, y - 1, Loop.DOWN)) return false;
                    if (current.pointsAt(Loop.RIGHT) && notAligned(x+1, y, Loop.LEFT)) return false;
                    if (current.pointsAt(Loop.LEFT) && notAligned(x-1, y, Loop.RIGHT)) return false;
                }
            }
        }
        return true;
    }

    private boolean notAligned(int x, int y, int direction){
        return x < 0 ||
                x >= level.length ||
                y < 0 ||
                y >= level[0].length ||
                level[x][y] == null ||
                !level[x][y].pointsAt(direction);
    }

    private void sleep(int milli){
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
