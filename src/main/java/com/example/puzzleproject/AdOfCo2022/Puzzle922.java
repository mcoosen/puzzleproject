package com.example.puzzleproject.AdOfCo2022;

import com.example.puzzleproject.StartApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle922 {

// soort hashmap maken waar de head overheen beweegt en de tail volgt hem.
    // elke cel een 0 of 1 geven die aangeeft of de tail er geweest is, daarna tellen
    // grootte van de map bepalen door er een eerste keer doorheen te loopen?
    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);
    List<String> tailPointsVisited = new ArrayList<>();
    List<String> restTailPointsVisited = new ArrayList<>();
    boolean keepTrack = false;

    Integer[] intTail = new Integer[]{ 1000,1000 };

    Integer[] intTail1 = new Integer[]{ 1000,1000 };
    Integer[] intTail2 = new Integer[]{ 1000,1000 };
    Integer[] intTail3 = new Integer[]{ 1000,1000 };
    Integer[] intTail4 = new Integer[]{ 1000,1000 };
    Integer[] intTail5 = new Integer[]{ 1000,1000 };
    Integer[] intTail6 = new Integer[]{ 1000,1000 };
    Integer[] intTail7 = new Integer[]{ 1000,1000 };
    Integer[] intTail8 = new Integer[]{ 1000,1000 };
    Integer[] intTail9 = new Integer[]{ 1000,1000 };


    public void puzzle922() {

        List<String> inputFileByline = FileReader("AdOfCo2022/puzzle9.txt");

        Integer positionX = 1000;
        Integer positionY = 1000;
        for(int i= 0; i < inputFileByline.size(); i++) {
            String input = inputFileByline.get(i);
            String direction  = input.split(" ")[0];
            int movement  = Integer.parseInt(input.split(" ")[1]);

            Map<Integer[],Boolean> headMovement = new HashMap<>();
            for (int x = 0; x < movement; x++) {
                if(direction.equals("R")){
                    positionX = positionX +1;
                } else if(direction.equals("L"))  {
                    positionX = positionX -1;
                } else if(direction.equals("U")){
                    positionY = positionY +1;
                } else if (direction.equals("D")){
                    positionY = positionY -1;
                } else {
                    log.info("direction not found");
                }
                Integer[] intArray = new Integer[]{positionX, positionY};
                headMovement.put(intArray, true);
                determineTailMovement(intArray);
            }

        }

        long count = tailPointsVisited.stream().distinct().count();

        System.out.println("total points visited: " + count );
    }


    public void puzzle922B() {

        List<String> inputFileByline = FileReader("AdOfCo2022/puzzle9.txt");

        Integer positionX = 1000;
        Integer positionY = 1000;

        for(int i= 0; i < inputFileByline.size(); i++) {
            String input = inputFileByline.get(i);
            String direction  = input.split(" ")[0];
            int movement  = Integer.parseInt(input.split(" ")[1]);

            for (int x = 0; x < movement; x++) {
                if(direction.equals("R")){
                    positionX = positionX +1;
                } else if(direction.equals("L"))  {
                    positionX = positionX -1;
                } else if(direction.equals("U")){
                    positionY = positionY +1;
                } else if (direction.equals("D")){
                    positionY = positionY -1;
                } else {
                    log.info("direction not found");
                }
                Integer[] intHead = new Integer[]{positionX, positionY};

                determineTailMovementTimes9(intHead);
            }

        }

        long count = tailPointsVisited.stream().distinct().count();

        System.out.println("total points visited: " + count );

    }

    private void determineTailMovementTimes9(Integer[] intHead) {

        intTail1 = determineTailMovementAndReturn(intHead, intTail1);
        intTail2 = determineTailMovementAndReturn(intTail1, intTail2);
        intTail3 = determineTailMovementAndReturn(intTail2, intTail3);
        intTail4 = determineTailMovementAndReturn(intTail3, intTail4);
        intTail5 = determineTailMovementAndReturn(intTail4, intTail5);
        intTail6 = determineTailMovementAndReturn(intTail5, intTail6);
        intTail7 = determineTailMovementAndReturn(intTail6, intTail7);
        intTail8 = determineTailMovementAndReturn(intTail7, intTail8);
        keepTrack = true;
        intTail9 = determineTailMovementAndReturn(intTail8, intTail9);

    }


    private void determineTailMovement(Integer[] intArray) {

        int distanceH = intArray[0] - intTail[0];
        int distanceV = intArray[1] - intTail[1];
            if(distanceH < -1){
                if(distanceV < 0){
                    intTail[1] = intTail[1] -1;
                }  else if (distanceV > 0){
                    intTail[1] = intTail[1] +1;
                }
                intTail[0] = intTail[0] -1;
            }  else if (distanceH > 1){
                if(distanceV < 0){
                    intTail[1] = intTail[1] -1;
                }  else if (distanceV > 0){
                    intTail[1] = intTail[1] +1;
                }
                intTail[0] = intTail[0] +1;
            }

            if(distanceV < -1){
                intTail[1] = intTail[1] -1;
                if(distanceH < 0){
                    intTail[0] = intTail[0] -1;
                }  else if (distanceH > 0){
                    intTail[0] = intTail[0] +1;
                }
            }  else if (distanceV > 1){
                if(distanceH < 0){
                    intTail[0] = intTail[0] -1;
                }  else if (distanceH > 0){
                    intTail[0] = intTail[0] +1;
                }
                intTail[1] = intTail[1] +1;
            }

            String coordinate = String.valueOf(intTail[0])  + String.valueOf(intTail[1]);
        tailPointsVisited.add(coordinate);

    }

    private Integer[] determineTailMovementAndReturn(Integer[] intHead, Integer[] intTailIn) {

        int distanceH = intHead[0] - intTailIn[0];
        int distanceV = intHead[1] - intTailIn[1];
        Integer[] intTailOut = intTailIn;

        if(distanceH < -1 && distanceV == 0){
            intTailOut[0] = intTailIn[0] -1;
        } else if(distanceH < -1 && distanceV > 0 || distanceH == -1 && distanceV > 1) {
            intTailOut[0] = intTailIn[0] -1;
            intTailOut[1] = intTailIn[1] +1;
        } else if(distanceH < -1 || distanceH == -1 && distanceV < -1) {
            intTailOut[0] = intTailIn[0] -1;
            intTailOut[1] = intTailIn[1] -1;
        }

        if(distanceH > 1 && distanceV == 0){
            intTailOut[0] = intTailIn[0] +1;
        } else if(distanceH > 1 && distanceV > 0 || distanceH == 1 && distanceV > 1) {
            intTailOut[1] = intTailIn[1] +1;
            intTailOut[0] = intTailIn[0] +1;
        } else if(distanceH > 1|| distanceH == 1 && distanceV < -1) {
            intTailOut[0] = intTailIn[0] +1;
            intTailOut[1] = intTailIn[1] -1;
        }

        if(distanceV < -1 && distanceH == 0){
            intTailOut[1] = intTailIn[1] -1;
        } else if (distanceV > 1 && distanceH == 0){
            intTailOut[1] = intTailIn[1] +1;
        }


        String coordinate = String.valueOf(intTailOut[0])  + String.valueOf(intTailOut[1]);
        if((!tailPointsVisited.contains(coordinate)) && (keepTrack)){
            tailPointsVisited.add(coordinate);

        }

        keepTrack = false;
        return intTailOut;

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
