package com.example.puzzleproject.AdOfCo2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Puzzle12 {

    List<Cave> caves = new ArrayList<>();

    public void puzzle122021() {

        List<String> inputFileByline = FileReader("AdOfCo2021/puzzle12.txt");

        //what to do create a list of all the caves and it's connections
        // go throug all connections, but how do you know if you have taken a connection already, sort of like a maze, where each
        // also seperate class perhaps, path finder, that lists all connections of a start plus cave pair
        PathFinder pathFinder = createPathFinder(inputFileByline);

        List<String> startingCaves = pathFinder.getStartingCaves();

        for(String startingCave: startingCaves){
            pathFinder.goThroughAllOptions(startingCave);
        }

        pathFinder.purgeFinalPaths();
        System.out.println("have i done it? count should be " + pathFinder.getFinalPaths().size());


    }

    private PathFinder createPathFinder(List<String> inputFileByline) {

        PathFinder pathFinder = new PathFinder();
        for(int i= 0; i < inputFileByline.size(); i++) {
            String listInputString[] = inputFileByline.get(i).split("-");

            if(listInputString[0].equals("start") ){
                pathFinder.startingCaves.add(listInputString[1]);
            } else if( listInputString[1].equals("start")){
                pathFinder.startingCaves.add(listInputString[0]);
            } else if(listInputString[0].equals("end") ){
                pathFinder.endingCaves.add(listInputString[1]);
            } else if( listInputString[1].equals("end")){
                pathFinder.endingCaves.add(listInputString[0]);
            } else {
                pathFinder.connections.add(listInputString);
            }
        }
        pathFinder.createStartingCaves();
        pathFinder.createCavesFromConnections(pathFinder.connections);
        pathFinder.addEndConnections();
        pathFinder.determineForm();
        System.out.println("these are the connections: ;" + pathFinder.getConnections().toString());


        return pathFinder;
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
