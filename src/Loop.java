package src;

import java.util.Random;

public class Loop {
    public static final int LEFT = 0;
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;

    public static final int SOURCE_LOOP = 0;
    public static final int I_LOOP = 1;
    public static final int T_LOOP = 2;
    public static final int L_LOOP = 3;
    public static final int CROSS_LOOP = 4;

    private boolean[] pointsAt;
    private static final Random rand = new Random();
    private int orientation, type;

    public Loop(int type, int ... directions){
        this.type = type;
        orientation = 3;
        pointsAt = new boolean[4];
        for(int direction: directions){
            pointsAt[direction] = true;
        }
    }

    public int getOrientation(){
        return orientation;
    }
    public int getType(){
        return type;
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
        orientation = (orientation+1) % 4;
    }

    public static void randomize(Loop loop){
        for(int i = 0; i <= rand.nextInt(4); i++) {
            loop.rotate();
        }
    }

    public static Loop lLoop(){
        return new Loop(L_LOOP,LEFT,UP);
    }
    public static Loop iLoop(){
        return new Loop(I_LOOP,UP,DOWN);
    }
    public static Loop crossLoop(){
        return new Loop(CROSS_LOOP, LEFT,UP,RIGHT,DOWN);
    }
    public static Loop tLoop(){
        return new Loop(T_LOOP, LEFT,UP,RIGHT);
    }
    public static Loop sourceLoop(){
        return new Loop(SOURCE_LOOP, DOWN);
    }

}
