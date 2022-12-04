package com.example.puzzleproject.AdOfCo2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Puzzle16 {


    public void puzzle16() {

        List<String> inputFileByline = FileReader("AdOfCo2021/puzzle16.txt");
        String hexaLine = inputFileByline.get(0);
        String binaryLine = convertHexToDecimal(hexaLine);
        System.out.println(binaryLine);

        //what to do with the binary line. First take the first 3 numbers (0,1,2) convert it to a version, that you need to remember
        // then next 3 numbers 3,4,5, (T) that needs to be converted to a type, if 4 then we are at the end of the line, pars the rest with the rules,
        // leading 1 and then 4 left, remember that
        // if T is anything other then a 4, then we need the next number, which tells us which type it 0 or 1, it should be able to do it recursively, so a while loop i guess
        // only the last



    }

    private String convertHexToDecimal(String hexaLine) {
        String binaryLine = "";
        for(int i = 0; i < hexaLine.length(); i++){

            String c = Character.toString(hexaLine.charAt(i));
            switch (c) {
                case("0"):
                    binaryLine = binaryLine + "0000";
                    break;
                case("1"):
                    binaryLine = binaryLine + "0001";
                    break;
                case("2"):
                    binaryLine = binaryLine + "0010";
                    break;
                case("3"):
                    binaryLine = binaryLine + "0011";
                    break;
                case("4"):
                    binaryLine = binaryLine + "0100";
                    break;
                case("5"):
                    binaryLine = binaryLine + "0101";
                    break;
                case("6"):
                    binaryLine = binaryLine + "0110";
                    break;
                case("7"):
                    binaryLine = binaryLine + "0111";
                    break;
                case("8"):
                    binaryLine = binaryLine + "1000";
                    break;
                case("9"):
                    binaryLine = binaryLine + "1001";
                    break;
                case("A"):
                    binaryLine = binaryLine + "1010";
                    break;
                case("B"):
                    binaryLine = binaryLine + "1011";
                    break;
                case("C"):
                    binaryLine = binaryLine + "1100";
                    break;
                case("D"):
                    binaryLine = binaryLine + "1101";
                    break;
                case("E"):
                    binaryLine = binaryLine + "1110";
                    break;
                case("F"):
                    binaryLine = binaryLine + "1111";
                    break;
            }

        }
        return binaryLine;
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
