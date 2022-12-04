package com.example.puzzleproject.AdOfCo2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Puzzle11 {

    int[][][] grid;
    int xMinOne;
    int xPlusOne;
    int yMinOne;
    int yPlusOne;
    int totalNumberOfFlashes;
    int totalNumberAfterRound;


    public void puzzle112021() {

        List<String> inputFileByline = FileReader("AdOfCo2021/puzzle11.txt");

        grid = new int[inputFileByline.size()][inputFileByline.get(0).length()][2];


        for(int v= 0; v < inputFileByline.size(); v++) {
           String line = inputFileByline.get(v);

           for(int h = 0; h < line.length(); h++){
               grid[v][h][0] = Integer.parseInt(String.valueOf(line.charAt(h))) ;
               grid[v][h][1] = 0;
           }
        }
        //System.out.println("grid created: " + Arrays.deepToString(grid));

        //loop through grid and give energy +1
        int steps = 2000;

        for(int x = 0; x < steps; x++){
            moveOneStep(x);
            //System.out.println("total number of flashes: " + totalNumberOfFlashes);
            if((totalNumberOfFlashes-totalNumberAfterRound)==100){
                System.out.println("found the round: " + (x + 1));
            }
            totalNumberAfterRound = totalNumberOfFlashes;


        }


    }

    private void moveOneStep(int step) {
        for(int v= 0; v < grid.length; v++) {
            for (int h = 0; h < grid[0].length; h++) {
                grid[v][h][0] = grid[v][h][0]+1 ;
            }
        }
        //System.out.println("grid created + 1: " + Arrays.deepToString(grid));

        //check if any have energy > 9, give neighbours + 1, set as flashed,
        boolean hasGridFlashed = true;
        while(hasGridFlashed){
            hasGridFlashed = false;
            for(int v= 0; v < grid.length; v++) {
                for (int h = 0; h < grid[0].length; h++) {
                    if(grid[v][h][0] > 9 && grid[v][h][1] == 0){
                        // set as flashed
                        grid[v][h][1] = 1;
                        totalNumberOfFlashes = totalNumberOfFlashes +1;
                        giveNeighboursEnergy(v,h);
                        hasGridFlashed =true;
                    }
                }
            }

        }

        // alle flashed waardes op 0 zetten
        for(int v= 0; v < grid.length; v++) {
            for (int h = 0; h < grid[0].length; h++) {
                if(grid[v][h][1] == 1){
                    grid[v][h][0] = 0;
                    grid[v][h][1] = 0;
                }
            }
        }

//        boolean areWeInSync = true;
//
//            for (int v = 0; v < grid.length; v++) {
//                if(!areWeInSync){
//                    break;
//                }
//                for (int h = 0; h < grid[0].length; h++) {
//                    if (!(grid[v][h][0] == 0)) {
//                        System.out.println("break");
//                        areWeInSync = false;
//                        break;
//                    } else {
//                        System.out.println("found the synchronising step: " + step);
//                    }
//                }
//            }

    }

    private void giveNeighboursEnergy(int v, int h) {

        xMinOne = h-1;
        xPlusOne = h+1;
        yMinOne = v-1;
        yPlusOne = v+1;
        
        int left;
        int right;
        int above;
        int below;
        int aboveLeft;
        int aboveRight;
        int belowLeft;
        int belowRight;

        // if statement for values outside of grid

        if( xMinOne < 0 && yMinOne < 0 ){
            left = -1;
            grid[v][xPlusOne][0] = grid[v][xPlusOne][0] +1 ;
            above = -1;
            grid[yPlusOne][h][0] = grid[yPlusOne][h][0] + 1;
            aboveLeft = -1;
            aboveRight = -1;
            belowLeft = -1;
            grid[yPlusOne][xPlusOne][0] = grid[yPlusOne][xPlusOne][0] + 1;
        } else if( xMinOne < 0 && !(yPlusOne >= grid.length)){
            left = -1;
            grid[v][xPlusOne][0] = grid[v][xPlusOne][0] +1 ;
            grid[yMinOne][h][0] = grid[yMinOne][h][0] + 1;
            grid[yPlusOne][h][0] = grid[yPlusOne][h][0] + 1;
            aboveLeft = -1;
            grid[yMinOne][xPlusOne][0] = grid[yMinOne][xPlusOne][0] + 1;
            belowLeft = -1;
            grid[yPlusOne][xPlusOne][0] = grid[yPlusOne][xPlusOne][0] + 1;
        } else if ( yMinOne < 0 && (!(xPlusOne >= grid[0].length))){
            grid[v][xMinOne][0] = grid[v][xMinOne][0] +1;
            grid[v][xPlusOne][0] = grid[v][xPlusOne][0] +1 ;
            above = -1;
            grid[yPlusOne][h][0] = grid[yPlusOne][h][0] + 1;
            aboveLeft = -1;
            aboveRight = -1 ;
            grid[yPlusOne][xMinOne][0] = grid[yPlusOne][xMinOne][0] + 1;
            grid[yPlusOne][xPlusOne][0] = grid[yPlusOne][xPlusOne][0] + 1;
        } else if ( yMinOne < 0 && xPlusOne >= grid.length){
            grid[v][xMinOne][0] = grid[v][xMinOne][0] +1;
            right = -1;
            above = -1;
            grid[yPlusOne][h][0] = grid[yPlusOne][h][0] + 1;
            aboveLeft = -1;
            aboveRight = -1 ;
            grid[yPlusOne][xMinOne][0] = grid[yPlusOne][xMinOne][0] + 1;
            belowRight = -1;
        } else if ( xPlusOne >= grid[0].length && yPlusOne >= grid.length){
            grid[v][xMinOne][0] = grid[v][xMinOne][0] +1;
            right = -1;
            grid[yMinOne][h][0] = grid[yMinOne][h][0] + 1;
            below = -1;
            grid[yMinOne][xMinOne][0] = grid[yMinOne][xMinOne][0] + 1;
            aboveRight = -1 ;
            belowLeft = -1;
            belowRight = -1;
        } else if ( xPlusOne >= grid[0].length){
            grid[v][xMinOne][0] = grid[v][xMinOne][0] +1;
            right = -1;
            grid[yMinOne][h][0] = grid[yMinOne][h][0] + 1;
            grid[yPlusOne][h][0] = grid[yPlusOne][h][0] + 1;
            grid[yMinOne][xMinOne][0] = grid[yMinOne][xMinOne][0] + 1;
            aboveRight = -1;
            grid[yPlusOne][xMinOne][0] = grid[yPlusOne][xMinOne][0] + 1;
            belowRight = -1;
        } else if ( yPlusOne >= grid.length && (!(xMinOne < 0))){
            grid[v][xMinOne][0] = grid[v][xMinOne][0] +1;
            grid[v][xPlusOne][0] = grid[v][xPlusOne][0] +1 ;
            grid[yMinOne][h][0] = grid[yMinOne][h][0] + 1;
            below = -1;
            grid[yMinOne][xMinOne][0] = grid[yMinOne][xMinOne][0] + 1;
            grid[yMinOne][xPlusOne][0] = grid[yMinOne][xPlusOne][0] + 1;
            belowLeft = -1;
            belowRight = -1;
        } else if ( yPlusOne >= grid.length && xMinOne < 0){
            left = -1;
            grid[v][xPlusOne][0] = grid[v][xPlusOne][0] +1 ;
            grid[yMinOne][h][0] = grid[yMinOne][h][0] + 1;
            below = -1;
            aboveLeft = -1;
            grid[yMinOne][xPlusOne][0] = grid[yMinOne][xPlusOne][0] + 1;
            belowLeft = -1;
            belowRight = -1;
        }


        if (yPlusOne < grid.length && xPlusOne < grid[0].length &&  yMinOne >= 0 && xMinOne >= 0) {
            grid[v][xMinOne][0] = grid[v][xMinOne][0] +1; // left
            grid[v][xPlusOne][0] = grid[v][xPlusOne][0] +1 ; // right
            grid[yMinOne][h][0] = grid[yMinOne][h][0] + 1; // above
            grid[yPlusOne][h][0] = grid[yPlusOne][h][0] + 1; // below
            grid[yMinOne][xMinOne][0] = grid[yMinOne][xMinOne][0] + 1; //aboveLeft
            grid[yMinOne][xPlusOne][0] = grid[yMinOne][xPlusOne][0] + 1; //aboveRight
            grid[yPlusOne][xMinOne][0] = grid[yPlusOne][xMinOne][0] + 1; // belowLeft
            grid[yPlusOne][xPlusOne][0] = grid[yPlusOne][xPlusOne][0] + 1; // belowRight
            
        }

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
