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


public class Puzzle422 {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);



    public void puzzle422() {

        List<String> inputFileByline =  FileReader("AdOfCo2022/puzzle4.txt");

        int totalPairs = 0;
        int noPairs = 0;

        for(int i= 0; i < inputFileByline.size(); i++) {
            String input = inputFileByline.get(i);
            String[] sections = input.split(",");
            String[] firstPair = sections[0].split("-");
            String[] secondPair = sections[1].split("-");

            if((Integer.parseInt(firstPair[0]) <= Integer.parseInt(secondPair[0])) && (Integer.parseInt(firstPair[1]) >= Integer.parseInt(secondPair[1]))){
                log.info("we have an overlap");
                totalPairs++;
            } else if((Integer.parseInt(firstPair[0]) >= Integer.parseInt(secondPair[0])) && (Integer.parseInt(firstPair[1]) <= Integer.parseInt(secondPair[1]))){
                log.info("we have an overlap");
                totalPairs++;
            } else {
                log.info("no overlap found");
                noPairs++;
            }

        }
        log.info("Total overlapping pairs found: " + totalPairs);
        log.info("No overlap found: " + noPairs);

    }

    public void puzzle422B() {

        List<String> inputFileByline =  FileReader("AdOfCo2022/puzzle4.txt");

        int totalPairs = 0;
        int noPairs = 0;

        for(int i= 0; i < inputFileByline.size(); i++) {
            String input = inputFileByline.get(i);
            String[] sections = input.split(",");
            String[] firstPair = sections[0].split("-");
            String[] secondPair = sections[1].split("-");
            int number11 = Integer.parseInt(firstPair[0]);
            int number12 = Integer.parseInt(firstPair[1]);
            int number21 = Integer.parseInt(secondPair[0]);
            int number22 = Integer.parseInt(secondPair[1]);

            if(number11 <= number21 && number21 <= number12){
                log.info("we have an overlap");
                totalPairs++;
            } else if (number11 > number21 && number11 <= number22) {
                log.info("we have an overlap");
                totalPairs++;
            } else {
                log.info("no overlap found");
                noPairs++;
            }

        }
        log.info("Total overlapping pairs found: " + totalPairs);
        log.info("No overlap found: " + noPairs);

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
