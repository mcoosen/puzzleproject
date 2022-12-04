package com.example.puzzleproject.AdOfCo2021;

import com.example.puzzleproject.AdOfCo2021.dijkstra.Dijkstra;
import com.example.puzzleproject.AdOfCo2021.dijkstra.Graph;
import com.example.puzzleproject.AdOfCo2021.dijkstra.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Puzzle15 {


    public void puzzleAlgo(){
        List<String> inputFileByline = FileReader("AdOfCo2021/puzzle15.txt");

        int[][] grid = new int[inputFileByline.get(0).length()][inputFileByline.size()];
        Graph graph = new Graph();

        // first add all nodes
        for(int y= 0; y < inputFileByline.size(); y++) {
            String numbers = inputFileByline.get(y);
            for(int x = 0; x < numbers.length(); x++){
                int input = Integer.parseInt(String.valueOf(numbers.charAt(x)));
                int identifier = y*100+x;
                Node node = new Node(identifier);

                grid[x][y] = input;
                graph.addNode(node);
            }

        }

        // then add all destinations and distance
        // add above and left
        for(int y= 0; y < inputFileByline.size(); y++) {
            String numbers = inputFileByline.get(y);
            for(int x = 0; x < numbers.length(); x++){
                int input = Integer.parseInt(String.valueOf(numbers.charAt(x)));
                int identifier = y*100+x;
                Node node = graph.getNode(identifier);

                if(x < 99){
                    Node nodeRight = graph.getNode(identifier+1);
                    node.addDestination(nodeRight,grid[x+1][y]);
                }
                if(identifier < 9900){
                    Node nodeBelow = graph.getNode(identifier+100);
                    node.addDestination(nodeBelow,grid[x][y+1]);
                }


            }

        }


        graph = Dijkstra.calculateShortestPathFromSource(graph, graph.getNode(0));

        Node node = graph.getNode(9999);
        System.out.println("this should be shortest path: " + node.getDistance());
    }


    public void puzzle15() {

        List<String> inputFileByline = FileReader("AdOfCo2021/puzzle15.txt");
        int[][] grid = new int[inputFileByline.get(0).length()][inputFileByline.size()];
        Graph graph = new Graph();


        // first add input file to grid
        for(int y= 0; y < inputFileByline.size(); y++) {
            String numbers = inputFileByline.get(y);
            for(int x = 0; x < numbers.length(); x++){
                int input = Integer.parseInt(String.valueOf(numbers.charAt(x)));
                grid[x][y] = input;
            }

        }

        //create large grid // creating the large grid went wrong??
        int[][] largeGrid = new int[inputFileByline.get(0).length()*5][inputFileByline.size()*5];

        for(int y= 0; y < grid.length; y++) {
            for(int x = 0; x < grid.length; x++){
                int number = grid[x][y];
                largeGrid[x][y] = number;

                largeGrid[x+100][y] = createNumber(number+1);
                largeGrid[x+200][y] = createNumber(number+2);
                largeGrid[x+300][y] = createNumber(number+3);
                largeGrid[x+400][y] = createNumber(number+4);

                largeGrid[x][y+100] = createNumber(number+1);
                largeGrid[x][y+200] = createNumber(number+2);
                largeGrid[x][y+300] = createNumber(number+3);
                largeGrid[x][y+400] = createNumber(number+4);

                largeGrid[x+100][y+100] = createNumber(number+2);
                largeGrid[x+200][y+200] = createNumber(number+4);
                largeGrid[x+300][y+300] = createNumber(number+6);
                largeGrid[x+400][y+400] = createNumber(number+8);

                largeGrid[x+100][y+200] = createNumber(number+3);
                largeGrid[x+100][y+300] = createNumber(number+4);
                largeGrid[x+100][y+400] = createNumber(number+5);

                largeGrid[x+200][y+100] = createNumber(number+3);
                largeGrid[x+200][y+300] = createNumber(number+5);
                largeGrid[x+200][y+400] = createNumber(number+6);

                largeGrid[x+300][y+100] = createNumber(number+4);
                largeGrid[x+300][y+200] = createNumber(number+5);
                largeGrid[x+300][y+400] = createNumber(number+7);

                largeGrid[x+400][y+100] = createNumber(number+5);
                largeGrid[x+400][y+200] = createNumber(number+6);
                largeGrid[x+400][y+300] = createNumber(number+7);
            }
        }

        //System.out.println("this the large grid: " + largeGrid);

        // first add all nodes
        for(int y= 0; y < largeGrid.length; y++) {
            for(int x = 0; x < largeGrid.length; x++){
                int identifier = y*500+x;
                Node node = new Node(identifier);
                graph.addNode(node);
            }

        }

        // then add all destinations and distance
        // add above and left
        for(int y= 0; y < largeGrid.length; y++) {
            for(int x = 0; x < largeGrid.length; x++){
                int identifier = y*500+x;
                Node node = graph.getNode(identifier);
//                if(identifier == 2448 || identifier == 2399){
//                    System.out.println("break here");
//                }
                if(x < 499){
                    Node nodeRight = graph.getNode(identifier+1);
                    node.addDestination(nodeRight,largeGrid[x+1][y]);
                }
                if(identifier < 249499){
                    Node nodeBelow = graph.getNode(identifier+500);
                    node.addDestination(nodeBelow,largeGrid[x][y+1]);
                }
                if(!(x % 500 == 0)){
                    if(x == 99){
                        System.out.println("break" + System.currentTimeMillis());
                    }
                    Node nodeLeft = graph.getNode(identifier-1);
                    node.addDestination(nodeLeft,largeGrid[x-1][y]);
                }

                if(identifier > 499){
                    Node nodeAbove = graph.getNode(identifier-500);
                    node.addDestination(nodeAbove,largeGrid[x][y-1]);
                }

            }

        }

        graph = Dijkstra.calculateShortestPathFromSource(graph, graph.getNode(0));


        Node node = graph.getNode(249999);
        System.out.println("this should be shortest path: " + node.getDistance());

    }

    public int createNumber(int number){
        if(number >= 10){
            return number-9;
        }
        return number;
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
