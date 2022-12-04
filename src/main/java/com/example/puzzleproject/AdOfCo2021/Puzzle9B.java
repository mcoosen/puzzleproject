package com.example.puzzleproject.AdOfCo2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle9B {

    int[][] grid;
    int xMinOne;
    int xPlusOne;
    int yMinOne;
    int yPlusOne;

    public void puzzle9B2021() {

        List<String> inputFileByline = FileReader("AdOfCo2021/puzzle9.txt");
        List<Coordinates> coordinatesList = new ArrayList<>();

        grid = new int[inputFileByline.size()][inputFileByline.get(0).length()];


        for(int v= 0; v < inputFileByline.size(); v++) {
           String line = inputFileByline.get(v);

           for(int h = 0; h < line.length(); h++){
               grid[v][h] = Integer.parseInt(String.valueOf(line.charAt(h))) ;
           }
        }
        System.out.println("grid created: " + Arrays.deepToString(grid));

        Basin basin = new Basin(grid,0,0);
        basin.exploreBasin();
        //loop through grid

    }


    private static List<String> FileReader(String source){

        Path path = Paths.get("src/main/resources/" + source );
        List<String> list = new ArrayList<>();

        try(BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line = br.readLine();

            int i =0;
            while (line != null){
                list.add(i, line);
                i++;
                line = br.readLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
