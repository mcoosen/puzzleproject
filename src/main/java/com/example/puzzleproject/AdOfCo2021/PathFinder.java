package com.example.puzzleproject.AdOfCo2021;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PathFinder {

    Cave startingCave;
    List<String> startingCaves = new ArrayList<>();
    List<String> smallCavesVisited = new ArrayList<>();
    List<String> endingCaves = new ArrayList<>();
    List<String[]> connections = new ArrayList<>();
    List<Cave> caves = new ArrayList<>();
    List<String> paths = new ArrayList<>();
    List<String> finalPaths = new ArrayList<>();

//    public PathFinder(String startingCave, List<String> startingCaves, List<String> smallCavesVisited, List<String> endingCaves, List<String[]> connections) {
//        this.startingCave = startingCave;
//        this.startingCaves = startingCaves;
//        this.smallCavesVisited = smallCavesVisited;
//        this.endingCaves = endingCaves;
//        this.connections = connections;
//    }

    public void goThroughAllOptions(String variable) {
       //String connection = variable;

        String path = "START-" + variable;
        paths.add(path);

        // what has te be done in each cave
        // determine if there are valid connections, if so add them to path list, save some kind of path list that needs to be checked,
        // keep track if small cave is already in path, then don't allow it
        // go to next, but how to go back
        // check if a path is at it's end, because no connections then go the next path to check

        //might have a solution, always go with path 0, and the latest cave in path 0, if that has connections, go further, if not, remove it and put it in final path list.

        //so what needs to be de do loop
        // get path0
        // get latest cave in path
        // get connections, if none end, remove path, put in final list and go again
        // get connections, if some, check if when lowercase it has already been visited, if so see step above
        // if connections left, add all to path and path and replace path 0,
        // go for another round


        // what if a small cave can be visited twice.

        do{
            String workingPath = paths.get(0);
            String LatestCave = getLatestCave(workingPath);
            Cave cave = caves.get(getNumberOfCave(LatestCave));
//            if(cave.getConnections().size() == 0){
//                System.out.println("end reached of this path");
//                finalPaths.add(workingPath);
//                paths.remove(0);
//            }

            boolean isThereAnotherPath = false;
            boolean firstRealConnectionFound = false;


            for(int z = 0; z < cave.getConnections().size(); z++){

                // check op connection door path te controleren op waarde in lowercase
                if(workingPath.contains(cave.getConnections().get(z).toLowerCase()) && !(workingPath.contains("1"))){
                    //System.out.println("first small cave found that i wan't to go back through");
                    String connection = cave.getConnections().get(z);
                    if(!firstRealConnectionFound){
                        paths.set(0,workingPath + "-" + connection + "1");
                    } else{
                        paths.add(workingPath + "-" + connection + "1");
                    }
                    firstRealConnectionFound = true;
                    isThereAnotherPath = true;
                } else if (workingPath.contains(cave.getConnections().get(z).toLowerCase())){
                    //won't go through with this
                } else if(!firstRealConnectionFound) {
                    String connection = cave.getConnections().get(z);
                    paths.set(0,workingPath + "-" + connection);
                    firstRealConnectionFound = true;
                    isThereAnotherPath = true;
                } else{
                    String connection = cave.getConnections().get(z);
                    paths.add(workingPath + "-" + connection);
                }
            }

            if(!isThereAnotherPath){
                //System.out.println("end reached of this path");
                finalPaths.add(workingPath);
                paths.remove(0);
            }

        } while(paths.size() > 0);
        ;


    }

    private String getLatestCave(String workingPath) {
        String[] caves = workingPath.split("-");
        String cave = caves[caves.length-1];
        if(cave.contains("1")){
            cave = cave.substring(0, cave.length()-1);
        }
        return cave;
    }

    public void createStartingCaves(){
        for(String startingCave: startingCaves){
            Cave cave = new Cave(startingCave, true);
            caves.add(cave);
        }
    }

    public void addEndConnections(){
        for(String endingCave: endingCaves){
            int caveNumber = getNumberOfCave(endingCave);
            // caves.get(caveNumber).setHasEnd(true);
            caves.get(caveNumber).connections.add("end");
        }
    }

    public void determineForm(){
        for(Cave cave: caves){
            String code = cave.getCode();
            if(code.toUpperCase().equals(code)){
                cave.setForm("big");
            } else {
                cave.setForm("small");
            }
        }
    }

    public void createCavesFromConnections(List<String[]> connections) {

        for(int i = 0; i < connections.size(); i++){
            String[] connection = connections.get(i);

            // for connection 0
            int caveNumber = getNumberOfCave(connection[0]);
            if(caveNumber == -1){
                Cave cave = new Cave(connection[0], connection[1]);
                caves.add(cave);
            } else if (caveNumber >= 0){
                caves.get(caveNumber).connections.add(connection[1]);
            }

            // for connection 1
            int caveNumber1 = getNumberOfCave(connection[1]);
            if(caveNumber1 == -1){
                Cave cave = new Cave(connection[1], connection[0]);
                caves.add(cave);
            } else if (caveNumber1 >= 0){
                caves.get(caveNumber1).connections.add(connection[0]);
            }
        }
        // create end cave
        Cave cave = new Cave("end");
        caves.add(cave);


    }


    // creating codes for different settings
    // -1 does not exist
    // -2 start
    // -3 end
    // y is the number already in the list
    private int getNumberOfCave(String cave) {
        for(int y = 0; y< caves.size(); y++){
            if(caves.get(y).getCode().equals(cave)) {
                return y;
            } else {

            };
        }
        return -1;
    }

    public void purgeFinalPaths() {

        finalPaths.removeIf(finalPath -> !finalPath.contains("end"));
    }
}
