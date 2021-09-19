package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Level {
    ArrayList<int[][]> levels = new ArrayList<>();




    public void loadLevels(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Levels.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                int width = line.charAt(0)-48;
                line = br.readLine();
                int height = line.charAt(0)-48;
                int[][] level = new int[width][height];
                for(int y = 0; y < height; y++){
                    line = br.readLine();
                    for(int x = 0; x < width; x++){
                        level[x][y] = line.charAt(x);
                    }
                }
                levels.add(level);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
