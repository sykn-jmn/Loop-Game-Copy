package src;

public class Loop {
    private static final int LEFT = 0;
    private static final int UP = 1;
    private static final int RIGHT = 2;
    private static final int DOWN = 3;

    private boolean[] pointsAt;


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

}
