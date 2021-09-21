package src;

import java.io.*;

public class Main {
    public static void main(String[] args){
        Level level = new Level();
        level.loadBlueprints();

        Display display = new Display();
        Game game = new Game(level.getLevel(2), display);
    }

}
