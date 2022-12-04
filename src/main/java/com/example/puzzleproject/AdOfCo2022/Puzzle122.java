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
import java.util.*;


public class Puzzle122 {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);



    public void puzzle122() {

        List<String> inputFileByline =  FileReader("AdOfCo2022/puzzle1.txt");


        List<Integer> calories = new ArrayList<>();
        int sum = 0;
        for(int i= 0; i < inputFileByline.size(); i++) {
            int number = Integer.parseInt(inputFileByline.get(i));
//            List<Integer> numbers = new ArrayList<>();
//            numbers.add(number);
            sum = number + sum;
            if(i == inputFileByline.size()-1){
                calories.add(sum);
                sum =0;
                i++;
                break;
            }

            if(inputFileByline.get(i+1).equals("")){
                calories.add(sum);
                sum =0;
                i++;
            }
        }
        Collections.sort(calories);
        int nr1 = calories.get(calories.size()-1);
        int nr2 = calories.get(calories.size()-2);
        int nr3 = calories.get(calories.size()-3);
        int totalSum = nr1 + nr2 + nr3;

        System.out.println("should be highest number: " + calories.get(calories.size()-1));
        System.out.println("top 3 together should be: " + totalSum);
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
