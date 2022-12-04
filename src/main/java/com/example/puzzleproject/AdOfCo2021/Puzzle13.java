package com.example.puzzleproject.AdOfCo2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Puzzle13 {



    private int x1;
    private int x2;
    private int y1;
    private int y2;

    private List<String> instructions = new ArrayList<>();

    public void puzzle132021() {

        List<String> inputFileByline = FileReader("AdOfCo2021/puzzle13.txt");
        List<String[]> coordinatesList = new ArrayList<>();

        int topX = 0;
        int topY = 0;

        for(int i= 0; i < inputFileByline.size(); i++) {
           String line = inputFileByline.get(i);

           if(line.contains(",")){


            String[] coordinate = line.split(",");


               coordinatesList.add(coordinate);
               //System.out.println(coordinate);
               int coordinateX = Integer.parseInt(coordinate[0]);
               int coordinateY = Integer.parseInt(coordinate[1]);
               if(coordinateX > topX){
                   topX = coordinateX;
               }
               if(coordinateY > topY){
                   topY = coordinateY;
               }
               //System.out.println(topX + " x , y "+ topY);
           } else{
               System.out.println("this line has instructions: " + line);
               if(line.contains("fold along")){
                   instructions.add(line.substring(11));
               }

           }


        }

        int[][] grid = new int[topX+1][topY+1];

        for(String[] coordinate: coordinatesList){
            x1 = Integer.parseInt(coordinate[0]);
            y1 = Integer.parseInt(coordinate[1]);

            grid[x1][y1] = 1;


        }

        //System.out.println("first array: " + Arrays.deepToString(grid));

        for(String instruction: instructions){

           String axis = instruction.substring(0,1);
           int fold = Integer.parseInt(instruction.substring(2));

            if(axis.equals("y")){
                for(int x =0; x < grid.length; x++){

                    for(int i = fold+1; i < grid[0].length; i++){
                        if(grid[x][i] == 1) {
                            grid[x][i] = 0;
                            int newI = fold * 2 - i;
                            grid[x][newI] = 1;
                        }
                    }
                }
            } else if (axis.equals("x")){
                for(int y =0; y < grid[0].length; y++){

                    for(int i = fold+1; i < grid.length; i++){
                        if(grid[i][y] == 1) {
                            grid[i][y] = 0;
                            int newI = fold * 2 - i;
                            grid[newI][y] = 1;
                        }
                    }
                }
            }


            int counter = 0;

            for(int x = 0; x < grid.length; x++){
                for(int y = 0; y < grid[0].length; y++){
                    if(grid[x][y] >= 1){
                        counter++;
                    }
                }

            }

            System.out.println("answer should be: " + counter);



        }

        // create new grid
        int[][] newGrid = new int[40][6];
        for(int y =0; y < 6; y++){

            for(int x = 0; x < 40; x++){
                newGrid[x][y] = grid[x][y];
            }
        }

        // how to print

        //line 1:
        for(int y = 0; y< 6; y++){
            System.out.print("this is line " + y + ": ");
            for(int z = 0; z < 40; z++){
                System.out.print(newGrid[z][y] + " ");
            }
            System.out.println("");
        }


        //System.out.println("after folding array: " + Arrays.deepToString(newGrid));


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
