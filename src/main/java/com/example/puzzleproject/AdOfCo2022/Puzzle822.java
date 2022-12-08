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
import java.util.Collections;
import java.util.List;

public class Puzzle822 {

    List<List<Integer>> rows = new ArrayList<>();
    List<List<Integer>> columns = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);
    List<Integer> rowToLeft = new ArrayList<>();
    List<Integer> rowToRight = new ArrayList<>();
    List<Integer> columnToTop = new ArrayList<>();
    List<Integer> columnToBottom = new ArrayList<>();


    public void puzzle822() {

        createRowsAndColums();

        int treeCounter = rows.size() * 4 -4;
        for(int x= 1; x < rows.size()-1; x++) {
            for(int y = 1; y < columns.size()-1; y++){

                int numberToCheck = rows.get(x).get(y);

                rowToLeft = rows.get(x).subList(0,y);
                rowToRight = rows.get(x).subList(y+1,rows.get(x).size());
                columnToTop = columns.get(y).subList(0,x);
                columnToBottom = columns.get(y).subList(x+1,rows.get(y).size());

                if(Collections.max(rowToLeft) < numberToCheck ||
                        Collections.max(rowToRight) < numberToCheck ||
                        Collections.max(columnToTop) < numberToCheck ||
                        Collections.max(columnToBottom) < numberToCheck)  {

                    log.info("tree visible: " + numberToCheck);
                    treeCounter++;
                } else {
                    log.info("tree not visible with number: " + numberToCheck);
                }

            }
        }

        System.out.println("total sum of points is: " + treeCounter);
    }

    public void puzzle822B() {

        createRowsAndColums();

        int highestScenicScore = 0;
        for(int x= 1; x < rows.size()-1; x++) {
            for(int y = 1; y < columns.size()-1; y++){

                int numberToCheck = rows.get(x).get(y);

                rowToLeft = rows.get(x).subList(0,y);
                rowToRight = rows.get(x).subList(y+1,rows.get(x).size());
                columnToTop = columns.get(y).subList(0,x);
                columnToBottom = columns.get(y).subList(x+1,rows.get(y).size());

                // get views
                int scenicScoreLeft = getScenicScoreLeftTop(rowToLeft, numberToCheck);
                int scenicScoreRight = getScenicScoreRightBottom(rowToRight, numberToCheck);
                int scenicScoreTop = getScenicScoreLeftTop(columnToTop, numberToCheck);
                int scenicScoreBottom = getScenicScoreRightBottom(columnToBottom, numberToCheck);

                int scenicScore = scenicScoreLeft * scenicScoreBottom * scenicScoreRight * scenicScoreTop;
                log.info("Scenic Score: " + scenicScore + " for tree with number: " + numberToCheck);
                if(scenicScore > highestScenicScore){
                    highestScenicScore = scenicScore;
                }

            }
        }

        System.out.println("highest Scenic score is: " + highestScenicScore);
    }

    private int getScenicScoreLeftTop(List<Integer> listToCheck, int numberToCheck) {
        int counter = 0;
        for(int i = listToCheck.size()-1; i >= 0; i--){

                counter++;
                if(listToCheck.get(i) >= numberToCheck){
                    return counter;
                }

        }
        return counter;
    }

    private int getScenicScoreRightBottom(List<Integer> listToCheck, int numberToCheck) {
        int counter = 0;
        for (Integer integer : listToCheck) {

            counter++;
            if (integer >= numberToCheck) {
                return counter;
            }
        }
        return counter;
    }

    private void createRowsAndColums() {
        List<String> inputFileByline = FileReader("AdOfCo2022/puzzle8.txt");


        for(int v= 0; v < inputFileByline.size(); v++) {
            String line = inputFileByline.get(v);
            List<Integer> row = new ArrayList<>();

            for(int h = 0; h < line.length(); h++){
                int inputInt = Integer.parseInt(String.valueOf(line.charAt(h)));
                row.add(inputInt);
                if(v == 0){
                    List<Integer> column = new ArrayList<>();
                    columns.add(column);
                }
                columns.get(h).add(inputInt);
            }
            rows.add(row);
        }
        System.out.println("List created");
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
