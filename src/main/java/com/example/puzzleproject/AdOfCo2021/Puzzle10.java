package com.example.puzzleproject.AdOfCo2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Puzzle10 {



    public void puzzle102021() {

        List<String> inputFileByline = FileReader("AdOfCo2021/puzzle10.txt");

        List<List<String>> linesInArray = new ArrayList<>();


        for(int v= 0; v < inputFileByline.size(); v++) {
            String line = inputFileByline.get(v);
            List<String> lineArray = new ArrayList<>();

            for(int h = 0; h < line.length(); h++){
                lineArray.add(String.valueOf(line.charAt(h)));;
            }
            linesInArray.add(lineArray);
        }
//        System.out.println("this the first line " + linesInArray.get(0));

        linesInArray = createIncompleLinesList(linesInArray);

        for(List<String> line: linesInArray){
            //get symbol that is incomplete, save what is needed and
        }


    }

    private List<List<String>> createIncompleLinesList(List<List<String>> linesInArray) {

        //        int counter = 0;


        List<Integer> linesToBeRemoved = new ArrayList<>();
        List<Long> scores = new ArrayList<>();
        for(int x = 0; x < linesInArray.size();x++){
            List<Integer> numbers = new ArrayList<>();

            for(int i = 0; i < linesInArray.get(x).size(); i++){

                if(linesInArray.get(x).get(i).equals("(")){
                    numbers.add(1);
                } else if(linesInArray.get(x).get(i).equals("[")){
                    numbers.add(2);
                } else if(linesInArray.get(x).get(i).equals("{")){
                    numbers.add(3);
                } else if(linesInArray.get(x).get(i).equals("<")){
                    numbers.add(4);
                } else if(linesInArray.get(x).get(i).equals(")")){
                    if(numbers.get(numbers.size()-1) == 1){
                        numbers.remove(numbers.size()-1);
                    } else {
//                        System.out.println("this line is corrupt, adding 3 points: " + line);
//                        counter = counter + 3;
                        System.out.println("line: "+ x + " will be removed " + linesInArray.get(x).toString());

                        linesToBeRemoved.add(x);

                        break;
                    }
                } else if(linesInArray.get(x).get(i).equals("]")){
                    if(numbers.get(numbers.size()-1) == 2){
                        numbers.remove(numbers.size()-1);
                    }else {
//                        System.out.println("this line is corrupt, adding 57 points: " + line);
//                        counter = counter + 57;
                        System.out.println("line: "+ x + " will be removed " + linesInArray.get(x).toString());

                        linesToBeRemoved.add(x);
                        break;
                    }
                } else if(linesInArray.get(x).get(i).equals("}")){
                    if(numbers.get(numbers.size()-1) == 3){
                        numbers.remove(numbers.size()-1);
                    }else {
//                        System.out.println("this line is corrupt, adding 1197 points: " + line);
//                        counter = counter + 1197;
                        System.out.println("line: "+ x + " will be removed " + linesInArray.get(x).toString());

                        linesToBeRemoved.add(x);
                        break;
                    }
                } else if(linesInArray.get(x).get(i).equals(">")){
                    if(numbers.get(numbers.size()-1) == 4){
                        numbers.remove(numbers.size()-1);
                    }else {
//                        System.out.println("this line is corrupt, adding 25137 points: " + line);
//                        counter = counter + 25137;
                        System.out.println("line: "+ x + " will be removed " + linesInArray.get(x).toString());

                        linesToBeRemoved.add(x);
                        break;
                    }
                }

                if(i == linesInArray.get(x).size()-1 ){
                    System.out.println("for line: " + x + " these numbers are left " + numbers);
                    // dit is eigenlijk al wat ik nodig heb, maar dan moet ik het nog omdraaien
                    // en de rekenregels er overheen gooien
                    Collections.reverse(numbers);

                    long score = 0;
                    for(int number:numbers){
                        score = score*5;
                        score = score + number;

                    }
                    System.out.println("for line: " + x + "total score is: " + score);
                    scores.add(score);
                }
            }




        }

        scores.sort(Long::compareTo);
        System.out.println("these are the sorted scores: " + scores);
        int middle = (scores.size()-1)/2;
        System.out.println("this should be the middle score: " + scores.get(middle));

        for(Integer line: linesToBeRemoved){
            linesInArray.remove(line);
        }
//        System.out.println("total is: " + counter);
        System.out.println("lines left: " + linesInArray.toString());
        return linesInArray;
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
