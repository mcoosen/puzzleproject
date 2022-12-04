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


public class Puzzle322 {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);



    public void puzzle322() {

        List<String> inputFileByline =  FileReader("AdOfCo2022/puzzle3.txt");

        int totalScore = 0;
        String itemType = "";
        for(int i= 0; i < inputFileByline.size(); i++) {
            String input = inputFileByline.get(i);
//            List<Integer> numbers = new ArrayList<>();
//            numbers.add(number);
            final int mid = input.length() / 2;
            String firstCompartment = input.substring(0, mid );
            String secondCompartment = input.substring( mid );


            List<String> listOfLettersFirst = new ArrayList<>();
            List<String> listOfLettersSecond = new ArrayList<>();
            for(int x = 0; x< mid; x++){
                listOfLettersFirst.add(firstCompartment.substring(x, x+1));
                listOfLettersSecond.add(secondCompartment.substring(x, x+1));
            }
            boolean outOfLoop = false;
            for(int y = 0; y < listOfLettersFirst.size(); y++){
                if(outOfLoop){
                    break;
                }
                for(int z = 0; z < listOfLettersFirst.size(); z++){
                    if(listOfLettersFirst.get(y).equals(listOfLettersSecond.get(z))){
                        itemType = listOfLettersFirst.get(y);
                        log.info("we have a winner: " + itemType );
                        int priority = getPriority(itemType);
                        log.info("and the priority is: " + priority);
                        totalScore += priority;
                        log.info("totalscore = " + totalScore);
                        outOfLoop = true;
                        break;
                    };
                }

            }

        }

    }

    public void puzzle322B() {

        List<String> inputFileByline =  FileReader("AdOfCo2022/puzzle3.txt");

        int totalScore = 0;
        String itemType = "";
        for(int i= 0; i < inputFileByline.size(); i+=3) {

            String firstRuckSack = inputFileByline.get(i);
            String secondRuckSack = inputFileByline.get(i+1);
            String thirdRuckSack = inputFileByline.get(i+2);


            List<String> listOfLettersFirst = new ArrayList<>();
            List<String> listOfLettersSecond = new ArrayList<>();
            List<String> listOfLettersThird = new ArrayList<>();
            for(int x = 0; x< firstRuckSack.length(); x++){
                listOfLettersFirst.add(firstRuckSack.substring(x, x+1));
            }
            for(int x1 = 0; x1< secondRuckSack.length(); x1++){
                listOfLettersSecond.add(secondRuckSack.substring(x1, x1+1));
            }
            for(int x2 = 0; x2< thirdRuckSack.length(); x2++){
                listOfLettersThird.add(thirdRuckSack.substring(x2, x2+1));
            }

            boolean outOfLoop = false;
            for(int y = 0; y < listOfLettersFirst.size(); y++){
                if(outOfLoop){
                    break;
                }
                for(int z = 0; z < listOfLettersSecond.size(); z++){
                    if(outOfLoop){
                        break;
                    }
                    if (listOfLettersFirst.get(y).equals(listOfLettersSecond.get(z))){

                        for(int z1 = 0; z1 < listOfLettersThird.size(); z1++) {
                            if (listOfLettersFirst.get(y).equals(listOfLettersThird.get(z1))) {

                                itemType = listOfLettersFirst.get(y);
                                log.info("we have a winner: " + itemType);
                                int priority = getPriority(itemType);
                                log.info("and the priority is: " + priority);
                                totalScore += priority;
                                log.info("totalscore = " + totalScore);
                                outOfLoop = true;
                                break;
                            }

                        }
                    }

                }

            }

        }

    }

    private int getPriority(String itemType) {


        char ch = itemType.charAt(0);
        if(Character.isUpperCase(ch)){
            int pos = ch - 'A' + 1 + 26;
            return pos;
        } else {
            int pos = ch - 'a' + 1;
            return pos;
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
