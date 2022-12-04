package com.example.puzzleproject.AdOfCo2021;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BingoBoard {


    List<List<Integer>> rows;
    List<List<Integer>> columns;
    List<Integer> row;
    List<Integer> column;

    boolean bingo;
    int numberCalled;
    int numberOfRoundsUsed;
    int numberCounter;

    public BingoBoard() {
        this.rows = new ArrayList<>();
        this.columns = new ArrayList<>();
        this.row = new ArrayList<>();
        this.column = new ArrayList<>();
        this.numberCalled = 0;
        this.numberOfRoundsUsed = 0;
        this.bingo = false;
        this.numberCounter = 0;
    }



    public int numberOfroundsForWin(List<Integer> numbers) {

        for(int n = 0; n < numbers.size(); n++){
            if(bingo){
                break;
            }
            numberCalled = numbers.get(n);

            for(int r = 0; r < rows.size(); r++){
            if(bingo){
               break;
            }

                for(int c = 0; c < rows.get(r).size(); c++){
                    if(rows.get(r).get(c) == numbers.get(n)){
                        rows.get(r).set(c, -1);
                        numberCounter++;
                    }
                    if(columns.get(r).get(c) == numbers.get(n)){
                        columns.get(r).set(c, -1);
                    }
                    if(sumOfRow(rows.get(r)) == -5 || sumOfRow(columns.get(r)) == -5){
                        System.out.println("Bingo");
                        bingo = true;
                        numberOfRoundsUsed = n+1;
                        break;
                    }

                }

            }

        }


        return numberOfRoundsUsed;
    }

    public int sumOfRow(List<Integer> row){

        int sumOfRow = 0;
        for(int m = 0; m < row.size(); m++){
            sumOfRow = sumOfRow + row.get(m);
        }

        return sumOfRow;
    }

    public int sumOfBoardNumbersLeft() {

        int rowNumbersLeft = 0;

        for(int l= 0; l < rows.size();l++){

            rowNumbersLeft = rowNumbersLeft + sumOfRow( rows.get(l));

        }


        return rowNumbersLeft + numberCounter;
    }
}
