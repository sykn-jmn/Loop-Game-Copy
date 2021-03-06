package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Level {
    ArrayList<int[][]> blueprints = new ArrayList<>();

    public Level(){
        loadBlueprints();
    }

    public int getNumLevels(){
        return blueprints.size();
    }

    // creates a game level with Loops[][] using the int[][] blueprint
    public Loop[][] getLevel(int levelIndex){

        int[][] level = blueprints.get(levelIndex);
        return intToLoop(level);
    }

    public static Loop[][] intToLoop(int[][] level){
        Loop[][] loops = new Loop[level.length][level[0].length];

        for(int y = 0; y < level[0].length; y++){
            for(int x = 0; x < level.length; x++){
                switch(level[x][y]){
                    case 5 -> loops[x][y] = null;
                    case Loop.SOURCE_LOOP -> loops[x][y] = Loop.sourceLoop();
                    case Loop.I_LOOP -> loops[x][y] = Loop.iLoop();
                    case Loop.T_LOOP -> loops[x][y] = Loop.tLoop();
                    case Loop.L_LOOP -> loops[x][y] = Loop.lLoop();
                    case Loop.CROSS_LOOP -> loops[x][y] = Loop.crossLoop();
                }
            }
        }

        return loops;
    }

    public static void randomizeAll(Loop[][] loops){
        for(Loop[] out:loops){
            for(Loop in: out){
                if(in != null) Loop.randomize(in);
            }
        }
    }

    //loads the level blueprints from the Levels.txt file
    public void loadBlueprints(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Levels.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                int[][] level = new int[9][9];
                for(int y = 0; y < 9; y++){
                    line = br.readLine();
                    for(int x = 0; x < 9; x++){
                        level[x][y] = line.charAt(x)-48;
                    }
                }
                blueprints.add(level);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //used for debugging
    public void printBlueprint(){
        for(int[][] blueprint: blueprints){
            for(int y = 0; y <blueprint[0].length; y++){
                for (int[] ints : blueprint) {
                    System.out.print(ints[y]);
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}
