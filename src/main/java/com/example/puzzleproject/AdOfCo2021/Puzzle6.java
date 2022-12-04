package com.example.puzzleproject.AdOfCo2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle6 {

    public void  Puzzle62021(){

        List<String> inputFileByline = FileReader("AdOfCo2021/puzzle6.txt");
        //create numbers
        String numbersString = inputFileByline.get(0);
        List<Integer> numbers = createNumberList(numbersString);
        NumberOfFish numberOfFish = new NumberOfFish(0,0,0,0,0,0,0,0,0);

        for(int number: numbers){
            if(number == 0){
                numberOfFish.zeroDayFish++;
            } else if (number == 1){
                numberOfFish.oneDayFish++;
            } else if (number == 2){
                numberOfFish.twoDayFish++;
            } else if (number == 3){
                numberOfFish.threeDayFish++;
            } else if (number == 4){
                numberOfFish.fourDayFish++;
            } else if (number == 5){
                numberOfFish.fiveDayFish++;
            } else if (number == 6){
                numberOfFish.sixDayFish++;
            } else if (number == 7){
                numberOfFish.sevenDayFish++;
            } else if (number == 8){
                numberOfFish.eightDayFish++;
            }
        }

        System.out.println("number of fish on day 0: " + numberOfFish);

        int numberOfDays = 256;
        for(int i = 0; i < numberOfDays; i++){
            numberOfFish.addOneDay();
            System.out.println("numbers of each fish on day " + (i+1) + ": " + numberOfFish);
            System.out.println("total number of fish after day " + (i+1) + ": " + numberOfFish.totalNumberOfFish());
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

    private List<Integer> createNumberList(String numbersString){

        int[] numbers = Arrays.stream(numbersString.split(","))
                .map(String::trim).mapToInt(Integer::parseInt).toArray();
//        System.out.println(Arrays.toString(numbers));
        List<Integer> numberList = new ArrayList<>();

        for(int n = 0; n < numbers.length; n++){
            numberList.add(n,numbers[n]);
        }
        return numberList;
    }
}
