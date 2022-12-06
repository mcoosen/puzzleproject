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


public class Puzzle622 {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);



    public void puzzle622() {

        List<String> inputFileByline =  FileReader("AdOfCo2022/puzzle6.txt");
        String input = inputFileByline.get(0);

        // create a for loop where we take each character and compares it with all the other ones, if match then break, if no match continue
        for(int i = 0; i < input.length(); i++){
            String one = String.valueOf(input.charAt(i));
            String two = String.valueOf(input.charAt(i+1));
            String three = String.valueOf(input.charAt(i+2));
            String four = String.valueOf(input.charAt(i+3));
            if( !(one.equals(two) || one.equals(three) || one.equals(four) || two.equals(three) || two.equals(four) || three.equals(four))){
                log.info("we have winner: " + (i+4));
                log.info("characters found: " + one + two + three + four);
                break;
            } else {
                log.info("found same characters in round: " + (i+ 4));
                log.info("characters found: " + one + two + three + four);
            }
        }

    }

    public void puzzle622B() {

        List<String> inputFileByline =  FileReader("AdOfCo2022/puzzle6.txt");
        String input = inputFileByline.get(0);

        // create a for loop where we take each character and compares it with all the other ones, if match then break, if no match continue
        boolean equalFound = false;
        boolean winnerFound = false;
        int i = 0;
        while(!winnerFound){
            String inputString = input.substring(i, i+14);
            int x = 0;
            while(!equalFound){
                char inputChar = inputString.charAt(x);
                for(int y = x+1; y < 14; y++){
                    char compareChar = inputString.charAt(y);
                    if(inputChar - compareChar == 0){
                        log.info("char are equal: " + inputChar + compareChar);
                        log.info("total string: " + inputString + " marker is: " + (i+14));
                        equalFound = true;
                        break;
                    }
                }
                if(!equalFound && x == 13){
                    log.info("we have winner: " + (i+14));
                    log.info("input found: " + inputString);
                    winnerFound = true;
                    break;
                }
            x++;
            }
            equalFound = false;
            i++;
        }
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
