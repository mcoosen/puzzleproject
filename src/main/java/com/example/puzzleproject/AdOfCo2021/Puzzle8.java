package com.example.puzzleproject.AdOfCo2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Puzzle8 {

    public void  Puzzle82021(){

        List<String> inputFileByline = FileReader("AdOfCo2021/puzzle8.txt");
        List<List<String>> inputListOfFinalFour= new ArrayList<>();
        List<List<String>> inputListOfFirstTen = new ArrayList<>();


        for(int i= 0; i < inputFileByline.size(); i++) {
        inputListOfFinalFour.add(createInputStringOfFinalFour(inputFileByline.get(i)));

        }
//        System.out.println("one list created of inputs: " + inputListOfFinalFour.get(5).toString());

        for(int i= 0; i < inputFileByline.size(); i++) {
            inputListOfFirstTen.add(createInputStringOfFirstTen(inputFileByline.get(i)));

        }

        int totalCount = 0;
        for(int y = 0; y < inputListOfFirstTen.size(); y++){
            DigitNumber digitNumber = new DigitNumber();
            digitNumber.assignValuesUsingInputlist(inputListOfFirstTen.get(y));
            digitNumber.determineAbove();
            digitNumber.determineSixAndRight();
            digitNumber.determineNineAndZero();
            int fourDigits = 0;
            for(int x = 0; x < inputListOfFinalFour.get(y).size(); x++){
                int number = digitNumber.returnNumber(inputListOfFinalFour.get(y).get(x));
                fourDigits = fourDigits*10 + number;
            }


            System.out.println("This is the four digits: "  + fourDigits);
            totalCount = totalCount + fourDigits;
        }

        System.out.println("this is total count: " + totalCount);




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

    private int countString(String input){
        return input.length();
    }

    private List<String> createInputStringOfFinalFour(String InputString){

        String listInputString[] = InputString.split(" ");
        List<String> listToReturn = new ArrayList<>();

        for(int i = 11; i < 15; i++){

            listToReturn.add(listInputString[i]);
        }

        return listToReturn;
    }

    private List<String> createInputStringOfFirstTen(String InputString){

        String listInputString[] = InputString.split(" ");
        List<String> listToReturn = new ArrayList<>();

        for(int i = 0; i < 10; i++){

            listToReturn.add(listInputString[i]);
        }

        return listToReturn;
    }


}
