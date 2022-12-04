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
import java.util.List;


public class Puzzle222 {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);



    public void puzzle222() {

        List<String> inputFileByline =  FileReader("AdOfCo2022/puzzle2.txt");


        List<String> calories = new ArrayList<>();
        int sum = 0;
        int roundScore = 0;
        for(int i= 0; i < inputFileByline.size(); i++) {
            String input = inputFileByline.get(i);
//            List<Integer> numbers = new ArrayList<>();
//            numbers.add(number);
            String firstCh = String.valueOf(input.toCharArray()[0]) ;
            String secondCh = String.valueOf(input.toCharArray()[2]);



            if(secondCh.equals("X")){ // need to lose
                if(firstCh.equals("A")){
                    roundScore += 3;
                } else if (firstCh.equals("B")){
                    roundScore += 1;
                } else if (firstCh.equals("C")){
                    roundScore += 2;
                } else {
                    System.out.println("error");
                }
                roundScore += 0;
            } else if (secondCh.equals("Y")){ // need to draw
                if(firstCh.equals("A")){
                    roundScore += 1;
                } else if (firstCh.equals("B")){
                    roundScore += 2;
                } else if (firstCh.equals("C")){
                    roundScore += 3;
                } else {
                    System.out.println("error");
                }
                roundScore += 3;
            } else if (secondCh.equals("Z")){ // need to win
                if(firstCh.equals("A")){
                    roundScore += 2;
                } else if (firstCh.equals("B")){
                    roundScore += 3;
                } else if (firstCh.equals("C")){
                    roundScore += 1;
                } else {
                    System.out.println("error");
                }
                roundScore += 6;
            } else {
                System.out.println("error");
            }

            System.out.println("score round: " + i + " is: " + roundScore);
        }

        System.out.println("Total score should be: " + roundScore);
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
