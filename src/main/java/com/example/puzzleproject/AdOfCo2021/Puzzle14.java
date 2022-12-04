package com.example.puzzleproject.AdOfCo2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Puzzle14 {


    List<Character> characters = new ArrayList<>();

    private List<String> instructions = new ArrayList<>();

    public void puzzle14() {

        List<String> inputFileByline = FileReader("AdOfCo2021/puzzle14.txt");
        String polymerTemplate = inputFileByline.get(0);
        List<PolymerRule> polymerRules  = new ArrayList<>();

        for(int i= 2; i < inputFileByline.size(); i++) {
           String line = inputFileByline.get(i);
            String[] ruleParts = line.split(" -> ");
            PolymerRule polymerRule = new PolymerRule(ruleParts[0],ruleParts[1]);
            polymerRules.add(polymerRule);
            if(!characters.contains((ruleParts[1].charAt(0)))){
                characters.add(ruleParts[1].charAt(0));
            }

        }

        int steps = 0;
        do{
            String newTemplate = "A";
            for(int i = 0; i < polymerTemplate.length()-1; i++){

                // need to make exception for last pair or you get out of bounds
                String workingPair;
                if(i+2 ==  polymerTemplate.length()){
                    workingPair = polymerTemplate.substring(i);
                } else {
                    workingPair = polymerTemplate.substring(i, i+2);
                }
                for(PolymerRule polymerRule: polymerRules){
                    if(polymerRule.getPair().equals(workingPair)){
                        newTemplate = newTemplate.substring(0, newTemplate.length() - 1);
                        newTemplate = newTemplate + workingPair.substring(0,1) + polymerRule.getInsertion() + workingPair.substring(1);
                    }
                }
            }
            polymerTemplate = newTemplate;
           // System.out.println("template after step " + steps + " is: " + polymerTemplate);
            //countLettersForStep(polymerTemplate);
            steps++;
        } while (steps < 40);

        // after 10 steps it becomes unmanageble, so need to split it up 4 times.


    }

    private void countLettersForStep(String polymerTemplate) {

        List<Long> counts = new ArrayList<>();
        for(Character character: characters){
            long count = polymerTemplate.chars().filter(ch -> ch == character).count();
            //System.out.println("letter " + character + " found in step: " + count);
            counts.add(count);
        }

        counts.sort(Long::compareTo);
        System.out.println("Highest count should be: " + counts.get(counts.size()-1));
        System.out.println("Lowest count should be: " + counts.get(0));
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
