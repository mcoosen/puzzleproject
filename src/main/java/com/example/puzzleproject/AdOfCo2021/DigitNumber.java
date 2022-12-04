package com.example.puzzleproject.AdOfCo2021;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DigitNumber {

    String above;
    String leftAbove;
    String leftBelow;
    String below;
    String middle;
    String rightAbove;
    String rightBelow;

    List<String> possibleLetters = new ArrayList<>();
    List<String> tempMiddleDown = new ArrayList<>();
    List<String> tempMiddleUp = new ArrayList<>();
    List<String> tempRight = new ArrayList<>();
    List<String> tempSixes = new ArrayList<>();
    List<String> tempFives = new ArrayList<>();


    String uniqueInput1;

    String uniqueInput4;

    String uniqueInput6;
    String uniqueInput7;

//    public DigitNumber(List<String> tempRight, List<String> tempSixes, List<String> tempFives) {
//        this.tempRight = tempRight;
//        this.tempSixes = tempSixes;
//        this.tempFives = tempFives;
//    }

    public void assignValuesUsingInputlist(List<String> inputList){

        for(int y= 0; y < inputList.size(); y++) {
            int count = inputList.get(y).length();
            if(count == 2 ){
                uniqueInput1 = inputList.get(y);
            } else if(count == 3){
                uniqueInput7 = inputList.get(y);
            } else if(count == 4 ){
                uniqueInput4 = inputList.get(y);
            } else if(count == 5){
                tempFives.add(inputList.get(y));
            } else if(count == 6){
                tempSixes.add(inputList.get(y));
            }
        }
    }

    public void determineAbove(){
        possibleLetters.add("a");
        possibleLetters.add("b");
        possibleLetters.add("c");
        possibleLetters.add("d");
        possibleLetters.add("e");
        possibleLetters.add("f");
        possibleLetters.add("g");
        String[] one = uniqueInput1.split("");
        String[] seven = uniqueInput7.split("");

        for(int i = 0; i < seven.length; i++){
            if(! (seven[i].equals(one[0]) || seven[i].equals(one[1]))){
                above = seven[i];
                possibleLetters.remove(above);
            } else {
                tempRight.add(seven[i]);
            }
        }
    }

    public void determineSixAndRight(){

        for(int i = 0; i < tempSixes.size(); i++){

                String missingLetter = "";
                if(!tempSixes.get(i).contains("a")){
                    missingLetter = "a";
                } else if(!tempSixes.get(i).contains("b")){
                    missingLetter = "b";
                } else if(!tempSixes.get(i).contains("c")){
                    missingLetter = "c";
                } else if(!tempSixes.get(i).contains("d")){
                    missingLetter = "d";
                } else if(!tempSixes.get(i).contains("e")){
                    missingLetter = "e";
                } else if(!tempSixes.get(i).contains("f")){
                    missingLetter = "f";
                } else if(!tempSixes.get(i).contains("g")){
                    missingLetter = "g";
                }

                if( (missingLetter.equals(tempRight.get(0)) || missingLetter.equals(tempRight.get(1))) ){
                        uniqueInput6 =  tempSixes.get(i);

                    if(missingLetter.equals(tempRight.get(0))){
                        rightAbove = tempRight.get(0);
                        rightBelow = tempRight.get(1);
                    } else{
                        rightBelow = tempRight.get(0);
                        rightAbove = tempRight.get(1);
                    }
                    possibleLetters.remove(rightBelow);
                    possibleLetters.remove(rightAbove);

                } else {
                    tempMiddleDown.add(missingLetter);
                }

        }
    }

    public void determineNineAndZero(){
        String[] four = uniqueInput4.split("");
        for(int i = 0; i < four.length; i++){
            if(! (four[i].equals(rightAbove) || four[i].equals(rightBelow))){
                tempMiddleUp.add(four[i]);
            }
        }

        if(tempMiddleUp.get(0).equals(tempMiddleDown.get(0)) || tempMiddleUp.get(0).equals(tempMiddleDown.get(1)) ){
            middle = tempMiddleUp.get(0);
            leftAbove = tempMiddleUp.get(1);
        } else {
            middle = tempMiddleUp.get(1);
            leftAbove = tempMiddleUp.get(0);
        }
        possibleLetters.remove(middle);
        possibleLetters.remove(leftAbove);

        if(tempMiddleDown.get(0).equals(middle) ){
            leftBelow = tempMiddleDown.get(1);
        } else {
            leftBelow = tempMiddleDown.get(0);
        }
        possibleLetters.remove(leftBelow);
        below = possibleLetters.get(0);
    }


    public int returnNumber(String input){

        int count = input.length();

        if(count == 2 ){
            return 1;
        } else if(count == 3){
           return 7;
        } else if(count == 4 ){
            return 4;
        } else if(count == 5){
            if(!input.contains(rightAbove)){
                return 5;
            } else if(!input.contains(rightBelow)){
                return 2;
            } else{
                return 3;
            }

        } else if(count == 6){
            if(!input.contains(middle)){
                return 0;
            } else if(!input.contains(leftBelow)){
                return 9;
            } else{
                return 6;
            }
        } else if(count == 7){
            return 8;
        } else{
            System.out.println("ERROR");
            return -1;
        }
    }

}
