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

public class Puzzle7 {

    public void  Puzzle72021(){

        List<String> inputFileByline = FileReader("AdOfCo2021/puzzle7.txt");
        //create numbers
        String numbersString = inputFileByline.get(0);
        List<Integer> numbers = createNumberList(numbersString);
        int highestNumber = 0;
        int lowestNumber = Integer.MAX_VALUE;

        for(int number: numbers){
            if(number > highestNumber){
                highestNumber = number;
            }
            if(number < lowestNumber){
                lowestNumber = number;
            }
        }
        System.out.println(lowestNumber );
        System.out.println(highestNumber);

        int lowestFuelConsumption = Integer.MAX_VALUE;
        int position = 0;

        for(int i = lowestNumber; i < highestNumber; i++){
            int fuelConsumption = 0;

            for(int number: numbers){
               fuelConsumption = fuelConsumption + getFuelConsumptionPerPositionPart2(number,i);
                System.out.println("Fuelconsumption for position " + i + "and number " + number + " is " + getFuelConsumptionPerPositionPart2(number,i));
            }
            System.out.println("total Fuelconsumption for position " + i + " is " + fuelConsumption);
            if(fuelConsumption < lowestFuelConsumption){
                lowestFuelConsumption = fuelConsumption;
                position = i;

            }

        }

        System.out.println("lowest Fuelconsumption is for position " + position + " and consumption is " + lowestFuelConsumption);



    }

    public int getFuelConsumptionPerPosition(int number, int position){
        if(number > position){
            return number - position;
        } else if( number < position){
            return position - number;
        } else{
            return 0;
        }

    }

    public int getFuelConsumptionPerPositionPart2(int number, int position){
        int totalSteps = 0;
        int numberToReturn = 0;
        if(number > position){
            totalSteps = number - position;
        } else if( number < position){
            totalSteps = position - number;
        } else {
            return numberToReturn;
        }


        for(int i = 1; i <= totalSteps; i++){
           numberToReturn =  numberToReturn + (i);
        }
        return numberToReturn;
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
