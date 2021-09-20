package src;

import java.io.*;

public class Main {
    public static void main(String[] args){
        Level level = new Level();
        level.loadBlueprints();

        new Game(level.getLevel(1));
    }

}
