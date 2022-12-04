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

public class Puzzle5 {



    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public void puzzle52021() {

        List<String> inputFileByline = FileReader("AdOfCo2021/puzzle5.txt");
        List<Coordinates> coordinatesList = new ArrayList<>();

        int topX = 0;
        int topY = 0;

        for(int i= 0; i < inputFileByline.size(); i++) {
           String line = inputFileByline.get(i);

           Coordinates coordinates = new Coordinates();
           coordinates.createFromString(line);


               coordinatesList.add(coordinates);
               System.out.println(coordinates);
               int coordinateX = coordinates.getHighestCoordinateX();
               int coordinateY = coordinates.getHighestCoordinateY();
               if(coordinateX > topX){
                   topX = coordinateX;
               }
               if(coordinateY > topY){
                   topY = coordinateY;
               }
               System.out.println(topX + " x , y "+ topY);



        }

        int[][] grid = new int[topX+1][topY+1];

        for(Coordinates coordinate: coordinatesList){
            x1 = coordinate.getX1();
            x2 = coordinate.getX2();
            y1 = coordinate.getY1();
            y2 = coordinate.getY2();

            if(y1 == y2){
                if(x1 < x2){
                    for(int i= x1; i <= x2; i++){
                        grid[i][y1]++;
                    }
                }
                if(x1 > x2){
                    for(int i= x1; i >= x2; i--){
                        grid[i][y1]++;
                    }
                }
//                System.out.println("satisfied demands: " + Arrays.deepToString(grid));
            } else if(x1 == x2){
                if(y1 < y2){
                    for(int i= y1; i <= y2; i++){
                        grid[x1][i]++;
                    }
                }
                if(y1 > y2){
                    for(int i= y1; i >= y2; i--){
                        grid[x1][i]++;
                    }
                }
//                System.out.println("satisfied demands: " + Arrays.deepToString(grid));
            } else{
                if(x1 < x2 && y1 < y2){
                    for(int i= 0; i <= x2-x1; i++){
                        grid[x1+i][y1+i]++;
                    }
                }
                if(x1 > x2 && y1 > y2){
                    for(int i= 0; i <= x1-x2; i++){
                        grid[x1-i][y1-i]++;
                    }
                }
                if(x1 > x2 && y1 < y2){
                    for(int i= 0; i <= x1-x2; i++){
                        grid[x1-i][y1+i]++;
                    }
                }
                if(x1 < x2 && y1 > y2){
                    for(int i= 0; i <= x2-x1; i++){
                        grid[x1+i][y1-i]++;
                    }
                }
            }

        }

        System.out.println("final array: " + Arrays.deepToString(grid));
        int counter = 0;

        for(int x = 0; x < 991; x++){
            for(int y = 0; y < 985; y++){
                if(grid[x][y] > 1){
                    counter++;
                }
            }

        }

        System.out.println("answer should be: " + counter);

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
