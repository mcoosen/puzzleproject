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
import java.util.Collections;
import java.util.List;


public class Puzzle522 {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);



    public void puzzle522() {

        List<String> inputFileByline =  FileReader("AdOfCo2022/puzzle5.txt");
        List<List<String>> listOfCrates = createListOfCrates();

        for(int i= 10; i < inputFileByline.size(); i++) {
            String input = inputFileByline.get(i);
            String[] numbers = input.split("(\\D+)");
            int numberOfCrates = Integer.parseInt(numbers[1]);
            int rowToMoveFrom = Integer.parseInt(numbers[2])-1;
            int rowToMoveTo = Integer.parseInt(numbers[3])-1;

            for(int y = 0; y < numberOfCrates ; y++){
                listOfCrates.get(rowToMoveTo).add(listOfCrates.get(rowToMoveFrom).get(listOfCrates.get(rowToMoveFrom).size()-1));
                listOfCrates.get(rowToMoveFrom).remove(listOfCrates.get(rowToMoveFrom).size()-1);
            }


        }
        log.info("processing finished");
        String lastContainers = "";
        for(int x = 0; x < 9 ; x++){
            String lastContainer = listOfCrates.get(x).get(listOfCrates.get(x).size()-1);
            lastContainers += lastContainer;
        }

        log.info("Code is: " + lastContainers);
    }

    public void puzzle522B() {

        List<String> inputFileByline =  FileReader("AdOfCo2022/puzzle5.txt");
        List<List<String>> listOfCrates = createListOfCrates();

        for(int i= 10; i < inputFileByline.size(); i++) {
            String input = inputFileByline.get(i);
            String[] numbers = input.split("(\\D+)");
            int numberOfCrates = Integer.parseInt(numbers[1]);
            int rowToMoveFrom = Integer.parseInt(numbers[2])-1;
            int rowToMoveTo = Integer.parseInt(numbers[3])-1;


            for(int y = 0; y < numberOfCrates ; y++){

                listOfCrates.get(rowToMoveTo).add(listOfCrates.get(rowToMoveFrom).get(listOfCrates.get(rowToMoveFrom).size()-numberOfCrates+y));
            }
            for(int z = 0; z < numberOfCrates ; z++){

              listOfCrates.get(rowToMoveFrom).remove(listOfCrates.get(rowToMoveFrom).size()-1);
            }
        }
        log.info("processing finished");
        String lastContainers = "";
        for(int x = 0; x < 9 ; x++){
            String lastContainer = listOfCrates.get(x).get(listOfCrates.get(x).size()-1);
            lastContainers += lastContainer;
        }

        log.info("Code is: " + lastContainers);
    }

    private List<List<String>> createListOfCrates() {

//              [Q] [B]         [H]
//          [F] [W] [D] [Q]     [S]
//          [D] [C] [N] [S] [G] [F]
//          [R] [D] [L] [C] [N] [Q]     [R]
//      [V] [W] [L] [M] [P] [S] [M]     [M]
//      [J] [B] [F] [P] [B] [B] [P] [F] [F]
//      [B] [V] [G] [J] [N] [D] [B] [L] [V]
//      [D] [P] [R] [W] [H] [R] [Z] [W] [S]
//        1   2   3   4   5   6   7   8   9

        List<List<String>> listOfCrates = new ArrayList<>();
        List<String> stackNumberOne = new ArrayList<>();
        List<String> stackNumberTwo = new ArrayList<>();
        List<String> stackNumberThree = new ArrayList<>();
        List<String> stackNumberFour = new ArrayList<>();
        List<String> stackNumberFive = new ArrayList<>();
        List<String> stackNumberSix = new ArrayList<>();
        List<String> stackNumberSeven = new ArrayList<>();
        List<String> stackNumberEight = new ArrayList<>();
        List<String> stackNumberNine = new ArrayList<>();
        Collections.addAll(stackNumberOne, "D", "B", "J", "V");
        Collections.addAll(stackNumberTwo, "P", "V", "B", "W", "R", "D", "F");
        Collections.addAll(stackNumberThree, "R", "G","F", "L", "D", "C","W", "Q");
        Collections.addAll(stackNumberFour, "W", "J","P", "M", "L", "N","D", "B");
        Collections.addAll(stackNumberFive, "H", "N","B", "P", "C", "S","Q");
        Collections.addAll(stackNumberSix, "R", "D", "B", "S", "N", "G");
        Collections.addAll(stackNumberSeven, "Z", "B", "P", "M", "Q", "F", "S", "H");
        Collections.addAll(stackNumberEight, "W", "L", "F");
        Collections.addAll(stackNumberNine, "S", "V", "F", "M","R");
        listOfCrates.add(stackNumberOne);
        listOfCrates.add(stackNumberTwo);
        listOfCrates.add(stackNumberThree);
        listOfCrates.add(stackNumberFour);
        listOfCrates.add(stackNumberFive);
        listOfCrates.add(stackNumberSix);
        listOfCrates.add(stackNumberSeven);
        listOfCrates.add(stackNumberEight);
        listOfCrates.add(stackNumberNine);

        return listOfCrates;
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
