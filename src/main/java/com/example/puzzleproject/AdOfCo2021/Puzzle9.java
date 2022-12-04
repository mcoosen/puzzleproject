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

public class Puzzle9 {

    int[][] grid;
    int xMinOne;
    int xPlusOne;
    int yMinOne;
    int yPlusOne;


    public void puzzle92021() {

        List<String> inputFileByline = FileReader("AdOfCo2021/puzzle9.txt");

        grid = new int[inputFileByline.size()][inputFileByline.get(0).length()];


        for(int v= 0; v < inputFileByline.size(); v++) {
           String line = inputFileByline.get(v);

           for(int h = 0; h < line.length(); h++){
               grid[v][h] = Integer.parseInt(String.valueOf(line.charAt(h))) ;
           }
        }
        System.out.println("grid created: " + Arrays.deepToString(grid));

        //loop through grid
        int totalSum = 0;

        for(int v= 0; v < grid.length; v++) {


            for(int h = 0; h < 100; h++){

                xMinOne = h-1;
                xPlusOne = h+1;
                yMinOne = v-1;
                yPlusOne = v+1;


                // if statement for values outside of grid

                if( xMinOne < 0 ){
                    xMinOne = xMinOne + 2;
                }
                if ( yMinOne < 0){
                    yMinOne = yMinOne + 2;
                }
                if ( xPlusOne >= 100){
                    xPlusOne = xPlusOne - 2;
                }
                if ( yPlusOne >= grid.length){
                    yPlusOne = yPlusOne - 2;
                }

                int point = grid[v][h];
                int left = grid[v][xMinOne];
                int right = grid[v][xPlusOne];
                int above = grid[yMinOne][h];
                int below = grid[yPlusOne][h];

                // check if point is lower then neighbours
                if(point < left && point < right && point < above && point < below){
                    System.out.println("we have a winner: " + point + " coordinate h&v: " + h + v);
                    totalSum = totalSum + point + 1;

                };
            }
        }

        System.out.println("total sum of points is: " + totalSum);
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
