package src;

import java.util.Random;

public class Loop {
    public static final int LEFT = 0;
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;

    private boolean[] pointsAt;
    private static final Random rand = new Random();


    public Loop(int ... directions){
        pointsAt = new boolean[4];
        for(int direction: directions){
            pointsAt[direction] = true;
        }
    }

    public boolean pointsAt(int direction){
        return pointsAt[direction];
    }

    //Might have a better way than creating a new Array
    public void rotate(){
        boolean[] copy = new boolean[4];
        for(int i = 3; i >= 0; i--){
            if(pointsAt[i]) {
                copy[(i+1) % 4] = true;
            }
        }
        pointsAt = copy;
    }

    public static void randomize(Loop loop){
        for(int i = 0; i <= rand.nextInt(4); i++) loop.rotate();
    }

    public static Loop lLoop(){
        return new Loop(LEFT,UP);
    }
    public static Loop iLoop(){
        return new Loop(LEFT,RIGHT);
    }
    public static Loop crossLoop(){
        return new Loop(LEFT,UP,RIGHT,DOWN);
    }
    public static Loop tLoop(){
        return new Loop(LEFT,UP,RIGHT);
    }
    public static Loop sourceLoop(){
        return new Loop(LEFT);
    }

}
