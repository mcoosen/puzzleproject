package com.example.puzzleproject.AdOfCo2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Puzzle14B {


    List<Character> characters = new ArrayList<>();

    Map<String,Long> pairs  = new HashMap<>();
    List<PolymerRule> polymerRules  = new ArrayList<>();
    Map<String,Long> tempPairs  = new HashMap<>();

    private LinkedList<String> instructions = new LinkedList<>();

    public void puzzle14B() {

        List<String> inputFileByline = FileReader("AdOfCo2021/puzzle14.txt");
        String polymerTemplate = inputFileByline.get(0);


        // would the solution be create a count of each pair

//        for(int y = 0; y < 63300; y++ ){
////            instructions.add(Integer.toString(y));
////        }



        for(int i= 2; i < inputFileByline.size(); i++) {
           String line = inputFileByline.get(i);
            String[] ruleParts = line.split(" -> ");
            PolymerRule polymerRule = new PolymerRule(ruleParts[0],ruleParts[1]);
            polymerRules.add(polymerRule);
            pairs.put(ruleParts[0],0L);
            if(!characters.contains((ruleParts[1].charAt(0)))){
                characters.add(ruleParts[1].charAt(0));
            }

        }


        tempPairs.putAll(pairs);
        for(int i = 0; i < polymerTemplate.length()-1; i++){

            // need to make exception for last pair or you get out of bounds
            String workingPair;
            if(i+2 ==  polymerTemplate.length()){
                workingPair = polymerTemplate.substring(i);
                for(Map.Entry<String,Long> entry: tempPairs.entrySet()){
                    if(entry.getKey().equals(workingPair)){
                        Long count = entry.getValue() + 1;
                        entry.setValue(count);
                    }
                }
            } else {
                workingPair = polymerTemplate.substring(i, i+2);

                for(Map.Entry<String,Long> entry: tempPairs.entrySet()){
                    if(entry.getKey().equals(workingPair)){
                        Long count = entry.getValue() + 1;
                        entry.setValue(count);
                    }
                }
            }
        }


        int steps = 0;
        do{
            tempPairs = createNewPairs(tempPairs);
            countLettersForStep(tempPairs);
            steps++;
        } while (steps < 40);



    }

    private Map<String, Long> createNewPairs(Map<String, Long> inputPairs) {
        Map<String,Long> newPairs  = new HashMap<>();
        newPairs.putAll(pairs);
        for(Map.Entry<String,Long> entry: inputPairs.entrySet()){
            String workingPair = entry.getKey();
            for(PolymerRule polymerRule: polymerRules){
                if(polymerRule.getPair().equals(workingPair)){
                    String newPair1 = workingPair.substring(0,1) + polymerRule.getInsertion();
                    String newPair2 = polymerRule.getInsertion() + workingPair.substring(1);
                    // dit moet anders want wat als het pair er al in zit, en wat als
                    newPairs.put(newPair1,(newPairs.get(newPair1)+inputPairs.get(workingPair)));
                    newPairs.put(newPair2,(newPairs.get(newPair2)+inputPairs.get(workingPair)));
                }
            }
        }

        return newPairs;



    }

    private void countLettersForStep( Map<String,Long> inputPairs) {

        List<Long> counts = new ArrayList<>();

        for(Character character: characters){
            long count = 0;
            if(character.equals('B')){
                count = 1;
            }
            for(Map.Entry<String,Long> entry: inputPairs.entrySet()){
                Character lastCharacter = entry.getKey().charAt(1);
                if(lastCharacter.equals(character)){
                    count = count + entry.getValue();
                }
            }

            System.out.println("letter " + character + " found in step: " + count);
            counts.add(count);
        }


        counts.sort(Long::compareTo);
       // System.out.println("Highest count should be: " + counts.get(counts.size()-1));
        //System.out.println("Lowest count should be: " + counts.get(0));
        System.out.println("Answer should be " + (counts.get(counts.size()-1)-counts.get(0)));


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
