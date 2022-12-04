package com.example.puzzleproject.AdOfCo2021;

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


public class Puzzle4 {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);



    public void puzzle42021() {

        List<String> inputFileByline =  FileReader("AdOfCo2021/puzzle4.txt");


        //create numbers
        String numbersString = inputFileByline.get(0);
        List<Integer> numbers = createNumberList(numbersString);

        // create bingo boards
        List<BingoBoard> bingoBoards = new ArrayList<>();

        for(int i= 1; i < inputFileByline.size(); i++){

            if(inputFileByline.get(i).equals("")){
                i++;
                BingoBoard bingoBoard = new BingoBoard();
                List<Integer> column1 = new ArrayList<>();
                 List<Integer> column2 = new ArrayList<>();
                 List<Integer> column3 = new ArrayList<>();
                 List<Integer> column4 = new ArrayList<>();
                 List<Integer> column5 = new ArrayList<>();
                 for(int x = 0; x < 5; x++){

                   List<Integer> row = createNumberListForBB(inputFileByline.get(i));
                     bingoBoard.getRows().add(row);
                     column1.add(x, row.get(0));
                     column2.add(x, row.get(1));
                     column3.add(x, row.get(2));
                     column4.add(x, row.get(3));
                     column5.add(x, row.get(4));
                    i++;


                }
                bingoBoard.getColumns().add(0, column1);
                bingoBoard.getColumns().add(1, column2);
                bingoBoard.getColumns().add(2, column3);
                bingoBoard.getColumns().add(3, column4);
                bingoBoard.getColumns().add(4, column5);

                i--;
                bingoBoards.add(bingoBoard);

            }
        }

        int lowestRound = 150;
        int boardWinner;
        int lastnumber= 0;
        int sumOfBoardWinner = 0;

        for(int z= 0; z < bingoBoards.size(); z++) {

            BingoBoard bingoBoard = bingoBoards.get(z);
            int roundsForWin = bingoBoard.numberOfroundsForWin(numbers);
            int finalRound = bingoBoard.getNumberCalled();
            int sumOfBoardNumbersLeft = bingoBoard.sumOfBoardNumbersLeft();

            System.out.println("bingoboard number " + z);
            System.out.println("rounds needed: " + roundsForWin);
            System.out.println("final number for this board: " + finalRound);
            System.out.println("Sum that is left of the rest: " + sumOfBoardNumbersLeft);
            System.out.println("final score: " + finalRound*sumOfBoardNumbersLeft);

            if(roundsForWin < lowestRound){
                lowestRound = roundsForWin;
                boardWinner = z;
                lastnumber = finalRound;
                sumOfBoardWinner = sumOfBoardNumbersLeft;
            }
        }

        System.out.println("Answer should be: " + lastnumber*sumOfBoardWinner);


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

    private List<Integer> createNumberListForBB(String numbersString){

        numbersString = numbersString.replace(" ", ",").replace(",,",",");
        if (String.valueOf(numbersString.charAt(0)).equals(",")){
            numbersString = numbersString.substring(1);
        }
        return createNumberList(numbersString);
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
