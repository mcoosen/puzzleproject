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
import java.util.Arrays;
import java.util.List;

public class Puzzle1022 {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);
    List<Integer> cycleInputs = Arrays.asList(20,60,100,140,180,220);
    int cycle = 0;
    long register = 1;

    List<List<String>> lineNumbers = new ArrayList<>();
    List<String> line0 = new ArrayList<>();
    List<String> line1 = new ArrayList<>();
    List<String> line2 = new ArrayList<>();
    List<String> line3 = new ArrayList<>();
    List<String> line4 = new ArrayList<>();
    List<String> line5 = new ArrayList<>();


    public void puzzle1022() {

        List<String> inputFileByline = FileReader("AdOfCo2022/puzzle10.txt");
        long sumOfSignalStrength = 0;

        for(int i= 0; i < inputFileByline.size(); i++) {
            String input = inputFileByline.get(i);

            int numberToUpdate;

            if(input.contains("noop")){
                cycle++;
            } else {
                cycle++;
            }
            sumOfSignalStrength += checkForSignalStrength(cycle, register);

            if(input.contains("addx")){
                numberToUpdate = Integer.parseInt(input.split(" ")[1]);
                cycle++;
                sumOfSignalStrength += checkForSignalStrength(cycle, register);
                register += numberToUpdate;
            }
        }
        log.info("total sum is: " + sumOfSignalStrength);
    }

    private long checkForSignalStrength(int cycle, long register) {
        if(cycleInputs.contains(cycle)){
            long signalStrength = register * cycle;
            log.info("current cycle: " + cycle);
            log.info("current register: " + register);
            log.info("signal strength: " + signalStrength);
            return signalStrength;
        }
        return 0;
    }

    private void drawPixel() {
        int position = cycle -1;
        int lineNumber = 0;
        if(cycle >= 41 && cycle <= 80) {
            position = cycle -41;
            lineNumber = 1;
        } else if (cycle >= 81 && cycle <= 120) {
            position = cycle -81;
            lineNumber = 2;
        } else if (cycle >= 121 && cycle <= 160) {
            position = cycle -121;
            lineNumber = 3;
        } else if (cycle >= 161 && cycle <= 200) {
            position = cycle -161;
            lineNumber = 4;
        } else if (cycle >= 201) {
            position = cycle - 201;
            lineNumber = 5;
        }
        if(position >= register-1 && position <= register +1){
            lineNumbers.get(lineNumber).add("#");
        } else {
            lineNumbers.get(lineNumber).add(".");
        }

    }


    public void puzzle1022B() {
        List<String> inputFileByline = FileReader("AdOfCo2022/puzzle10.txt");
        lineNumbers.add(line0);
        lineNumbers.add(line1);
        lineNumbers.add(line2);
        lineNumbers.add(line3);
        lineNumbers.add(line4);
        lineNumbers.add(line5);

        for(int i= 0; i < inputFileByline.size(); i++) {
            String input = inputFileByline.get(i);

            int numberToUpdate;

            if(input.contains("noop")){
                cycle++;
            } else {
                cycle++;
            }

            drawPixel();

            if(input.contains("addx")){
                numberToUpdate = Integer.parseInt(input.split(" ")[1]);
                cycle++;
                drawPixel();
                register += numberToUpdate;
            }
        }
        log.info(" line 1 " + lineNumbers.get(0).toString());
        log.info(" line 2 " + lineNumbers.get(1).toString());
        log.info(" line 3 " + lineNumbers.get(2).toString());
        log.info(" line 4 " + lineNumbers.get(3).toString());
        log.info(" line 5 " + lineNumbers.get(4).toString());
        log.info(" line 6 " + lineNumbers.get(5).toString());

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
