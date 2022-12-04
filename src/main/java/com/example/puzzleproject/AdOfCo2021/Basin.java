package com.example.puzzleproject.AdOfCo2021;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Basin {

    int size;
    int xPointOfEntry;
    int yPointOfEntry;
    int counter = 0;

    List<Integer> listSizes = new ArrayList<>();
    List<int[]> pointsToBeChecked = new ArrayList<>();
    int[][] grid;
    int xMinOne;
    int xPlusOne;
    int yMinOne;
    int yPlusOne;

    int left = 0;
    int right = 0;
    int above = 0;
    int below = 0;

    public Basin( int[][] grid, int xPointOfEntry, int yPointOfEntry) {
        this.size = 0;
        this.grid = grid;
        this.xPointOfEntry = xPointOfEntry;
        this.yPointOfEntry = yPointOfEntry;
    }

    public int[][] exploreBasin(){

        for(int v= xPointOfEntry; v < grid.length; v++) {

            for(int h = yPointOfEntry; h < grid[0].length; h++){

                int point = grid[v][h];
                if(!(point == 9)){
                    pointsToBeChecked.add(new int[]{v, h});


                    while(!(pointsToBeChecked.size() == 0)){
                        int y = pointsToBeChecked.get(0)[0];
                        int x = pointsToBeChecked.get(0)[1];
                        if(grid[y][x] == 9){
                            pointsToBeChecked.remove(0);
                            continue;
                        }
                        createNeighbours(y,x);
                        if(!(left == 9)){
                            pointsToBeChecked.add(new int[]{y, xMinOne});
                        }
                        if(!(right == 9)){
                            pointsToBeChecked.add(new int[]{y, xPlusOne});
                        }
                        if(!(above == 9)){
                            pointsToBeChecked.add(new int[]{yMinOne, x});
                        }
                        if(!(below == 9)){
                            pointsToBeChecked.add(new int[]{yPlusOne, x});
                        }

                        grid[y][x] = 9;
                        pointsToBeChecked.remove(0);
                        size++;

                    }
                    counter++;
                    System.out.println("this is basin number " + counter + " is: " + size);
                    findThreeLargestBasins(size);
                    size = 0;
                }
            }
        }


        System.out.println("largest basins are: " + listSizes.get(listSizes.size()-1) );
        System.out.println("largest basins are: " + listSizes.get(listSizes.size()-2) );
        System.out.println("largest basins are: " + listSizes.get(listSizes.size()-3) );

        return grid;
    }

    private void findThreeLargestBasins(int size) {


        listSizes.add(size);
        listSizes.sort(Integer::compareTo);


    }

    private void createNeighbours(int v, int h) {
        xMinOne = h-1;
        xPlusOne = h+1;
        yMinOne = v-1;
        yPlusOne = v+1;



        // if statement for values outside of grid

        if( xMinOne < 0 && yMinOne < 0 ){
            left = 9;
            right = grid[v][xPlusOne];
            above = 9;
            below = grid[yPlusOne][h];
        } else if( xMinOne < 0 && !(yPlusOne >= grid.length)){
            left = 9;
            right = grid[v][xPlusOne];
            above = grid[yMinOne][h];
            below = grid[yPlusOne][h];
        } else if ( yMinOne < 0 && (!(xPlusOne >= grid[0].length))){
            left = grid[v][xMinOne];
            right = grid[v][xPlusOne];
            above = 9;
            below = grid[yPlusOne][h];
        } else if ( yMinOne < 0 && xPlusOne >= grid.length){
            left = grid[v][xMinOne];
            right = 9;
            above = 9;
            below = grid[yPlusOne][h];
        } else if ( xPlusOne >= grid[0].length && yPlusOne >= grid.length){
            left = grid[v][xMinOne];
            right = 9;
            above = grid[yMinOne][h];
            below = 9;
        } else if ( xPlusOne >= grid[0].length){
            left = grid[v][xMinOne];
            right = 9;
            above = grid[yMinOne][h];
            below = grid[yPlusOne][h];
        } else if ( yPlusOne >= grid.length && (!(xMinOne < 0))){
            left = grid[v][xMinOne];
            right = grid[v][xPlusOne];
            above = grid[yMinOne][h];
            below = 9;
        } else if ( yPlusOne >= grid.length && xMinOne < 0){
            left = 9;
            right = grid[v][xPlusOne];
            above = grid[yMinOne][h];
            below = 9;
        }


        if (yPlusOne < grid.length && xPlusOne < grid[0].length &&  yMinOne >= 0 && xMinOne >= 0) {
            left = grid[v][xMinOne];
            right = grid[v][xPlusOne];
            above = grid[yMinOne][h];
            below = grid[yPlusOne][h];
        }
    }
}
